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


    
    public static TreeNode constructBst(TreeNode parent, int[] values, int low, int high, TreeNode[] nodeArray) {
        if (low > high) {
            return null;
        }

        int middle = (low + high) / 2;

        TreeNode newNode = new TreeNode();

        newNode.data = values[middle];

        newNode.parent = parent;

        if (parent == null)
            newNode.depth = 0;
        else
            newNode.depth = parent.depth + 1;

        newNode.left = constructBst(newNode, values, low, middle - 1, nodeArray);

        newNode.right = constructBst(newNode, values, middle + 1, high, nodeArray);

        nodeArray[middle] = newNode;

        return newNode;

    }


    /*Find the Least Common Ancestor of a BINARY SEARCH TREE
    ancestor – the current ancestor node (root node is passed by the caller for first time)
    n1 and n2 are two nodes in the tree whose least common ancestor should be found
    Return value - least common ancestor node of n1 and n2
    */
    public static TreeNode bstLCA(TreeNode ancestor, TreeNode n1, TreeNode n2) {
        if (ancestor == null || n1 == null || n2 == null)
            return null;

        /*If the ancestor data is between n1 data and n2 data, then the
        ancestor is the least common ancestor*/
        if (n1.data <= ancestor.data && ancestor.data <= n2.data)
            return ancestor;

        if (n2.data <= ancestor.data && ancestor.data <= n1.data)
            return ancestor;

        /*If the ancestor data is greater than n1 data and n2 data, then
        the LCA will be in the left subtrie of the ancestor*/
        if (ancestor.data > n1.data && ancestor.data > n2.data)
            return bstLCA(ancestor.left, n1, n2);

        /*The ancestor data is less than n1 data and n2 data. So
        the LCA will be in the right subtrie of the ancestor*/
        return bstLCA(ancestor.right, n1, n2);
    }


    /*n: node in the binary tree
    Return value: depth of the node
    */
    public static int findDepth(TreeNode n) {
        int depth = 0;

        while (n.parent != null) {
            n = n.parent;
            ++depth;
        }
        return depth;
    }

    /*Find the Least Common Ancestor of a BINARY TREE
    n1 and n2 are two nodes in the tree
    Return value: least common ancestor node of n1 and n2
    */
    public static TreeNode lca(TreeNode n1, TreeNode n2) {

        int depth1 = findDepth(n1);
        int depth2 = findDepth(n2);

        /* If n1 is deeper than n2, then move n1 up the tree
        till the depth of n1 and n2 match
        */
        while (depth1 > depth2) {
            n1 = n1.parent;
            depth1--;
        }

        /* If n2 is deeper than n1, then move n2 up the tree
        till the depth of n1 and n2 match
        */
        while (depth2 > depth1) {
            n2 = n2.parent;
            depth2--;
        }

        /*Move n1 and n2 up the tree until a common node is found*/
        while (n1 != n2 ) {
            n1 = n1.parent;
            n2 = n2.parent;
        }

        return n1;
    }
    
}



class Trees_07_LCA {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        TreeNode[] nodeArray = new TreeNode[MAX_NUM_NODES_IN_TREE];

        /*numberArray contains data in ascending order from 0*/
        int i;
        for (i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {
            /*Construct the tree based on the data stored in the number array
            the nodes will also be stored in the nodeArray*/
            TreeNode root = TreeNode.constructBst(null, numberArray, 0, numElems - 1, nodeArray);

            System.out.println("Printing the tree:");
            PrintTreeHelper.printTree(root, numElems);

            /*Generate all pairs of nodes in the tree using the nodeArray*/
            for (i = 0; i < numElems; ++i) {
                for (int j = i+1; j < numElems; ++j) {

                    /*Find the Least Common Ancestor for the two nodes
                    using the algorithm for the BINARY TREE.
                    We have created a Binary Search Tree which is also a Binary Tree
                    So we can apply the BINARY TREE algo for a BST*/
                    TreeNode lca1 = TreeNode.lca(nodeArray[i], nodeArray[j]);

                    /*There is a different algo to find the LCA that is 
                    applicable only for BINARY SEARCH TREE. 
                    Since we have created a Binary Search Tree, use the algo for BST */
                    TreeNode lca2 = TreeNode.bstLCA(root, nodeArray[i], nodeArray[j]);

                    /*The two results should match*/
                    if (lca1 != lca2)
                        handleError();

                    System.out.println("Least Common Ancestor of " + nodeArray[i].data + " and "   
                        + nodeArray[j].data + " = " + lca1.data);
                }
            }

            System.out.println("_____________________________________________________");

        }

        
        System.out.println("Test passed");
    }

}











