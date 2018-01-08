/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class TreeNode {
    
    public int data;
    public int depth;
    public int sum;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode (int val) {
        data = val;
        depth = 0;
        sum = 0;
        left = null;
        right = null;
        parent = null;
    }

    public TreeNode() {
        data = 0;
        depth = 0;
        sum = 0;
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
    curNode: current node of the binary search tree 
    bstSumArray: has single element that stores the sum of nodes greater 
            than current node
    */
    public static void computeSumOfGreaterNodes(TreeNode curNode, 
                        int[] bstSumArray) {
        if (curNode == null)
            return;

        /*Since greater elements are in the right sub-tree, first process the
        right sub-tree*/
        computeSumOfGreaterNodes(curNode.right, bstSumArray);

        /*Assign the sum of the greater nodes*/
        curNode.sum = bstSumArray[0];

        /*Add the current node's data to the sum*/
        bstSumArray[0] += curNode.data;

        /*Process the left sub-tree*/
        computeSumOfGreaterNodes(curNode.left, bstSumArray);
    }
    
}



class Trees_14_ComputeGreaterSum {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static void verifySum (TreeNode curNode, int[] sumArray, int[] indexArray) {

        if (curNode == null)
            return;

        verifySum(curNode.left, sumArray, indexArray);

        System.out.println("Node data = " + curNode.data + ", Greater Sum = " + curNode.sum);

        int currentIndex = indexArray[0];
        if (sumArray[currentIndex] != curNode.sum)
            handleError();

        indexArray[0] += 1;

        verifySum(curNode.right, sumArray, indexArray);
    }



    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE]; 
        int[] sumArray = new int[MAX_NUM_NODES_IN_TREE];
        int[] bstSumArray = new int[1];
        int[] indexArray = new int[1];

        /*numberArray contains numbers from 0 in ascending order*/
        int i;
        for (i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*construct the tree using the numberArray*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root, numElems);

            /*For each node, compute the sum of greater nodes and store in the node itself */
            bstSumArray[0] = 0;
            TreeNode.computeSumOfGreaterNodes(root, bstSumArray);

            /*Compute the greater sum of the number array and store it in sumArray*/
            int totalSoFar = 0;
            for (i = numElems - 1; i >= 0; --i) {
                sumArray[i] = totalSoFar;
                totalSoFar += numberArray[i] ;
            }

            /*Verify the greater sum stored in the nodes of the tree with the sumArray*/
            indexArray[0] = 0;
            verifySum(root, sumArray, indexArray);

            System.out.println("___________________________________________________");

        }
    

        
        System.out.println("Test passed");
    }

}











