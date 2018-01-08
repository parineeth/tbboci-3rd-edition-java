/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Search_03_FindFirst {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*
    a: array being searched
    x: element being searched
    Return value: first position of x in a, if x is absent -1 is returned
    */
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


    public static int findBruteForce(int[] a, int x) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i] == x)
                return i;
        }
        return -1;
    }

    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }

        System.out.println("");
    }

    public static void printResult(int result, int x) {
        System.out.println("The position of first occurence of " + x  + " is " + result);
    }

    public static void test01()     {
        Random randomGenerator = new Random();

        /*Number of elements in the array*/
        int n = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);
        int[] a = new int[n];
        int i;
        for (i = 0; i < n; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        Arrays.sort(a);

        System.out.print("Input : ");
        printArray(a);

        /*Search all elements that are present and verify the result */
        int result;
        for (int x : a ) {
            result = findFirst(a, x);

            printResult(result, x);

            int expectedResult = findBruteForce(a, x);
            if (expectedResult != result)
                handleError();
        }



        /*Search for a non-existent item. Result should be -1*/
        int x = MAX_VALUE + 1;
        result = findFirst(a, x);
        printResult(result, x);
        if (result != -1) 
            handleError();

        System.out.println("_____________________________________________");
    }


    public static void main(String[] args)  {
        for (int i = 0; i < MAX_NUM_TESTS; ++i)
            test01();

        System.out.println("Test passed");

    }

}


