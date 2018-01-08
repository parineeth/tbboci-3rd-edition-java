/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Dyn_10_MatrixMinPathSum {


    public static final int INT_MAX = 100000;

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    public static int min(int a, int b, int c) {
        int smallest = a;
    
        if (b < smallest)
            smallest = b;

        if (c < smallest)
            smallest = c;

        return smallest; 
    }

    /*
    a: non-empty 2D matrix in which we have to find the minimum cost path 
    Return value: cost of the minimum path from (0, 0) to (numRows-1, numCols-1)
    */
    public static int findMinPath(int[][] a) {
        int numRows = a.length; 
        int numCols = a[0].length;
        int[][] cost = new int[numRows][numCols];
        int i, j;

        cost[0][0] = a[0][0];
        for (i = 1; i < numRows; ++i) 
            cost[i][0] = cost[i-1][0] + a[i][0];

        for (j = 1; j < numCols; ++j) 
            cost[0][j] = cost[0][j-1] + a[0][j];

        for (i = 1; i < numRows; ++i) {
            for (j = 1; j < numCols; ++j) {
                cost[i][j] = a[i][j] +
                    min(cost[i-1][j], cost[i][j-1], cost[i-1][j-1]);
            }
        }

        return cost[numRows - 1][numCols - 1];
    }


    public static int minPathR(int[][] a, int curRow, int curCol) {
        if (curRow < 0 || curCol < 0)
            return INT_MAX;

        if (curRow == 0 && curCol == 0)
            return a[0][0];

        return a[curRow][curCol] + min(minPathR(a, curRow - 1, curCol),
                          minPathR(a, curRow, curCol - 1),
                          minPathR(a, curRow - 1, curCol - 1));
    }


    public static void printMatrix(int[][] m) {
        int numRows = m.length;
        int numCols = m[0].length;

        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void test() {
        int[][] a = {{5, 3, 1},
                {2, 6, 5},
                {9, 3, 1}};


        printMatrix(a);

        /*Find minimum path cost using dynamic programming*/
        int result = findMinPath(a);

        /*Find minimum path cost using recursion*/
        int expectedResult = minPathR(a, 2, 2);

        System.out.println("Minimum cost of path =  " + result);

        /*The two results should match*/
        if (result != expectedResult)
            handleError();
    
    }


    public static void main(String[] args) {
        test();
        System.out.println("Test passed");
    }

}

