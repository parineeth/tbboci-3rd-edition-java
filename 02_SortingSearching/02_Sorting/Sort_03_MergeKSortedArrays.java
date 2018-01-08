/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Node {
    int value;
    int arrayNo;
}


class Sort_03_MergeKSortedArrays {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE = 100;
    public static final int MAX_NUM_ARRAYS = 5;
    public static final int MAX_INT = 99999;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*
    heap: min heap of nodes. 
    pos: position of the heap that may need to be fixed
    heapSize: current number of nodes in the heap
    */
    public static void heapify(Node[] heap, int pos, int heapSize) {
        int left = 2 * pos;
        int right = (2 * pos) + 1;
        int ixOfSmallest = pos;

        /*Find which of the three are the smallest - heap[pos] OR left child
        OR right child*/
        if (left < heapSize && heap[pos].value > heap[left].value)
            ixOfSmallest = left;

        if (right < heapSize && heap[ixOfSmallest].value > heap[right].value)
            ixOfSmallest = right;

        if (ixOfSmallest != pos) {
            /*If pos doesn't contain the smallest node,
            then swap the smallest node into pos */
            Node temp = heap[pos];
            heap[pos] = heap[ixOfSmallest];
            heap[ixOfSmallest] = temp;

            heapify(heap, ixOfSmallest, heapSize);
        }
    }


    /*
    arrays: the arrays to be merged. arrays[0] has the first array, arrays[1] has
            the second array and so on
    Return value: the merged results are passed back in this array
    */
    public static int[] mergeKSortedArrays(int[][] arrays) {
        int k = arrays.length;  /*number of arrays*/
        int n = arrays[0].length; /*number of elements in each array*/
        Node[] heap = new Node[k];
        int[] arrPos = new int[k];
        int[] result = new int[k * n];

        /*Store the first element in each array into the heap*/
        int i;
        for (i = 0; i < k; ++i) {
            heap[i] = new Node();
            heap[i].value = arrays[i][0];
            heap[i].arrayNo = i;
            arrPos[i] = 1;
        }

        /*Construct the initial heap using the heapify procedure*/
        for (i = k - 1; i >= 0; --i)
            heapify(heap, i, k);

        /*
        Process the remaining elements in the arrays. When all elements in the
        arrays have been processed, MAX_INT will be present at root of heap
        */
        int resIndex = 0;
        while (heap[0].value != MAX_INT) {
            /*
            root of the heap will have the lowest value. So store
            it into the result
            */
            result[resIndex++] = heap[0].value;

            int arrayNo = heap[0].arrayNo;
            int pos = arrPos[arrayNo];

            /*
            If the root belongs to array x, then replace the root with
            the next element in array x
            */
            if (pos >= n) {
                /*If we have exhausted all elements in the array, 
                then insert MAX_INT into the heap*/
                heap[0].value = MAX_INT;
                heap[0].arrayNo = arrayNo;
            } else {
                heap[0].value = arrays[arrayNo][pos];
                heap[0].arrayNo = arrayNo;
            }

            /*Re-adjust the heap after replacing the root*/
            heapify(heap, 0, k);

            arrPos[arrayNo]++;
        }

        return result;
    }



    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }

        System.out.println("");
    }


    public static void test01(int numArrays, int numElements) {
        int[][] arrays = new int[numArrays][numElements];
        Random randomGenerator = new Random();

        int i, j;
        for (i = 0; i < numArrays ; ++i) {
            for (j = 0; j < numElements; ++j) {
                arrays[i][j] = randomGenerator.nextInt(MAX_VALUE);
            }
        }

        for (i = 0; i < numArrays; ++i) {
            Arrays.sort(arrays[i]);
            System.out.print("Array " + i + ": ");
            printArray(arrays[i]);
        }

        int[] result = mergeKSortedArrays(arrays);

        System.out.print("Output : ");
        printArray(result);

        int[] expectedResult = new int[numArrays * numElements];
        int pos = 0;
        for (i = 0; i < numArrays ; ++i) {
            for (j = 0; j < numElements; ++j) {
                expectedResult[pos] = arrays[i][j];
                pos++; 
            }
        }
        
        Arrays.sort(expectedResult);


        for (i = 0; i < numArrays * numElements; ++i)
            if (result[i] != expectedResult[i])
                handleError();
    }



    public static void main(String[] args) {
        Random randomGenerator = new Random();

        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            int numArrays = 1 + randomGenerator.nextInt(MAX_NUM_ARRAYS);
            if (numArrays < 2)
                numArrays = 2;

            int numElements = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

            test01(numArrays, numElements);

            System.out.println("_________________________________________________");
        }
        System.out.println("Test passed");
    }

}
