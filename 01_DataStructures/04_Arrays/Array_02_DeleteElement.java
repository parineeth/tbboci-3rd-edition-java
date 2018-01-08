/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;
import java.util.Arrays;

class Array_02_DeleteElement {


    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE = 10;

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
    a: input array from which all occurences of an element should be removed
    x: element to be removed
    Return value: output array after removing x 
    */
    public static int[] removeElement(int[] a, int x) {
        int fillPos = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != x) {
                a[fillPos] = a[i];
                fillPos++;
            }
        }

        int[] result = Arrays.copyOf(a, fillPos);
        return result;
    }



    public static void verify(int[] a, int x)  {
        for (int curVal : a) {
            if (curVal == x) {
                /*We found x which should have been deleted*/
                handleError();
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

        /*Pick an element in array to be deleted*/
        int randPos = randomGenerator.nextInt(length1);
        int x = a[randPos];

        System.out.println("Element to remove: " + x);

        System.out.print ("Original array : ");
        printArray(a);


        /*Remove the element x */
        int[] result = removeElement(a, x);

        System.out.print ("After removing : ");
        printArray(result);

        verify(result, x);

        System.out.println("________________________________________________");
    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
