package Chemistry;

import Collision.*;
import java.awt.Color;

public class Molecule extends Particle {

	public static final int MOLECULE_A = 0x01;
	public static final int MOLECULE_B = 0x02;
	public static final int MOLECULE_C = 0x04;
	public static final Color COLOR_A = new Color (0, 250, 154);
	public static final Color COLOR_B = new Color (0, 255, 255);
	public static final Color COLOR_C = new Color (255, 20, 147);
	public static final float MASS_A = 9;
	public static final float MASS_B = 16;
	public static final float MASS_C = (MASS_A + MASS_B) / 2;
	public static final int FORWARD_REACTION = MOLECULE_A | MOLECULE_B;
	public static final int BACKWARD_REACTION = MOLECULE_C | MOLECULE_C;
	public static final float ENTHALPY = -50f;
	public static final float FORWARD_EA = 10;
	public static final float BACKWARD_EA = FORWARD_EA - ENTHALPY;
	private int moleculeType;
	private Color color;
	private float mass;

	public Molecule (float x, float y, float dx, float dy, int type) {
		super (x, y, dx, dy, (float) Math.sqrt (Molecule.matchMass (type)));
		this.moleculeType = type;
		this.color = Molecule.matchColor (moleculeType);
		this.setMass (Molecule.matchMass (type));
	}

	public static Color matchColor (int moleculeType) {
		switch (moleculeType) {
			case MOLECULE_A:
				return COLOR_A;
			case MOLECULE_B:
				return COLOR_B;
			case MOLECULE_C:
				return COLOR_C;
			default:
				return Color.black;
		}
	}

	public static float matchMass (int moleculeType) {
		switch (moleculeType) {
			case MOLECULE_A:
				return MASS_A;
			case MOLECULE_B:
				return MASS_B;
			case MOLECULE_C:
				return MASS_C;
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

	public float getMass () {
		return this.mass;
	}

	public float getEnergy () {
		float v = this.getVelocity ().getMagnitude ();
		return 0.5f * this.mass * v * v;
	}

	public void setMass (float mass) {
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
			case FORWARD_REACTION:
				if (energy > FORWARD_EA) {
					m1.convertTo (MOLECULE_C);
					m2.convertTo (MOLECULE_C);
				}
				break;
			case BACKWARD_REACTION:
				if (energy > BACKWARD_EA) {
					m1.convertTo (MOLECULE_A);
					m2.convertTo (MOLECULE_B);
					break;
				}
		}

		//pair[0] = m1;
		//pair[1] = m2;
	}
}
