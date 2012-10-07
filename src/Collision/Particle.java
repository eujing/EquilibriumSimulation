package Collision;

import QuadTree.*;

public class Particle extends QuadTreeObject {

	private float x;
	private float y;
	private Vector2D v;
	private float r;

	public Particle (float x, float y, float dx, float dy, float r) {
		this.setPosition (x, y);
		this.setVelocity (dx, dy);
		this.r = r;
	}

	public boolean collidesWith (Particle p) {
		float deltaX = this.getX () - p.getX ();
		float deltaY = this.getY () - p.getY ();
		float minDist = this.getR () + p.getR ();
		float distance = deltaX * deltaX + deltaY * deltaY;

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
		return "(x, y) = (" + getX () + ", " + getY () + ") r = " + getR ();
	}
	
	public void replaceWith (Particle o) {
		this.x = o.x;
		this.y = o.y;
		this.v = o.v;
		this.r = o.r;
	}

	@Override
	public boolean completelyInBoundry (QuadTreeNode node) {
		if ((getX () - getR () >= node.getX () && getX () + getR () <= node.getX () + node.getW ())
			&& (getY () - getR () >= node.getY () && getY () + getR () <= node.getY () + node.getH ())) {
			return true;
		}

		return false;
	}

	@Override
	public QuadTreeQuery getQuery () {
		return (new QuadTreeQuery (
				x + (v.dx < 0 ? v.dx : 0) - r,
				y + (v.dy < 0 ? v.dy : 0) - r,
				2 * r + (v.dx < 0 ? -v.dx : v.dx),
				2 * r + (v.dy < 0 ? -v.dy : v.dy)));
	}

	public final void setPosition (float x, float y) {
		this.x = x;
		this.y = y;
	}

	public final void setVelocity (float dx, float dy) {
		this.v = new Vector2D (dx, dy);
	}

	public final void setVelocity (Vector2D v) {
		this.v = v;
	}
	
	public void setR (float r) {
		this.r = r;
	}

	public Vector2D getVelocity () {
		return this.v;
	}

	public float getNewX () {
		return getX () + v.dx;
	}

	public float getNewY () {
		return getY () + v.dy;
	}

	@Override
	public float getX () {
		return x;
	}

	@Override
	public float getY () {
		return y;
	}

	public float getR () {
		return r;
	}
}
