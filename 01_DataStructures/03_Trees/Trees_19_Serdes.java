/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.io.*;



class TreeNode {
    public static final int SPECIAL_VALUE = -1;

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


    /*curNode: current node of the binary tree
    bw: buffered file writer stream where the binary tree should be stored
    Return value: 0 on sucess 
    */
    public static int serializeTree(TreeNode curNode, 
                BufferedWriter bw) throws IOException {
        /*If curNode is null, then store the special value and return*/
        if (curNode == null) {
            bw.write(String.valueOf(SPECIAL_VALUE) + "\n");
            return 0;
        }

        /*Traverse the nodes in pre-order*/
        /*First write the data of the node into the file*/
        bw.write(String.valueOf(curNode.data) + "\n");

        /*Traverse the left subtree*/
        serializeTree(curNode.left, bw);

        /*Traverse the right subtree*/
        serializeTree(curNode.right, bw);

        return 0;
    }

    /*br: buffered reader file stream where the data about binary tree was stored
    Return value: the reconstructed node of the binary tree
    */
    public static TreeNode deserializeTree(BufferedReader br) throws IOException {
        /*Read the data from the line in the file*/
        int value = Integer.parseInt(br.readLine());

        /*If the special value is read, then return null */
        if (value == SPECIAL_VALUE)
            return null;

        /*Traverse in pre-order*/
        /*Store the value read from the file in the newNode*/
        TreeNode newNode = new TreeNode();
        newNode.data = value;

        newNode.left = deserializeTree(br); /*Construct the left subtree*/

        newNode.right = deserializeTree(br); /*Construct the right sub-tree*/

        return newNode;
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

    public static boolean areTreesIdentical(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return true;

        if ( (n1 != null && n2 == null) || (n1 == null && n2 != null))
            return false;

        if (n1.data != n2.data)
            return false;

        if (!areTreesIdentical(n1.left, n2.left))
            return false;

        if (!areTreesIdentical(n1.right, n2.right))
            return false;

        return true;
    }


    
}



class Trees_19_Serdes {

    public static final int MAX_NUM_NODES_IN_TREE = 10;
    

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(0);
    }



    public static void main(String[] args) throws IOException{
        int[] numberArray = new int[MAX_NUM_NODES_IN_TREE];
        BufferedReader in; 
            BufferedWriter out; 

        for (int i = 0; i < MAX_NUM_NODES_IN_TREE; ++i) {
            numberArray[i] = i;
        }

        for (int numElems = 1; numElems <= MAX_NUM_NODES_IN_TREE; ++numElems) {

            TreeNode root1 = TreeNode.constructBst(null, numberArray, 0, numElems - 1);


            out = new BufferedWriter(new FileWriter("serialTree.txt"));

            TreeNode.serializeTree(root1, out);

            out.close();


            in = new BufferedReader(new FileReader("serialTree.txt"));

            TreeNode root2 = TreeNode.deserializeTree(in);

            in.close();

            if (!TreeNode.areTreesIdentical(root1, root2))
                handleError();

        }

        
        System.out.println("Test passed ");
    }

}











