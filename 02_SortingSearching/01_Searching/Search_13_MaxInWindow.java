/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.lang.*;
import java.util.*;

class Search_13_MaxInWindow {


    public static final int MAX_NUM_STACK_ELEMS = 100;
    public static final int MAX_VALUE = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void printArray(int[] a) {    
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println();
    }


    public static void compare(int[] result, int[] expected) {
        int n = result.length;
        for (int i = 0; i < n; ++i) {
            if (result[i] != expected[i])
                handleError();
        }
    }



    /*
    a: array for which we have to find the maximum in every window of size k
    k: size of the window
    dq: double ended queue that stores array indices
    Returns: array that contains the result (maximum in every window of size k)
    */
    public static int[] findWindowMax(int[] a, int k, ArrayDeque<Integer> dq) {
        int n = a.length;
        int[] result = new int[n - k +1];
        int pos = 0;
        for (int i = 0; i < n ; ++i) {
            /*Remove the elements outside the current window from 
            front of dequeue*/
            while (!dq.isEmpty() && (dq.getFirst() + k <= i))
                dq.removeFirst();

            /*Remove all elements that are smaller than or equal to 
            current element from the rear of the dequeue*/
            while (!dq.isEmpty() && a[i] >= a[dq.getLast()] )
                dq.removeLast();

            /*Push the index of current element into the end of dequeue*/
            dq.addLast(i);

            if (i >= k-1) {
                /*Front of dequeue has index of maximum element 
                for the current window*/
                result[pos] = a[dq.getFirst()];
                ++pos;
            }
        }
        return result;
    }



    public static void test01() {
        int[] a = {2, -1, 4, 3, 2, 6, 0, 7, 8, 10, 3, 2};
        int[] expected1 = {2, -1, 4, 3, 2, 6, 0, 7, 8, 10, 3, 2};
        int[] expected2 = {2, 4, 4, 3, 6, 6, 7, 8, 10, 10, 3};
        int[] expected3 = {4, 4, 4, 6, 6, 7, 8, 10, 10, 10};
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();

        System.out.print(  "Input                  : ");
        printArray(a);

        for (int k = 1; k <= 3; ++k) {
            int[] result = findWindowMax(a, k, dq);

            System.out.print("Max in window of size " + k + ": "); 
            printArray(result);

            if (k == 1)
                compare(result, expected1);
            else if (k == 2)
                compare(result, expected2);
            else if (k == 3)
                compare(result, expected3); 
        }


    }


    public static void main(String[] args) {
        test01();
        System.out.println("Test passed");
    }

}
