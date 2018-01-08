/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Array_01_NextGreatest {

    public static final int MAX_NUM_ITEMS  = 10;
    public static final int MAX_VALUE = 100;
    public static final int INVALID_NUMBER = -1;

    private static void handleError() {
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
    a: non-empty array in which each element should be replaced with next greatest
    */
    public static void replaceWithNextGreatest(int[] a) {
        int n = a.length;
        int nextGreatest = a[n-1];
        a[n-1] = INVALID_NUMBER;  

        /*Process the array backwards*/
        for (int i = n-2; i >= 0; --i) {
            int temp = a[i];

            a[i] = nextGreatest;

            if (temp > nextGreatest)
                nextGreatest = temp;
        }
    }


    public static void test_01() {
        Random randomGenerator = new Random();

        /*Choose a random number of elements*/
        int n = 1 + randomGenerator.nextInt(MAX_NUM_ITEMS);

        int[] a = new int[n];
        int[] b = new int[n];

        /*Generate n random values and store them in arrays a and b*/
        int i;
        for (i = 0; i < n; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
            b[i] = a[i];
        }

        System.out.print("Input: ");
        printArray(a);

        /*Replace with next greatest using the efficient algorithm. The result will be in array a*/
        replaceWithNextGreatest(a);

        /*Replace with next greatest using brute force approach. The result will be in array b*/
        for (i = 0; i < n - 1; ++i) {
            int max = 0;
            for (int j = i + 1; j < n; ++j) {
                if (b[j] > max)
                    max = b[j];
            }
            b[i] = max;
        }
        b[n - 1] = -1;

        System.out.print("Output: ");
        printArray(a);

        /*Compare the efficient algortihm result with the brute force approach*/
        for (i = 0; i < n; ++i) {
            if (a[i] != b[i])
                handleError();
        }
    }

    

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            test_01();

            System.out.println("__________________________________");

        }
        
        System.out.println("Test passed");
    }


}
