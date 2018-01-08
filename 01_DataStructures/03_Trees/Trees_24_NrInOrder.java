/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class TreeNode  {
    public static final int MAX_NUM_NODES_IN_TREE = 10;
    public static int[] nrResultArray = new int[MAX_NUM_NODES_IN_TREE];
    public static int nrIndex = 0;
    
    public int data;

    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode() {
        left = null;
        right = null;
        parent = null;
    }


    public static TreeNode constructBst (TreeNode parent, int[] values, int low, int high) {
        if (low > high)
            return null;

        int middle = (low + high) / 2;

        TreeNode newNode = new TreeNode();
        if (newNode == null)
            return null;

        /*Construct the new node using the middle value*/
        newNode.data = values[middle];
        newNode.parent = parent;

        /*Construct the left sub-tree using values[low] to values[middle-1]*/
        newNode.left = constructBst(newNode, values, low, middle - 1);

        /*Construct the right sub-tree using values[middle+1] to values[high]*/
        newNode.right = constructBst(newNode, values, middle + 1, high);

        return newNode;

    }

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void process(TreeNode curNode) {
        nrResultArray[nrIndex++] = curNode.data;
        System.out.print(curNode.data + " ");
    }

    /*
    root: root node of the binary tree
    s: stack for storing the nodes for in-order traversal
    */
    public static void nonRecursiveInOrder(TreeNode root, Stack<TreeNode> s) {
        TreeNode curNode = root;
        while (curNode != null || !s.isEmpty()) {
            if (curNode != null) {
                /*push the current node onto stack*/
                s.push(curNode);

                /*Traverse to the left sub-tree*/
                curNode = curNode.left;

            } else {
                /*pop the node from stack and process it*/
                curNode = s.pop();

                /*process or print the node in-order*/
                process(curNode);

                /*Traverse to the right sub-tree*/
                curNode = curNode.right;
            }
        }
    }

    public static void recursiveInOrder(TreeNode node) {
        if (node == null)
            return;

        recursiveInOrder(node.left);

        if (nrResultArray[nrIndex] != node.data)
            handleError();

        ++nrIndex;

        recursiveInOrder(node.right);
    }

}




class Trees_24_NrInOrder {

    public static final int MAX_NUM_NODES_IN_TREE = 10;


    public static void test01() {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        Stack<TreeNode> s = new Stack<TreeNode>();

        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {
            /*Construct the tree*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root, numElems);

            /*Perform the non-recursive in-order traversal. Store the node data
            in nrResultArray during the traversal*/
            TreeNode.nrIndex = 0;
            System.out.print("Non Recursive In-order : ");
            TreeNode.nonRecursiveInOrder(root, s);

            /*Verify the non-recursive in-order result stored in nrResultArray
            by comparing it with the recursive in-order traversal*/
            TreeNode.nrIndex = 0;
            TreeNode.recursiveInOrder(root);

            System.out.println("\n__________________________________________________");

        }

        System.out.println("Test passed");

        return ;
    }



    public static void main(String[] args) {
        test01();

        System.out.println("Test passed");
        return;
    }

}
