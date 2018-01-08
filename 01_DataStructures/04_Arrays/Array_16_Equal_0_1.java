/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Int {
    public int value;

    public Int() {
        value = 0;
    }
    
}

class Array_16_Equal_0_1 {

    public static final int MAX_NUM_TESTS = 10; 
    public static final int MAX_NUM_ELEMS = 10; 
    public static final int MAX_VALUE     = 2; 
    public static final int MIN_INT       = -100;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void printArray(int[] a) {
        for (int curVal : a ) {
            System.out.print(curVal + " ");
        }
    
        System.out.println("");
    }


    /*
    a:input array 
    startIndex: start index of longest subarray of equal 0's and 1's is returned
    endIndex: end index of longest subarray of equal 0's and 1's is returned 
    Returns: the length of the longest subarray with equal 0's and 1's  
    */
    public static int findSubArray(int[] a, Int startIndex, Int endIndex) {
        int numElements = a.length;
        int[] firstIxForSum = new int[ (2 * numElements + 1)] ;
        int i;

        /*firstIxForSum will store the first seen index for a particular 
        normalized running sum. Initialize the sum table. MIN_INT should be < -1
        normalized running sum = numElements + runningSum*/
        for (i = 0; i < (2 * numElements + 1); ++i)
            firstIxForSum[i] = MIN_INT;

        /*Before we start processing, we say that at index -1, running sum is 0
        The normalized running sum =  numElements + runningSum = numElements
        + 0 = numElements. So firstIxForSum[numElements] is set to -1*/
        firstIxForSum[numElements] = -1;
        int maxLength = 0;
        int runningSum = 0;
        startIndex.value = endIndex.value = -1;
        for (i = 0; i < numElements; ++i) {
            /*If we get a 1, increment the running sum. If we get a 0
            then decrement the running sum*/
            if (a[i] == 1)
                runningSum++;
            else
                runningSum--;

            /*If there are 10 elements, then running sum can vary from -10
            to +10. Normalize the running sum into an index from 0 to 20*/
            int normalizedSum = numElements + runningSum;
            if (firstIxForSum[normalizedSum] == MIN_INT) {
                /*We are observing the normalized running sum
                for the first time. Store the index in firstIxForSum*/
                firstIxForSum[normalizedSum] = i;
            } else {
                /*We have already observed the normalized running sum
                before. Suppose we have a normalized running sum of 3
                at index 10 and we again observe normalized running sum
                of 3 at index 18, then there are equal 0's and 1's 
                from index 11 to index 18*/
                int firstIndex = firstIxForSum[normalizedSum];
                if (i - firstIndex > maxLength) {
                    maxLength = i - firstIndex;
                    startIndex.value = firstIndex + 1;
                    endIndex.value = i; 
                }
            }

        }

        return maxLength;
    }


    /*
    a:input array 
    numElements: number of elements in array a
    Returns: length of longest subarray with equal 0's and 1's   
    */
    public static int bruteForceSubArray(int[] a) {
        int i, j, length, maxLength = 0;
        int numElements = a.length;
    
        for (i = 0; i < numElements - 1; ++i) {
            /*If we get a 1 we add 1 to running sum, if we get a 0, 
            we subtract -1*/
            int runningSum = (2 * a[i]) - 1;
        
            for (j = i+1; j < numElements; ++j) {
                runningSum += (2 * a[j]) - 1;
                if (runningSum == 0) {
                    length = j - i + 1;
                    if (length > maxLength) 
                        maxLength = length;
                }
            }
        }

        return maxLength;
    }



    public static void test() {
        Random randomGenerator = new Random();
        
        /*Randomly decide the number of elements in the array*/
        int length = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        if (length < 2)
            length = 2;

        int[] a = new int[length];

        /*Fill the array with random values*/
        for (int i = 0; i < length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        printArray(a);

        /*Find the length of longest sub-array with equal 0's and 1's 
        using the efficient technique*/
        Int startIndex = new Int();
        Int endIndex = new Int(); 
        int result = findSubArray(a, startIndex, endIndex);

        System.out.println("Length of longest sub array with equal 0's and 1's  = " + 
                result + "\n\t(start index = " + startIndex.value + 
                ", end index = " + endIndex.value + ")" );

        /*Find the length of longest sub-array with equal 0's and 1's 
        using brute force*/
        int bruteForceResult = bruteForceSubArray(a);

        /*The two results should match*/
        if (result != bruteForceResult)
            handleError();


        System.out.println("________________________________________________");
    }


    public static void main(String[] args)  {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
