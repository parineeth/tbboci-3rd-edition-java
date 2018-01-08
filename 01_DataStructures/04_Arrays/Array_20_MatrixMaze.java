/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Array_20_MatrixMaze {

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


    /*
    m: matrix that has to be navigated
    curRow: row of the current cell
    curCol: column of the current cell
    Return value: the total number of paths possible is returned
    */
    public static int navigateMaze(int[][] m, int curRow, int curCol) {
        if (curRow < 0 || curCol < 0)
            return 0;

        if (m[curRow][curCol] == -1)
            return 0; /*We can't traverse this cell, so simply return*/

        if (curRow == 0 && curCol == 0) {
            /*We have reached the destination*/
            return 1;
        }

        /*Try continuing the path by going to the cell in previous row*/
        int numPaths = navigateMaze(m, curRow - 1, curCol);

        /*Try continuing the path by going to the cell in previous col*/
        numPaths += navigateMaze(m, curRow, curCol - 1);

        /*Try continuing the path by going to the diagonally above cell*/
        numPaths += navigateMaze(m, curRow - 1, curCol - 1);

        return numPaths;
    }



    public static void test_01() {

        int[][] m = {   {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
            };

        System.out.println("Input: ");
        printMatrix(m);

        int numPaths = navigateMaze(m, m.length - 1, m[0].length - 1);

        System.out.println("Number of paths = " + numPaths);

        if (numPaths != 321)
            handleError();
    }



    public static void main(String[] args) {
        test_01();
        System.out.println("Test passed");
    }


}
