/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Sort_07_KthSmallest {

    public static final int MAX_NUM_TESTS = 100;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE     = 100;
    public static final int MAX_INT       = 1000000;

    public static void handleError() {
        System.out.println("Test failed ");
        System.exit(1);
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
    
        System.out.println("");
    }


    /* Helper function for finding the kth smallest element in an array
    * This function, picks a pivot and arranges all numbers smaller than pivot to
    * the left of the pivot and all numbers greater than pivot to the right of pivot
    * a: array on which the partition operation should be performed
    * left: index of the starting element of the partition in the array
    * right: index of the ending element of the partition in the array
    * Return value: index of the pivot element of the partition
    */
    public static int partition(int[] a, int left, int right) {
        int numElements = right - left + 1;
        Random randomGenerator = new Random();
        int randPos = left + (randomGenerator.nextInt(numElements));

        /*pick a random element and swap it with the last element*/
        int temp = a[randPos];
        a[randPos] = a[right];
        a[right] = temp;

        /*The last element is treated as the pivot*/
        int pivot = a[right];

        int i = left;
        for (int j = left; j <= right - 1; ++j) {
            if (a[j] <= pivot) {
                /*If i is not equal to j, then a[i] has a value
                greater than pivot and a[j] has a value less than
                pivot. So swap a[i] and a[j]*/
                if (i != j) {
                    temp = a[i]; 
                    a[i] = a[j];
                    a[j] = temp;
                }
                ++i;
            } 
        }

        /*Swap a[i] and the pivot that is at a[right]*/
        temp = a[i];
        a[i] = a[right];
        a[right] = temp;

        return i; /*the pivot is now at i. So return i*/
    }


    /*Finds the kth smallest element in an array
    a: array in which the kth smallest element should be found
    k: value of k (can range from 0 to a.length - 1)
    Returns: the kth smallest element
    */
    public static int findKthSmallest(int[] a, int k) {
        int left = 0;
        int right = a.length - 1;

        while (k >= left && k <= right) {
            int pivotPos = partition(a, left, right);
    
            if (pivotPos == k)
                return a[pivotPos];
            else if (pivotPos < k) {
                left = pivotPos + 1;
            } else {
                right = pivotPos - 1;
            }
        }

        return MAX_INT; /*incorrect k value was specified*/
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
        int k = randomGenerator.nextInt(length);

        int[] a = new int[length];

        /*Fill the array with random values*/
        generateArray(a);

        printArray(a);

        /*Find the result using an efficient technique*/
        int result = findKthSmallest(a, k);

        System.out.println("K = " + k + ", Kth smallest is " + result);
    
        /*Find the expected result using sorting*/
        Arrays.sort(a);
        int expectedResult = a[k];


        /*The two results should match*/
        if (result != expectedResult ) {
            handleError();
        }


        System.out.println("________________________________________________");

    }


    public static void main(String[] args)  {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
