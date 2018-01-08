/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Search_01_SmallestAndSecondSmallest {


    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE     = 10;
    public static final int MAX_INT       = 1000000;
    public static final int MIN_INT       = -100000;

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
    a:input array 
    minValue: the two smallest values will be returned here
    */
    public static void findTwoSmallest(int[] a, int[] minValue) {
        minValue[0] = minValue[1] = MAX_INT;

        for (int curVal : a) {
            if (curVal < minValue[0]) {
                minValue[1] = minValue[0];
                minValue[0] = curVal;
            } else if (curVal < minValue[1]) {
                minValue[1] = curVal;
            }
        }
    }




    /*
    a:input array 
    expectedResult: the two smallest values will be returned here 
    */
    public static void sortingTwoSmallest(int[] a, int[] expectedResult) {
        int length = a.length;

        expectedResult[0] = expectedResult[1] = MAX_INT;

        Arrays.sort(a);

        if (length > 0)
            expectedResult[0] = a[0];

        if (length > 1) 
            expectedResult[1] = a[1];
    }





    public static void generateArray(int[] a) {
        int length = a.length;
        Random randomGenerator = new Random();
    
        for (int i = 0; i < length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }
    }



    public static void test() {
        int[] result = new int[2];
        int[] expectedResult = new int[2];
        Random randomGenerator = new Random();


        /*Randomly decide the number of elements in the array*/
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);
        if (length < 2)
            length = 2;

        int[] a = new int[length];  

        /*Fill the array with random values*/
        generateArray(a);

        printArray(a);

        /*Find the result using an efficient technique*/
        findTwoSmallest(a, result);
    
        System.out.println("Two Smallest are = " + result[0] + ", " + result[1]);


        /*Find the result using sorting*/
        sortingTwoSmallest(a, expectedResult);


        /*The two results should match*/
        if ( (result[0] == expectedResult[0] && result[1] == expectedResult[1]) 
            || (result[1] == expectedResult[0] && result[0] == expectedResult[1]) ) {
            /*The results match. Do nothing*/
        } else {
            handleError();
        }


        System.out.println("________________________________________________");

    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
