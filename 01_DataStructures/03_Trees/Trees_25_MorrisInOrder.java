/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class TreeNode  {
    public static final int MAX_NUM_NODES_IN_TREE = 10;
    public static int[] morrisResultArray = new int[MAX_NUM_NODES_IN_TREE];
    public static int morrisIndex = 0;
    
    public int data;

    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode() {
        left = null;
        right = null;
        parent = null;
    }


    public static TreeNode constructBst (TreeNode parent, int[] values, int low, int high) {


        if (low > high)
            return null;

        int middle = (low + high) / 2;

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

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void printMorris(TreeNode curNode) {
        morrisResultArray[morrisIndex] = curNode.data;
        morrisIndex++;
        System.out.print(curNode.data + " ");
    }

    /*
    root: root node of the binary tree
    */
    public static void morrisInOrder(TreeNode root) {
        TreeNode curNode = root;

        while (curNode != null) {
            /*If curNode has no left subTree, then print/process the 
            curNode then move over to curNode.right and continue */
            if (curNode.left == null) {
                printMorris(curNode);
                curNode = curNode.right;
                continue;
            } 
    
            /*The curNode has a left sub-tree. First store the left  
            predecessor of curNode in leftPre. The left predecessor 
            can be found by traversing to the left of current node 
            and then repeatedly going to the right till we hit 
            a leaf node
            */
            TreeNode leftPre = curNode.left;
            while (leftPre.right != null && leftPre.right != curNode) 
                leftPre = leftPre.right;

            if (leftPre.right == null) {
                /*If left predecessor is null, it means we have
                not yet traversed the left sub-tree of current node. So 
                create a thread from leftPre.right to the current node
                to remember that on reaching leftPre the next in-order 
                node is current node. Then proceed to curNode.left
                */
                leftPre.right = curNode;
                curNode = curNode.left;
            } else {
                /*If left predecessor is not null, then it
                means that we have finished traversing left sub-tree 
                of current node. So remove thread from leftPre.right 
                to current node. The current node is the in-order node 
                to be processed. So process it and then move to right 
                sub-tree of curNode
                */
                leftPre.right = null;
                printMorris(curNode);
                curNode = curNode.right;
            }
        }
    }



    public static void recursiveInOrder(TreeNode node) {
        if (node == null)
            return;

        recursiveInOrder(node.left);

        if (morrisResultArray[morrisIndex] != node.data)
            handleError();

        ++morrisIndex;

        recursiveInOrder(node.right);
    }

}




class Trees_25_MorrisInOrder {

    public static final int MAX_NUM_NODES_IN_TREE = 10;


    public static void test01() {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];

        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {
            /*Construct the tree*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Printing tree:");
            PrintTreeHelper.printTree(root, numElems);

            /*Perform the morris in-order traversal. Store the node data
            in morrisResultArray during the traversal*/
            TreeNode.morrisIndex = 0;
            System.out.print("Morris In-order : ");
            TreeNode.morrisInOrder(root);

            /*Verify the morris in-order result stored in morrisResultArray
            by comparing it with the recursive in-order traversal*/
            TreeNode.morrisIndex = 0;
            TreeNode.recursiveInOrder(root);

            System.out.println("\n__________________________________________________");

        }

        System.out.println("Test passed");

        return ;
    }



    public static void main(String[] args) {
        test01();
        return;
    }

}
