/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Algo_Dyn_08_CutRod {

    public static final int MAX_NUM_TESTS = 100;
    public static final int MAX_LENGTH = 10;
    public static final int MAX_VALUE     = 10;



    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /*Helper function for printing out the sizes of the individual pieces*/
    public static void printPieces(int[] firstCut, int totalLength) {

        System.out.print("The rod piece lengths are : ");

        int curLength = totalLength;
        while (curLength > 0 && firstCut[curLength] > 0) {
            System.out.print(firstCut[curLength] + " ");
            curLength = curLength - firstCut[curLength];
        }

        System.out.println(""); 
    }

    /*
    price: price[i] gives the price of a rod of length i. price[0] is 0
    totalLength: the total length of the rod given to us. Should be >= 1
    Return value: the best value that can be fetched from the rod
    */
    public static int cutRod(int[] price, int totalLength) {
        /*Initialize bestValue to 0*/
        int[] bestValue = new int[totalLength + 1];

        /*firstCut[i] will indicate the length of the first piece when we  
        cut the rod of length i. This is needed to print out where we 
        should cut so that we get the best value*/
        int[] firstCut = new int[totalLength + 1];

        for (int curLength = 1; curLength <= totalLength; ++curLength) {
            /*We are cutting a rod whose length is curLength
            The length of the first piece after the cut can 
            range from 1 to curLength*/
            for (int i = 1; i <= curLength; ++i) {
                if (price[i] + bestValue[curLength - i] > 
                        bestValue[curLength]) {
                    bestValue[curLength] = price[i] + 
                            bestValue[curLength - i];
                    firstCut[curLength] = i;
                }
            }
        }

        printPieces(firstCut, totalLength);
        return bestValue[totalLength];
    }


    public static int cutRodRecursive(int[] price, int curLength) {

        if (curLength <= 0)
            return 0;

        int bestValue = price[curLength];
        for (int i = 1; i < curLength; ++i) {
            bestValue = Math.max(bestValue, price[i] + cutRodRecursive(price, curLength - i));
        }

        return bestValue;
    }


    public static void printArray(int[] price) {
        int numElems = price.length;

        System.out.print("Length: ");
        int i;
        for (i = 0; i < numElems; ++i)
            System.out.print(i + " ");
        System.out.println("");
    
        System.out.print("Price : ");
        for (i = 0; i < numElems; ++i) {
            System.out.print(price[i] + " ");
        }
    
        System.out.println("");
    }


    public static void test() {
        Random randomGenerator = new Random();

        int length = 1 + randomGenerator.nextInt(MAX_LENGTH);

        int[] price = new int[length+1];

        price[0] = 0;
        /*Randomly fill in the remaining prices*/
        for (int i = 1; i <= length; ++i) {
            price[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        printArray(price);

        /*Find the result using an efficient technique*/
        int result = cutRod(price, length);
    
        System.out.println("Max value =  " + result);


        /*Find the result using the recursive technique*/
        int expectedResult = cutRodRecursive(price, length);

        /*The two results should match*/
        if (result != expectedResult) {
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


