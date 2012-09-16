package GUI;

import Collision.*;
import QuadTree.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
		
		simLoop = new Thread (this);
		pEngine = new Physics (width, height);
	}
	
	public void startSimulation () {
		runSimulation = true;
		simLoop.start ();
	}
	
	public void stopSimulation () {
		runSimulation = false;
	}
	
	@Override
	public void run () {
		long startTime, endTime, timeTaken;
		long maxFrameTime = Math.round (1000.0 / maxFps);
		System.out.println ("Max fps " + (1000.0 / maxFrameTime));
		
		endTime = System.currentTimeMillis ();
		while (runSimulation) {
			startTime = endTime;
			pEngine.updateParticles ();
			repaint ();
			endTime = System.currentTimeMillis ();
			timeTaken = endTime - startTime;
			
			if (timeTaken < maxFrameTime) {
				try {
					simLoop.sleep (maxFrameTime - timeTaken);
				}
				catch (Exception ex) {
					System.out.println (ex.getMessage ());
				}
				
			}
			
			endTime = System.currentTimeMillis ();
			timeTaken = endTime - startTime;
			
			if (timeTaken > 0) {
				currentFps = 1000.0 / (double) timeTaken;
			}
		}
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		ArrayList <Particle> particles = pEngine.getParticles();
		
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
				g2.drawOval (x, y, l, l);
				g2.fillOval (x, y, l, l);
			}
		}
		
		DecimalFormat df = new DecimalFormat ("0.##");
		g2.drawString ("Fps: " + df.format (currentFps), 10, 10);
	}

}

