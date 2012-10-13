package QuadTree;

public abstract class QuadTreeObject {

		private QuadTreeNode associatedLeaf;

		public QuadTreeObject () {
		}

		public abstract double getX ();

		public abstract double getY ();

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

