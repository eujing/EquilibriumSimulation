package Collision;

import QuadTree.*;
import java.util.ArrayList;

public class Physics<T extends Particle> {
	private static final boolean DEBUG_ENERGY = false;
	private static final boolean DEBUG_COLLISION = false;
	private float width;
	private float height;
	private QuadTree qTree;
	private ArrayList <T> particles;
	
	public Physics (float width, float height) {
		this.width = width;
		this.height = height;
		
		this.qTree = new QuadTree (width, height);
		particles = new ArrayList<> ();
	}
	
	public void addParticle (T p) {
		particles.add (p);
		qTree.insert (p);
	}
	
	public void deleteAllParticles () {
		particles.clear ();
		qTree.createTree ();
	}
	
	private float CalculateSystemEnergy () {
		float ke = 0;
		for (T tmp : particles) {
			float m = tmp.getR () * tmp.getR ();
			ke += 0.5 * m * tmp.getVelocity ().getMagnitude () * tmp.getVelocity ().getMagnitude ();
		}
		return ke;
	}
	
	private void logEnergyDebug (String msg) {
		if (Physics.DEBUG_ENERGY) {
			System.out.println ("[Energy]" + msg + " = " + CalculateSystemEnergy ());
		}
	}
	
	private void updateNextPosition (T p) {
		p.setPosition (p.getNewX (), p.getNewY ());
	}
	
	private void reversePosition (T p) {
		p.setPosition (p.getX () - p.getVelocity ().dx, p.getY () - p.getVelocity ().dy);
	}
	
	private void updateBoundryCheck (T p) {
		Vector2D v = p.getVelocity ();
		float r = p.getR ();
		if ((p.getX () - r < 0 && v.dx < 0) || 
			(p.getX () + r > width && v.dx > 0)) {
			p.setVelocity (-v.dx, v.dy);
			updateNextPosition (p);
		}
		
		v = p.getVelocity ();
		if ((p.getY () - r < 0 && v.dy < 0) || 
			(p.getY () + r > height && v.dy > 0)) {
			p.setVelocity (v.dx, -v.dy);
			updateNextPosition (p);
		}
	}
	
	private void updateCollisionCheck (T p) {
		//Collision culling here
		ArrayList<T> possibleColliders = qTree.getObjectsWithinBound (p);
		//System.out.println ("Reduced collision checks to " +  ((double) possibleColliders.size () / (double) (particles.size () - 1) * 100) + "%");

		//Collision
		for (T o : possibleColliders) {
			if (p != o) {
				if (p.collidesWith (o)) {
					reversePosition (p);
					reversePosition (o);
					
					float pMass = p.getR () * p.getR ();
					float oMass = o.getR () * o.getR ();
					float po_dx = p.getX () - o.getX ();
					float po_dy = p.getY () - o.getY ();
					float sinPhi, cosPhi, distance;

					//Get trigo angle shifts needed for collision to be 1D
					distance = (float) Math.sqrt (po_dx * po_dx + po_dy * po_dy);
					sinPhi = po_dy / distance;
					cosPhi = po_dx / distance;

					//Transform velocities to rotated coordinate system
					Vector2D pVelRefInit = new Vector2D (
						(p.getVelocity ().dx * cosPhi + p.getVelocity ().dy * sinPhi),
						(p.getVelocity ().dx * -sinPhi + p.getVelocity ().dy * cosPhi));
					
					Vector2D oVelRefInit = new Vector2D (
						(o.getVelocity ().dx * cosPhi + o.getVelocity ().dy * sinPhi),
						(o.getVelocity ().dx * -sinPhi + o.getVelocity ().dy * cosPhi));

					//Calculate new velocities based on 1D collision
					Vector2D pVelRefFinal = new Vector2D (
						(pVelRefInit.dx * (pMass - oMass) + 2 * oMass * oVelRefInit.dx) / (pMass + oMass),
						pVelRefInit.dy);
					
					Vector2D oVelRefFinal = new Vector2D (
						(oVelRefInit.dx * (oMass - pMass) + 2 * pMass * pVelRefInit.dx) / (pMass + oMass),
						oVelRefInit.dy);

					//Will not exit penetration in 1 update, push apart with velocity bias which is valid for 2 updates only
					/*float scale;
					float minDistance = p.getR () + o.getR ();
					float horizontalIncrease = (pVelRefFinal.dx < 0 ? -pVelRefFinal.dx : pVelRefFinal.dx)
											   + (oVelRefFinal.dx < 0 ? -oVelRefFinal.dx : oVelRefFinal.dx);
					
					if (distance + horizontalIncrease < minDistance) {
						scale = 1 - (minDistance - distance) / horizontalIncrease;
						Vector2D pBias = new Vector2D (pVelRefFinal.dx * scale, 0);
						Vector2D oBias = new Vector2D (oVelRefFinal.dx * scale, 0);
						p.setBias (pBias);
						o.setBias (oBias);
						
						//Apply biases
						pVelRefFinal.add (pBias);
						oVelRefFinal.add (oBias);
					}					
					else {
						//Remove biases
						if (p.hasBias ()) {
							pVelRefFinal.subtract (p.getBias ());
						}
						if (o.hasBias ()) {
							oVelRefFinal.subtract (o.getBias ());
						}
					}*/

					
					//Transform velocities back to original system
					Vector2D pVelFinal = new Vector2D (
						(pVelRefFinal.dx * cosPhi - pVelRefFinal.dy * sinPhi),
						(pVelRefFinal.dx * sinPhi + pVelRefFinal.dy * cosPhi));
					
					Vector2D oVelFinal = new Vector2D (
						(oVelRefFinal.dx * cosPhi - oVelRefFinal.dy * sinPhi),
						(oVelRefFinal.dx * sinPhi + oVelRefFinal.dy * cosPhi));
					
					p.setVelocity (pVelFinal);
					o.setVelocity (oVelFinal);
					
					updateNextPosition (p);
					updateNextPosition (o);
					qTree.update (o);

					if (DEBUG_COLLISION && p.collidesWith (o)) {
						System.out.println ("oh no");
					}
					
					p.afterCollisionHandling (p, o);
					
				}
			}
		}
	}
	
	public void updateParticles () {
		for (T p : particles) {
			updateNextPosition (p);
			updateBoundryCheck (p);
			qTree.update (p);
			updateCollisionCheck (p);
			//Done
			updateNextPosition (p);
			//System.out.println ("Objects in tree: " + qTree.treeHead.getAllObjectsUnderNode ().size () + " / " + particles.size ());
			//System.out.println (qTree);
		}
	}
	
	public ArrayList <T> getParticles () {
		return particles;
	}
	
	public QuadTree getQuadTree () {
		return qTree;
	}

}

