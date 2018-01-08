/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Search_10_RotatedMax {

    public static final int MAX_NUM_ELEMENTS = 10;
    public static final int MAX_NUM_TESTS = 10;


    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }



    /*
    a: non empty array that has been sorted and rotated. 
        There should NOT be duplicates in the array
    Return value: maximum element in the array
    */
    public static int findMax(int[] a, int n) {
        int start = 0;
        int end = n - 1;

        while (a[start] > a[end]) {
            int mid = (start + end) / 2;

            if (mid < n - 1 && a[mid] > a[mid + 1])
                return a[mid];

            if (a[mid] > a[end]) {
                start = mid;    /*max is in the region (mid, end)*/
            } else {
                end = mid - 1; /*max is in the region (start, mid - 1)*/
            }
        }

        return a[end];
    }




    public static void printArray(int[] a, int n) {

        for (int i = 0; i < n; ++i) {
            System.out.print(a[i] + " ");
        }

        System.out.println("");
    }

    public static void generateSortedRotatedArray(int[] a, int n) {
        Random randomGenerator = new Random();

        /*Generate the first random value*/
        a[0] = randomGenerator.nextInt(10);
        if (n == 1)
            return;

        /*Generate the remaining random values in increasing sorted order without duplication*/
        int i;
        for (i = 1; i < n; ++i) {
             a[i] = a[i - 1] + 1 + (randomGenerator.nextInt(10));
        }

        /*Randomly decide the number of rotations*/
        int numRotations = randomGenerator.nextInt(n);

        /*Perform the rotations*/
        for (int rotationIter = 0; rotationIter < numRotations; ++rotationIter) {
            int temp = a[n-1];
            for (i = n - 1; i >= 1; --i) {
                a[i] = a[i - 1];
            }
            a[0] = temp;
        }

    }


    public static void test01() {
        Random randomGenerator = new Random();

        /*randomly decide the number of elements*/
        int n = 1 + (randomGenerator.nextInt(MAX_NUM_ELEMENTS));
        int[] a = new int[n];

        /*Generate the sorted rotated array*/
        generateSortedRotatedArray(a, n);

        System.out.print("Input : ");
        printArray(a, n);

        /*Find the max using linear search*/
        int testResult = -1;
        for (int i = 0; i < n; ++i) {
            if (a[i] > testResult) {
                testResult = a[i];
            }
        }

        /*Find the max using binary search*/
        int actualResult = findMax(a, n);

        System.out.println("The maximum element is " + actualResult);

        /*Compare the results of linear and binary search*/
        if (testResult != actualResult)
            handleError();

        System.out.println("_______________________________________________");
    }



    public static void main(String[] args) {

        for (int testNr = 0; testNr < MAX_NUM_TESTS; ++testNr) {
            test01();
        }

        System.out.println("Test passed ");
    }

}


