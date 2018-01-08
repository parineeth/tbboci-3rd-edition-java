/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Math_11_UnfairCoin {

    /*Keep the number of coin flips >= 1000 so that we have sufficient number of flips*/
    public static final int MAX_COIN_FLIPS  = 1000;


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*
    Return value: returns 0 with a probability of 0.7 and 1 with a probability of 0.3
    */
    public static int tossUnfairCoin() {
        Random randomGenerator = new Random();
        int randNum = randomGenerator.nextInt(10);

        if (randNum <= 6) {
            /*If we get a number from 0 to 6, return 0.
            So likelihood of getting 0 is 0.7 */
            return 0;
        } else {
            /*If we get a number from 7 to 9, return 1
            So likelihood of getting 1 is 0.3*/
            return 1;
        }
        
    }

    /*
    Returns 0 with a probability of 0.5 and 1 with a probability of 0.5
    */
    public static int tossFairCoin() {
        while (true) {
            int x = tossUnfairCoin();
            int y = tossUnfairCoin();

            if (x == 0 && y == 1)
                return 0; 
            else if (x == 1 && y == 0) 
                return 1;
        }
    }




    public static void main(String[] args)  {

        int numHeads = 0, numTails = 0;
        for (int i = 0; i < MAX_COIN_FLIPS; ++i) {
            int outcome = tossFairCoin();

            if (outcome == 0) 
                numHeads++;
            else if (outcome == 1)
                numTails++;
            else 
                handleError();
        }

        System.out.println("Number of heads = " + numHeads + " Number of tails = " + numTails);

        int diff = Math.abs(numHeads - numTails) / 2;

        /*The difference between number of heads and number of tails should not be more than
        5% of the total number of coin flips*/
        if (diff > (5 * MAX_COIN_FLIPS / 100)) 
            handleError();

        System.out.println("Test passed");
    }

}

