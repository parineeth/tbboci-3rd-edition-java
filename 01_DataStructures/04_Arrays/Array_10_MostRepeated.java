/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Array_10_MostRepeated {

    public static final int MIN_INT = -100000;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }

    /*
    a: array consisting of numbers. A number can have a value between 0 to k-1
    k: k should be <= num elements in array
    */
    public static int findMostRepeated(int[] a,  int k) {
        int n = a.length;

        /*For each number found in the array, go to the index corresponding 
        to the number and add k to the value at the index. */
        int i;
        for (i = 0; i < n; ++i) {
            /*By the time we come to location i, we might have already  
            added k to the value at this location one or more times. So 
            take a[i] % k to get the original value
            */
            int index = a[i] % k;
            a[index] += k;
        }


        int mostRepeated = -1;
        int maxValue = MIN_INT;
        for (i = 0; i < n; ++i) {

            if (a[i] > maxValue) {
                /*Note that index i will give the most repeated number*/
                mostRepeated = i;
                maxValue = a[i];
            }

            /*Get back the original value in the array*/
            a[i] = a[i] % k;
        }

        return mostRepeated;
    }

    public static void test() {
        int[] a = {2, 4, 0, 5, 2, 1, 9, 6, 8, 9, 2, 7};
        int n = 12; /*Number of elements in the array*/
        int k = 10; /*The value of all numbers in the array are less than k and k <= n*/

        System.out.print("Input : ");
        printArray(a);

        int result = findMostRepeated(a, k);

        System.out.println("Most repeated element = " + result);

        int expectedResult = 2;
        if (result != expectedResult)
            handleError();
    }



    public static void main(String[] args) {
        test();
        System.out.println("Test passed ");
    }

}
