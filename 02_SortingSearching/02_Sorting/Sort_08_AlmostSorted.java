/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Sort_08_AlmostSorted {


    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE     = 100;


    public static void handleError() {
        System.out.println(  "Test failed");
        System.exit(1);
    }




    /*
    a: almost sorted array that should be fully sorted
    k: max distance that any element should be moved so that array becomes sorted
    */
    public static void sortAlmostSortedArray(int[] a, int k) {
        int length = a.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        int readPos = 0, writePos = 0;

        /*Fill in the first k values into the minHeap. If length is less than k
        then we have to fill in only length number of elements */
        for (readPos = 0; readPos < Math.min(k, length); ++readPos) {
            minHeap.add(a[readPos]);
        }

        /*Add the element a[readPos] to the heap and then pop out a value.
        Value popped from heap will contain the next smallest value. Add the   
        value popped from the heap back into the array at the write position*/
        while (readPos < length) {
            minHeap.add(a[readPos]);
            a[writePos] = minHeap.remove();

            readPos++;
            writePos++;
        }

        /*Pop out the remaining elements in the heap and store them back into 
        the array*/
        while (!minHeap.isEmpty()) {
            a[writePos] = minHeap.remove();
            ++writePos;
        }
    }

    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
    
        System.out.println(  );
    }


    public static void generateArray(int[] a, int k) {
        Random randomGenerator = new Random();
        int length = a.length;

        /*We have to generate values in the array, so that it is almost sorted
        The maximum distance that any element should be moved so that array becomes
        sorted is k.*/
    
        for (int i = 0; i < length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        /*First completely sort the array*/
        Arrays.sort(a);

        /*Divide the array into regions of the size k*/
        int maxNumRegions = (length + k - 1) / k;

        for (int regionNr = 0; regionNr < maxNumRegions; ++regionNr) {
            /*Within each region of the array, perform k random swaps*/
            for (int numSwaps = 0; numSwaps < k; ++numSwaps) {
                int index1 = (regionNr * k) + randomGenerator.nextInt(k);
                int index2 = (regionNr * k) + randomGenerator.nextInt(k);
    
                if (index1 < length && index2 < length) {
                    int temp = a[index1];
                    a[index1] = a[index2];
                    a[index2] = temp;
                }
            }
        }
    }


    public static void test() {
        Random randomGenerator = new Random();
    
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);
        int k = 1 + randomGenerator.nextInt(length);

        int[] a = new int[length];

        generateArray(a, k);

        System.out.println("K = " + k);
        System.out.print("Input  : ");
        printArray(a);

        /*Copy values of a into b*/
        int[] b = new int[length];
        int i;
        for (i = 0; i < length; ++i)
            b[i] = a[i];


        /*Sort the almost sorted array using the efficient technique*/
        sortAlmostSortedArray(a, k);

        System.out.print(  "Output : ");
        printArray(a);


        /*Perform normal sort on b*/
        Arrays.sort(b);

        /*a and b should match*/
        for ( i = 0; i < length; ++i) {
            if (a[i] != b[i])
                handleError();
        }

        System.out.println(  "_________________________________________________");
    
    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    
    }
}

