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


    /* n: root of the binary search tree
    Returns - The maximum element of the binary search tree
    */
    public static TreeNode getMax(TreeNode n) {
        if (n == null)
            return null;

        /*The rightmost node has the maximum value*/
        while (n.right != null)
            n = n.right;

        return n;
    }



    /*
    x: any node in the binary search tree 
    Return value: the node previous to node x
    */
    public static TreeNode getPrevious(TreeNode x) {
        TreeNode y;

        /*Handle Case - 1*/
        if (x.left != null) {
            y = x.left;
            while (y.right != null) {
                y = y.right;
            }
            return y;
        }

        /*Handle Case - 2*/
        y = x.parent;
        while (y != null && y.left == x) {
            x = y;
            y = y.parent;
        }

        return y;
    }





    /*
    x: any node in the binary search tree
    Return value: the node next to node x
    */
    public static TreeNode getNext(TreeNode x) {
        TreeNode y;

        /*Handle Case - 1*/
        if (x.right != null) {
            y = x.right;
            while (y.left != null) {
                y = y.left;
            }
            return y;
        }

        /*Handle Case - 2*/
        y = x.parent;
        while (y != null && y.right == x) {
            x = y;
            y = y.parent;
        }

        return y;
    }



    /*
    root: root of the binary search tree
    k: sum of two nodes should equal k
    Return value: Array with the 2 tree nodes which sum up to k if they  
            exist, null otherwise
    */
    public static TreeNode[] findPairSumToK(TreeNode root, int k) {
        TreeNode[] result = null;

        if (root == null)
            return null;

        /*Store the leftmost node in n1*/
        TreeNode n1 = root;
        while (n1.left != null)
            n1 = n1.left;

        /*Store the right most node in n2*/
        TreeNode n2 = root;
        while (n2.right != null)
            n2 = n2.right;

        /*Process the tree by picking one node from left and one node from right*/
        while (n1 != n2) {
            int sum = n1.data + n2.data;

            /*check if the left node and right node sum to k*/
            if (sum == k) {
                result = new TreeNode[2];
                result[0] = n1;
                result[1] = n2;
                return  result;
            }

            if (sum < k) {
                /*Pick the next higher value node from the left*/
                n1 = getNext(n1);
            } else {
                /*Pick the next smaller value from the right*/
                n2 = getPrevious(n2);
            }
        }

        return null;
    }



    
}



class Trees_13_PairSumToK {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*Returns the two value that sum to K in the numberArray.
    If there are no pair of values that sum to K, return value will be null*/
    public static int[] findArraySumToK(int[] numberArray, int numElems, int K) {
        int[] result = null;

        for (int i = 0; i < numElems - 1; ++i) {
            for (int j = numElems - 1; j > i; --j) {

                if (numberArray[i] + numberArray[j] == K) {
                    result = new int[2];
                    result[0] = numberArray[i];
                    result[1] = numberArray[j];
                    return result;
                }
            }
        }
        return null;
    }

    public static void checkCondition(boolean condition) {
        if (!condition)
            handleError();
    }


    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        TreeNode[] nodeResult;
        int[] expectedResult;

        /*numberArray contains numbers from 0 in ascending order*/
        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*construct the Binary Search Tree using the numberArray*/
            TreeNode root1 = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root1, numElems);

            /*Test for different values of K*/
            for (int K = 0; K <= (2 * numElems); ++K) {

                /*find the pair of nodes that sum to K in the tree*/
                nodeResult = TreeNode.findPairSumToK(root1, K);
                
                TreeNode n1, n2;
                if (nodeResult == null) {
                    n1 = null;
                    n2 = null;
                } else {
                    n1 = nodeResult[0];
                    n2 = nodeResult[1];
                }

                /*find the two numbers that add upto K in the number array*/
                expectedResult = findArraySumToK(numberArray, numElems, K);
                
                int num1, num2;
                if (expectedResult == null) {
                    num1 = -1;
                    num2 = -1;
                } else {
                    num1 = expectedResult[0];
                    num2 = expectedResult[1];
                }

                /*The result from the tree and the array should match*/
                if (n1 == null || num1 == -1) {
                    checkCondition(n1 == null);
                    checkCondition(n2 == null);
                    checkCondition(num1 == -1);
                    checkCondition(num2 == -1);
                } else {

                    checkCondition(n1.data == num1);
                    checkCondition(n2.data == num2);

                    System.out.println(n1.data + " + " + n2.data + " = " + K);
                }

            }

            System.out.println("__________________________________________");

        }

        
        System.out.println("Test passed");
    }

}











