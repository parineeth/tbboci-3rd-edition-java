/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Array_14_ArrayProduct {

    public static final int MAX_NUM_TESTS = 100;
    public static final int MAX_VALUE = 9;
    public static final int MAX_NUM_ELEMS = 9;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*
    a: input array 
    Returns: result[i] will contain product of all elements of array a except a[i]
    */
    public static int[] computeProduct(int[] a) {
        int length = a.length;
        int[] result = new int[length]; 

        /*Compute the product of elements of array a in forward direction.
        Store product of a[0] to a[i-1] in result[i]*/
        int i, product = 1;
        for (i = 0; i < length; ++i) {
            result[i] = product;
            product = a[i] * product; 
        }

        /*Next compute the product of elements of array a in reverse direction
        So we now compute product of a[n-1] to a[i+1] and multiply it with 
        value in result[i]. In this way result[i] will contain product of 
        a[0]...a[i-1]*a[i+1]....a[n-1]*/
        product = 1;
        for (i = length - 1; i >= 0; --i) {
            result[i] = result[i] * product;
            product = a[i] * product; 
        }
        return result;
    }

    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }

    public static void verifyResult(int[] a, int[] result) {
        int length = a.length;

        for (int i = 0; i < length; ++i) {
            int expectedResult = 1;
            for (int j = 0; j < length; ++j) {
                if (i != j) 
                    expectedResult = expectedResult * a[j];         
            }

            if (expectedResult != result[i])
                handleError();
        }
    }


    public static void test() {
        Random randomGenerator = new Random();

        int length = 1 + (randomGenerator.nextInt(MAX_NUM_ELEMS));
        int[] a = new int[length];

        for (int i = 0; i < length; ++i) {
            a[i] = 1 + (randomGenerator.nextInt(MAX_VALUE));
        } 

        System.out.print("Input  : ");
        printArray(a);

        int[] result = computeProduct(a);

        System.out.print("Output : ");
        printArray(result);

        verifyResult(a, result);

        System.out.println("_________________________________________");
    }


    public static void main(String[] args) {

        for (int i = 0; i < MAX_NUM_TESTS; ++i)
            test();

        System.out.println("Test passed ");

    }


}
