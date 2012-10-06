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
	private static final int padding = 10;
	private static final int border = 20;
	private int width;
	private int height;
	private int maxY;
	private int bufferSize;
	private int graphWidth;
	private int graphHeight;
	private String title = "";
	private String xLabel = "";
	private String yLabel = "";
	private ArrayList<Float>[] buffers;
	private DataSet[] dataCollectors;
	private Timer timer;
	private Line2D.Float[] xAxis;
	private Line2D.Float[] yAxis;
	private int[] xCoords;
	
	public GraphPanel (int width, int height, int maxY, int bufferSize, int freq, DataSet[] dataCollectors) {
		this.width = width;
		this.height = height;
		this.maxY = maxY;
		this.bufferSize = bufferSize;
		
		this.setPreferredSize (new Dimension (width, height));
		buffers = (ArrayList<Float>[]) new ArrayList [dataCollectors.length];
		for (int i = 0; i < dataCollectors.length; i++) {
			buffers[i] = new ArrayList <> (bufferSize); 
		}
		
		this.dataCollectors = dataCollectors;
		timer = new Timer (freq, this);
		
		this.graphWidth = width - (2 * border) - padding;
		this.graphHeight = height - (2 * border) - padding;
		int left = border, right = width - border;
		int top = border, bottom = height - border;
		//Generate xAxis
		xAxis = new Line2D.Float [3];
		xAxis[0] = new Line2D.Float (left, bottom - padding, right, bottom - padding);
		xAxis[1] = new Line2D.Float (right, bottom - padding, right - 5, bottom - padding - 2);
		xAxis[2] = new Line2D.Float (right, bottom - padding, right - 5, bottom - padding + 2);
		
		//Generate yAxis
		yAxis = new Line2D.Float [3];
		yAxis[0] = new Line2D.Float (left + padding, bottom, left + padding, top);
		yAxis[1] = new Line2D.Float (left + padding, top, left + padding - 2, top + 5);
		yAxis[2] = new Line2D.Float (left + padding, top, left + padding + 2, top + 5);
		
		xCoords = new int [bufferSize];
		for (int i = 0; i < bufferSize; i++) {
			xCoords[i] = border + padding + (int) (((double) i / (double) bufferSize) * (graphWidth));
		}
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public void setLabels (String xLabel, String yLabel) {
		this.xLabel = xLabel;
		this.yLabel = yLabel;
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
	
	private void drawCenteredString (Graphics2D g2, String s, int x, int y) {
		int length = g2.getFontMetrics ().stringWidth (s);
		g2.drawString (s, x - length/2, y);
	}
	
	private void drawStringFromBack (Graphics2D g2, String s, int x, int y) {
		int length = g2.getFontMetrics ().stringWidth (s);
		g2.drawString (s, x - length, y);
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
		drawStringFromBack (g2, xLabel, width - border, height - border + 5);
		drawStringFromBack (g2, yLabel, border + padding - 5, border + 7);
		drawCenteredString (g2, title, width / 2, border);
		
		for (int i = 0; i < dataCollectors.length; i++) {
			ArrayList <Float> buffer = buffers[i];
			int[] yCoords = new int [buffer.size ()];
			for (int j = 0; j < buffer.size (); j++) {
				yCoords[j] = height - padding - border - (int) (buffer.get (j) / maxY * (graphHeight));
			}
			
			g2.setColor (dataCollectors[i].color);
			g2.drawPolyline (xCoords, yCoords, yCoords.length < bufferSize ? yCoords.length : bufferSize);
		}
	}
}