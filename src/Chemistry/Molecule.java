package Chemistry;

import Collision.*;
import java.awt.Color;

public class Molecule extends Physics.PhysicsCompatible {

	private int moleculeType;
	private Color color;
	private double mass;
	private IReactable reactable;

	public Molecule (double x, double y, double dx, double dy, int type, IReactable reactable) {
		super (x, y, dx, dy, Math.sqrt (Molecule.matchMass (type)));
		this.moleculeType = type;
		this.reactable = reactable;
		this.color = reactable.matchColor (moleculeType);
		this.setMass (Molecule.matchMass (type));
	}

	public static double matchMass (int moleculeType) {
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
	public double getMass () {
		return this.mass;
	}

	public double getEnergy () {
		double vel = this.v.getMagnitude ();
		return 0.5f * this.mass * vel * vel;
	}

	public final void setMass (double mass) {
		this.mass = mass;
		this.r = Math.sqrt (mass);
	}

	private void convertTo (int moleculeType) {
		double newMass = Molecule.matchMass (moleculeType);
		double velRatio = Math.sqrt (this.mass / newMass);
		this.v.scale (velRatio);
		this.setMass (newMass);
		this.color = reactable.matchColor (moleculeType);
		this.moleculeType = moleculeType;
	}

	@Override
	public void afterCollisionHandling (Particle p, Particle o) {
		Molecule m1 = (Molecule) p;
		Molecule m2 = (Molecule) o;
		int reaction = m1.moleculeType | m2.moleculeType;
		double energy = m1.getEnergy () + m2.getEnergy ();

		switch (reaction) {
			case ReactionManager.FORWARD_REACTION:
				if (energy > reactable.getActivationEnergy ()) {
					m1.convertTo (ReactionManager.MOLECULE_C);
					m2.convertTo (ReactionManager.MOLECULE_C);
				}
				break;
			case ReactionManager.BACKWARD_REACTION:
				if (energy > reactable.getActivationEnergy () - reactable.getEnthalpy ()) {
					m1.convertTo (ReactionManager.MOLECULE_A);
					m2.convertTo (ReactionManager.MOLECULE_B);
					break;
				}
		}
	}
}
