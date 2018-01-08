/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Int {
    public int value;
}

class Algo_Dyn_09_McsMatrix {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_ELEMENT_VALUE = 100;
    public static final int MAX_NUM_ROWS = 5;
    public static final int MAX_NUM_COLS = 5;


    public static final int MIN_INT = (-1 * 0x7FFFFFFF);

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /*a: array of numbers for which MCS should be found. size >= 1
    mcsStartPos: the starting array index of the MCS is returned here
    mcsEndPos: the ending array index of the MCS is returned here 
    Return value: Maximum continous sum of the elements 
    */
    public static int kadaneMcs(int[] a, Int mcsStartPos, Int mcsEndPos) {
        int length = a.length;
        int curStartPos = 0; /*store the start position of the current window*/
        int maxLocal = a[0], maxGlobal = a[0];
        mcsStartPos.value = mcsEndPos.value = 0;

        /*Traverse from the second element onwards*/
        for (int i = 1; i < length; ++i) {
            maxLocal = Math.max(a[i], a[i] + maxLocal);
            if (maxLocal == a[i])
                curStartPos = i;/*start a new window here*/

            /*Find the global maximum*/
            if (maxLocal > maxGlobal) {
                maxGlobal = maxLocal;
                mcsStartPos.value = curStartPos;
                mcsEndPos.value = i;
            }
        }

        return maxGlobal;
    }


    /*
    matrix: non-empty input matrix for which we have to find the maximum sum
    Return value: the sum of elements in the max sum sub-matrix in the input matrix
    */
    public static int findMaxSumMatrix(int[][] matrix) {

        int nRows = matrix.length;
        int nCols = matrix[0].length;

        Int start = new Int();
        Int end = new Int();

        /*create a temporary 1-D array which stores the result of column additions*/
        int[] a = new int[nRows]; 

        int maxSum = MIN_INT;
        for (int leftCol = 0; leftCol < nCols; ++leftCol) {
            /*We have chosen a left column. Initialize the temporary array to 0*/
            int i;
            for (i = 0; i < nRows; ++i)
                a[i] = 0;

            /*Iterate through the right side columns which are >= left column*/
            for (int rightCol = leftCol; rightCol < nCols; ++rightCol) {
        
                /*Add elements in the current right column to array a*/
                for (i = 0; i < nRows; ++i) 
                    a[i] += matrix[i][rightCol];

                /*Find the maximum continuous sum of the 1-D array using
                kadane algo. The start and end indices returned by kadane  
                algo will correspond to the start row and end row of the 
                2-D matrix*/
                int curSum = kadaneMcs(a, start, end);

                if (curSum > maxSum) {
                    maxSum = curSum;
            
                    /*The maximum sum sub-matrix is bounded between 
                    col1 = leftCol, col2 = rightCol, row1 = start 
                    row2 = end*/
                }
            }
        }


        return maxSum;
    }



    public static int findBruteForceMcs(int[][] matrix) {
        int mcs = MIN_INT;
        int nRows = matrix.length;
        int nCols = matrix[0].length;

        /*Fix the startRow, endRow, startCol and endCol of the sub-matrix*/
        for (int startRow = 0; startRow < nRows; ++startRow) {
            for (int endRow = startRow; endRow < nRows; ++endRow) {
                for (int startCol = 0; startCol < nCols; ++startCol) {
                    for (int endCol = startCol; endCol < nCols; ++endCol) {

                        /*Find the sum in the current sub-matrix*/
                        int curSum = 0;
                        for (int i = startRow; i <= endRow; ++i) {
                            for (int j = startCol; j <= endCol; ++j) {
                                curSum += matrix[i][j];
                            }
                        }

                        if (curSum > mcs)
                            mcs = curSum;
                    }
                }
            }
        }

        return mcs;
    }


    public static void printMatrix(int[][] matrix) {
        int nRows = matrix.length;
        int nCols = matrix[0].length;

        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }


    public static void test() {
        Random randomGenerator = new Random();

        /*Pick a random number of rows*/
        int nRows = 1 + randomGenerator.nextInt(MAX_NUM_ROWS);

        /*Pick a random number of rows*/
        int nCols = 1 + randomGenerator.nextInt(MAX_NUM_COLS);

        int[][] matrix = new int[nRows][nCols];

        /*Generate random values into the matrix */
        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                matrix[i][j] = randomGenerator.nextInt(MAX_ELEMENT_VALUE);

                /*Some elements will be negative*/
                if (randomGenerator.nextInt(2) == 0)
                    matrix[i][j] = -1 * matrix[i][j];
            }
        }

        System.out.println("Input : ");
        printMatrix(matrix);

        /*Find the maximum continuous sum*/
        int algoMcs = findMaxSumMatrix(matrix);

        System.out.println("Max Continuous Sum =  " + algoMcs);

        /*Find the maximum continuous sum using brute force*/
        int bruteForceMcs = findBruteForceMcs(matrix);

        /*The two results should match*/
        if (algoMcs != bruteForceMcs) {
            handleError();
        }

        System.out.println("______________________________________________");

    }



    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM_TESTS; ++i)
            test();

        System.out.println("Test passed");
    }

}
