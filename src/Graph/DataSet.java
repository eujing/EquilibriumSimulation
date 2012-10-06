package Graph;

import java.awt.Color;

public abstract class DataSet implements IOutputData {
	public Color color;
	public float prevAvg;
	public int prevIndex;
	public float currSum;
	public int nNewPoints;
	
	public DataSet (Color c) {
		this.color = c;
		prevAvg = 0;
		prevIndex = 0;
		currSum = 0;
		nNewPoints = 0;
	}
}