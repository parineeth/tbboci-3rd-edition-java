/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;



class Array_13_MaxProductOf3 {

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
    Returns: the maximum product of 3 elements in the array 
    */
    public static int findMaxProduct(int[] a) {
        int[] maxValue = new int[3];
        int[] minValue = new int[2];
        int length = a.length;

        assert (length >= 3);

        maxValue[0] = maxValue[1] = maxValue[2] = MIN_INT;
        minValue[0] = minValue[1] = MAX_INT;

        for (int curVal : a) {
            /*Check if curVal is among the 3 largest values*/
            if (curVal > maxValue[0]) {
                maxValue[2] = maxValue[1];
                maxValue[1] = maxValue[0];
                maxValue[0] = curVal;
            } else if (curVal > maxValue[1]) {
                maxValue[2] = maxValue[1];
                maxValue[1] = curVal;
            } else if (curVal > maxValue[2]) {
                maxValue[2] = curVal;
            }

            /*Check if curVal is among the 2 smallest values*/
            if (curVal < minValue[0]) {
                minValue[1] = minValue[0];
                minValue[0] = curVal;
            } else if (curVal < minValue[1]) {
                minValue[1] = curVal;
            }
        }

        return Math.max(maxValue[0] * maxValue[1] * maxValue[2],
                minValue[0] * minValue[1] * maxValue[0]);
    }


    /*
    a:input array 
    Returns: the maximum product of 3 elements in the array 
    */
    public static int bruteForceMaxProduct(int[] a) {
        int maxProduct = MIN_INT;
        int length = a.length;

        for (int i = 0; i < length - 2; ++i) {
            for (int j = i+1; j < length - 1; ++j) {
                for (int k = j+1; k < length; ++k) {
                    int curProduct = a[i] * a[j] * a[k];

                    if (curProduct > maxProduct)
                        maxProduct = curProduct;
                }
            }
        }

        return maxProduct;
    }





    public static void generateArray(int[] a) {
        int length = a.length;
        Random randomGenerator = new Random();
    
        for (int i = 0; i < length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);

            if (randomGenerator.nextInt(2) == 0) {
                a[i] = a[i] * -1;
            }
        }
    }



    public static void test() {
        Random randomGenerator = new Random();


        /*Randomly decide the number of elements in the array*/
        int length = randomGenerator.nextInt(MAX_NUM_ELEMS);
        if (length < 3)
            length = 3;

        int[] a = new int[length];  


        /*Fill the array with random values*/
        generateArray(a);

        printArray(a);

        /*Find the result using the efficient technique*/
        int result = findMaxProduct(a);
    
        System.out.println("Max product =  " + result);


        /*Find the result using brute force*/
        int bruteForceResult = bruteForceMaxProduct(a);


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
