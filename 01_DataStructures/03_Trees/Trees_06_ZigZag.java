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


        if (low > high) {

            return null;
        }

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


    /* Helper function for printing in zig zag order
    printStack: stack used for printing the nodes
    storeStack: stack that stores the children of nodes in printStack
    leftToRight: if set to 1, left child is stored first followed by right child
    */
    public static void processStacks(Stack<TreeNode> printStack, 
                Stack<TreeNode> storeStack, boolean leftToRight) {
        while (!printStack.isEmpty()) {

            TreeNode curNode = printStack.pop();
            printData(curNode.data);

            if (leftToRight) {
                if (curNode.left != null)
                    storeStack.push(curNode.left);
                if (curNode.right != null)
                    storeStack.push(curNode.right);
            } else {
                if (curNode.right != null)
                    storeStack.push(curNode.right);
                if (curNode.left != null)
                    storeStack.push(curNode.left);
            }
        }
    }

    /*root: root of the binary tree to be printed spirally
    s0, s1: stacks used for storing nodes of the binary tree
    */
    public static void printZigZag(TreeNode root, Stack<TreeNode> s0, 
                Stack<TreeNode> s1) {
        if (root == null)
            return;

        /*Push root into stack s0 and start processing*/
        s0.push(root);

        while (!s0.isEmpty()) {
            /*s0 is used for printing. The children of nodes in s0 are
            stored in s1 in left to right direction*/
            processStacks(s0, s1, true);
            System.out.println();

            /*s1 is used for printing. The children of nodes in s1 are
            stored in s0 in right to left direction*/
            processStacks(s1, s0, false);
            System.out.println();
        }
    }


    
}



class Trees_06_ZigZag {

    public static final int MAX_NUM_NODES_IN_TREE = 10;
    public static final int MAX_NODE_VALUE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        /*numberArray contains data in ascending order from 0*/
        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*Construct the tree based on the data stored in the number array*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root, numElems);

            System.out.println("Zig Zag order is:");
            TreeNode.printZigZag(root, s1, s2);

            System.out.println("___________________________________________________\n");

        }

        
        System.out.println("Test passed");
    }

}











