/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


import java.util.Random;

class TreeNode {
    public int data;
    public int depth;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode() {
        data = 0;
        depth = 0;
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

        if (parent != null)
            newNode.depth = parent.depth + 1;
        else
            newNode.depth = 0;

        /*Construct the left sub-tree using values[low] to values[middle-1]*/
        newNode.left = TreeNode.constructBst(newNode, values, low, middle - 1);

        /*Construct the right sub-tree using values[middle+1] to values[high]*/
        newNode.right = TreeNode.constructBst(newNode, values, middle + 1, high);

        return newNode;
    }


    /*Helper function for finding the error nodes in a Binary Search Tree
    curNode: current tree node 
    prevNodeArray: contains the node that is the in-order predecessor of curNode
    error1: the first error node is returned here
    error2: the second error node is returned here
    */
    public static void findErrorNodes(TreeNode curNode, TreeNode[] prevNodeArray, 
                TreeNode[] error1, TreeNode[] error2) {
        if (curNode == null)
            return;

        /*Check for error node in the left sub-tree*/
        findErrorNodes(curNode.left, prevNodeArray, error1, error2); 

        /*curNode should be greater than previous node. So if data in curNode 
        is less than or equal to previous node then we have found an error */
        TreeNode prevNode = prevNodeArray[0];
        if (prevNode != null && curNode.data <= prevNode.data) {
            if (error1[0] == null) {
                error1[0] = prevNode;
                error2[0] = curNode; 
            } else {
                error2[0] = curNode;
                return;
            }
        }  

        /*Update previous node to current node*/
        prevNodeArray[0] = curNode;

        /*Check for error node in the right sub-tree*/
        findErrorNodes(curNode.right, prevNodeArray, error1, error2); 
    }

    /*Main function for correcting the Binary Search Tree
    * root: root node of the Binary Search Tree in which two nodes have been swapped
    */
    public static void correctBst(TreeNode root) {
        TreeNode[] error1 = new TreeNode[1]; 
        TreeNode[] error2 = new TreeNode[1];
        TreeNode[] prevNodeArray = new TreeNode[1];

        /*Find the two error nodes*/
        findErrorNodes(root, prevNodeArray, error1, error2);

        /*If we found two error nodes, then swap their data*/
        if (error1[0]  != null && error2[0] != null) {
            int tempData = error1[0].data;
            error1[0].data = error2[0].data;
            error2[0].data = tempData;
        }
    }


    /*curNode: node whose left and right sub-trees need to be checked
    prevNodeArray: contains the node that is the in-order predecessor of curNode
    Return value: true if the tree is a binary search tree, false otherwise
    */
    public static boolean isBst(TreeNode curNode, TreeNode[] prevNodeArray) {
        if (curNode == null)
            return true;

        if (!isBst(curNode.left, prevNodeArray)) /*Check if the left sub-tree is a BST*/
            return false;

        /*If data in curNode is less than or equal to previous node then it is not a BST*/
        TreeNode prevNode = prevNodeArray[0];
        if ( prevNode != null && curNode.data <= prevNode.data)
            return false;

        /*Update previous node to current node*/
        prevNodeArray[0] = curNode;

        return isBst(curNode.right, prevNodeArray); /*Check if the right sub-tree is a BST*/
    }



}




class Trees_23_CorrectBst {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }



    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        TreeNode[] prevNodeArray = new TreeNode[1];
        Random randomGenerator = new Random();

        /*Test for different number of nodes in the tree*/
        for (int numElems = 2; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*Construct a sorted array*/
            for (int i = 0; i < numElems; ++i) {
                numberArray[i] = i;
            }

            /*Choose two random indexes*/
            int index1 = randomGenerator.nextInt(numElems);
            int index2 = randomGenerator.nextInt(numElems);

            System.out.println("Num elements = " + numElems + ", Swapping node at " + 
                    index1 + " with node at " + index2 + "\n"); 

            /*Swap two locations in the sorted array*/
            int temp = numberArray[index1];
            numberArray[index1] = numberArray[index2];
            numberArray[index2] = temp;

            /*Construct the Incorrect Binary Search Tree based on the numberArray
            Since two locations in the numberArray are incorrect, two nodes in the 
            Binary Search Tree will be incorrect.*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing the Incorrect tree: ");        
            PrintTreeHelper.printTree(root, numElems);

            /*Correct the Binary Search Tree*/
            TreeNode.correctBst(root);

            System.out.println("Printing the Corrected tree: ");        
            PrintTreeHelper.printTree(root, numElems);

            /*Verify if the Binary Search Tree is proper*/
            prevNodeArray[0] = null;
            if (!TreeNode.isBst(root, prevNodeArray) )
                handleError();

            System.out.println("______________________________________________________");

        }

        System.out.println("Test passed");

    }

}
