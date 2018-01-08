/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class TreeNode {
    
    public int data;
    public int depth;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode (int val) {
        data = val;
        depth = 0;
        left = null;
        right = null;
        parent = null;
    }

    public TreeNode() {
        data = 0;
        depth = 0;
        left = null;
        right = null;
        parent = null;
    }


    /* 
    parent: parent of the BST node currently being constructed
    values: sorted array to be converted into BST
    low, high: lower and upper indexes of the array region being operated upon
    Return value: BST node created that corresponds to values[(low+high)/2] 
    */
    public static TreeNode constructBst (TreeNode parent, int[] values, 
                    int low, int high) {
        int middle = (low + high) / 2;

        if (low > high)
            return null;

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


    /*curNode: current node 
    prevNodeArray: in-order predecessor of curNode is present in prevNodeArray[0]
    Return value: true if the tree is a binary search tree, false otherwise
    */
    public static boolean isBst(TreeNode curNode, TreeNode[] prevNodeArray) {
        if (curNode == null)
            return true;

        if (!isBst(curNode.left, prevNodeArray)) /*Check if the left sub-tree is a BST*/
            return false;

        /*If data in curNode is less than or equal to previous node then it is not a BST*/
        TreeNode prevNode = prevNodeArray[0];
        if (prevNode != null && curNode.data <= prevNode.data)
            return false;

        /*Update previous node to current node*/
        prevNodeArray[0] = curNode;

        return isBst(curNode.right, prevNodeArray); /*Check if the right sub-tree is a BST*/
    }
    
}



class Trees_08_ArrayToBst {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printResult(boolean result) {
        if (result) 
            System.out.println("The tree is a Binary Search Tree"); 
        else 
            System.out.println("The tree is NOT a Binary Search Tree");
        
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

            System.out.println("Printing tree: ");
            PrintTreeHelper.printTree(root, numElems);

            /*Verify if the tree is binary search tree. Verification should pass*/
            prevNodeArray[0] = null;
            boolean result = TreeNode.isBst(root, prevNodeArray);
            printResult(result);
            if (!result)
                handleError();


            System.out.println("_____________________________________________________");
        }

    
        System.out.println("Test passed");
    }


}











