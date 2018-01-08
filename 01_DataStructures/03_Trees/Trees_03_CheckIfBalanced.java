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



    public static TreeNode constructUnbalancedTree(int numNodes) {
        TreeNode root = null, prevNode = null;

        for (int i = 0; i < numNodes; ++i) {
            TreeNode newNode = new TreeNode();
            newNode.data = i;

            if (root == null)
                root = newNode;

            if (prevNode != null) {
                prevNode.right = newNode;
            }
            prevNode = newNode;
        }
    
        return root;
    }


    /*curNode: cur node of the binary tree being checked
    height: height of the curNode is returned here
    Return value: true if the tree is balanced, false otherwise
    */
    public static boolean isBalanced(TreeNode curNode, Int height) {
        Int leftHeight = new Int();
        Int rightHeight = new Int();

        if (curNode == null) {
            height.value = 0;
            return true;
        }


        boolean isLeftBalanced = isBalanced(curNode.left, leftHeight);
        boolean isRightBalanced = isBalanced(curNode.right, rightHeight);

        if (!isLeftBalanced || !isRightBalanced)
            return false;

        /*If the difference between height of left subtree and height of
        right subtree is more than 1, then the tree is unbalanced*/
        if (Math.abs(leftHeight.value - rightHeight.value) > 1)
            return false;

        /*To get the height of the current node, we find the maximum of the  
        left subtree height and the right subtree height and add 1 to it*/
        height.value = Math.max(leftHeight.value, rightHeight.value) + 1;

        return true;    
    }

}


class Trees_03_CheckIfBalanced {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void printUnbalancedTree(TreeNode root) {
    
        TreeNode curNode = root;
        int count = 0;
        while (curNode != null) {
            for (int i = 0; i < count; ++i) {
                System.out.print("\t");
            }
        
            System.out.println(curNode.data);

            ++count;
            curNode = curNode.right;
        }

    }


    public static void printResult(boolean result) {
        if (result) 
            System.out.println("The tree is balanced\n\n"); 
        else {
            System.out.println("The tree is NOT balanced\n");
        }
    }



    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        Int height = new Int();

        /*numberArray contains data in ascending order from 0 to MAX_NUM_NODES_IN_TREE*/
        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in a balanced tree*/
        boolean result;
        int numElems;
        TreeNode root;
        for (numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*Construct a balanced tree based on the data stored in the number array*/
            root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree");
            PrintTreeHelper.printTree(root, numElems);

            result = TreeNode.isBalanced(root, height);

            printResult(result);

            if (result != true)
                handleError();

            System.out.println("_____________________________________________________");

        }

        /*Test for different number of nodes in a unbalanced tree*/
        for (numElems = 3; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*Construct an unbalanced tree */
            root = TreeNode.constructUnbalancedTree(numElems);

            printUnbalancedTree(root);

            result = TreeNode.isBalanced(root, height);

            printResult(result);

            if (result != false)
                handleError();

            System.out.println("_____________________________________________________");
        }


        System.out.println("Test passed");

    }
}
