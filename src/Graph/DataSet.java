package Graph;

import java.awt.Color;

public abstract class DataSet {
	public float currSum;
	public int nNewPoints;
	
	public DataSet () {
		reset ();
	}
	
	public final void reset () {
		currSum = 0;
		nNewPoints = 0;
	}
	
	public abstract float getNextDataPoint ();
	public abstract Color getColor ();
}