/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Search_11_RotatedSearch {

    public static final int MAX_NUM_ELEMENTS = 10;
    public static final int MAX_NUM_TESTS = 100;

    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }


    /*
    a: non-empty array that has been sorted and rotated. There should not be any duplicates
    x: element to be searched in the array
    Return value: location of the element in array if found, -1 if not found
    */
    public static int findElement(int[] a, int x) {
        int start = 0, end = a.length - 1;

        while (start <= end) {
            int mid = (start+end)/2;

            if (x == a[mid]) {
                return mid;
            }

            /*Check which portion of array has elements in sorted order*/
            if (a[start] <= a[mid]) {
                /*
                The lower portion (start, mid) is still sorted even after
                rotations. So use this portion for taking decisions
                */
                if (a[start] <= x && x < a[mid]) 
                    end = mid - 1; /*search in region (start, mid-1)*/
                else
                    start = mid + 1; /*search in region (mid+1, end)*/
            } else {
                /*
                The upper portion (mid, end) is sorted even after
                rotations. So use this portion for taking decisions
                */
                if (a[mid] < x && x <= a[end]) 
                    start = mid + 1; /*search in region (mid+1, end)*/
                else
                    end = mid - 1; /*search in region (start, mid-1)*/
            }
        }

        return -1;
    } 


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }

        System.out.println("");
    }


    public static void generateSortedRotatedArray(int[] a) {
        int n = a.length;
        Random randomGenerator = new Random();

        /*Generate the first random value*/
        a[0] = randomGenerator.nextInt(10);
        if (n == 1)
            return;

        /*Generate the remaining random values in increasing sorted order without duplication*/
        int i;
        for (i = 1; i < n; ++i) {
             a[i] = a[i - 1] + 1 + (randomGenerator.nextInt(10));
        }

        /*Randomly decide the number of rotations*/
        int numRotations = randomGenerator.nextInt(n);

        /*Perform the rotations*/
        for (int rotationIter = 0; rotationIter < numRotations; ++rotationIter) {
            int temp = a[n-1];
            for (i = n - 1; i >= 1; --i) {
                a[i] = a[i - 1];
            }
            a[0] = temp;
        }

    }


    public static void test01() {
        Random randomGenerator = new Random();

        /*randomly decide the number of elements*/
        int n = 1 + (randomGenerator.nextInt(MAX_NUM_ELEMENTS));

        int[] a = new int[n];

        /*Generate the sorted rotated array*/
        generateSortedRotatedArray(a);

        System.out.print("Input : ");
        printArray(a);

        for (int index = 0; index < n; ++index) {
            /*Pick the element at the index*/
            int x = a[index];

            /*Search the element using binary search*/
            int actualResult = findElement(a, x);

            System.out.println("Location of " + x + " is " + actualResult);

            /*Verify the result*/
            if (actualResult != index)
                handleError();
        }

        System.out.println("_________________________________________");
    }




    public static void main(String[] args) {
        for (int testNr = 0; testNr < MAX_NUM_TESTS; ++testNr) {
            test01();
        }

        System.out.println("Test passed ");
    }

}


