package GUI;

import Chemistry.*;
import Collision.*;
import QuadTree.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements Runnable {
	private static final int vMultiplier = 2;
	private static final boolean DEBUG_QUADS = false;
	private static final boolean DEBUG_QUERIES = false;
	private IReactable reactable;
	private boolean runSimulation;
	private double maxFps;
	private double currentFps;
	private Thread simLoop;
	public ReactionManager rEngine;

	public CanvasPanel (int width, int height, double fps, IReactable reactable) {
		this.setPreferredSize (new Dimension (width, height));
		this.reactable = reactable;
		this.maxFps = fps;
		rEngine = new ReactionManager (new Physics<Molecule> (width, height));
		this.setBackground (Color.white);
	}

	public void addNRandomParticles (int n, int temp, int type) {
		Random rand = new Random ();
		float factor = vMultiplier * (float) Math.sqrt ((float) temp / (float) MainWindow.DEFAULT_TEMP);
		for (int i = 0; i < n; i++) {
			rEngine.pEngine.addParticle (new Molecule (
				this.getWidth () * rand.nextFloat (), this.getHeight () * rand.nextFloat (),
				(rand.nextInt (3) - 1) * factor, (rand.nextInt (3) - 1) * factor, type, reactable));
		}
	}

	public void startSimulation () {
		if (!runSimulation) {
			runSimulation = true;
			(simLoop = new Thread (this)).start ();
		}
	}

	public void stopSimulation () {
		if (runSimulation) {
			runSimulation = false;
		}
	}

	@Override
	public void run () {
		double startTime, endTime, timeTaken;
		double maxFrameTime = 1000.0 / maxFps;
		int frames = 0;
		double lastPurgeTime = System.currentTimeMillis ();

		while (runSimulation) {
			startTime = System.currentTimeMillis ();

			rEngine.update ();
			repaint ();

			endTime = System.currentTimeMillis ();
			if (++frames > 10) {
				currentFps = frames / ((endTime - lastPurgeTime) / 1000.0);
				frames = 0;
				lastPurgeTime = endTime;
			}
			timeTaken = endTime - startTime;

			if (timeTaken < maxFrameTime) {
				try {
					simLoop.sleep ((long) maxFrameTime - (long) timeTaken);
				}
				catch (Exception ex) {
					System.out.println (ex.getMessage ());
				}

			}

		}
	}

	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		ArrayList<Molecule> molecules = rEngine.pEngine.getParticles ();

		if (CanvasPanel.DEBUG_QUADS) {
			ArrayList<Shape> lines = rEngine.pEngine.getQuadTree ().getLines ();
			for (int i = 0; i < lines.size (); i++) {
				g2.draw ((Shape) lines.get (i));
			}
		}

		if (molecules.size () > 0) {
			for (int i = 0; i < molecules.size (); i++) {

				Molecule p = molecules.get (i);
				if (DEBUG_QUERIES) {
					QuadTreeQuery query = p.getQuery ();
					g2.drawRect ((int) query.x, (int) query.y, (int) query.w, (int) query.h);
				}

				int x = (int) (p.x - p.r + 0.5f), y = (int) (p.y - p.r + 0.5f);
				int l = (int) (p.r * 2 + 0.5f);

				g2.setColor (p.getColor ());
				//g2.drawOval (x, y, l, l);
				g2.fillOval (x, y, l, l);
			}
		}

		g2.setColor (Color.black);
		DecimalFormat df = new DecimalFormat ("0.##");
		g2.drawString ("Fps: " + df.format (currentFps), 10, 10);
	}
}
