package Chemistry;

import java.awt.Color;

public interface IReactable {
	public Color matchColor (int type);
	public double getActivationEnergy ();
	public double getEnthalpy ();
}
