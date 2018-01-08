/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class TreeNode {
    
    public int data;
    public int depth;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public TreeNode next;


    public TreeNode (int val) {
        data = val;
        left = null;
        right = null;
        parent = null;
        next = null;
    }

    public TreeNode() {
        left = null;
        right = null;
        parent = null;
        next = null;
    }

    /*Recursively Construct a Binary Search Tree from the input array sorted in ascending order*/
    public static TreeNode  constructBst (TreeNode parent, int[] values, int low, int high) {


        if (low > high) 
            return null;

        int middle = (low + high) / 2;

        TreeNode newNode = new TreeNode();

        newNode.data = values[middle];

        newNode.parent = parent;

        newNode.left = constructBst(newNode, values, low, middle - 1);

        newNode.right = constructBst(newNode, values, middle + 1, high);

        return newNode;

    }


    public static void printData(int data) {
        System.out.print(data + " ");
    }

    /*
    root: root node of the tree 
    q: queue used for printing the tree 
    */
    public static void printLevelOrder(TreeNode root, Queue<TreeNode> q) {
        if (root == null)
            return;

        /*Add the root node to the empty queue*/
        q.add(root);
        int numNodesInCurLevel = 1;
        int numNodesInNextLevel = 0;

        /*Process the nodes in the queue in Breadth First manner*/
        while (!q.isEmpty()) {

            /*Remove the node at the head of the queue*/
            TreeNode n = q.remove();

            printData(n.data); /*print the data in the node*/

            /*Add the left child to the queue*/
            if (n.left != null) {
                q.add(n.left);
                numNodesInNextLevel++;
            }

            /*Add the right child to the queue*/
            if (n.right != null) {
                q.add(n.right);
                numNodesInNextLevel++;
            }

            numNodesInCurLevel--;

            /*go to next line, if all nodes in current level are processed*/
            if (numNodesInCurLevel == 0) {
                System.out.println();
                numNodesInCurLevel = numNodesInNextLevel;
                numNodesInNextLevel = 0;
            }
        }
    }


    
}



class Trees_05_LevelOrderPrint {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        Queue<TreeNode> q = new LinkedList<TreeNode>();

        /*numberArray contains data in ascending order from 0*/
        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*Construct the tree based on the data stored in the number array*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree");
            PrintTreeHelper.printTree(root, numElems);

            System.out.println("Level order is:");
            TreeNode.printLevelOrder(root, q);

            System.out.println("_________________________________________________________");

        }

        
        System.out.println("Test passed");
    }

}











