package Graph;

import java.awt.Color;

public abstract class DataSet implements IOutputData {
	public Color color;
	
	public DataSet (Color c) {
		this.color = c;
	}
}