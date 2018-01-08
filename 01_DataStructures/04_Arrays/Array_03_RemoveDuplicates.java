/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Array_03_RemoveDuplicates {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE     = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void printArray(int[] a)  {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
    
        System.out.println("");
    }


    /*
    a: non-empty input array from which duplicates should be removed. 
        this array will be modified
    Returns: new output array which doesn't have duplicates 
    */
    public static int[] removeDuplicates(int[] a) {
        int length = a.length;

        /*Sort the array*/
        Arrays.sort(a);

        int fillPos = 1;
        for (int i = 1; i < length; ++i) {
            if (a[i] != a[i - 1]) {
                a[fillPos] = a[i];
                fillPos++;
            }
        }

        int[] result = Arrays.copyOf(a, fillPos);
        return result;
    }


    public static void verify(int[] a) {
        int length = a.length;

        for (int i = 0; i < length; ++i) {
            for (int j = i+1; j < length; ++j) {
                if (a[i] == a[j]) {
                    /*We found a duplicate*/
                    handleError();
                }
            }
        }
    }



    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly decide the number of elements*/
        int length1 = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        int[] a = new int[length1];

        /*Fill the array with random values*/
        for (int i = 0; i < length1; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        System.out.print ("Original array : ");
        printArray(a);


        /*Remove the duplicates */
        int[] result = removeDuplicates(a);

        System.out.print ("After removing duplicates : ");
        printArray(result);

        verify(result);


        System.out.println("________________________________________________");

    }


    public static void main(String[] args)  {

        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
