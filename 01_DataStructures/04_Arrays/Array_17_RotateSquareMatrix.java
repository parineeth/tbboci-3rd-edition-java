/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Array_17_RotateSquareMatrix {

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
    m: 2d matrix to be rotated by 90 degrees in clockwise direction
    */
    public static void rotateSquareMatrix90(int[][] m) {
        int n = m.length; /*Get the number of rows in the square matrix*/

        /*maxI and maxJ have the boundaries of the first quadrant*/
        int maxI = (n/2) - 1;
        int maxJ = ((n+1)/2) - 1;

        for (int i = 0; i <= maxI; ++i) {
            for (int j = 0; j <= maxJ; ++j) {

                /*Perform  a four way swap*/
                int temp = m[i][j];

                m[i][j] = m[n - j - 1][i];

                m[n - j - 1][i] = m[n - i - 1][n - j - 1];

                m[n - i - 1][n - j - 1] = m[j][n - i - 1];

                m[j][n - i - 1] = temp;
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

        rotateSquareMatrix90(m);

        printMatrix(m);
    
        System.out.println("Test passed");
    }


}
