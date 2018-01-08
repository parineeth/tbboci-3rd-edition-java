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


    /*
    preOrder: array containing the data of nodes of the binary tree in pre-order
    inOrder: array containing the data of nodes of the binary tree in in-order
    inStart: starting index of current region in the inOrder array 
    inEnd: ending index of current region in the inOrder array
    prePos: the index in the pre-order array
    Return value: newly created binary tree node
    */
    public static TreeNode constructTree(int[] preOrder, int[] inOrder, int inStart, 
                    int inEnd, Int prePos) {
        /*Termination condition for recursion*/
        if (inStart > inEnd)
            return null;

        /* Assign the pivot from pre-order array*/
        int pivot = preOrder[prePos.value];

        /*Find pivot in in-order array*/
        int inLocation;
        for (inLocation = inStart; inLocation <= inEnd; ++inLocation) {
            if (inOrder[inLocation] == pivot) {
                break;
            }
        }

        /*Create the new node and assign the pivot data*/
        TreeNode newNode = new TreeNode();
        newNode.data = pivot;

        /*Advance to the next member in the pre-order array*/
        prePos.value++;

        /*First recursively construct the left sub-tree */
        newNode.left = constructTree(preOrder, inOrder, inStart, 
                    inLocation - 1, prePos);

        /*Recursively construct the right sub-tree*/
        newNode.right = constructTree(preOrder, inOrder, inLocation + 1, 
                    inEnd, prePos);

        return newNode;
    }

    
}



class Trees_18_BstFromInorder {

    public static final int MAX_NUM_NODES_IN_TREE = 10;
    public static final int MAX_NODE_VALUE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printArray(int[] array, int length) {
        for (int i = 0; i < length; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void traversePreOrder(TreeNode curNode, int[] inArray, Int pos) {
        if (curNode == null)
            return;

        inArray[pos.value] = curNode.data;
        pos.value++;

        traversePreOrder(curNode.left, inArray, pos);
        traversePreOrder(curNode.right, inArray, pos);
    }


    public static void traverseInOrder(TreeNode curNode, int[] inArray, Int pos) {
        if (curNode == null)
            return;

        traverseInOrder(curNode.left, inArray, pos);

        inArray[pos.value] = curNode.data;
        pos.value++;

        traverseInOrder(curNode.right, inArray, pos);
    }


    public static void verifyTrees(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return;

        if ( (n1 == null && n2 != null) || (n1 != null && n2 == null))
            handleError();

        if (n1.data != n2.data)
            handleError();

        verifyTrees(n1.left, n2.left);
        verifyTrees(n1.right, n2.right);
    }


    public static void test_01() {
        int[] numberArray = new int [MAX_NUM_NODES_IN_TREE];
        int[] preOrder = new int[MAX_NUM_NODES_IN_TREE]; 
        int[] inOrder = new int[MAX_NUM_NODES_IN_TREE];
        Int pos = new Int();
        Int prePos = new Int();

        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of elements in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {
            /*Construct the original tree*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            /*Traverse the original tree in pre-order and store the result in array preOrder*/
            pos.value = 0;
            traversePreOrder(root, preOrder, pos);

            System.out.print("Pre-order is : ");
            printArray(preOrder, numElems);

            /*Traverse the original tree in in-order and store the result in array inOrder*/
            pos.value = 0;
            traverseInOrder(root, inOrder, pos);

            System.out.print("In-order is : ");
            printArray(inOrder, numElems);

            /*Re-construct the tree using the preOrder and inOrder arrays*/
            int inStart = 0;
            int inEnd = numElems - 1;
            prePos.value = 0;
            TreeNode assembledRoot = TreeNode.constructTree(preOrder, inOrder, inStart, inEnd, prePos);

            System.out.print("Printing tree:\n");
            PrintTreeHelper.printTree(root, numElems);

            /*Verify if the original tree and reconstructed tree match*/
            verifyTrees(root, assembledRoot);

            System.out.println("___________________________________________________________________");

        }

    }   



    public static void main(String[] args) {
        test_01();
        System.out.println("Test passed successfully");
    }

}











