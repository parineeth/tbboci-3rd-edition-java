/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Dyn_02_CoinChange {

    public static final int MAX_INT_VALUE = (0x7FFFFFFF);

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    



    /*denom: array having the coin denominations. Should have atleast 1 element
    finalAmount: amount for which change has to be obtained
    Return value: Minimum number of coins needed to represent finalAmount
    */
    public static int findMinCoins(int[] denom, int finalAmount) {
        /*Array for storing the minimum number of coins for an amount*/
        int[] minNumCoins = new int[finalAmount + 1];

        /*Array for storing the coin denomination chosen for an amount*/
        int[] chosenDenom = new int[finalAmount + 1];

        minNumCoins[0] = 0;
        int curAmount;
        for (curAmount = 1; curAmount <= finalAmount; curAmount++) {
            minNumCoins[curAmount] = MAX_INT_VALUE;
            for (int curDenom : denom) {
                if (curDenom <= curAmount) {
                    int smallerAmt = curAmount - curDenom;

                    if (1 + minNumCoins[smallerAmt] < 
                            minNumCoins[curAmount]) {
                        minNumCoins[curAmount] = 
                            1 + minNumCoins[smallerAmt];
                        chosenDenom[curAmount] = curDenom;
                    }
                }
            }
        }

        int result = minNumCoins[finalAmount];
        System.out.println("Minimum number of coins = " + result);

        /*print the chosen denominations to get the final amount*/
        curAmount = finalAmount;
        while (curAmount > 0) {
            System.out.print(chosenDenom[curAmount] + " ");
            curAmount = curAmount - chosenDenom[curAmount];
        }
        System.out.println(" = " + finalAmount);

        return result;
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }


    public static int test01() {
        int[] denom = {1, 2, 3, 4, 5};
        int totalAmount = 13;

        System.out.print("Coin denominations : ");
        printArray(denom);
        System.out.println("Total amount needed = " + totalAmount);

        int minCoins = findMinCoins(denom, totalAmount);

        if (minCoins != 3)
            handleError();

        System.out.println("_____________________________________________");

        return 0;
    }


    public static int test02() {
        int[] denom = {1, 2, 3, 4};
        int totalAmount = 17;

        System.out.print("Coin denominations : ");
        printArray(denom);
        System.out.println("Total amount needed = " + totalAmount);

        int minCoins = findMinCoins(denom, totalAmount);

        if (minCoins != 5)
            handleError();

        System.out.println("_____________________________________________");

        return 0;
    }

    public static int test03() {
        int[] denom = {1, 5, 10, 25, 50};
        int totalAmount = 30;

        System.out.print("Coin denominations : ");
        printArray(denom);
        System.out.println("Total amount needed = " + totalAmount);

        int minCoins = findMinCoins(denom, totalAmount);

        if (minCoins != 2)
            handleError();

        System.out.println("_____________________________________________");

        return 0;
    }


    public static int test04() {
        int[] denom = {1, 3, 4, 5};
        int totalAmount = 7;

        System.out.print("Coin denominations : ");
        printArray(denom);
        System.out.println("Total amount needed = " + totalAmount);

        int minCoins = findMinCoins(denom, totalAmount);

        if (minCoins != 2)
            handleError();

        System.out.println("_____________________________________________");

        return 0;
    }




    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();

        System.out.println("Test passed");

    }

}


