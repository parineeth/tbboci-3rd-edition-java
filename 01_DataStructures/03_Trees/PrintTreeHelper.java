/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class PrintTreeHelper {
    public static final int MAX_NUM_COLS_ON_SCREEN = 40;

    /*Helper function for printing the binary tree
    curNode: current node being processed in pre-oder traversal
    curDepth: depth of the current node. Depth of root is 0
    maxDepthArray: maximum depth of the tree is returned here
    printArray: The nodes of the binary tree are stored in this array. printArray[0] will contain the root.
        printArray[1] will store the left child of root. printArray[2] will store right child of root and so on
        In case, we get a null node while traversing the tree, we will still store it
    pos: position of the current node in the printArray
    */
    public static void fillPrintArray(TreeNode curNode, int curDepth, int[] maxDepthArray, TreeNode[] printArray, 
        int pos) 
    {
        /*Store the node in the array (even if the node is null)*/
        printArray[pos] = curNode;

        if (curNode == null)
            return;

        if (curDepth > maxDepthArray[0])
            maxDepthArray[0] = curDepth;

        fillPrintArray(curNode.left, curDepth + 1, maxDepthArray, printArray, (2*pos) + 1);
        fillPrintArray(curNode.right, curDepth + 1, maxDepthArray, printArray, (2*pos) + 2);
        
    }

    /*
    Main function for printing the tree
    */
    public static void printTree(TreeNode root, int maxNumNodesInTree) {

        TreeNode[] printArray; 
        int[] maxDepthArray = new int[1];

        if (maxNumNodesInTree > 16) {
            System.out.println("Oops, too many nodes in tree. Can't print the tree");
            return;
        }

        int maxPrintArraySize = 1 << (maxNumNodesInTree + 2);
        printArray = new TreeNode[maxPrintArraySize];

        /*Store the nodes of the tree in printArray. printArray[0] will hold the root.
        printArray[1] will hold root's left child. printArray[2] will hold root's right child
        printArray[3] to print[6] will hold the nodes at depth 2 and so on. 
        Even null nodes are stored in the array*/
        maxDepthArray[0] = -1;
        fillPrintArray(root, 0, maxDepthArray, printArray, 0);
        int maxDepth = maxDepthArray[0];

        /*Our print function is limited by the number of columns on the screen. We can print
        properly on till a depth of 3*/
        if (maxDepth > 3) {
            System.out.println("Oops can't print the tree at depth more than 4 ");
            return; 
        }

        /* If MAX_NUM_COLS_ON_SCREEN is 40,
        for depth 0, startDisplayPos = 20, offset = 40: root is printed at column 20
        for depth 1, startDisplayPos = 10, offset = 20: printing happens at column 10 and 30
        for depth 2, startDisplayPos = 5,  offset = 10: printing happens at column 5, 15, 25 and 35
        */
        int curDepth = 0, printIndex = 0;
        int startDisplayPos = MAX_NUM_COLS_ON_SCREEN / 2;
        int offset = MAX_NUM_COLS_ON_SCREEN;

        while (curDepth <= maxDepth) {
            int numNodesInThisLevel = (1 << curDepth);
            int screenPos = 0;
            int nodeDisplayPos = startDisplayPos;

            for (int i = 0; i < numNodesInThisLevel; ++i) {

                TreeNode curNode = printArray[printIndex];

                /*We need to display current node at nodeDisplayPos
                So print spaces on the screen till screenPos reaches nodeDisplayPos*/
                while (screenPos < nodeDisplayPos) {
                    System.out.print(" ");
                    screenPos++;
                }

            
                if (curNode != null) {
                    System.out.print(curNode.data);
                    screenPos++;
                }

                nodeDisplayPos += offset; /*Compute the position of the next node*/
                printIndex++; /*Go to the next node in the printArray*/
            }

            /*We are going to nodes at next depth. So go to a new line*/
            System.out.println("");

            curDepth++;
            startDisplayPos = startDisplayPos / 2;
            offset = offset / 2;
        }

        System.out.println("\n");
    
    }
}
