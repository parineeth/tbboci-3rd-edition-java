/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Math_03_MissingNumber {


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
    a: array of unique numbers. A number can have a value between 1 to n.
    n: maximum value that can be stored in array. array has n-1 elements
    Return value: missing element in the array
    */
    public static int findMissing(int[] a, int n) {
        /*Since 1 element is missing, there are only n-1 elements in the array*/
        int totalSum = 0;
        for (int i = 0; i < n - 1; ++i) { 
            totalSum += a[i];
        }

        int expectedSum = n * (n+1) / 2;

        int missingNum = expectedSum - totalSum;

        return missingNum;
    }

    public static void test() {
        int[] a = {1, 2, 3, 4, 6, 7, 8, 9, 10};
        int length = 9; /*Number of elements in the array*/

        System.out.print("Input : ");
        printArray(a);

        int result = findMissing(a, length+1);

        System.out.println("Missing element = " + result);

        int expectedResult = 5;
        if (result != expectedResult)
            handleError();
    }



    public static void main(String[] args) {
        test();

        System.out.println("Test passed ");
    }

}
