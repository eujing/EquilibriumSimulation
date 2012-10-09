package Chemistry;

import Collision.*;
import java.awt.Color;

public abstract class Molecule extends Physics.PhysicsCompatible implements IReactable {
	private int moleculeType;
	private Color color;
	private float mass;

	public Molecule (float x, float y, float dx, float dy, int type) {
		super (x, y, dx, dy, (float) Math.sqrt (Molecule.matchMass (type)));
		this.moleculeType = type;
		this.color = matchColor (moleculeType);
		this.setMass (Molecule.matchMass (type));
	}

	public static float matchMass (int moleculeType) {
		switch (moleculeType) {
			case ReactionManager.MOLECULE_A:
				return ReactionManager.MASS_A;
			case ReactionManager.MOLECULE_B:
				return ReactionManager.MASS_B;
			case ReactionManager.MOLECULE_C:
				return ReactionManager.MASS_C;
			default:
				return 0;
		}
	}

	public Color getColor () {
		return this.color;
	}

	public int getType () {
		return this.moleculeType;
	}
	
	@Override
	public float getMass () {
		return this.mass;
	}

	public float getEnergy () {
		float v = this.getVelocity ().getMagnitude ();
		return 0.5f * this.mass * v * v;
	}

	public final void setMass (float mass) {
		this.mass = mass;
		this.setR ((float) Math.sqrt (mass));
	}

	private void convertTo (int moleculeType) {
		float newMass = Molecule.matchMass (moleculeType);
		float velRatio = (float) Math.sqrt (this.mass / newMass);
		this.getVelocity ().scale (velRatio);
		this.setMass (newMass);
		this.color = matchColor (moleculeType);
		this.moleculeType = moleculeType;
	}

	@Override
	public void afterCollisionHandling (Particle p, Particle o) {
		Molecule m1 = (Molecule) p;
		Molecule m2 = (Molecule) o;
		int reaction = m1.moleculeType | m2.moleculeType;
		float energy = m1.getEnergy () + m2.getEnergy ();

		switch (reaction) {
			case ReactionManager.FORWARD_REACTION:
				if (energy > getActivationEnergy ()) {
					m1.convertTo (ReactionManager.MOLECULE_C);
					m2.convertTo (ReactionManager.MOLECULE_C);
				}
				break;
			case ReactionManager.BACKWARD_REACTION:
				if (energy > getActivationEnergy () - getEnthalpy ()) {
					m1.convertTo (ReactionManager.MOLECULE_A);
					m2.convertTo (ReactionManager.MOLECULE_B);
					break;
				}
		}
	}
}
