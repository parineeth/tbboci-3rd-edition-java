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

        /*Handle Case-1, left child exists*/
        if (x.left != null) {
            y = x.left;
            while (y.right != null) {
                y = y.right;
            }
            return y;
        }

        /*Handle Case-2, left child doesn't exist*/
        y = x.parent;
        while (y != null && y.left == x) {
            x = y;
            y = y.parent;
        }

        return y;
    }





    /*
    x: any node in the binary search tree
    Return value: the node after node x
    */
    public static TreeNode getNext(TreeNode x) {
        TreeNode y;

        /*Handle Case-1: right child exists*/
        if (x.right != null) {
            y = x.right;
            while (y.left != null) {
                y = y.left;
            }
            return y;
        }

        /*Handle Case-2: right child doesn't exist*/
        y = x.parent;
        while (y != null && y.right == x) {
            x = y;
            y = y.parent;
        }

        return y;
    }


    
}



class Trees_11_PrevNext {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printNode (TreeNode n) {
        if (n != null)
            System.out.print(n.data);
        else
            System.out.print("null");
    }

    /*Verify the previous and next functions*/
    public static void verifyPreviousNext(TreeNode curNode, int numElems) {

        if (curNode == null)
            return;

        /*Get the previous and next node for the current node*/
        TreeNode prevNode = TreeNode.getPrevious(curNode);
        TreeNode nextNode = TreeNode.getNext(curNode);

        System.out.print("Cur node = ");
        printNode(curNode);

        System.out.print(": Prev node = ");
        printNode(prevNode);

        System.out.print(", Next node = ");
        printNode(nextNode);
        System.out.println();

        if (curNode.data == 0) {
            /*If curNode has data = 0, it is the first node
            The previous node should be NULL*/
            if (prevNode != null)
                handleError();
        } else {
            /*curNode->data should be prevNode->data + 1*/
            if (prevNode.data + 1 != curNode.data)
                handleError();
        }

        if (curNode.data == numElems - 1) {
            /*If curNode has data = numElems - 1, it is the last node
            The next node should be NULL*/
            if (nextNode != null) 
                handleError();
        } else {
            /*nextNode->data should be curNode->data + 1 */
            if (curNode.data + 1 != nextNode.data)
                handleError();
        }

        verifyPreviousNext(curNode.left, numElems);
        verifyPreviousNext(curNode.right, numElems);

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

            /*verify the getPrevious and getNext functions*/
            verifyPreviousNext(root1, numElems);

            System.out.println("____________________________________________________");
        }
        
        System.out.println("Test passed");
    }

}











