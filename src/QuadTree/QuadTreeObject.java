package QuadTree;

public abstract class QuadTreeObject {

		private QuadTreeNode associatedLeaf;

		public QuadTreeObject () {
		}

		public abstract float getX ();

		public abstract float getY ();

		public abstract boolean completelyInBoundry (QuadTreeNode node);

		public abstract QuadTreeQuery getQuery ();

		public void setNode (QuadTreeNode node) {
			associatedLeaf = node;
		}

		public QuadTreeNode getNode () {
			/*if (associatedLeaf == null) {
				System.out.println ("Not in tree!");
			}*/

			return associatedLeaf;
		}
	}

