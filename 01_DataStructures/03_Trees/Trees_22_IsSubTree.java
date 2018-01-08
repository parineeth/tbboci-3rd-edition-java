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

    public TreeNode() {
        data = 0;
        depth = 0;
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

        if (parent != null)
            newNode.depth = parent.depth + 1;
        else
            newNode.depth = 0;

        /*Construct the left sub-tree using values[low] to values[middle-1]*/
        newNode.left = TreeNode.constructBst(newNode, values, low, middle - 1);

        /*Construct the right sub-tree using values[middle+1] to values[high]*/
        newNode.right = TreeNode.constructBst(newNode, values, middle + 1, high);

        return newNode;
    }

    /*Helper function that compares the nodes 
    n1: node belonging to the main tree
    n2: node belonging to sub-tree being searched
    Return value: true if sub-tree of n1 matches sub-tree of n2. false otherwise
    */
    public static boolean compareNodes(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return true;

        if (n1 == null || n2 == null)
            return false;

        if (n1.data != n2.data)
            return false;

        return (compareNodes(n1.left, n2.left) 
            && compareNodes(n1.right, n2.right));
    }



    /*Main function that checks if tree under root2 is a subtree of tree under root1
    root1: main tree node
    root2: root of the sub-tree being searched
    Return value: true if tree under root2 is present in tree under root1
    */
    public static boolean isSubTree(TreeNode root1, TreeNode root2) {
        /*empty tree is treated as a sub-tree of the main tree*/
        if(root2 == null)
            return true;

        if (root1 == null)
            return false;

        if (compareNodes(root1, root2))
            return true;

        /*Check if tree of root2 is present in left sub-tree of root1 
        or right sub-tree of root1*/
        return (isSubTree(root1.left, root2) 
            || isSubTree(root1.right, root2));

    } 


}





class Trees_22_IsSubTree {

    public static final int MAX_NUM_NODES_IN_TREE = 10;
    public static final int INVALID_VALUE = 100;

    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }


    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];

        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*Construct the subTree to be searched*/
            TreeNode subTreeRoot = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            /*Create the main tree node*/
            TreeNode mainTreeRoot = new TreeNode();
            mainTreeRoot.data =  INVALID_VALUE;
            mainTreeRoot.left = null;
            mainTreeRoot.right = null;
        
            /*main tree root has an invalid value and won't match with any of the 
            sub-tree nodes. So checking for sub-tree should fail*/
            if (TreeNode.isSubTree(mainTreeRoot, subTreeRoot))
                handleError();
        
            /*Connect the sub-tree root to the left of the main-tree. We should
            now find the sub-tree in the main tree*/
            mainTreeRoot.left = subTreeRoot; 
            mainTreeRoot.right = null;
            if (!TreeNode.isSubTree(mainTreeRoot, subTreeRoot))
                handleError();

            /*Connect the sub-tree root to the right of the main-tree. We should
            now find the sub-tree in the main tree*/
            mainTreeRoot.left = null; 
            mainTreeRoot.right = subTreeRoot;
            if (!TreeNode.isSubTree(mainTreeRoot, subTreeRoot))
                handleError();

            /*We should find the sub-tree in the sub-tree itself*/
            if (!TreeNode.isSubTree(subTreeRoot, subTreeRoot))
                handleError();

            /*If sub-tree is null, then we should still treat it as a sub-tree of main tree*/
            if (!TreeNode.isSubTree(mainTreeRoot, null))
                handleError();

            /*If main-tree is null, then we shouldn't find the sub-tree*/
            if (TreeNode.isSubTree(null, subTreeRoot))
                handleError();

        }

        System.out.println("Test passed");

    }
}

