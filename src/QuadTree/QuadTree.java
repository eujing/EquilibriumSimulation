package QuadTree;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class QuadTree<T extends QuadTreeObject> {
	private static final boolean DEBUG_INSERT = false;
	private static final boolean DEBUG_DELETE = false;
	private static final boolean DEBUG_UPDATE = false;
	private float w;
	private float h;
	public QuadTreeNode treeHead;

	public QuadTree (float w, float h) {
		this.w = w;
		this.h = h;
		createTree ();
	}

	public final void createTree () {
		treeHead = new QuadTreeNode<> (0f, 0f, w, h, null);
	}

	@Override
	public String toString () {
		return treeHead.toString ();
	}

	public ArrayList<Shape> getLines () {
		return getLines (treeHead);
	}

	public ArrayList<Shape> getLines (QuadTreeNode node) {
		ArrayList<Shape> lines = new ArrayList<> ();

		if (node != null && node.childNodes != null) {
			lines.add (new Line2D.Float (node.x, (2 * node.y + node.h) / 2, node.x + node.w, (2 * node.y + node.h) / 2));
			lines.add (new Line2D.Float ((2 * node.x + node.w) / 2, node.y, (2 * node.x + node.w) / 2, node.y + node.h));

			for (int i = 0; i < node.childNodes.length; i++) {
				lines.addAll (getLines (node.childNodes[i]));
			}

		}

		return lines;
	}

	private void logInsert (String message) {
		if (DEBUG_INSERT) {
			System.out.println ("[Insert] " + message);
		}
	}

	private void subdivideNode (QuadTreeNode node) {
		node.childNodes = new QuadTreeNode[4];
		float newW = node.w / 2;
		float newH = node.h / 2;

		node.childNodes[0] = new QuadTreeNode (node.x, node.y, newW, newH, node);
		node.childNodes[1] = new QuadTreeNode (node.x + newW, node.y, newW, newH, node);
		node.childNodes[2] = new QuadTreeNode (node.x, node.y + newH, newW, newH, node);
		node.childNodes[3] = new QuadTreeNode (node.x + newW, node.y + newH, newW, newH, node);
	}

	private boolean childrenHasAnyObjects (QuadTreeNode node) {
		if (node.childNodes != null) {
			return node.childNodes[0].hasObjectsWithin || node.childNodes[1].hasObjectsWithin || node.childNodes[2].hasObjectsWithin || node.childNodes[3].hasObjectsWithin;
		}
		return false;
	}

	/*Inserts object into a child
	 * return true when successfull insertion to child
	 * return false when cant insert into any child
	 */
	private boolean insertToAChild (T object, QuadTreeNode node) {
		if (node.childNodes == null) {
			subdivideNode (node);
		}

		for (int i = 0; i < node.childNodes.length; i++) {
			if (insert (object, node.childNodes[i])) {
				return true;
			}
		}

		return false;
	}

	/*Main insertion function (object being as deep as possible)
	 * Used to combine all other insertion functions recursively
	 * Flow:
	 * 1)Insert object as deep as possible, regardless of existing objects
	 * 2)Traverse up from deepest spot till an empty space is available
	 */
	private void insertToDeepestEmptyNode (T object, QuadTreeNode startNode) {
		//Find deepest node
		if (!insertToAChild (object, startNode)) {
			//Cannot put deeper
			QuadTreeNode testingNode = startNode;

			//Traverse up to find nearest empty node
			while (testingNode != null && testingNode.containedObject != null) {
				testingNode = testingNode.parentNode;
			}

			if (testingNode != null) {
				//trim unused leafs
				if (!childrenHasAnyObjects (testingNode)) {
					testingNode.childNodes = null;
				}

				testingNode.setContainedObject (object);
			}
		}

		startNode.hasObjectsWithin = true;
	}

	/*Starts insertion from a node, checking for bounds
	 * returns true on successful insert
	 * returns false when out of bounds
	 */
	private boolean insert (T object, QuadTreeNode node) {
		if (object.completelyInBoundry (node)) {
			insertToDeepestEmptyNode (object, node);
			return true;
		}
		else {
			//Not in bounds
			return false;
		}
	}

	/*
	 * Insertion from top of tree
	 */
	public void insert (T object) {
		insert (object, treeHead);
	}

	private void logDelete (String message) {
		if (DEBUG_DELETE) {
			System.out.println ("[Delete] " + message);
		}
	}

	/*
	 * Preserves the tree so a deeper search can be carried out from the current node
	 */
	private void deleteDown (T object) {
		QuadTreeNode node = object.getNode ();

		//Remove from tree
		node.containedObject = null;

		//Only delete down
		if (!childrenHasAnyObjects (node)) {
			node.hasObjectsWithin = false;
			node.childNodes = null;
		}
	}

	/*
	 * Cleans up and collapses nodes that are no longer useful after a deleteDown (during reallocation)
	 */
	private QuadTreeNode deleteUp (T object) {
		QuadTreeNode node = object.getNode ();

		//Collapse unused children to parent
		while (node.parentNode != null && 
			   node.parentNode.containedObject == null && 
			   !childrenHasAnyObjects (node.parentNode) ) {
			
			node = node.parentNode;
			node.hasObjectsWithin = false;
			node.childNodes = null;
		}

		return node;
	}

	private void logUpdate (String message) {
		if (DEBUG_UPDATE) {
			System.out.println ("[Update] " + message);
		}
	}

	public void update (T object) {
		QuadTreeNode node = object.getNode ();

		if (node != null) {
			//Exists in tree
			//Only delete down so insert can use existing subdivisions to check more efficiently
			deleteDown (object);

			//Check if reallocation needed. If not, try put deeper
			if (!insert (object, node)) {
				//Clean up unused subdivisions, then update node with next available one
				node = deleteUp (object);

				//Reallocation
				while (node != null && !insert (object, node)) {
					node = node.parentNode;
				}

				if (node == null) {
					//No node available
					insert (object);
				}
			}
		}
		else {
			//Insert to tree
			//Most probably particles stuck at boundry or "oh no"
			insert (object);
		}
	}

	public ArrayList<T> getObjectsWithinBound (QuadTreeObject originalObj) {
		return getObjectsWithinBound (originalObj, treeHead);
	}

	public ArrayList<T> getObjectsWithinBound (QuadTreeObject originalObj, QuadTreeNode node) {
		ArrayList<T> objects = new ArrayList<> ();
		QuadTreeQuery query = originalObj.getQuery ();

		if (query.intersects (node)) {
			if (node.containedObject != null) {
				objects.add ((T) node.containedObject);
			}

			if (node.childNodes != null) {
				for (QuadTreeNode child : node.childNodes) {
					if (child.hasObjectsWithin) {
						objects.addAll (getObjectsWithinBound (originalObj, child));
					}
				}
			}
		}

		return objects;
	}
}
