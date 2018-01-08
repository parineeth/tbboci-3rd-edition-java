/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Sort_06_DutchFlag {
    public static final int MAX_NUM_ELEMENTS = 10;
    public static final int MAX_VALUE = 10;
    public static final int MAX_NUM_TESTS = 10;

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /*
    a: input array that has to be sorted. 
    pivotValue: after sorting, all elements smaller than pivot will lie to the 
    left of the pivot and all values that are greater than pivot will lie to the 
    right of the pivot. If there are many pivot values, then they will occur  
    together in the middle 
    */
    public static void dutchSort(int[] a, int pivotValue) {
        int curPos = 0;
        int leftPos = 0;
        int rightPos = a.length - 1;

        while (curPos <= rightPos) {
            int temp;
            if (a[curPos] < pivotValue) {
                /*swap a[leftPos], a[curPos]*/
                temp = a[leftPos]; 
                a[leftPos] = a[curPos];
                a[curPos] = temp;
        
                ++leftPos;  /*Advance the left fill location*/
                ++curPos;   /*Process the next element*/

            } else if (a[curPos] > pivotValue) {
                /*swap a[curPos], a[rightPos];*/
                temp = a[curPos];
                a[curPos] = a[rightPos];
                a[rightPos] = temp;

                /*Advance the right fill location. Since we have newly 
                brought in an element from rightPos to curPos, we have 
                to process the new element. So don't advance curPos*/
                --rightPos; 

            } else {
                ++curPos; /*Process the next element*/
            }
        }
    }

    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }

        System.out.println("");
    }


    public static void test(int[] a, int pivotValue) {
        System.out.println("Pivot value = " + pivotValue);

        System.out.print("Before : ");
        printArray(a);

        /*Perform the sort*/
        dutchSort(a, pivotValue);

        /*Verify the result of dutch sort */
        int i = 0;
        int length = a.length;
        /*All elements less than pivot should occur first*/
        while (i < length && a[i] < pivotValue )
            ++i;

        /*If there are one or more pivot values, they should come next*/
        while (i < length && a[i] == pivotValue)
            ++i;

        /*All elements greater than the pivot value should occur at the end*/
        while ( i < length && a[i] > pivotValue)
            ++i;

        if (i != length)
            handleError();

        System.out.print("After  : ");
        printArray(a); 

        System.out.println("_______________________________________________");
    } 


    public static void main(String[] args)  {
        int[] a = new int[MAX_NUM_ELEMENTS];
        Random randomGenerator = new Random();

        /*Run several number of tests*/
        for (int iter = 0; iter < MAX_NUM_TESTS; ++iter) {
            /*Pick the number of elements in the array randomly*/
            int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMENTS);

            /*Randomly generate the elements in the array*/
            int i;
            for (i = 0; i < length; ++i) {
                a[i] = randomGenerator.nextInt(MAX_VALUE);
            }
        
            /*Pick a random pivot*/
            i = randomGenerator.nextInt(length);
            int pivotValue = a[i];
        
            test(a, pivotValue);
        }

        System.out.println("Test passed");
    }

}
