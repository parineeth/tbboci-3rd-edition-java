/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Algo_Misc_08_Inversions {

    public static final int MAX_NUM_TESTS = 100;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE     = 10;



    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    public static void printArray(int[] a) {
        for (int curVal: a)
            System.out.print(curVal + " ");

        System.out.println("");
    }

    /*Helper function that merges two sorted regions
    a: array where a[left] to a[mid] is sorted and a[mid+1] to a[right] is sorted
        We now need to merge these two regions
    temp: temporary array used for sorting
    Return value: Number of inversions  
    */
    public static int merge(int[] a, int[] temp, int left, int mid, int right) {
        int numInversions = 0;

        int i = left;
        int j = mid + 1;
        int k = left; /*k is used for storing the merged values into temp*/

        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                numInversions += mid + 1 - i;
            }
        }

        /*Handle any pending entries in first region*/
        while (i <= mid) 
            temp[k++] = a[i++];

        /*Handle any pending entries in second region*/
        while (j <= right)
            temp[k++] = a[j++];

        /*Restore the values from temp into a*/
        for (i = left; i <= right; ++i)
            a[i] = temp[i]; 

        return numInversions;
    }

    /* Helper function that performs merge sort
    a: array that should be sorted
    temp: temporary array used for sorting
    left: first index of the region in the array to be sorted
    right: last index of the region in the array to be sorted
    Return value: Number of inversions  
    */
    public static int mergeSort(int[] a, int[] temp, int left, int right) {
        if (left >= right)
            return 0;

        int mid = (left + right) / 2;
     
        int numInversions = mergeSort(a, temp, left, mid);

        numInversions += mergeSort(a, temp, mid + 1, right);

        numInversions += merge(a, temp, left, mid, right);

        return numInversions;
    }


    /*
    a: array of numbers. should have atleast one number
    Return value: number of inversions
    */
    public static int findInversions(int[] a) {
        int numElements = a.length;

        int[] temp = new int[numElements];

        int numInversions =  mergeSort(a, temp, 0, numElements - 1);

        return numInversions;
    }



    public static int bruteForceInversions(int[] a) {
        int numInversions = 0;
        int numElems = a.length;

        for (int i = 1; i < numElems; ++i) {
            for (int j = 0; j < i; ++j) {
                if (a[j] > a[i])
                    numInversions++;
            }
        }

        return numInversions; 
    } 


    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly decide the number of elements in the array*/
        int numElems = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        int[] a = new int[numElems];    

        /*Randomly fill in the array*/
        for (int i = 0; i < numElems; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        printArray(a);

        /*Find the result using the brute force technique*/
        int expectedResult = bruteForceInversions(a);

        /*Find the result using an efficient technique*/
        int result = findInversions(a);
    
        System.out.println("Number of Inversions = " + result);


        /*The two results should match*/
        if (result != expectedResult) {
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


