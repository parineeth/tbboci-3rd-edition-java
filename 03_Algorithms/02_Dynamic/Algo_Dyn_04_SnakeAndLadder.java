/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Dyn_04_SnakeAndLadder {

    public static final int MAX_POSITIONS_ON_BOARD  = 100;

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }



    /*isSnake: if there is a snake at position 20, isSnake[20] is true
    ladder: if there is a ladder from position 30 to 44, then ladder[44] = 30.
        if there is no ladder at location 90 then ladder[90] = -1
    predecessor: this array has the previous board position from where we came to 
        current position with least number of dice throws. If predecessor[100]   
        = 95, then we reached 100 from 95. It is computed and returned. 
    Return value: least number of throws to reach the final position on the board
    */
    public static int findLeastThrows(boolean[] isSnake, int[] ladder, 
                        int[] predecessor) {
        /*for a particular position pos on board, leastThrows[pos] will store
        the least number of dice throws required to reach the position*/
        int[] leastThrows = new int [MAX_POSITIONS_ON_BOARD + 1];

        /*All positions from 1 to 6 can be reached from a single dice throw*/
        int pos;
        for (pos = 1; pos <= 6; pos++) {
            leastThrows[pos] = 1;
            predecessor[pos] = 0;
        }

        for (pos = 7; pos <= MAX_POSITIONS_ON_BOARD; ++pos) {
            int minThrows = MAX_POSITIONS_ON_BOARD;

            /*Find how many dice throws are needed to reach pos from any of
            the 6 previous cells*/
            for (int i = 1; i <= 6; ++i) {
                int prevPos = pos - i;

                if (isSnake[prevPos])
                    continue;

                /*Pick minimum throws needed from the 6 previous cells*/
                if (leastThrows[prevPos] + 1 < minThrows) {
                    minThrows = leastThrows[prevPos] + 1;
                    predecessor[pos] = prevPos;
                }
            }

            /*Suppose we are at pos = 14 and ladder[14] = 4, then there is 
            a ladder from 4 to 14. So number of dice throws needed to reach 
            14 = number of dice throws needed to reach position 4*/
            int ladderStartPos = ladder[pos];
            if (ladderStartPos != -1) {
                if (leastThrows[ladderStartPos] < minThrows) {
                    minThrows = leastThrows[ladderStartPos];
                    predecessor[pos] = ladderStartPos;
                }
            }

            leastThrows[pos] = minThrows;
        }
        return leastThrows[MAX_POSITIONS_ON_BOARD];
    }



    public static void main(String[] args)  {
        boolean[] isSnake = new boolean[MAX_POSITIONS_ON_BOARD + 1];
        int[] ladder = new int[MAX_POSITIONS_ON_BOARD + 1];
        int[] predecessor = new int[MAX_POSITIONS_ON_BOARD + 1];
        
        int i;
        for (i = 0; i <= MAX_POSITIONS_ON_BOARD; ++i) {
            ladder[i] = -1;
        }

        /*if there is a snake at position 20 on board, the isSnake[20] is set to true*/
        isSnake[17] = true;
        isSnake[54] = true;
        isSnake[62] = true;
        isSnake[64] = true;
        isSnake[87] = true;
        isSnake[93] = true;
        isSnake[95] = true;
        isSnake[99] = true;

        /*if there is a ladder from position 30 to 44, then ladder[44] = 30.
        if there no ladder at location 90 then ladder[90] = -1*/
        ladder[14] = 4;
        ladder[31] = 9;
        ladder[38] = 20;
        ladder[84] = 28;
        ladder[59] = 40;
        ladder[67] = 51;
        ladder[81] = 63;
        ladder[91] = 71;

        int minThrows = findLeastThrows(isSnake, ladder, predecessor);

        System.out.println("Least number of throws = " + minThrows);

        if (minThrows != 7)
            handleError();

        System.out.println("The positions on the board corresponding to dice throws in reverse order are: ");

        i = MAX_POSITIONS_ON_BOARD;
        System.out.println(i);
        while (i > 0) {
            System.out.println(predecessor[i]);
            i = predecessor[i];
        } 

        System.out.println("Test passed");

    }

}
