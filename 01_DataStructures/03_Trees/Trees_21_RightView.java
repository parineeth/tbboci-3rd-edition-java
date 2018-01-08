/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Int {
    public int value;
}

class TreeNode {

    public int data;

    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;


    public static void printData(int data) {
        System.out.print(data + " ");
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




    /*curNode: current node in the tree being processed
    curLevel: the depth of the current node. Root node of tree has a level of 0
    maxLevel: Maximum level seen in the tree so far. We pass -1 for the root node*/
    public static void printRightView(TreeNode curNode, int curLevel, 
                    Int maxLevel) {
        if (curNode == null)
            return;

        /*If the current node is the first node we have observed in current level,
        then print it*/
        if (maxLevel.value < curLevel) {
            printData(curNode.data);
            maxLevel.value = curLevel;
        }   

        /*First expand the right child and then the left child*/    
        printRightView(curNode.right, curLevel + 1, maxLevel);
        printRightView(curNode.left, curLevel + 1, maxLevel);
    }

}



class Trees_21_RightView {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void main(String[] args) {
        Int maxLevel = new Int();
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];

        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*construct the tree using the numberArray*/
            TreeNode root1 = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root1, numElems);

            /*Print the right view nodes*/
            maxLevel.value = -1;
            System.out.print("The TreeNode right view nodes are : ");
            TreeNode.printRightView(root1, 0, maxLevel);

            System.out.println("\n__________________________________________\n");

        }


        System.out.println("Test passed");

    }

}
