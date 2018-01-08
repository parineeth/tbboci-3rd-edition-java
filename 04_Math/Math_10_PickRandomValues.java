/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;
import java.util.Arrays;

class Math_10_PickRandomValues {


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*Returns a random number between low and high, low and high are inclusive*/
    public static int getRandomNumber(int low, int high) {
        Random randomGenerator = new Random();
        int randomNum = low + randomGenerator.nextInt(high - low + 1);

        return randomNum;
    }

    /*
    a: input array of unsorted numbers
    k: number of random values to pick
    Return value: the k random values will be stored in result
    */
    public static int[] pickRandomValues(int[] a, int k) {
        int n = a.length;
    
        /*We will need to rearrange the elements in array a. Since the user
        may expect array a to remain unchanged, we are allocating memory 
        for another array b and copying elements of a into b*/
        int[] b = Arrays.copyOf(a, n);

        int[] result = new int[k];

        int lastIndex = n-1; 
        int j = 0;
        while (j < k) {
            /*Pick a random position from 0 to lastIndex*/
            int randIndex = getRandomNumber(0, lastIndex);

            /*Store b[randIndex] in the result*/
            result[j] = b[randIndex];

            /*Let's say original value at b[randIndex] is x.
            b[randIndex] is now overwritten with b[lastIndex].  
            So we cannot choose x again in the next iterations*/
            b[randIndex] = b[lastIndex];

            --lastIndex;
            ++j; 
        }
        return result;
    }


    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }



    public static void main(String[] args)  {
        int[] a = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        int k = 5;  /*number of random values to be chosen*/
        int[] result;

        System.out.print("Input : ");
        printArray(a);

        result = pickRandomValues(a, k);

        System.out.print("The random values are: ");
        printArray(result); 

        System.out.println("Test passed");
    
    }

}


