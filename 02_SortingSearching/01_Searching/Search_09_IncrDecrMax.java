/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Search_09_IncrDecrMax {

    public static final int MIN_INT = -100000;

    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }

    /*
    a: array where elements first increase and then decrease
    Return value: maximum element in the array
    */
    public static int findMax(int[] a) {
        int start = 0, end = a.length - 1, maxElement = MIN_INT;

        while (start <= end) {
            /*If only one element is left, then it is the max element*/
            if (start == end)   {
                maxElement = a[start];
                break;
            }

            /*If two elements are left, find the maximum of the two*/
            if (start + 1 == end) {
                maxElement = a[start];
                if (a[start+1] > maxElement)
                    maxElement = a[start+1];
                break;
            }

            /*If there are more than two elements left, then inspect the
            middle element in between start and end*/
            int mid = (start+end)/2;

            /*If middle element is greater than previous element and also 
            greater than the next element, then it is the maximum element*/
            if (a[mid - 1] < a[mid] && a[mid] > a[mid + 1]) {
                maxElement = a[mid];
                break;
            }

            /*We have not yet been able to find the maxElement. So modify 
            the range in which to search in the next iteration */
            if (a[mid - 1] < a[mid] && a[mid] < a[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return maxElement;
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }

        System.out.println("");
    }



    public static void test01() {
        int[] a = {50, 40, 30, 20, 10};

        printArray(a);
        int result = findMax(a);

        System.out.println("Maximum element = " + result);

        if (result != 50)
            handleError();

        System.out.println("_________________________________________");
    }

    public static void test02() {
        int[] a = {30, 50, 40, 30, 20};

        printArray(a);
        int result = findMax(a);

        System.out.println("Maximum element = " + result);

        if (result != 50)
            handleError();

        System.out.println("_________________________________________");
    }

    public static void test03() {
        int[] a = {30, 40, 50, 20, 10};

        printArray(a);
        int result = findMax(a);

        System.out.println("Maximum element = " + result);

        if (result != 50)
            handleError();

        System.out.println("_________________________________________");
    }

    public static void test04() {
        int[] a = {10, 20, 30, 50, 10};

        printArray(a);
        int result = findMax(a);

        System.out.println("Maximum element = " + result);

        if (result != 50)
            handleError();

        System.out.println("_________________________________________");
    }


    public static void test05() {
        int[] a = {10, 20, 30, 40, 50};

        printArray(a);
        int result = findMax(a);

        System.out.println("Maximum element = " + result);

        if (result != 50)
            handleError();

        System.out.println("_________________________________________");
    }




    public static void main(String[] args)  {
        test01();
        test02();
        test03();
        test04();
        test05();

        System.out.println("Test passed");
    }

}
