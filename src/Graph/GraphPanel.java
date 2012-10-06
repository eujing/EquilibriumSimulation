package Graph;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GraphPanel extends JPanel implements ActionListener {
	private int width;
	private int height;
	private int maxX;
	private int maxY;
	private int bufferSize;
	private int freq;
	private ArrayList<Float>[] buffers;
	private DataSet[] dataCollectors;
	private Timer timer;
	private Line2D.Float[] xAxis;
	private Line2D.Float[] yAxis;
	private int[] xCoords;
	
	public GraphPanel (int width, int height, int maxX, int maxY, int bufferSize, int freq, DataSet[] dataCollectors) {
		this.width = width;
		this.height = height;
		this.maxX = maxX;
		this.maxY = maxY;
		this.bufferSize = bufferSize;
		this.freq = freq;
		
		this.setPreferredSize (new Dimension (width, height));
		buffers = (ArrayList<Float>[]) new ArrayList [dataCollectors.length];
		for (int i = 0; i < dataCollectors.length; i++) {
			buffers[i] = new ArrayList <> (bufferSize); 
		}
		
		this.dataCollectors = dataCollectors;
		timer = new Timer (freq, this);
		
		//Generate xAxis
		xAxis = new Line2D.Float [3];
		xAxis[0] = new Line2D.Float (0, height - 10, width, height - 10);
		xAxis[1] = new Line2D.Float (width, height - 10, width - 10, height - 15);
		xAxis[2] = new Line2D.Float (width, height - 10, width - 10, height - 5);
		
		//Generate yAxis
		yAxis = new Line2D.Float [3];
		yAxis[0] = new Line2D.Float (10, 0, 10, height);
		yAxis[1] = new Line2D.Float (10, 0, 5, 10);
		yAxis[2] = new Line2D.Float (10, 0, 15, 10);
		
		xCoords = new int [bufferSize];
		for (int i = 0; i < bufferSize; i++) {
			xCoords[i] = 10 + (int) (((double) i / (double) bufferSize) * (width- 10));
		}
	}
	
	private void updateBuffers () {
		for (int i = 0; i < dataCollectors.length; i++) {
			System.out.println (buffers[i].size () + " / " + bufferSize);
			if (buffers[i].size () >= bufferSize) {
				buffers[i].remove (0);
			}
			buffers[i].add (dataCollectors[i].getNextDataPoint ());
		}
	}
	
	public void startPlotting () {
		timer.start ();
	}
	
	public void stopPlotting () {
		timer.stop ();
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		updateBuffers ();
		repaint ();
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Line2D.Float l : xAxis) {
			g2.draw (l);
		}
		for (Line2D.Float l : yAxis) {
			g2.draw (l);
		}
		
		for (int i = 0; i < dataCollectors.length; i++) {
			ArrayList <Float> buffer = buffers[i];
			int[] yCoords = new int [buffer.size ()];
			for (int j = 0; j < buffer.size (); j++) {
				yCoords[j] = height - 10 - (int) (buffer.get (j) / maxY * (height - 10));
			}
			
			g2.setColor (dataCollectors[i].color);
			g2.drawPolyline (xCoords, yCoords, yCoords.length < bufferSize ? yCoords.length : bufferSize);
		}
	}
}