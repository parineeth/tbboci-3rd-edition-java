/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Search_12_FindKLargest {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 20;
    public static final int MAX_K_VALUE = 10; 
    public static final int MAX_VALUE  = 100;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /* Helper function to perform heapify
    heap: min heap.  Maximum number of elements in heap is k
    pos: position of the heap that may need to be fixed
    heapSize: current number of nodes in the heap
    */
    public static void heapify(int[] heap, int pos, int heapSize) {
        int left = 2 * pos;
        int right = (2 * pos) + 1;
        int ixOfSmallest = pos;

        /*Find which of the three are the smallest - heap[pos] OR left child
        OR right child*/
        if (left < heapSize && heap[pos] > heap[left])
            ixOfSmallest = left;
        if (right < heapSize && heap[ixOfSmallest] > heap[right])
            ixOfSmallest = right;


        if (ixOfSmallest != pos) {
            /*
            If pos doesn't contain the smallest value,
            then swap the smallest value into pos 
            */
            int temp = heap[pos];
            heap[pos] = heap[ixOfSmallest];
            heap[ixOfSmallest] = temp;

            /*Recursively re-adjust the heap*/
            heapify(heap, ixOfSmallest, heapSize);
        }
    }

    /*Main function to find the k largest elements
    a: non-empty array in which we have to find the k largest elements
    k: the number of largest elements that we need to find. k <= length of array
    Return value: the k largest elements will be stored in the heap and returned
    */
    public static int[] findKLargest(int[] a, int k) {
        int n = a.length;
        int[] heap = new int[k];

        /*Store the first k elements of the array in the heap*/
        int i;
        for (i = 0; i < k; ++i)
            heap[i] = a[i];

        /*Construct the initial min-heap*/
        for (i = k - 1; i >= 0; --i)
            heapify(heap, i, k);

        for (i = k; i < n; ++i) {
            /*The root of heap will have the smallest item in the heap
            If current item in array is greater than root of heap, then
            place current item into root of heap and re-adjust the heap
            */
            if (a[i] > heap[0]) {
                heap[0] = a[i];
                heapify(heap, 0, k);
            }
        }
        return heap;
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }

        System.out.println("");
    }

    public static void reverseArray(int[] a) {
        int n = a.length;
        for (int i = 0, j = n - 1; i < n / 2; ++i, --j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    } 


    public static void test01() {
        int num_elements = MAX_NUM_ELEMS;
        int[] a = new int[num_elements];
        Random randomGenerator = new Random();

        /*Randomly generate the k value*/
        int k = 1 + randomGenerator.nextInt(MAX_K_VALUE);

        System.out.println("K = " + k);

        /*randomly generate the numbers in the array*/
        int i;
        for (i = 0; i < num_elements; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        System.out.print("Input  : ");
        printArray(a);

        /*Find the k largest elements using the heap*/
        int[] heap = findKLargest(a, k);

        /*Sort the array in ascending order*/
        Arrays.sort(a);

        /*Reverse the array to arrange the elements in descending order*/
        reverseArray(a);

        /*Print the top k elements in the heap. Note that elements in the heap will themselves 
        NOT be in sorted order*/
        System.out.print("Output : ");
        printArray(heap);

        /*Verify that each element in the heap is present in the top k elements in the sorted array */
        for (int curHeapVal : heap) {
            boolean found = false;
            for (int j = 0; j < k; ++j) {
                if (curHeapVal == a[j] ) {
                    found = true;
                    break;
                }
            }
        
            if (!found)
                handleError();
        }
    }



    public static void main(String[] args)  {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test01();
            System.out.println("_________________________________________________\n\n");
        }

        System.out.println("Test passed");
    }


}
