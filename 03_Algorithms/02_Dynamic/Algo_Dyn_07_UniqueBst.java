/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Dyn_07_UniqueBst {


    public static final int MAX_INT = 1000000;

    public static void handleError() {
        System.out.println("Test failed ");
        System.exit(1);
    }


    /*
    n: total number of nodes in the binary search tree
    Return value: Number of unique binary search trees that can be constructed with n nodes
    */
    public static int findNumUniqueBst(int n) {
        if (n <= 2)
            return n;

        int[] numBst = new int[n+1];

        numBst[0] = 1;  /*We are making this 1 to simplify the calculation*/
        numBst[1] = 1;
        numBst[2] = 2;

        for (int i = 3; i <= n; ++i) {
            /*the left sub-tree size can vary from 0 to i-1 
            (one node has to be reserved for root)*/
            for (int leftSubTreeSize = 0; leftSubTreeSize < i; 
                    ++leftSubTreeSize) {
                /*Subtract the left subtree size and the root node to 
                get right subtree size*/
                int rightSubTreeSize = i - 1 - leftSubTreeSize;

                numBst[i] += numBst[leftSubTreeSize] * 
                            numBst[rightSubTreeSize];
            }
        }

        return numBst[n];
    }





    public static void test(int n, int expectedResult) {
        int result = findNumUniqueBst(n);

        System.out.println("Number of unique BST with " + n + " nodes = " + result);

        if (result != expectedResult)
            handleError();
    
        System.out.println("");  
    }



    public static void main(String[] args) {
        /*catalan numbers*/
        test(1, 1);
        test(2, 2);
        test(3, 5);
        test(4, 14);
        test(5, 42);
        test(6, 132);
        test(7, 429);

        System.out.println("Test passed");
    }

}
