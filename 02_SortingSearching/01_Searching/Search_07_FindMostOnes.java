/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Search_07_FindMostOnes {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static int findFirst(int[] a, int x) {
        int n = a.length;
        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (a[mid] == x) {
                if (mid == 0 || a[mid - 1] != x)
                    return mid;
                else
                    end = mid - 1;

            } else if (a[mid] > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }


    /*
    a: array where each row is sorted and has only 0's and 1's
    Return value: row number that has most ones, if no row has 1's then return -1
    */
    public static int findRowWithMostOnes(int[][] a) {
        int nrows = a.length; 
        int ncols = a[0].length;
        int maxRow = -1;
        int maxNumOnes = 0;

        for (int i = 0; i < nrows; ++i) {
            /*Find the position of the first 1 in the row*/
            int firstOneIndex = findFirst(a[i], 1);

            //Compute number of 1's in row based on position of the first 1
            int curNumOnes;
            if (firstOneIndex == -1)
                curNumOnes = 0; /*there are no 1's in the row*/
            else
                curNumOnes = ncols - firstOneIndex;

            if (curNumOnes > maxNumOnes) {
                maxNumOnes = curNumOnes;
                maxRow = i;
            }
        }

        return maxRow;
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


    public static void test01() {
        int[][] a = {{1, 1, 1, 1, 1, 1},
                      {0, 0, 0, 0, 0, 0},
                      {0, 0, 0, 0, 0, 1},
                      {0, 1, 1, 1, 1, 1},
                      {0, 0, 0, 0, 1, 1}
                 };
        
        printMatrix(a);
    
        int result = findRowWithMostOnes(a);
        System.out.println("\nRow with most ones = " + result);

        if (result != 0)
            handleError();

        System.out.println("___________________________________________________");
    }


    public static void test02() {
        int[][] a = {{0, 0, 0, 0, 0, 1},
                      {0, 0, 0, 0, 0, 0},
                      {0, 0, 0, 0, 0, 1},
                      {0, 1, 1, 1, 1, 1},
                      {0, 0, 0, 0, 1, 1}
                 };
        
        printMatrix(a);

        int result = findRowWithMostOnes(a);
        System.out.println("\nRow with most ones = " + result);


        if (result != 3)
            handleError();

        System.out.println("___________________________________________________");

    }


    public static void main(String[] args)  {
        test01();
        test02();
        System.out.println("Test passed");
    }

}
