package Collision;

public class Vector2D {
	public double dx;
	public double dy;
	private boolean calcMagnitude;
	private double magnitude;
	private boolean calcAngle;
	private double angle;
	
	public Vector2D (double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
		this.calcMagnitude = true;
		this.calcAngle = true;
	}
	
	public void add (Vector2D o) {
		this.dx += o.dx;
		this.dy += o.dy;
		this.calcMagnitude = true;
		this.calcAngle = true;
	}
	
	public void subtract (Vector2D o) {
		this.dx -= o.dx;
		this.dy -= o.dy;
		this.calcMagnitude = true;
		this.calcAngle = true;
	}
	
	public void scale (double s) {
		this.dx *= s;
		this.dy *= s;
		this.calcMagnitude = true;
	}
	
	public double getMagnitude () {
		if (this.calcMagnitude) {
			this.magnitude = Math.sqrt (this.dx * this.dx + this.dy * this.dy);
			calcMagnitude = false;
		}
		
		return this.magnitude;
	}
	
	public double getAngle () {
		if (this.calcAngle) {
			this.angle = Math.atan2 (dy, dx);
			this.calcAngle = false;
		}
		
		return this.angle;
	}
}

