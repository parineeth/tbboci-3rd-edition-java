/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Int {
    public int value;

    public Int() {
        value = 0;
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


    public static int getMax(int a, int b) {
        return ((a > b) ? a: b);
    }


    /*
    curNode: current node of the binary tree 
    height: height of current node is returned here. Leaf node has a height of 1
    diameter: diameter of the tree is returned here
    */
    public static void findDiameter(TreeNode curNode, Int height, Int diameter) {
        Int leftHeight = new Int();
        Int rightHeight = new Int();

        if (curNode == null) {
            height.value = 0;
            return;
        }

        /*Find the height of the left sub-tree*/
        findDiameter(curNode.left, leftHeight, diameter);

        /*Find the height of the right sub-tree*/
        findDiameter(curNode.right, rightHeight, diameter);

        /*Calculate height of curNode*/
        height.value = 1 + getMax(leftHeight.value, rightHeight.value);

        /*Calculate longest path between any two leafs passing through curNode*/
        int longestPath = leftHeight.value + rightHeight.value + 1;

        /*If the length of longest path through curNode is greater than
        the current diameter, then assign it to the diameter*/
        if (longestPath > diameter.value)
            diameter.value = longestPath;
    }


    
}



class Trees_17_Diameter {

    public static final int MAX_NUM_NODES_IN_TREE = 10;
    public static final int MAX_NODE_VALUE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void test()   {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        Int height = new Int();
        Int diameter = new Int();

        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of elements in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {
            /*Construct the tree from the array*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root, numElems);

            /*Find the diameter*/
            diameter.value = 0;
            TreeNode.findDiameter(root, height, diameter);

            System.out.println("Height = " +  height.value + ", Diameter = " + diameter.value);

            System.out.println("___________________________________________________");

        }

    }




    public static void main(String[] args) {
        test();
        System.out.println("Test passed");
    }

}











