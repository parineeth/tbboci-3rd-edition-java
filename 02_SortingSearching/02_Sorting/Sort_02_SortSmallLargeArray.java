/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Sort_02_SortSmallLargeArray {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS  = 10;
    public static final int MAX_VALUE = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*
    a: array of size m+n which has m elements at beginning and n spaces at end
    b: array of size n with n elements
    m: number of elements in array a
    n: number of elements in array b
    */
    public static void mergeArrays(int[] a, int[] b, int m, int n)  {
        int i = m - 1;
        int j = n - 1;
        int fillPos = m + n - 1; /*Start filling from the rear of the array*/

        while (i >= 0 && j >= 0) {
            if (a[i] > b[j]) {
                a[fillPos--] = a[i--];
            } else {
                a[fillPos--] = b[j--];
            }
        }

        /*Fill up the remaining elements of array a if any*/
        while (i >= 0)
            a[fillPos--] = a[i--];

        /*Fill up the remaining elements of array b if any*/
        while (j >= 0)
            a[fillPos--] = b[j--];
    }



    public static void printArray(int[] m, int numElems) {
        int prev = 0;

        for (int i = 0; i < numElems; ++i) {
            System.out.print(m[i] + " ");

            if (i > 0 && prev > m[i])
                handleError();

            prev = m[i];
        }

        System.out.println("");
    }


    /*Sort in ascending order using bubble sort*/
    public static void sortArray(int[] a, int n) {
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }


    public static void test01() {
        Random randomGenerator = new Random();

        /*Generate two random values m and n*/
        int n = randomGenerator.nextInt(MAX_NUM_ELEMS);
        int m = randomGenerator.nextInt(MAX_NUM_ELEMS);

        /*Let a have size of (m+n) and b have a size of n*/
        int[] a = new int[m+n];
        int[] b = new int[n];


        /*Generate m random values in a*/
        int i;
        for (i = 0; i < m; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        /*Generate n random values in b*/
        for (i = 0; i < n; ++i) {
            b[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        /*Sort the two arrays*/
        sortArray(a, m);
        sortArray(b, n);

        System.out.print("Input1 : ");
        printArray(a, m);
        System.out.print("Input2 : ");
        printArray(b, n);

        /*Merge the arrays*/
        mergeArrays(a, b, m, n);

        System.out.print("Output : ");
        printArray(a, m +n);

        System.out.println("____________________________________________");

    }


    public static void main(String[] args)  {

        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test01();
        }

        System.out.println("Test passed");

    }

}


