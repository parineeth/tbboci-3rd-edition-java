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
        left = null;
        right = null;
        parent = null;
    }

    /*Recursively Construct a Binary Search Tree from the input array sorted in ascending order*/
    public static TreeNode  constructBst (TreeNode parent, int[] values, int low, int high) {


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

    /*
    curNode: current node of the tree whose mirror image should be computed
    */
    public static void computeMirrorImage(TreeNode curNode) {
        if (curNode != null) {
            /*Swap the left child and right child of the current node*/
            TreeNode tempNode = curNode.left;
            curNode.left = curNode.right;
            curNode.right = tempNode;

            /*Recursively compute the mirror image */
            computeMirrorImage(curNode.left);
            computeMirrorImage(curNode.right);
        }
    }   


    public static boolean compareNodes ( TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)  /*If both the nodes are null */
            return true;  /* return symmetric*/

        /*If one node is null and the other is not null*/
        if ( (n1 != null && n2 == null) || (n1 == null && n2 != null))  
            return false; /*return not symmetric*/

        if (n1.data != n2.data) /*If data of two nodes don't match */
            return false; /* return not symmetric */
    
        if (!compareNodes (n1.left, n2.right)) 
            return false;

        if (!compareNodes (n1.right, n2.left))
            return false;

        return true; /*Return symmetric*/
    }

    /*Returns true if the tree is symmetric, false otherwise*/
    public static boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        return compareNodes(root.left, root.right);
    }   

    
}



class Trees_01_MirrorImage {

    public static final int MAX_NUM_NODES_IN_TREE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static void main(String[] args) {
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];

        /*numberArray contains data in ascending order from 0 to MAX_NUM_NODES_IN_TREE*/
        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        /*Test for different number of nodes in the tree*/
        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            /*Construct the tree based on the data stored in the number array*/
            TreeNode root1 = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            /*Construct an identical tree and store root in root2*/
            TreeNode root2 = TreeNode.constructBst(null, numberArray, 0, numElems - 1);

            System.out.println("Original Tree:");           
            PrintTreeHelper.printTree(root1, numElems);

            /*Compute the mirror image*/
            TreeNode.computeMirrorImage(root2);

            System.out.println("Mirror Image:");            
            PrintTreeHelper.printTree(root2, numElems);

            /*Root1 and Root2 have trees that are mirror images of each other.
            So if we now have a mainRoot whose left child is root1 and right child is root2
            then mainRoot should be symmetric*/
            TreeNode mainRoot = new TreeNode();
            mainRoot.left = root1;
            mainRoot.right = root2;

            if (!TreeNode.isSymmetric(mainRoot))
                handleError();

            System.out.println("___________________________________________");

        }

        
        System.out.println("Test passed");
    }

}











