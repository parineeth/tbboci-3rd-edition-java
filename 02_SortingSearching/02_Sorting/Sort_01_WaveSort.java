/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;
import java.util.Arrays;

class Sort_01_WaveSort {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE = 100;

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
    a: non-empty array that has to be sorted so that the values in it 
        alternatively increase and decrease. The elements should be unique
    */
    public static void waveSort(int[] a) {
        /*Sort the elements in ascending order*/
        Arrays.sort(a);

        /*Swap the neighboring elements*/
        for (int i = 1; i < a.length - 1; i += 2) {
            int temp = a[i];
            a[i] = a[i+1];
            a[i+1] = temp;
        }
    }

    /*Generate an array without duplicates*/
    public static void generateArray(int[] a) {
        Random randomGenerator = new Random();
    
        for (int i = 0; i < a.length; ++i) {
            int temp;
            while (true) {
                /*Generate a random number*/
                temp = randomGenerator.nextInt(MAX_VALUE);

                /*Search if the random number is already present*/
                boolean found = false;
                for (int j = 0; j < i; ++j) {
                    if (a[j] == temp) {
                        found = true;
                        break;
                    } 
                }

                /*If the random number is already present, then try again*/
                if (found)
                    continue;
                else
                    break;
            }

            a[i] = temp;
        }

    
    }


    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly pick the number of elements*/
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        int[] a = new int[length];

        /*Generate numbers in the array without any duplicates*/
        generateArray(a);

        System.out.print ("Input : ");
        printArray(a);

        /*Perform the wave sort*/
        waveSort(a);

        System.out.print ("Output: ");
        printArray(a);

    
        boolean isPrevIncreasing = false;
        if (length > 1 && a[0] < a[1]) {
            isPrevIncreasing = true;
        }
            
        /*Verify if the elements are alternately increasing and decreasing*/
        for (int i = 1; i < length - 1; ++i) {
            boolean isCurIncreasing = false;
            if (a[i] < a[i+1])
                isCurIncreasing = true;

            if (isPrevIncreasing == isCurIncreasing)
                handleError();

            isPrevIncreasing = isCurIncreasing;
        }

        System.out.println("_________________________________________________");
    }


    public static void main(String[] args)  {   
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }


}



