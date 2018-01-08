/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_15_OddOccuringNum {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    /*
    a: array consisting of numbers, where one element occurs odd number of times 
        while remaining elements occur even number of times 
    Return value: element that occurs odd number of times
    */
    public static int findOddOccurence(int[] a) {
        /*XOR all the elements*/
        int result = 0;
        for (int i = 0; i < a.length; ++i) {
            result = result ^ a[i];
        }

        return result;
    }

    public static void test() {
        int[] a = {1, 8, 4, 8, 2, 1, 4};

        System.out.print("Input : ");
        printArray(a);

        int result = findOddOccurence(a);

        System.out.println("Element that occurs odd number of times = " + result);

        int expectedResult = 2;
        if (result != expectedResult)
            handleError();
    }



    public static void main(String[] args) {
        test();

        System.out.println("Test passed ");

    }

}
