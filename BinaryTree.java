//Name: Edward Latulipe-Kang
//Student ID: 260746475

import java.util.ArrayList;

public class BinaryTree { //BinaryTree objects are defined by the following three parameters
	Node root;
	BinaryTree left; 
	BinaryTree right;
	
	public class Node { //Node object template defined by gBall object --> Helper class

		gBall Ball;

		public Node(gBall Ball) {
			this.Ball = Ball;
		} 

	}

	public void insert(gBall input) { //method to insert a gBall object ball into different nodes of a BTree

		if (root == null) {

			root = new Node(input); //if there is nothing in a given node, add gBall object and create subTrees
			left = new BinaryTree();
			right = new BinaryTree();
		}

		else {

			if(input.Ball_size <= root.Ball.Ball_size)left.insert(input); //if ball smaller than or equal to ball in the root, insert into left node
			else right.insert(input); //if ball is bigger, insert into right node
		}
	}
	
	public void remove(BinaryTree mybt) {//this sets all the nodes of the Binary Tree to null recursively
		if(root != null) {
			root = null;
			remove(left);
			remove(right);
			
		}
	}

	public ArrayList<gBall> trace(ArrayList<gBall> sorted_balls){ // Method trace of type ArrayList for gBall objects that takes in the same type as arguments --> used to sort the balls 

		if (left.root != null) sorted_balls = left.trace(sorted_balls);
		if (root != null) sorted_balls.add(root.Ball);
		if (right.root != null) sorted_balls = right.trace(sorted_balls);

		return sorted_balls;
	}

}
