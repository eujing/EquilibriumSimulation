package Graph;

import java.awt.Color;
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
	private static final int sampleSize = 100;
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
	private ArrayList<Float>[] trendBuffers;
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
		buffers = (ArrayList<Float>[]) new ArrayList[dataCollectors.length];
		trendBuffers = (ArrayList<Float>[]) new ArrayList[dataCollectors.length];

		for (int i = 0; i < dataCollectors.length; i++) {
			buffers[i] = new ArrayList<> (bufferSize);
			trendBuffers[i] = new ArrayList<> (bufferSize);
		}

		this.dataCollectors = dataCollectors;
		timer = new Timer (freq, this);

		this.graphWidth = width - (2 * border) - padding;
		this.graphHeight = height - (2 * border) - padding;
		int left = border, right = width - border;
		int top = border, bottom = height - border;
		//Generate xAxis
		xAxis = new Line2D.Float[3];
		xAxis[0] = new Line2D.Float (left, bottom - padding, right, bottom - padding);
		xAxis[1] = new Line2D.Float (right, bottom - padding, right - 5, bottom - padding - 2);
		xAxis[2] = new Line2D.Float (right, bottom - padding, right - 5, bottom - padding + 2);

		//Generate yAxis
		yAxis = new Line2D.Float[3];
		yAxis[0] = new Line2D.Float (left + padding, bottom, left + padding, top);
		yAxis[1] = new Line2D.Float (left + padding, top, left + padding - 2, top + 5);
		yAxis[2] = new Line2D.Float (left + padding, top, left + padding + 2, top + 5);

		xCoords = new int[bufferSize];
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
			DataSet set = dataCollectors[i];
			float nextPoint = set.getNextDataPoint ();

			//Data buffer
			if (buffers[i].size () >= bufferSize) {
				buffers[i].remove (0);
			}
			buffers[i].add (nextPoint);

			//Trend buffer
			if (set.nNewPoints < sampleSize) {
				set.currSum += nextPoint;
				set.nNewPoints++;
				trendBuffers[i].add (0f);
			}
			else {
				float newAvg = set.currSum / set.nNewPoints;
				float w = trendBuffers[i].size () - set.prevIndex;
				for (int j = set.prevIndex; j < trendBuffers[i].size (); j++) {
					trendBuffers[i].set (j, linearInterpolation (
						set.prevIndex,
						set.prevAvg,
						trendBuffers[i].size (),
						newAvg,
						j));
				}
				trendBuffers[i].add (newAvg);
				set.prevAvg = newAvg;
				set.prevIndex = trendBuffers[i].size ();
				set.currSum = 0;
				set.nNewPoints = 0;
			}
			if (trendBuffers[i].size () >= bufferSize) {
				trendBuffers[i].remove (0);
				set.prevIndex--;
			}
		}
	}

	private float linearInterpolation (float x1, float y1, float x2, float y2, float xn) {
		return (y2 - y1) / (x2 - x1) * (xn - x1) + y1;
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
		g2.drawString (s, x - length / 2, y);
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

		//Draw graphs
		for (int i = 0; i < dataCollectors.length; i++) {
			ArrayList<Float> buffer = buffers[i];
			int[] yCoords = new int[buffer.size ()];
			for (int j = 0; j < buffer.size (); j++) {
				yCoords[j] = height - padding - border - (int) (buffer.get (j) / maxY * (graphHeight));
			}

			g2.setColor (dataCollectors[i].color);
			g2.drawPolyline (xCoords, yCoords, yCoords.length < bufferSize ? yCoords.length : bufferSize);
		}

		//Draw trendlines
		for (int i = 0; i < dataCollectors.length; i++) {
			ArrayList<Float> buffer = trendBuffers[i];
			int[] yCoords = new int[buffer.size ()];
			for (int j = 0; j < buffer.size (); j++) {
				yCoords[j] = height - padding - border - (int) (buffer.get (j) / maxY * (graphHeight));
			}

			g2.setColor (Color.YELLOW);
			g2.drawPolyline (xCoords, yCoords, yCoords.length < bufferSize ? yCoords.length : bufferSize);
		}
	}
}