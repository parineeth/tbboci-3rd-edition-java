/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Search_14_StreamMedian {


    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE     = 100;
    public static final int INITIAL_CAPACITY = 10;


    public static void handleError() {
        System.out.println(  "Test failed");
        System.exit(1);
    }

    /*
    minHeap: priority queue for storing the larger half of numbers in the stream
    maxHeap: priority queue for storing the smaller half of numbers in the stream
    curValue: value of the current item in the stream 
    Return value: Median of the stream 
    */
    public static int getMedian(PriorityQueue<Integer> minHeap, 
            PriorityQueue<Integer> maxHeap, int curValue) {

        /*If minHeap is empty, add the current value to minHeap.
        If minHeap is non-empty, the top of minHeap will contain the smallest
        among the larger half of numbers in the stream. If current value is 
        larger than the top of minHeap, then add it to minHeap otherwise add 
        it to maxHeap
        */
        if (minHeap.isEmpty()) 
            minHeap.add(curValue);
        else if (curValue >= minHeap.peek())
            minHeap.add(curValue);
        else
            maxHeap.add(curValue);


        /*If minHeap has more than 1 element than the maxHeap, move the top 
        of minHeap into the maxHeap and vice versa. */
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.add(minHeap.remove());
        } else if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }


        /*If both heaps are of the same size, then the median will be the 
        average of the top element in the two heaps. Otherwise the median is 
        the top of the heap with more elements
        */
        int median;
        if (minHeap.size() == maxHeap.size()) {
            median = (minHeap.peek() + maxHeap.peek()) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            median = minHeap.peek();
        } else {
            median = maxHeap.peek();
        }

        return median;
    }

    public static void printArray(int[] a, int numElems) {
        for (int i = 0; i < numElems; ++i) {
            System.out.print(  a[i] + " ");
        }
    
        System.out.println(  );
    }



    public static int sortAndGetMedian(int[] a, int numElems) {
        int[] b = new int[numElems];

        /*Copy the values of a into b as we don't want to change the contents of a*/
        for (int i = 0; i < numElems; ++i) {
            b[i] = a[i];
        }

        Arrays.sort(b);

        System.out.print(  "Sorted : " );
        printArray(b, numElems);

        int middlePos = numElems / 2;
        int median;
        if (numElems % 2 == 0) {
            median = (b[middlePos - 1] + b[middlePos]) / 2;
        } else {
            median = b[middlePos];
        }

        return median;
    }


    public static void generateArray(int[] a) {
        int length = a.length;
        Random randomGenerator = new Random();
    
        for (int i = 0; i < length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }
    }


    public static void test() {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(INITIAL_CAPACITY, 
                                    Collections.reverseOrder());
        Random randomGenerator = new Random();
    

        /*Randomly pick the number of elements*/
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        int[] a = new int[length];

        /*Generate numbers in the array */
        generateArray(a);

        for (int i = 0; i < length; ++i) {
            /*Go on adding one element at a time and finding the median*/
            System.out.print(  "Stream : ");
            printArray(a, i+1);

            /*Find the median using the efficient technique*/
            int median = getMedian(minHeap, maxHeap, a[i]);

            /*Find the median using sorting*/
            int expectedResult = sortAndGetMedian(a, i+1);

            System.out.println(  "Median = " + median);

            if (median != expectedResult)
                handleError();

            System.out.println(  "_________________________________________________");
        
        }

    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println(  "Test passed");
    }

}


