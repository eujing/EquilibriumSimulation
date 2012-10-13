package Collision;

public interface ICollidable <T extends Particle> {
	public void afterCollisionHandling (T p, T o);
	public double getMass ();
}
