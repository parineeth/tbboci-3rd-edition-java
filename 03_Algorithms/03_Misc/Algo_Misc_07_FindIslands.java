/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Misc_07_FindIslands {

    public static final int MAX_NUM_TESTS = 100;

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    public static void printMatrix(int[][] m) {
        int nRows = m.length;
        int nCols = m[0].length;

        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /* 
    Helper function that indicates if we can enter the cell or not
    */
    public static boolean canEnterCell(int[][] matrix, boolean[][] isVisited, 
                    int curRow, int curCol) {
        int nRows = matrix.length;
        int nCols = matrix[0].length;

        /*If we are outside the bounds of the matrix or
        if the cell is already visited or if the value in cell is 0
        then we shouldn't enter the cell */
        if (curRow < 0 || curRow >= nRows 
            || curCol < 0 || curCol >= nCols
            || isVisited[curRow][curCol] 
            || matrix[curRow][curCol] == 0) {
            return false;
        }

        return true;
    }


    /* Helper function to count the number of islands of 1's
    matrix: 2d matrix consisting of 0's and 1's
    isVisited: if cell (i, j) has been visited, isVisited[i][j] is set to true
    curRow: row of the current cell being processed
    curCol: column of the current cell being processed
    */
    public static void expandSearch(int[][] matrix, boolean[][] isVisited, int curRow, int curCol) {
        int nRows = matrix.length; 
        int nCols = matrix[0].length;

        isVisited[curRow][curCol] = true;

        /*For the current cell, find out if we can continue the island of 1's
        with its neighbors. Each cell has 8 neighbors. The rows
        of neighbors will vary from curRow - 1 to curRow + 1
        The columns of the neighbors will vary from curCol - 1
        to curCol + 1*/
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                boolean isSafeCell = canEnterCell(matrix, isVisited, curRow+i, 
                                    curCol+j);

                if (isSafeCell) {
                    expandSearch(matrix, isVisited, curRow+i, curCol+j);
                }
            }
        }
    }

    /* Main function to find the number of islands of 1's
    matrix: 2d matrix consisting of 0's and 1's. Should not be empty
    */
    public static int findIslands(int[][] matrix) {
        int nRows = matrix.length; 
        int nCols = matrix[0].length;
        boolean[][] isVisited = new boolean[nRows][nCols];

        /*Initially all cells are not yet visited*/
        int i, j;
        for (i = 0; i < nRows; ++i)
            for (j = 0; j < nCols; ++j) 
                isVisited[i][j] = false;

        /*Search all the cells in matrix that are not yet visited*/
        int count = 0;
        for (i = 0; i < nRows; ++i) {
            for (j = 0; j < nCols; ++j) {
                if (matrix[i][j] == 1 && !isVisited[i][j]) {
                    /*We have found an island. Now expand the island 
                    in all directions*/
                    expandSearch(matrix, isVisited, i, j);
                    ++count;
                }
            }
        }
        return count;
    }


    public static void test01() {
        int[][] matrix = { 
                    {1, 1, 1, 0, 0},
                    {0, 1, 0, 0, 1},
                    {0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0},
                    {0, 1, 0, 1, 0}
                };

        int expectedResult = 4;

        printMatrix(matrix);

        int result = findIslands(matrix);

        System.out.println("Number of islands = " + result);

        if (result != expectedResult)
            handleError();

        System.out.println("______________________________________");
    }



    public static void test02() {
        int[][] matrix = { 
                    {1, 1, 1, 0, 0},
                    {0, 1, 1, 0, 0},
                    {1, 0, 1, 0, 0},
                    {1, 0, 1, 0, 0},
                    {0, 1, 1, 1, 0}
                };
        int expectedResult = 1;

        printMatrix(matrix);

        int result = findIslands(matrix);

        System.out.println("Number of islands = " + result);

        if (result != expectedResult)
            handleError();

        System.out.println("______________________________________");
    }



    public static void main(String[] args)  {
        test01();
        test02();

        System.out.println("Test passed");

    }

}
