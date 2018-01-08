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
root: the root node of the binary search tree 
k: indicates the kth largest value. k >= 1
Return value: kth largest node in the binary search tree
*/
public static TreeNode findKthLargest(TreeNode root, int k) {
    if (root == null || k < 1)
        return null;

    /*Find the node with the maximum value*/
    TreeNode n = root;
    while (n.right != null)
        n = n.right;

    /*Find the k-1 previous nodes */
    for (int i = 1; i < k; ++i) {
        n = getPrevious(n);
        if (n == null) {
            return null;
        }
    }

    return n;
}


    
}



class Trees_12_KthLargest {

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
            
            /*construct the Binary Search Tree*/
            TreeNode root1 = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root1, numElems);

            TreeNode curNode;
            for (int k = 1; k <= numElems; ++k) {
                /*Find the kth largest. k can take values from 1 to numElems*/
                curNode = TreeNode.findKthLargest(root1, k);

                System.out.println("K =  " + k + ", Kth Largest = " + curNode.data);

                /*Verify the result returned*/
                if (curNode.data != numElems - k )
                    handleError();
            }

            /*If we pass k value that is > than numElems, we should get NULL*/
            curNode = TreeNode.findKthLargest(root1, numElems + 1);
            if (curNode != null)
                handleError();

            System.out.println("_________________________________________________________");
        }

        
        System.out.println("Test passed");
    }

}











