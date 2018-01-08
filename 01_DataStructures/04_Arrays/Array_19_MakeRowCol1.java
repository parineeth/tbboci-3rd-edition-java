/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Array_19_MakeRowCol1 {

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
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
        System.out.println();
    }


    public static void compareMatrix(int[][] m, int[][] expectedResult) {
        int numRows = m.length;
        int numCols = m[0].length;

        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (m[i][j] != expectedResult[i][j])
                    handleError();
            }
        }

    }



    /*m: 2d matrix to be processed having atleast 1 row and 1 column
    */
    public static void processMatrix(int[][] m) {
        int i, j;
        boolean isOneInFirstRow = false, isOneInFirstCol = false;
        int numRows = m.length;
        int numCols = m[0].length;

        /*Check if any cell in the first row is set to 1*/
        for (i = 0; i < numRows; ++i)
            if (m[i][0] == 1) {
                isOneInFirstRow = true;
                break;
            }

        /*Check if any cell in first column is set to 1*/
        for (j = 0; j < numCols; ++j)
            if (m[0][j] == 1) {
                isOneInFirstCol = true;
                break;
            }

        /*Scan the matrix. If m[i][0] is equal to 1 then, set m[i][0] to 1
        and set m[0][j] to 1*/
        for (i = 1; i < numRows; ++i)
            for (j = 1; j < numCols; ++j)
                if (m[i][j] == 1) {
                    m[i][0] = 1;
                    m[0][j] = 1;
                }

        /*Mark the cells as 1 as indicated by m[i][0] and m[0][j]*/
        for (i = 1; i < numRows; ++i)
            for (j = 1; j < numCols; ++j)
                if (m[i][0] == 1 || m[0][j] == 1)
                    m[i][j] = 1;

        /*If there was a 1 initially in first column, set 1 in all the 
        cells of first column*/
        if (isOneInFirstCol)
            for (i = 0; i < numRows; ++i)
                m[i][0] = 1;

        /*If there was a 1 initially in first row, set 1 in all the 
        cells of the first row*/
        if (isOneInFirstRow)
            for (j = 0; j < numCols; ++j)
                m[0][j] = 1;
    }


    public static void test_01() {
        int[][] m = {   {1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
            };

        int[][] expectedResult = {
                {1, 1, 1, 1, 1},
                {1, 0, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 1, 1, 0},
                {1, 0, 1, 1, 0}
            };

        System.out.println("Input: ");
        printMatrix(m);

        processMatrix(m);

        System.out.println("Output: ");
        printMatrix(m);

        compareMatrix(m, expectedResult);

    }
    

    public static void main(String[] args) {
        test_01();
        System.out.println("Test passed");
    }


}
