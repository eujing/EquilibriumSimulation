package Chemistry;

import Collision.*;
import GUI.MainWindow;
import java.util.ArrayList;

public class ReactionManager {

	public Physics<Molecule> pEngine;
	private boolean tempChanged;
	private int prevTemp;
	private int currentTemp;
	private boolean pressureChanged;
	private int prevPressure;
	private int currentPressure;

	public ReactionManager (Physics<Molecule> pEngine) {
		this.pEngine = pEngine;
		tempChanged = false;
		pressureChanged = false;
		prevTemp = currentTemp = MainWindow.DEFAULT_TEMP;
		prevPressure = currentPressure = MainWindow.DEFAULT_PRESSURE;
	}

	public void update () {
		if (tempChanged || pressureChanged) {
			float tempRatio = 1;
			float pressureRatio = 1;
			if (tempChanged) {
				tempRatio = (float) Math.sqrt ((float) currentTemp / (float) prevTemp);
				tempChanged = false;
			}
			if (pressureChanged) {
				pressureRatio = (float) Math.sqrt ((float) currentPressure / (float) prevPressure);
				pressureChanged = false;
			}
			
			ArrayList<Molecule> particles = pEngine.getParticles ();
			for (int i = 0; i < particles.size (); i++) {
				particles.get (i).getVelocity ().scale (tempRatio * pressureRatio);
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

	public void setPressure (int p) {
		this.prevPressure = this.currentPressure;
		this.currentPressure = p;
		pressureChanged = true;
	}

	public float getConcA () {
		int numA = 0;
		for (Molecule m : pEngine.getParticles ()) {
			if (m.getType () == Molecule.MOLECULE_A) {
				numA++;
			}
		}
		return (float) (numA / 1000.0);
	}

	public float getConcB () {
		int numB = 0;
		for (Molecule m : pEngine.getParticles ()) {
			if (m.getType () == Molecule.MOLECULE_B) {
				numB++;
			}
		}
		return (float) (numB / 1000.0);
	}

	public float getConcC () {
		int numC = 0;
		for (Molecule m : pEngine.getParticles ()) {
			if (m.getType () == Molecule.MOLECULE_C) {
				numC++;
			}
		}
		return (float) (numC / 1000.0);
	}

	public float calcReactionQuotient () {
		return (getConcC () * getConcC () / (getConcB () * getConcA ()));
	}
}
