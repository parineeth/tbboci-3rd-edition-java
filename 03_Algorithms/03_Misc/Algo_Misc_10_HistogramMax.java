/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Misc_10_HistogramMax {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }


    /*histoHeight: histoHeight[i] has height of ith bar. Should not be empty
    Return value: returns the area of the largest rectangle in the histogram
    */
    public static int findMaxArea(int[] histoHeight) {
        int area, poppedIndex = 0, maxArea = 0;
        int n = histoHeight.length;
        Stack<Integer> heightStack = new Stack<Integer>(); 
        Stack<Integer> indexStack = new Stack<Integer>(); 

        for (int i = 0; i < n; ++i) {

            if ( heightStack.empty() || 
            histoHeight[i] > heightStack.peek() ) {
                /*push height and index of current bar*/
                heightStack.push(histoHeight[i]);
                indexStack.push(i);

            } else if (histoHeight[i] < heightStack.peek()) {

                while (!heightStack.empty() && 
                histoHeight[i] < heightStack.peek()) {
                    /* keep popping from index and height stacks*/
                    poppedIndex = indexStack.pop();
                    int poppedHeight = heightStack.pop();

                    /* calculate the area from popped bar 
                    to the current bar. 
                    Area = popped height * difference of index of 
                    current bar and popped bar*/
                    area =  poppedHeight * (i - poppedIndex);

                    if (area > maxArea)
                        maxArea = area;
                }

                /*push height of the current bar into height stack */
                heightStack.push(histoHeight[i]);


                /*push the LAST POPPED INDEX into the index stack, 
                since we can form a rectangle from the LAST POPPED INDEX 
                to the current bar (where the height of the rectangle is 
                height of current bar)*/
                indexStack.push(poppedIndex);
            }
        }


        /*Process the remaining elements in the stacks*/
        while (!heightStack.empty() ) {
            poppedIndex = indexStack.pop();
            area = heightStack.pop() * (n - poppedIndex);

            if (area > maxArea)
                maxArea = area;
        }

        return maxArea;
    }





    public static void test01() {
        int[] histoHeight = {3, 2, 5, 6, 1, 4, 4};

        int maxArea = findMaxArea(histoHeight);

        System.out.print("Histogram: ");
        printArray(histoHeight);
        System.out.println("Max area = " + maxArea);

        if (maxArea != 10)
            handleError();

        System.out.println("____________________________________");
    }


    public static void test02() {
        int[] histoHeight = {2, 4, 2, 1};

        int maxArea = findMaxArea(histoHeight);

        System.out.print("Histogram: ");
        printArray(histoHeight);
        System.out.println("Max area = " + maxArea);

        if (maxArea != 6)
            handleError();

        System.out.println("____________________________________");
    }



    public static void main(String[] args) {
        test01();

        test02();

        System.out.println("Test passed ");
    }

}
