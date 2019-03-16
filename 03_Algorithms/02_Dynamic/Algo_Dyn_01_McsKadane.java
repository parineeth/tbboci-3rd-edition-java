/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Int {
    int value;
}


class Algo_Dyn_01_McsKadane {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_ELEMENT_VALUE = 100;

    public static final int MIN_INT_VALUE = (-1 * 0x7FFFFFFF);

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /* a: array of numbers for which MCS should be found. 
        At least 1 element should be present
    mcsStartPos: the starting array index of the MCS is returned here
    mcsEndPos: the ending array index of the MCS is returned here 
    Return value: Maximum continuous sum of the elements 
    */
    public static int kadaneMcs(int[] a, Int mcsStartPos, Int mcsEndPos) {
        int length = a.length;
        int curStartPos = 0; /*store the start position of the current window*/
        int maxLocal = a[0], maxGlobal = a[0];
        mcsStartPos.value = mcsEndPos.value = 0;

        /*Traverse from the second element onwards*/
        for (int i = 1; i < length; ++i) {
            maxLocal = Math.max(a[i], a[i] + maxLocal);
            if (maxLocal == a[i])
                curStartPos = i;/*start a new window here*/

            /*Find the global maximum*/
            if (maxLocal > maxGlobal) {
                maxGlobal = maxLocal;
                mcsStartPos.value = curStartPos;
                mcsEndPos.value = i;
            }
        }

        return maxGlobal;
    }


    public static int findBruteForceMcs(int[] a) {
        int length = a.length;
        int mcs = MIN_INT_VALUE;

        for (int startElem = 0; startElem < length; ++startElem) {
            int curSum = 0;
            for (int i = startElem; i < length; ++i) {
                curSum += a[i];
                if (mcs < curSum) {
                    mcs = curSum;
                }
            }
        }


        return mcs;
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }

    public static void test() {
        Int mcsStartPos = new Int(); 
        Int mcsEndPos = new Int();
        Random randomGenerator = new Random();
        
        /*Pick a random number of elements*/
        int numElems = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        int[] a = new int[numElems];

        /*Generate random values into the array. */
        for (int i = 0; i < numElems; ++i) {
            a[i] = randomGenerator.nextInt(MAX_ELEMENT_VALUE);

            /*Some elements will be negative*/
            if (randomGenerator.nextInt(2) == 0)
                a[i] = -1 * a[i];
        }

        System.out.print("Input : ");
        printArray(a);


        /*Find the maximum continuous sum*/
        int algoMcs = kadaneMcs(a, mcsStartPos, mcsEndPos);

        System.out.println("Max Continuous Sum = " + algoMcs + 
                ", MCS start position = " + mcsStartPos.value + 
                ", MCS end position = " +  mcsEndPos.value);

        /*Find the maximum continuous sum using brute force*/
        int bruteForceMcs = findBruteForceMcs(a);

        /*The two results should match*/
        if (algoMcs != bruteForceMcs) {
            handleError();
        }

        System.out.println("______________________________________________");
    }


    public static void main(String[] args) {
        for (int testNr = 0; testNr < MAX_NUM_TESTS; ++testNr) {
            test();
        }

        System.out.println("Test passed");
    }

}
