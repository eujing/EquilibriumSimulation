package Chemistry;

import Collision.*;
import GUI.MainWindow;
import java.util.ArrayList;

public class ReactionManager {

	public static final int MOLECULE_A = 0x01;
	public static final int MOLECULE_B = 0x02;
	public static final int MOLECULE_C = 0x04;
	public static final float MASS_A = 3f;
	public static final float MASS_B = 5f;
	public static final float MASS_C = (MASS_A + MASS_B) / 2;
	public static final int FORWARD_REACTION = MOLECULE_A | MOLECULE_B;
	public static final int BACKWARD_REACTION = MOLECULE_C | MOLECULE_C;
	public Physics<Molecule> pEngine;
	private boolean tempChanged;
	private int prevTemp;
	private int currentTemp;
	private double activationEnergy;
	private double enthalpy;
	private double concA, concB, concC;

	public ReactionManager (Physics<Molecule> pEngine) {
		this.pEngine = pEngine;
		tempChanged = false;
		prevTemp = currentTemp = MainWindow.DEFAULT_TEMP;
		concA = concB = concC = 0;
	}

	public void update () {
		if (tempChanged) {
			double tempRatio = Math.sqrt ((double) currentTemp / (double) prevTemp);
			tempChanged = false;

			ArrayList<Molecule> particles = pEngine.getParticles ();
			for (int i = 0; i < particles.size (); i++) {
				particles.get (i).v.scale (tempRatio);
			}
		}

		pEngine.updateParticles ();
	}

	public void setTemperature (int t) {
		this.prevTemp = this.currentTemp;
		this.currentTemp = t;
		tempChanged = true;
	}

	public void setActivationEnergy (double Ea) {
		this.activationEnergy = Ea;
	}

	public void setEnthalpy (double H) {
		this.enthalpy = H;
	}

	public void updateAllConcs () {
		concA = concB = concC = 0;
		for (Molecule m : pEngine.getParticles ()) {
			switch (m.getType ()) {
				case ReactionManager.MOLECULE_A:
					concA++;
					break;
				case ReactionManager.MOLECULE_B:
					concB++;
					break;
				case ReactionManager.MOLECULE_C:
					concC++;
					break;
			}
		}
	}

	public double getConcOfType (int moleculeType) {
		/*int num = 0;
		 for (Molecule m : pEngine.getParticles ()) {
		 if (m.getType () == moleculeType) {
		 num++;
		 }
		 }*/

		switch (moleculeType) {
			case ReactionManager.MOLECULE_A:
				return concA;
			case ReactionManager.MOLECULE_B:
				return concB;
			case ReactionManager.MOLECULE_C:
				return concC;
		}
		
		return 0;
	}

	public double calcReactionQuotient () {
		updateAllConcs ();
		return (concC * concC / (concA * concB));
	}
}
