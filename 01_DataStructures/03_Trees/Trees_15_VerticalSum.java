/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Int {
    public int value;

    public Int() {
    }
    
}


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

    public static TreeNode constructBst (TreeNode parent, int[] values, int low, int high) {
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



    /*Helper function to find the vertical sum
    curNode: current node being processed in the binary tree
    col: column of the current node
    sumArray: array containing the sum of nodes in each column
    */
    public static void processSum(TreeNode curNode, int col, int[] sumArray) {
        if (curNode == null)
            return;

        sumArray[col] += curNode.data;

        /*column number of left child is col - 1*/
        processSum(curNode.left, col - 1, sumArray);

        /*column number of right child is col+1*/
        processSum(curNode.right, col +  1, sumArray);
    }

    /*Main function to find the vertical sum
    root: root of the binary tree 
    Return value: array which contains the vertical sum 
    */
    public static int[] computeVerticalSum(TreeNode root) {
        int numLeftCols = 0, numRightCols = 0;
        int[] sumArray = null;

        if (root == null)
            return null;

        /*Compute the number of left columns*/
        TreeNode curNode = root.left;
        while (curNode != null) {
            ++numLeftCols;
            curNode = curNode.left;
        }

        /*Compute the number of right columns*/
        curNode = root.right;
        while (curNode != null) {
            ++numRightCols;
            curNode = curNode.right;
        }

        int totalNumCols = numLeftCols+ numRightCols + 1;

        /*Dynamically create the array for storing the column sum*/
        sumArray = new int[totalNumCols];

        int rootCol = numLeftCols;

        /*Compute the vertical sum starting with the root*/
        processSum(root, rootCol, sumArray);

        return sumArray;
    }



    
}



class Trees_15_VerticalSum {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        int[] sumArray;

        int i;
        for (i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*construct the tree*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            /*Compute the vertical sum*/
            sumArray = TreeNode.computeVerticalSum(root);

            System.out.print("Vertical sum is:");

            for (i = 0; i < sumArray.length; ++i) {
                System.out.print(sumArray[i] + " ");
            }

            System.out.println("\n___________________________________________________");

        }

        System.out.println("Test passed");
    }

}











