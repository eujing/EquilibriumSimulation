package Collision;

import QuadTree.*;

public class Particle extends QuadTreeObject {

	public double x;
	public double y;
	public Vector2D v;
	public double r;

	public Particle (double x, double y, double dx, double dy, double r) {
		this.setPosition (x, y);
		v = new Vector2D (dx, dy);
		this.r = r;
	}

	public boolean collidesWith (Particle p) {
		double deltaX = this.x - p.x;
		double deltaY = this.y - p.y;
		double minDist = this.r + p.r;
		double distance = deltaX * deltaX + deltaY * deltaY;

		if (distance <= (minDist * minDist)) {
			return true;
		}

		return false;
	}

	//@Override
	public void afterCollisionHandling (Particle p, Particle o) {
	}

	@Override
	public String toString () {
		return "(x, y) = (" + getX () + ", " + getY () + ") r = " + this.r;
	}
	
	public void replaceWith (Particle o) {
		this.x = o.x;
		this.y = o.y;
		this.v = o.v;
		this.r = o.r;
	}

	@Override
	public boolean completelyInBoundry (QuadTreeNode node) {
		if ((this.x - this.r >= node.x && this.x + this.r <= node.x + node.w)
			&& (this.y - this.r >= node.y && this.y + this.r <= node.y + node.h)) {
			return true;
		}

		return false;
	}

	@Override
	public QuadTreeQuery getQuery () {
		//Velocity based
		/*return (new QuadTreeQuery (
				x + (v.dx < 0 ? v.dx : 0) - r,
				y + (v.dy < 0 ? v.dy : 0) - r,
				2 * r + (v.dx < 0 ? -v.dx : v.dx),
				2 * r + (v.dy < 0 ? -v.dy : v.dy)));*/
		//Simple
		return (new QuadTreeQuery (
			x - r,
			y - r,
			2 * r,
			2 * r));
	}

	public final void setPosition (double x, double y) {
		this.x = x;
		this.y = y;
	}

	public final void setVelocity (Vector2D v) {
		this.v = v;
	}

	public double getNewX () {
		return getX () + v.dx;
	}

	public double getNewY () {
		return getY () + v.dy;
	}

	@Override
	public double getX () {
		return x;
	}

	@Override
	public double getY () {
		return y;
	}
}
