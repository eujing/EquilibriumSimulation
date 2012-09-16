package QuadTree;

public class QuadTreeQuery {

		public float x;
		public float y;
		public float w;
		public float h;

		public QuadTreeQuery (float x, float y, float w, float h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}

		private boolean valueInRange (float value, float min, float max) {
			return (value >= min) && (value <= max);
		}

		public boolean intersects (QuadTreeNode node) {
			boolean xOverlap = valueInRange (this.x, node.getX (), node.getX () + node.getW ())
							   || valueInRange (node.getX (), this.x, this.x + this.w);

			boolean yOverlap = valueInRange (this.y, node.getY (), node.getY () + node.getH ())
							   || valueInRange (node.getY (), this.y, this.y + this.h);

			return xOverlap && yOverlap;
		}
	}

