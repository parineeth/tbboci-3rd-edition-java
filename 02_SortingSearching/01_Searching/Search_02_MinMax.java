/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Search_02_MinMax {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE     = 10;
    public static final int MAX_INT       = 1000000;
    public static final int MIN_INT       = -100000;

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
    result: the minimum value is returned in result[0] and 
        the maximum value is returned in result[1]
    */
    public static void findMinMax(int[] a, int[] result) {
        int length = a.length;
        int maxValue = MIN_INT;
        int minValue = MAX_INT;

        int i = 0;
        if (length % 2 == 1) {
            /*If there are odd number of elements, then initialize 
            maxValue and minValue with a[0]*/
            maxValue = minValue = a[0];
            i = 1;
        }

        for (; i < length; i += 2) {
            if (a[i] > a[i+1]) {
                if (a[i] > maxValue) 
                    maxValue = a[i];
                if (a[i+1] < minValue)
                    minValue = a[i+1];
            } else {
                if (a[i] < minValue)
                    minValue = a[i];
                if (a[i+1] > maxValue)
                    maxValue = a[i+1];
            }
        }

        result[0] = minValue;
        result[1] = maxValue;
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
        int[] a = new int[length];

        /*Fill the array with random values*/
        generateArray(a);

        printArray(a);

        /*Find the result using an efficient technique*/
        int[] result = new int[2];
        findMinMax(a, result);
        int resultMin = result[0];
        int resultMax = result[1];
    
        System.out.println("Min = " + resultMin + ", Max = " + resultMax);


        /*Find the result using sorting*/
        Arrays.sort(a);
        int expectedMin = a[0];
        int expectedMax = a[length-1];


        /*The two results should match*/
        if (resultMin != expectedMin || resultMax != expectedMax ) {
            handleError();
        }


        System.out.println("________________________________________________");

    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
