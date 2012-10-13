package QuadTree;

public class QuadTreeQuery {

	public double x;
	public double y;
	public double w;
	public double h;

	public QuadTreeQuery (double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean intersects (QuadTreeNode node) {
		boolean xOverlap = ((this.x >= node.x) && (this.x <= node.x + node.w)) ||
						   ((node.x >= this.x) && (node.x <= this.x + this.w));
		
		boolean yOverlap = ((this.y >= node.y) && (this.y <= node.y + node.h)) ||
						   ((node.y >= this.y) && (node.y <= this.y + this.h));

		return xOverlap && yOverlap;
	}
}