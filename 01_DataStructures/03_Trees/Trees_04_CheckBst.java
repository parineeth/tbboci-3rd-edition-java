/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class TreeNode {
    
    public int data;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;


    public TreeNode (int val) {
        data = val;
        left = null;
        right = null;
        parent = null;
    }

    public TreeNode() {
        left = null;
        right = null;
        parent = null;
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


    /*curNode: current node 
    prevNodeArray: in-order predecessor of curNode is present in prevNodeArray[0]
    Return value: true if the tree is a binary search tree, false otherwise
    */
    public static boolean isBst(TreeNode curNode, TreeNode[] prevNodeArray)  {
        if (curNode == null)
            return true;

        /*Check if the left sub-tree is a BST*/
        if (!isBst(curNode.left, prevNodeArray)) 
            return false;

        /*If data in curNode is <= previous node then it is not a BST*/
        TreeNode prevNode = prevNodeArray[0];
        if (prevNode != null && curNode.data <= prevNode.data)
            return false;

        /*Update previous node to current node*/
        prevNodeArray[0] = curNode;

        /*Check if the right sub-tree is a BST*/
        return isBst(curNode.right, prevNodeArray); 
    }
    
}



class Trees_04_CheckBst {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printResult(boolean result) {
        if (result) 
            System.out.println("The tree is a Binary Search Tree\n\n"); 
        else 
            System.out.println("The tree is NOT a Binary Search Tree\n");
        
    }

    public static void swapChildren(TreeNode curNode) {

        if (curNode == null)
            return;

        TreeNode temp = curNode.left;
        curNode.left = curNode.right;
        curNode.right = temp;

        swapChildren(curNode.left);
        swapChildren(curNode.right);

    }



    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        TreeNode[] prevNodeArray = new TreeNode[1];

        /*numberArray contains data in ascending order from 0 to MAX_NUM_NODES_IN_TREE*/
        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {
            /*Construct the tree based on the data stored in the number array*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree");
            PrintTreeHelper.printTree(root, numElems);

            /*Verify if the tree is binary search tree. Verification should pass*/
            prevNodeArray[0] = null;
            boolean result = TreeNode.isBst(root, prevNodeArray);
            printResult(result);
            if (!result)
                handleError();

            /*Swap the left and right child of the root*/
            swapChildren(root);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root, numElems);

            /*Verify if the tree is binary search tree. Verification should fail if numElems > 1
            since we have swapped the left and right child of the root*/
            prevNodeArray[0] = null;
            result = TreeNode.isBst(root, prevNodeArray);
            printResult(result);
            if (result && numElems > 1)
                handleError();

            System.out.println("_____________________________________________________");
        }

    
        System.out.println("Test passed");
    }

}











