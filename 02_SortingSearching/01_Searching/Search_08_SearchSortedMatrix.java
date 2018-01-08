/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Search_08_SearchSortedMatrix {

    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }


    /*
    m: matrix to be searched
    x: element to search
    Return value: true if element is present, false otherwise
    */
    public static boolean searchMatrix(int[][] m, int x) {
        int numRows = m.length;
        int numCols = m[0].length;

        int i = 0;
        int j = numCols - 1;
        boolean isFound = false;
        while (i < numRows && j >= 0) {
            if (m[i][j] == x) {
                isFound = true;
                break;
            } else if (m[i][j] < x) {
                ++i;    /*go to the row below*/
            } else {
                --j;    /*go to the previous column*/
            }
        }

        return isFound;
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

    public static void printResult(boolean result, int x) {
        if (result)
            System.out.println("Found " + x);
        else 
            System.out.println("Could NOT Find " + x);
    }

    public static void test(int[][] m, int x, boolean expectedResult) {
        boolean result = searchMatrix(m, x);
    
        printResult(result, x);

        if (result != expectedResult)
            handleError();
    }

    public static void main(String[] args)  {
        int[][] m = { {10, 20, 30, 40},
                    {15, 25, 35, 45},
                    {27, 29, 37, 48},
                    {32, 33, 39, 50}
                  };

        printMatrix(m);

        test(m, 40, true);
        test(m, 10, true);
        test(m, 50, true);
        test(m, 25, true);
        test(m, 37, true);
        test(m, 28, false);

        System.out.println("Test passed");

    }

}


