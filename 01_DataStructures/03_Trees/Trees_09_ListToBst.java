/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class TreeNode {
    
    public int data;
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
        data = 0;
        left = null;
        right = null;
        parent = null;
    }


    /*curNode: current node 
    prevNodeArray: in-order predecessor of curNode is present in prevNodeArray[0]
    Return value: true if the tree is a binary search tree, false otherwise
    */
    public static boolean isBst(TreeNode curNode, TreeNode[] prevNodeArray) {
        if (curNode == null)
            return true;

        if (!isBst(curNode.left, prevNodeArray)) /*Check if the left sub-tree is a BST*/
            return false;

        /*If data in curNode is less than or equal to previous node then it is not a BST*/
        TreeNode prevNode = prevNodeArray[0];
        if (prevNode != null && curNode.data <= prevNode.data)
            return false;

        /*Update previous node to current node*/
        prevNodeArray[0] = curNode;

        return isBst(curNode.right, prevNodeArray); /*Check if the right sub-tree is a BST*/
    }


    /*
    nodeArray: helper array of size 1 that contains a node. 
            this array is used for traversing the doubly linked list
    start: index of node in linked list at beginning of region being operated on
    end: index of node in linked list at end of region being operated on
    */
    public static TreeNode constructBst (TreeNode[] nodeArray, int start, int end) {
        if (start > end)
            return null;

        int middle = (start + end) / 2;

        /*Recursively construct the left subtree  using the nodes before the 
        middle node and get the root of the left sub-tree*/
        TreeNode leftChild = constructBst(nodeArray, start, middle - 1);

        /*nodeArray[0] will now be refering to the middle node*/
        TreeNode middleNode = nodeArray[0];

        /*Connect the left sub-tree to the middle node*/
        middleNode.left = leftChild;

        /*Advance to the next node after the middle node*/
        nodeArray[0] = middleNode.right;

        /*Recursively construct the right subtree using the nodes after the 
        middle node and connect the root of right subtree to middle node*/
        middleNode.right = constructBst(nodeArray, middle + 1, end);

        return middleNode;
    }

    
}



class Trees_09_ListToBst {

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
        System.out.println("\n");
    }

    public static void printResult(boolean result) {
        if (result) 
            System.out.println("The tree is a Binary Search Tree"); 
        else 
            System.out.println("The tree is NOT a Binary Search Tree");
        
    }

    public static void main(String[] args) {
        TreeNode[] nodeArray = new TreeNode[1];
        TreeNode[] prevNodeArray = new TreeNode[1];

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {
            
            /*Construct a doubly linked list*/
            TreeNode prev = null;
            TreeNode head = null;
            for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
                TreeNode newNode = new TreeNode();
                newNode.left = prev;
                newNode.right = null;
                newNode.data = i;

                if (prev != null)
                    prev.right = newNode;

                if (i == 0)
                    head = newNode;

                prev = newNode;
            }

            System.out.println("Printing the Doubly Linked List:");
            printList(head);

            /*Convert the doubly linked list to a Binary Search Tree*/
            nodeArray[0] = head;
            TreeNode root = TreeNode.constructBst(nodeArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root, numElems);

            /*Verify if the tree formed is a Binary Search Tree*/
            prevNodeArray[0] = null;
            boolean result = TreeNode.isBst(root, prevNodeArray);
            printResult(result);
            if(!result)
                handleError();

            System.out.println("_____________________________________________________________");

        }

    
        System.out.println("Test passed");
    }

}











