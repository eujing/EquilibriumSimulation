package Chemistry;

import Collision.*;
import GUI.MainWindow;
import java.util.ArrayList;

public class ReactionManager {

	public Physics<Molecule> pEngine;
	private boolean tempChanged;
	private int prevTemp;
	private int currentTemp;

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
		System.out.println ("set to " + t + " from " + this.currentTemp);
		this.prevTemp = this.currentTemp;
		this.currentTemp = t;
		tempChanged = true;
	}

	public float getConcOfType (int moleculeType) {
		int num = 0;
		for (Molecule m : pEngine.getParticles ()) {
			if (m.getType () == moleculeType) {
				num++;
			}
		}
		return (float) (num / 1000.0);
	}

	public float calcReactionQuotient () {
		return (getConcOfType (Molecule.MOLECULE_C) * getConcOfType (Molecule.MOLECULE_C) / (getConcOfType (Molecule.MOLECULE_B) * getConcOfType (Molecule.MOLECULE_A)));
	}
}
