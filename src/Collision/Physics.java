package Collision;

import QuadTree.*;
import java.util.ArrayList;

public class Physics<T extends Physics.PhysicsCompatible> {

	private static final boolean DEBUG_ENERGY = false;
	private static final boolean DEBUG_COLLISION = false;
	private float width;
	private float height;
	private QuadTree qTree;
	private ArrayList<T> particles;

	public static abstract class PhysicsCompatible extends Particle implements ICollidable {

		public PhysicsCompatible (double x, double y, double dx, double dy, double r) {
			super (x, y, dx, dy, r);
		}
	}

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

	public double CalculateSystemEnergy () {
		double ke = 0;
		for (T tmp : particles) {
			ke += 0.5 * tmp.getMass () * tmp.v.getMagnitude () * tmp.v.getMagnitude ();
		}
		return ke;
	}

	public void logEnergyDebug (String msg) {
		if (Physics.DEBUG_ENERGY) {
			System.out.println ("[Energy]" + msg + " = " + CalculateSystemEnergy ());
		}
	}

	private void updateNextPosition (T p) {
		p.setPosition (p.getNewX (), p.getNewY ());
	}

	private void reversePosition (T p) {
		p.setPosition (p.getX () - p.v.dx, p.getY () - p.v.dy);
	}

	private void updateBoundryCheck (T p) {
		if ((p.getX () - p.r < 0 && p.v.dx < 0)
			|| (p.getX () + p.r > width && p.v.dx > 0)) {
			p.v.dx = -p.v.dx;
			updateNextPosition (p);
		}

		//v = p.v;
		if ((p.getY () - p.r < 0 && p.v.dy < 0)
			|| (p.getY () + p.r > height && p.v.dy > 0)) {
			p.v.dy = -p.v.dy;
			updateNextPosition (p);
		}
	}

	private void updateCollisionCheck (T p) {
		//Collision culling here
		ArrayList<T> possibleColliders = qTree.getObjectsWithinBound (p);

		//Collision
		for (int i = 0; i < possibleColliders.size (); i++) {
			T o = possibleColliders.get (i);
			if (p != o) {
				if (p.collidesWith (o)) {
					reversePosition (p);
					reversePosition (o);

					double pMass = p.getMass ();
					double oMass = o.getMass ();
					double po_dx = p.getX () - o.getX ();
					double po_dy = p.getY () - o.getY ();
					double sinPhi, cosPhi, distance;

					//Get trigo angle shifts needed for collision to be 1D
					distance = Math.sqrt (po_dx * po_dx + po_dy * po_dy);
					sinPhi = po_dy / distance;
					cosPhi = po_dx / distance;

					//Push apart
					double target = p.r + o.r;
					double factor = (distance - target) / distance * 0.5;
					p.setPosition (p.getX () - po_dx * factor, p.getY () - po_dy * factor);
					o.setPosition (o.getX () + po_dx * factor, o.getY () + po_dy * factor);

					//Transform velocities to rotated coordinate system
					Vector2D pVelRefInit = new Vector2D (
						(p.v.dx * cosPhi + p.v.dy * sinPhi),
						(p.v.dx * -sinPhi + p.v.dy * cosPhi));

					Vector2D oVelRefInit = new Vector2D (
						(o.v.dx * cosPhi + o.v.dy * sinPhi),
						(o.v.dx * -sinPhi + o.v.dy * cosPhi));

					//Calculate new velocities based on 1D collision
					Vector2D pVelRefFinal = new Vector2D (
						(pVelRefInit.dx * (pMass - oMass) + 2 * oMass * oVelRefInit.dx) / (pMass + oMass),
						pVelRefInit.dy);

					Vector2D oVelRefFinal = new Vector2D (
						(oVelRefInit.dx * (oMass - pMass) + 2 * pMass * pVelRefInit.dx) / (pMass + oMass),
						oVelRefInit.dy);

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

					if (DEBUG_ENERGY) {
						logEnergyDebug ("After collisions");
					}
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
		}
	}

	public ArrayList<T> getParticles () {
		return particles;
	}

	public QuadTree getQuadTree () {
		return qTree;
	}
}
