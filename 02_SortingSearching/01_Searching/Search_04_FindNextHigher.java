/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;
import java.util.Arrays;

class Search_04_FindNextHigher {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE = 100;
    public static final int MAX_INT = 999999;

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
    a: sorted array containing elements in non-decreasing order
    k: we are searching for the number immediately above k
    Returns: the number immediately greater than k in the array if it exists,
        MAX_INT otherwise
    */
    public static int findNextHigher(int[] a, int k) {
        int low = 0; 
        int high = a.length - 1;

        int result = MAX_INT;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (a[mid] > k) {
                result = a[mid]; /*update the result and continue*/
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        } 

        return result;
    }



    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly decide the number of elements*/
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        int[] a = new int[length];

        /*Generate an array having random elements*/
        int i;
        for (i = 0; i < length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        /*Sort the array in ascending order*/
        Arrays.sort(a);

        System.out.print ("Input : ");
        printArray(a);

        /*choose a random element k in the array*/
        int pos = randomGenerator.nextInt(length);
        int k = a[pos];

        /*Find the next higher element after k using binary search*/
        int result = findNextHigher(a, k);

        System.out.println("Next element greater than " + k + " is " + result);

        /*Find the next higher element after k using linear search*/
        int expectedResult = MAX_INT;
        for (i = pos + 1; i < length; ++i) {
            if (a[i] > k) {
                expectedResult = a[i];
                break;
            }       
        }

        /*The linear search and binary search results should match*/
        if (result != expectedResult)
            handleError();
    
        System.out.println("________________________________________________");

    }


    public static void main(String[] args)  {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed ");
    }

}
