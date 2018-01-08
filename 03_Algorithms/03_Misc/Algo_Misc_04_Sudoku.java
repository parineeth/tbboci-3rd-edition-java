/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Misc_04_Sudoku {

    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    public static void verifyGrid(int[][] grid) {

        /*The sum of the numbers in a row should be 
        1 + 2 + 3 + 4+ 5 + 6 + 7 + 8 + 9 = 45*/
        int rowNr, colNr, sum;
        for (rowNr = 0; rowNr < NUM_ROWS; ++rowNr) {
            sum = 0;
            for (colNr = 0; colNr < NUM_COLS; ++colNr) 
                sum += grid[rowNr][colNr];

            if (sum != 45)
                handleError();
        }


        /*The sum of the numbers in a column should be 
        1 + 2 + 3 + 4+ 5 + 6 + 7 + 8 + 9 = 45*/
        for (colNr = 0; colNr < NUM_COLS; ++colNr) {
            sum = 0;
            for (rowNr = 0; rowNr < NUM_ROWS; ++rowNr) 
                sum += grid[rowNr][colNr];

            if (sum != 45)
                handleError();
        }
    
        /*We should also check if the sum in every 3*3 box is 45*/
    }


    public static void printGrid(int[][] grid, boolean verify) {
        for (int i = 0; i < NUM_ROWS; ++i) {
            for (int j = 0; j < NUM_COLS; ++j) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("\n\n");

        if (verify)
            verifyGrid(grid);
    }




    /* Helper function which checks if it is possible to place a number in a cell
    grid: the 2d sudoku matrix
    rowNr: row number of the cell we are checking
    colNr: column number of the cell we are checking
    num: the number which we want to place in the cell
    Returns: true if we can place num in the cell, false otherwise
    */
    public static boolean canFillCell(int[][] grid, int rowNr, int colNr, int num) {

        /*Ensure that the number is not present in any row of requested column*/
        int i, j;
        for (i = 0; i < NUM_ROWS; ++i)
            if (grid[i][colNr] == num)
                return false;

        /*Ensure that the number is not present in any column of requested row*/
        for (j = 0; j < NUM_COLS; ++j)
            if (grid[rowNr][j] == num)
                return false;

        /*Ensure that the number is not present in the 3*3 box it belongs to*/
        int regionStartRow = (rowNr / 3) * 3;
        int regionStartCol = (colNr / 3) * 3;

        for (i = regionStartRow; i < regionStartRow + 3; ++i)
            for (j = regionStartCol; j < regionStartCol + 3; ++j)
                if (grid[i][j] == num)
                    return false;

        return true;
    }

    /*Main function for solving the sudoku puzzle
    grid: the 2d sudoku matrix
    rowNr: row number of the current cell being processed
    colNr: column number of the current cell being processed
    */
    public static void solveSudoku(int[][] grid, int rowNr, int colNr) {

        if (rowNr >= NUM_ROWS) {
            /*We have found a solution. print the grid and
            terminate the recursion*/
            printGrid(grid, true);
            return;
        }

        /*Pre-compute the row and column of the next cell*/
        int nextRow = rowNr;
        int nextCol = colNr + 1;
        if (nextCol >= NUM_COLS) {
            nextCol = 0;
            nextRow = rowNr + 1;
        }

        if (grid[rowNr][colNr] == -1) {
            /*The puzzle writer has not assigned a number to this cell.
            So try assigning numbers 1-9 to the cell*/
            for (int num = 1; num <= 9; ++num) {
                if (canFillCell(grid, rowNr, colNr, num)) {
                    grid[rowNr][colNr] = num;
                    solveSudoku(grid, nextRow, nextCol);
                }
            }
            /*Once we are done trying all numbers from 1-9 assign the cell
            back to -1 to indicate puzzle writer has not assigned a number 
            to the cell*/
            grid[rowNr][colNr] = -1;

        } else {
            /*The puzzle writer has already assigned a value to the cell. 
            So proceed to the next cell*/
            solveSudoku(grid, nextRow, nextCol);
        }
    }


    public static void main(String[] args) {
        int[][] grid = new int[NUM_ROWS][NUM_COLS];

        /*Initialize all cells with -1*/
        for (int i = 0; i < NUM_ROWS; ++i)
            for (int j = 0; j < NUM_COLS; ++j)
                grid[i][j] = -1;

        /*Fill in the cells specified by the puzzle writer*/
        grid[0][0] = 5;
        grid[0][1] = 3;
        grid[0][4] = 7;

        grid[1][0] = 6;
        grid[1][3] = 1;
        grid[1][4] = 9;
        grid[1][5] = 5;

        grid[2][1] = 9;
        grid[2][2] = 8;
        grid[2][7] = 6;

        grid[3][0] = 8;
        grid[3][4] = 6;
        grid[3][8] = 3;

        grid[4][0] = 4;
        grid[4][3] = 8;
        grid[4][5] = 3;
        grid[4][8] = 1;

        grid[5][0] = 7;
        grid[5][4] = 2;
        grid[5][8] = 6;

        grid[6][1] = 6;
        grid[6][6] = 2;
        grid[6][7] = 8;

        grid[7][3] = 4;
        grid[7][4] = 1;
        grid[7][5] = 9;
        grid[7][8] = 5;

        grid[8][4] = 8;
        grid[8][7] = 7;
        grid[8][8] = 9;

        System.out.println("Input: ");
        printGrid(grid, false); /*passing false so that we don't verify the grid*/

        System.out.println("Output: ");
        solveSudoku(grid, 0, 0);

        System.out.println("Test passed");
    }

}
