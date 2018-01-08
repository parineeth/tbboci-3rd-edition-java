/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;
import java.util.Arrays;

class Array_12_3ElementSum {


    public static final int MAX_NUM_TESTS = 50;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE = 10;




    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }

    /*
    a: input array 
    S: the addition of any 3 elements in array should be equal to S
    Return value: Number of 3 elements subsets where sum of 3 elements is equal to S
    */
    public static int find3ElementSum(int[] a, int S) {
        int length = a.length;

        /*Sort the array in non-decreasing order*/
        Arrays.sort(a);

        int count = 0;
        for (int i = 0; i < length - 2; ++i) {
            /*Choose a[i]. Start picking the other two elements 
            from opposite ends. So start choosing from i+1 on one side
            and length - 1 on the other side
            */
            int low = i + 1;
            int high = length - 1;
            while (low < high) {
                int total = a[i] + a[low] + a[high];
                if (total == S) {
                    count++;
                    System.out.println(a[i] + " + " + a[low] + 
                        " + " + a[high] + " = " + total);
                    ++low;
                    --high;
                } else if (total > S) {
                    --high; /*We need to pick a smaller element*/
                } else {
                    ++low; /*We need to pick a larger element*/
                }
            }
        }

        return count;
    }



    public static void test() {
        Random randomGenerator = new Random();
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS); /*randomly choose size of array*/
        int[] a = new int[length];
        int S = 0;


        for (int i = 0; i < length; ++i) {
            /*Randomly choose the value of elements in the array*/
            a[i] = randomGenerator.nextInt(MAX_VALUE);

            /*Alternate elements will be negative*/
            if (i % 2 == 0) 
                a[i] = -1 * a[i];
        }

        System.out.print("Input : ");
        printArray(a);

        int result = find3ElementSum(a, S);

        System.out.println("________________________________________________");
    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed"); 
    }
}
