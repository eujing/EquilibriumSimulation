package GUI;

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

	private static final boolean DEBUG_QUADS = false;
	private static final boolean DEBUG_QUERIES = false;
	private boolean runSimulation;
	private double maxFps;
	private double currentFps;
	private Thread simLoop;
	public Physics pEngine;

	public CanvasPanel (int width, int height, double fps) {
		this.setPreferredSize (new Dimension (width, height));
		this.maxFps = fps;
		pEngine = new Physics (width, height);
		this.setBackground (Color.WHITE);
	}
	
	public void addTestParticles () {
		pEngine.addParticle (new Particle (90, 150, 1, 0, 10));
		pEngine.addParticle (new Particle (20, 150, 2, 0, 20));
		pEngine.addParticle (new Particle (200, 150, -2, 0, 40));
		pEngine.addParticle (new Particle (380, 150, 4, 0, 20));
		pEngine.addParticle (new Particle (20, 20, 5, 1, 20));
	}
	
	public void addNRandomParticles (int n) {
		Random rand = new Random ();
        for(int i = 0; i < n; i++){
			pEngine.addParticle (new Particle (
				this.getWidth () * haltonSequence (i, 2), 
				this.getHeight () * haltonSequence (i, 3), 
				rand.nextInt (5) - 2, 
				rand.nextInt (5) - 2, 
				5));
		}
	}

	private float haltonSequence (int index, int base) {
		float result = 0;
		float f = 1f / base;
		int i = index;
		
		while (i > 0) {
			result += f * (i % base);
			i = (int) Math.floor ((float) i / base);
			f = f / base;
		}
		
		return result;
	}
	
	public void startSimulation () {
		if (!runSimulation) {
			runSimulation = true;
			(simLoop = new Thread (this)).start ();
			System.out.println (pEngine.getParticles ().size ());
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
		System.out.println ("Max fps " + (1000.0 / maxFrameTime));

		while (runSimulation) {
			startTime = System.currentTimeMillis ();
			
			pEngine.updateParticles ();
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

		ArrayList<Particle> particles = pEngine.getParticles ();

		if (CanvasPanel.DEBUG_QUADS) {
			ArrayList<Shape> lines = pEngine.getQuadTree ().getLines ();
			for (int i = 0; i < lines.size (); i++) {
				g2.draw ((Shape) lines.get (i));
			}
		}

		if (particles.size () > 0) {
			for (int i = 0; i < particles.size (); i++) {

				Particle p = particles.get (i);
				if (DEBUG_QUERIES) {
					QuadTreeQuery query = p.getQuery ();
					g2.drawRect ((int) query.x, (int) query.y, (int) query.w, (int) query.h);
				}

				int x = (int) (p.getX () - p.getR () + 0.5f), y = (int) (p.getY () - p.getR () + 0.5f);
				int l = (int) (p.getR () * 2 + 0.5f);
				
				g2.setColor (Color.black);
				g2.drawOval (x, y, l, l);
				g2.fillOval (x, y, l, l);
			}
		}

		DecimalFormat df = new DecimalFormat ("0.##");
		g2.drawString ("Fps: " + df.format (currentFps), 10, 10);
	}
}
