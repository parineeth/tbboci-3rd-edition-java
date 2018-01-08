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
        left = null;
        right = null;
        parent = null;
    }

    public TreeNode() {
        left = null;
        right = null;
        parent = null;
    }


    public static TreeNode constructBst (TreeNode parent, int[] values, int low, int high) {
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


    public static void printData(int data) {
        System.out.print(data + " ");
    }

    /*
    curNode: current BST node being processed
    prevNodeArray: prevNodeArray[0] has node that is previous to curNode in list
    listHeadArray: head of result linked list will be passed in listHeadArray[0]
    Returns: 0 on success
    */
    public static int bstToList(TreeNode curNode, TreeNode[] prevNodeArray, 
                    TreeNode[] listHeadArray) {
        if (curNode == null)
            return 0;

        /*In-Order Traversal of the BST*/

        /*Convert the left sub-tree of node to linked list*/
        bstToList(curNode.left, prevNodeArray, listHeadArray);

        /*Link the previous node and the current node*/
        TreeNode prevNode = prevNodeArray[0];
        curNode.left = prevNode;

        if (prevNode != null) {
            prevNode.right = curNode;
        } else {
            /*Since previous node is null, this is the first 
            node of the list. So make head refer to it */
            listHeadArray[0] = curNode;
        }

        /*Make the current node the previous node*/
        prevNodeArray[0] = curNode;

        /*Convert the right sub-tree of node to linked list*/
        bstToList(curNode.right, prevNodeArray, listHeadArray);

        return 0; /*return success*/
    }


    
}



class Trees_10_BstToList {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printList(TreeNode head) {
        TreeNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.right;
        }
        System.out.println();
    }


    public static void verifyList(TreeNode head) {

        if (head == null)
            return;

        /*Traverse the doubly linked list from left to right*/
        TreeNode prevNode = head;
        TreeNode iterNode = head.right;
        TreeNode lastNode = head;
        while (iterNode != null) {
            /*The data should be arranged in increasing order*/
            if (prevNode.data >= iterNode.data) {
                handleError();
            }

            /*Find the last node in the doubly linked list*/
            if (iterNode.right == null)
                lastNode = iterNode;

            prevNode = iterNode;
            iterNode = iterNode.right;
        }

        /*Traverse the doubly linked list from right to left*/
        prevNode = lastNode;
        iterNode = lastNode.left;
        while (iterNode != null) {
            /*The data should be arranged in decreasing order*/
            if (prevNode.data <= iterNode.data) {
                handleError();
            }

            prevNode = iterNode;
            iterNode = iterNode.left;
        }
    }


    public static void main(String[] args) {
        TreeNode[] headArray = new TreeNode[1];
        TreeNode[] prevArray = new TreeNode[1];
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];

        /*numberArray contains data in ascending order from 0*/
        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*construct the Binary Search Tree*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root, numElems);

            /*Convert the Binary Search Tree to Doubly Linked List*/
            prevArray[0] = null;
            headArray[0] = null;
            TreeNode.bstToList(root, prevArray, headArray);

            System.out.println("Printing the Doubly Linked List:");
            printList(headArray[0]);

            /*Verify the Doubly Linked List*/
            verifyList(headArray[0]);

            System.out.println("___________________________________________________");
        }

        
        System.out.println("Test passed");
    }

}











