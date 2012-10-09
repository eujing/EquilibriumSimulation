package Chemistry;

import java.awt.Color;

public interface IReactable {
	public Color matchColor (int type);
	public float getActivationEnergy ();
	public float getEnthalpy ();
}
