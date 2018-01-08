/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Misc_02_NQueen {

    /*Helper function which checks if it is possible to place queen in curRow 
    at position colForRow[curRow]
    curRow: Row in which the current queen should be placed
    colForRow: colForRow[i] gives the column in which queen is placed in the ith row
    Return value: true if queen can be placed at colForRow[curRow], false otherwise
    */
    public static boolean checkPlacement(int curRow, int[] colForRow) {
        /*Check if the queens placed in the rows before the current row conflict 
        with the queen placed in current row*/
        for (int i = 0; i < curRow; ++i) {
            /*Check if two queens are present in the same column*/
            if (colForRow[i] == colForRow[curRow])
                return false;

            /*Check if two queens are in the same diagonal*/
            int colDiff = Math.abs(colForRow[curRow] - colForRow[i]);
            int rowDiff = curRow - i;
            if (rowDiff == colDiff)
                return false;       
             
        }

        return true;
    }

    /*Main function for arranging the queens
    curRow: current row in which the queen should be placed
    N: the number of cells in one row of the chess board
    colForRow: colForRow[i] is used for storing the column of the ith row queen 
    */
    public static void arrangeQueens(int curRow, int N, int[] colForRow) {
        int i;
        if (curRow == N) {
            /*We have found a successful arrangement. So print it*/
            for (i = 0; i < N; ++i) {
                System.out.println("Row = " + i + ", Col =  " + colForRow[i]); 
            }
            System.out.println("__________________________");

            return; /*Terminate the recursion*/
        }

        /*Try out different columns in the current row*/
        for (i = 0; i < N; ++i) {
            colForRow[curRow] = i;
            if (checkPlacement(curRow, colForRow)) {
                /*The placements of queens looks good so far. 
                Go to the next row*/
                arrangeQueens(curRow + 1, N, colForRow);
            }
        } 

    }


    public static void main(String[] args)  {
        int N = 4; /*Number of cells in one row of the chess board*/
        int[] colForRow = new int [N];
        
        System.out.println("N = " + N);

        arrangeQueens(0, N, colForRow);

        System.out.println("Test passed");
    }

}
