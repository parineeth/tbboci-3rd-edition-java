/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Array_11_LeastDifference {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE     = 100;
    public static final int MAX_INT       = 1000000;

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
    a:input array 
    Returns: the least absolute difference between any two elements in the array 
    */
    public static int findLeastDifference(int[] a) {
        int length = a.length;

        assert (length > 1);

        /*Sort the array in non-decreasing order*/
        Arrays.sort(a);

        int leastDifference = a[1] - a[0];
        for (int i = 1; i < length - 1; ++i) {
            if (a[i+1] - a[i] < leastDifference)
                leastDifference = a[i+1] - a[i];
        }

        return leastDifference;
    }


    /*
    a:input array 
    Returns: the least absolute difference between any two elements in the array 
    */
    public static int bruteForceLeastDifference(int[] a) {
        int length = a.length;
        int leastDifference = MAX_INT;

        for (int i = 0; i < length - 1; ++i) {
            for (int j = i+1; j < length; ++j) {
                if (Math.abs(a[i] - a[j]) < leastDifference)
                    leastDifference = Math.abs(a[i] - a[j]);
            }
        }

        return leastDifference;
    }


    public static void generateArray(int[] a) {
        int length = a.length;
        Random randomGenerator = new Random();
    
        for (int i = 0; i < length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }
    }



    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly decide the number of elements in the array*/
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);
        if (length < 2)
            length = 2;

        int[] a = new int[length];


        /*Fill the array with random values*/
        generateArray(a);

        printArray(a);

        /*Find the least difference using brute force*/
        int bruteForceResult = bruteForceLeastDifference(a);


        /*Find the least difference using the efficient technique*/
        int result = findLeastDifference(a);
    
        System.out.println("Least difference = " + result);


        /*The two results should match*/
        if (result != bruteForceResult)
            handleError();


        System.out.println("________________________________________________");

    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
