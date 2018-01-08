/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Math_12_RandAB {


    public static final int MAX_NUM_TRIALS  = 50;


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*
    Return value: returns 0 with a probability of 0.5 and 1 with a probability of 0.5
    */
    public static int binaryRand() {
        Random randomGenerator = new Random();
        int randNum = randomGenerator.nextInt(2);
        return randNum; 
    }

    /*Returns number x where a <= x <= b and x is uniformly distributed */
    public static int getRandomNum(int a, int b) {
        int numOutcomes = b - a + 1;
        int randNum;
        while (true) {
            randNum = 0;
            int i = 0;
            while ( (1 << i) < numOutcomes) {
                /*Append the random binary digit to the end*/
                randNum = (randNum << 1) | binaryRand();
                ++i;
            }

            if (randNum < numOutcomes)
                break;
            /*If randNum >= numOutcomes, we try again*/
        } 
        return randNum + a;
    }



    public static void test(int a, int b) {

        System.out.println("Generating random numbers from " + a + " to " + b);

        for (int i = 0; i < MAX_NUM_TRIALS; ++i) {
            int outcome = getRandomNum(a, b);

            System.out.println(outcome);

            if (outcome < a || outcome > b) 
                handleError();
        }

        System.out.println("____________________________________");
    }


    public static void main(String[] args) {
        test(0, 0);
        test(0, 1);
        test(1, 10);
        test(100, 500); 

        System.out.println("Test passed");

    }

}

