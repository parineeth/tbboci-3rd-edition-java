/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Algo_Greedy_01_StockPrices {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMENTS = 10;
    public static final int MAX_VALUE = 100;


    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }



    /*
    stockPrice: array of stock price values
    Return value: maximum profit possible
    */
    public static int findMaxProfit(int[] stockPrice) {
        int n = stockPrice.length;

        int maxProfit = 0;
        if (n <= 1)
            return maxProfit;

        int minStockPrice = stockPrice[0];

        for (int i = 1; i < n; ++i) {
    
            int curProfit = stockPrice[i] - minStockPrice;

            if (curProfit > maxProfit)
                maxProfit = curProfit;

            if (stockPrice[i] < minStockPrice)
                minStockPrice = stockPrice[i];
        }

        return maxProfit;
    }





    public static int findBruteForceMaxProfit(int[] stockPrice) {
        int n = stockPrice.length;

        int maxProfit = 0;
        if (n <= 1)
            return maxProfit;

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i+1; j < n ; ++j) {
                if (stockPrice[j] > stockPrice[i]) {
                    int curProfit = stockPrice[j] - stockPrice[i];
                    if (curProfit > maxProfit)
                        maxProfit = curProfit;
                }

            }
        }

        return maxProfit;
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }




    public static void main(String[] args)  {
        Random randomGenerator = new Random();

        for (int testNr = 0; testNr < MAX_NUM_TESTS; ++testNr) {
            /*Randomly pick the number of elements*/
            int numElements = 1 + randomGenerator.nextInt(MAX_NUM_ELEMENTS);

            int[] a = new int[numElements];

            /*Add random share values to the array*/
            for (int i=0; i < numElements; ++i)
                a[i] = randomGenerator.nextInt(MAX_VALUE);

            System.out.print("Input : ");
            printArray(a);

            /*Find the best profit possible*/
            int result = findMaxProfit(a);

            System.out.println("Maximum profit = " + result);

            /*Find the best profit using the brute force approach*/
            int bruteForceResult = findBruteForceMaxProfit(a);

            /*Both results should match*/
            if (result != bruteForceResult)
                handleError();

            System.out.println("__________________________________________________");

        }


        System.out.println("Test passed");

    }


}



