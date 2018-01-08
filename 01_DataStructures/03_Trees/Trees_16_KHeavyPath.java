/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.lang.*;

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
    curNode: current node of the binary tree 
    aboveSum: sum of the nodes from root to the parent of current node
    k: the threshold path value for retaining the nodes
    Return value: length of longest path from root to leaf in which current 
            node is present
    */
    public static int kHeavyPath(TreeNode curNode, int aboveSum, int k) {
        if (curNode == null)
            return aboveSum;

        aboveSum += curNode.data;

        /*Find longest path in left sub-tree that contains current node*/
        int maxLeftPath = kHeavyPath(curNode.left, aboveSum, k);

        /*If longest left sub-tree path is below threshold, prune left sub-tree*/
        if (maxLeftPath < k)
            curNode.left = null;

        /*Find longest path in right sub-tree that contains current node*/
        int maxRightPath = kHeavyPath(curNode.right, aboveSum, k);

        /*If longest right sub-tree path is below threshold, prune right sub-tree*/
        if (maxRightPath < k)
            curNode.right = null;

        /*longestPath is the maximum of maxLeftPath and maxRightPath*/
        int longestPath = Math.max(maxLeftPath, maxRightPath);

        return longestPath;
    }


    
}



class Trees_16_KHeavyPath {

    public static final int MAX_NUM_NODES_IN_TREE = 10;
    public static final int MAX_NODE_VALUE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static int getMax(int a, int b) {
        return ((a > b) ? a: b);
    }

    /*Verify if all paths from the root to any leaf has a path sum >= K*/
    public static int verifyPath(TreeNode curNode, int topSum, int K) {

        if (curNode == null)
            return topSum;

        topSum += curNode.data;

        int maxLeftPath = verifyPath(curNode.left, topSum, K);
        int maxRightPath = verifyPath(curNode.right, topSum, K);

        /*Only paths whose path sum is >= K should be present in the tree*/
        if (maxLeftPath < K && maxRightPath < K)
            handleError();

        return getMax (maxLeftPath, maxRightPath);
    }


    public static void test()   {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];

        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of elements in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*Construct the tree from the array*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Original tree:");
            PrintTreeHelper.printTree(root, numElems);

            int K = 20;

            /*if kHeavyPath returns a value less than K, then there is no path from
            the root to any leaf whose path sum is >= K. So the root itself will get removed.
            In this case set the root to NULL*/
            if (TreeNode.kHeavyPath(root, 0, K) < K)
                root = null;

            System.out.println("After retaining only K-Heavy paths, K = " + K);
            PrintTreeHelper.printTree(root, numElems);

            verifyPath(root, 0, K);


            System.out.println("___________________________________________________");

        }

    }




    public static void main(String[] args) {
        test();
        System.out.println("Test passed \n");
    }

}











