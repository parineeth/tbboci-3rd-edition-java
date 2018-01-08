/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Array_04_MoveZeroes {


    public static final int MAX_NUM_TESTS = 100;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE = 2;

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
    a: input array in which the zeroes should be moved to one end
    */
    public static void moveZeroes(int[] a) {
        int length = a.length;

        int left = 0;
        int right = length - 1;

        while (left < right) {
            /*Locate the first zero from the left*/
            while (left < length && a[left] != 0)
                left++;

            /*Locate first non-zero from the right*/
            while (right >= 0 && a[right] == 0)
                right--;

            if (left < right) {
                /*Swap a[left] and a[right]*/
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
            }
        }
    }



    public static void verify(int[] a)  {
        int length = a.length;
        boolean foundZero = false;

        for (int curVal : a) {
            if (curVal == 0) {
                foundZero = true;
            }
            else {
                /*We have found a non-zero. Since all zeroes are at the end,
                we should not have found a zero till now*/
                if (foundZero)
                    handleError();
            }
        }
    }



    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly decide the number of elements*/
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        int[] a = new int[length];

        /*Fill the array with random values*/
        for (int i = 0; i < length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        System.out.print ("Original array : ");
        printArray(a);

        /*Move zeroes to the right */
        moveZeroes(a);

        System.out.print ("After moving 0 : ");
        printArray(a);

        verify(a);

        System.out.println("________________________________________________");
    }


    public static void main(String[] args)  {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
