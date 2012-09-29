package Chemistry;

import Collision.*;
import java.awt.Color;

public class Molecule extends Particle {
	public static final int MOLECULE_A = 0x01;
	public static final int MOLECULE_B = 0x02;
	public static final int MOLECULE_C = 0x04;
	public static final Color COLOR_A = new Color (255, 20, 147);
	public static final Color COLOR_B = new Color (0, 255, 255);
	public static final Color COLOR_C = new Color (0, 250, 154);
	public static final float MASS_A = 9;
	public static final float MASS_B = 25;
	public static final float MASS_C = (MASS_A + MASS_B) / 2;
	public static final int FORWARD_REACTION = MOLECULE_A | MOLECULE_B;
	public static final int BACKWARD_REACTION = MOLECULE_C | MOLECULE_C;
	
	private int moleculeType;
	private Color color;
	private float mass;
	
	public Molecule (float x, float y, float dx, float dy, int type) {
		super (x, y, dx, dy, (float) Math.sqrt (Molecule.matchMass (type)));
		this.moleculeType = type;
		this.color = Molecule.matchColor (moleculeType);
		this.mass = Molecule.matchMass (type);
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
	
	private Molecule convertTo (int moleculeType) {
		float newMass = Molecule.matchMass (moleculeType);
		float velRatio = this.mass / newMass;
		return new Molecule (
			this.getX (), 
			this.getY (), 
			velRatio * this.getVelocity ().dx,
			velRatio * this.getVelocity ().dy,
			moleculeType);
	}
	
	@Override
	public void afterCollisionHandling (Particle p, Particle o) {
		Molecule m1 = (Molecule) p;
		Molecule m2 = (Molecule) o;
		int reaction = m1.moleculeType | m2.moleculeType;
		
		switch (reaction) {
			case FORWARD_REACTION:
				m1.convertTo (MOLECULE_C);
				m2.convertTo (MOLECULE_C);
			case BACKWARD_REACTION:
				m1.convertTo (MOLECULE_A);
				m2.convertTo (MOLECULE_B);
		}
	}
}

