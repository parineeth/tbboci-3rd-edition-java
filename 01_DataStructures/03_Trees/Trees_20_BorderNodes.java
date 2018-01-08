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


    public static void printData(int data)  {
        System.out.print(data + " ");
    }

    /*Print the left border*/
    public static void printLeftBorder(TreeNode curNode) {
        /*Keep traversing left and print the non-leaf nodes*/
        while (curNode != null) {
            /*If node has a left or right child, then it is a non-leaf node*/
            if (curNode.left != null || curNode.right != null)
                printData( curNode.data);

            curNode = curNode.left;
        }
    }

    /*Print the leaf nodes of the tree*/
    public static void printLeafNodes(TreeNode curNode) {
        if (curNode == null)
            return;

        if (curNode.left == null && curNode.right == null)
            printData(curNode.data);

        printLeafNodes(curNode.left);
        printLeafNodes(curNode.right);
    }

    /* Print the right border nodes of the tree*/
    public static void printRightBorder(TreeNode curNode)   {
        if (curNode == null)
            return;

        /*First reach the deepest right node and then start printing bottom-up*/
        printRightBorder(curNode.right);

        /*If the node has a left or right child, then it is a non-leaf node. 
        So print it*/
        if (curNode.left != null || curNode.right != null)
            printData(curNode.data);

    }


    /*Main function that prints the border nodes of a binary tree*/
    public static void printBorderNodes(TreeNode root) {
        if (root == null)
            return;

        printLeftBorder(root);
        printLeafNodes(root);
        printRightBorder(root);
    }



    
}



class Trees_20_BorderNodes {

    public static final int MAX_NUM_NODES_IN_TREE = 10;
    

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void main(String[] args) {
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

            /*Print the border nodes*/
            System.out.print("The border nodes are : ");

            TreeNode.printBorderNodes(root1);

            System.out.println("\n__________________________________________");

        }

        
        System.out.println("Test passed");
    }

}











