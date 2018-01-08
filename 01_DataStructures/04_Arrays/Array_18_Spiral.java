/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Array_18_Spiral {

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
    }


    /*
    m: 2d matrix that should be printed spirally
    */
    public static void printSpiral(int[][] m) {
        int numRows = m.length;
        int numCols = m[0].length;
        int r1 = 0, r2 = numRows - 1;
        int c1 = 0, c2 = numCols - 1;

        while (r1 <= r2 && c1 <= c2) {
            /*Print row r1*/
            int curRow = r1, curCol;
            for (curCol = c1; curCol <= c2; ++curCol) {
                System.out.print(m[curRow][curCol] + " ");
            }
            ++r1; /*Advance r1 to next row*/

            /*Print column c2*/
            curCol = c2;
            for (curRow = r1; curRow <= r2; ++curRow) {
                System.out.print(m[curRow][curCol] + " ");
            }
            --c2; /*Advance c2 to previous column*/

            if (r1 != r2) {
                /*Print row r2*/
                curRow = r2;
                for (curCol = c2; curCol >= c1; --curCol) {
                    System.out.print(m[curRow][curCol] + " ");
                }
                --r2; /*Advance r2 to previous row*/
            }

            if (c1 != c2) {
                /*Print column c1*/
                curCol = c1;
                for (curRow = r2; curRow >= r1; --curRow) {
                    System.out.print(m[curRow][curCol] + " ");
                }
                ++c1; /*Advance c1 to next column*/
            }
        }
    }
    

    public static void main(String[] args) {


        int[][] m = {   {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
            };

        printSpiral(m);
        
        System.out.println();
        System.out.println("Test passed successfully");
    }


}
