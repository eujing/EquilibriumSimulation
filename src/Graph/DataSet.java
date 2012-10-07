package Graph;

import java.awt.Color;

public abstract class DataSet implements IOutputData {
	public Color color;
	public float currSum;
	public int nNewPoints;
	
	public DataSet (Color c) {
		this.color = c;
		reset ();
	}
	
	public final void reset () {
		currSum = 0;
		nNewPoints = 0;
	}
}