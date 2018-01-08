/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Array_05_ArrayRotation {


    /*Helper function which reverses an array in region (low, high)
    a: array which needs to be reversed
    low: lower index of region to be reversed
    high: higher index of region to be reversed
    */
    public static void reverseArray(int[] a, int low, int high) {

        while (low < high) {
            int temp = a[low];
            a[low] = a[high];
            a[high] = temp;
            low++;
            high--;
        }
    }

    /*Main function to rotate a 1 dimensional array
    a: array which should be rotated. 
    numRotations: how many times to rotate the array. Should be >= 0
    */
    public static void rotateArray(int[] a, int numRotations) {
        int length = a.length;
        if (length == 0)
            return;

        /*Suppose an array has a length of 5, every time we rotate by 
        5 locations, we end up with the same array. So obtain numRotations 
        value from 0 to length - 1*/
        numRotations = numRotations % length;

        if (numRotations == 0)
            return;

        reverseArray(a, 0, length - 1);

        reverseArray(a, 0, numRotations - 1);

        reverseArray(a, numRotations, length - 1);
    }


    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }

    public static void compareArrays(int[] input, int[] expectedResult) {
    
        for (int i = 0; i < input.length; ++i) {
            if (input[i] != expectedResult[i]) 
                handleError();
        }

    }   


    public static void performTest(int[] input, int numRotations, int[] expectedResult) {
        System.out.println("Num Rotations = " + numRotations);

        System.out.print("Before Rotating: ");
        printArray(input);

        rotateArray(input, numRotations);
        compareArrays(input, expectedResult);

        System.out.print("After  Rotating: ");
        printArray(input); 

        System.out.println("________________________________________"); 
    }


    public static void test1() {
        int[] input = {10};
        int[] expectedResult = {10};
        int numRotations = 1;

        performTest(input, numRotations, expectedResult);
    }


    public static void test2() {
        int[] input = {10, 20, 30, 40, 50};
        int[] expectedResult = {50, 10, 20, 30, 40};
        int numRotations = 1;

        performTest(input, numRotations, expectedResult);
    }


    public static void test3() {
        int[] input = {10, 20, 30, 40, 50};
        int[] expectedResult = {40, 50, 10, 20, 30};
        int numRotations = 2;

        performTest(input, numRotations, expectedResult);   
    }


    public static void test4() {
        int[] input = {10, 20, 30, 40, 50};
        int[] expectedResult = {20, 30, 40, 50, 10};
        int numRotations = 4;

        performTest(input, numRotations, expectedResult);   
    }

    public static void test5() {
        int[] input = {10, 20, 30, 40, 50};
        int[] expectedResult = {10, 20, 30, 40, 50};
        int numRotations = 5;

        performTest(input, numRotations, expectedResult);       
    }



    public static void main(String[] args)  {
        test1();
        test2();
        test3();
        test4();
        test5();

        System.out.println("Test passed");
    }

}
