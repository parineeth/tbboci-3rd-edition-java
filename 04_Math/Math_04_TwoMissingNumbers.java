/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Math_04_TwoMissingNumbers {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }

    /*
    values: array of unique numbers. A number can have a value between 1 to n
    n: maximum value in the array is n. array has n-2 elements
    result: the missing elements in the array are returned here
    */
    public static void findMissing(int[] values, int n, int[] result) {
        /*Since 2 elements are missing, there are only n-2 elements in array*/
        int actualNormalSum = 0;
        int actualSquareSum = 0;
        for (int i = 0; i < n - 2; ++i) { 
            actualNormalSum += values[i];
            actualSquareSum += values[i] * values[i];
        }

        int expectedNormalSum = n * (n+1) / 2;
        int expectedSquareSum = n * (n+1) * (2*n + 1) / 6;

        int aPlusB = expectedNormalSum - actualNormalSum;
        int aSquarePlusBSquare = (expectedSquareSum - actualSquareSum);
        int twoAB =  ((aPlusB * aPlusB) - aSquarePlusBSquare);
        int aMinusB = (int) Math.sqrt(aSquarePlusBSquare - twoAB);

        int a = (aPlusB + aMinusB) / 2;
        int b = (aPlusB - a);
         
        result[0] = a;
        result[1] = b;
    }

    public static void test() {
        int[] values = {1, 3, 4, 6, 7, 8, 9, 10};
        int length = 8; /*Number of elements in the array*/
        int[] result = new int[2];

        System.out.print("Input : ");
        printArray(values);

        findMissing(values, length+2, result);

        System.out.println("Missing elements are = " + result[0] + " and " + result[1]);

        if ( (result[0] == 2 && result[1] == 5) ||
            (result[0] == 5 && result[1] == 2) ) {
            /*Do nothing*/
        } else {
            handleError();
        }
    }



    public static void main(String[] args) {
        test();

        System.out.println("Test passed ");
    }

}
