package Chemistry;

import Collision.*;
import GUI.MainWindow;
import java.util.ArrayList;

public class ReactionManager {
	public static final int MOLECULE_A = 0x01;
	public static final int MOLECULE_B = 0x02;
	public static final int MOLECULE_C = 0x04;
	
	public static final float MASS_A = 9f/5f;
	public static final float MASS_B = 16f/5f;
	public static final float MASS_C = (MASS_A + MASS_B) / 2;
	public static final int FORWARD_REACTION = MOLECULE_A | MOLECULE_B;
	public static final int BACKWARD_REACTION = MOLECULE_C | MOLECULE_C;
	
	public Physics<Molecule> pEngine;
	private boolean tempChanged;
	private int prevTemp;
	private int currentTemp;
	private float activationEnergy;
	private float enthalpy;

	public ReactionManager (Physics<Molecule> pEngine) {
		this.pEngine = pEngine;
		tempChanged = false;
		prevTemp = currentTemp = MainWindow.DEFAULT_TEMP;
	}

	public void update () {
		if (tempChanged) {
			float tempRatio = (float) Math.sqrt ((float) currentTemp / (float) prevTemp);
			tempChanged = false;

			ArrayList<Molecule> particles = pEngine.getParticles ();
			for (int i = 0; i < particles.size (); i++) {
				particles.get (i).getVelocity ().scale (tempRatio);
			}
		}

		pEngine.updateParticles ();
	}

	public void setTemperature (int t) {
		this.prevTemp = this.currentTemp;
		this.currentTemp = t;
		tempChanged = true;
	}
	
	public void setActivationEnergy (float Ea) {
		this.activationEnergy = Ea;
	}
	
	public void setEnthalpy (float H) {
		this.enthalpy = H;
	}

	public float getConcOfType (int moleculeType) {
		int num = 0;
		for (Molecule m : pEngine.getParticles ()) {
			if (m.getType () == moleculeType) {
				num++;
			}
		}
		return (num / 1000f);
	}

	public float calcReactionQuotient () {
		return (getConcOfType (ReactionManager.MOLECULE_C) * getConcOfType (ReactionManager.MOLECULE_C) / (getConcOfType (ReactionManager.MOLECULE_B) * getConcOfType (ReactionManager.MOLECULE_A)));
	}
}
