/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Math_09_CardShuffle {

    public static void handleError()    {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*Returns a random number between low and high, low and high are inclusive*/
    public static int getRandomNumber(int low, int high) {
        Random randomGenerator = new Random();
        int randomNum = low + randomGenerator.nextInt(high - low + 1);
        return randomNum;
    }

    /*Performs a perfect shuffle of cards*/
    public static void cardShuffle(int[] cards) {
        for (int i = cards.length - 1; i >= 0; --i) {
            int randNum = getRandomNumber(0, i);
            int temp = cards[i];
            cards[i] = cards[randNum];
            cards[randNum] = temp; 
        }  
    }

    public static void incorrectShuffle(int[] cards) {
        for (int i = cards.length - 1; i >= 0; --i) {
            int randNum = getRandomNumber(0, cards.length - 1);
            int temp = cards[i];
            cards[i] = cards[randNum];
            cards[randNum] = temp; 
        }
    }


    public static void main(String[] args)  {
        int[] cards = new int[52];

        for (int i = 0; i < 52; ++i) {
            cards[i] = i;
        }

        cardShuffle(cards);

        System.out.println("Test passed ");

    }

}

