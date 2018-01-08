/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Dyn_11_EggDrop {

    public static final int MAX_NUM_TESTS = 100;
    public static final int MAX_INT = 9999999;

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    /*
    numEggs: total number of identical eggs available. should be >= 1 
    numFloors: total number of floors available. should be >= 1
    Return value: minimum number of throws with which we can find egg strength
    */
    public static int findMinEggDrops(int numEggs, int numFloors) {
        int[][] minThrows = new int[numEggs + 1][numFloors + 1];
        int curEgg, curFloor;

        /*If there is only 1 floor, we need only 1 throw*/
        for (curEgg = 1; curEgg <= numEggs; ++curEgg) 
            minThrows[curEgg][1] = 1;


        /*If there is only 1 egg and k floors, we need k throws*/
        for (curFloor = 1; curFloor <= numFloors; ++curFloor) 
            minThrows[1][curFloor] = curFloor;


        for (curEgg = 2; curEgg <= numEggs; ++curEgg) {
            for (curFloor = 2; curFloor <= numFloors; ++curFloor) {
                minThrows[curEgg][curFloor] = MAX_INT;

                for (int floorIter = 1; floorIter <= curFloor; ++floorIter) {
                    /*Find the number of throws needed from floorIter*/
                    int numThrows = Math.max(
                        1 + minThrows[curEgg - 1][floorIter - 1], 
                        1 + minThrows[curEgg][curFloor - floorIter]);

                    if (minThrows[curEgg][curFloor] > numThrows) 
                        minThrows[curEgg][curFloor] = numThrows;

                }
            }
        }

        return minThrows[numEggs][numFloors];
    }


    public static void test(int numEggs, int numFloors, int expectedResult) {

        int result = findMinEggDrops(numEggs, numFloors);

        System.out.println("Num eggs = " + numEggs + " num floors = " + numFloors + 
                ", minimum throws = " + result);

        if (result != expectedResult)
            handleError(); 

    }

    public static void main(String[] args) {
        test(2, 100, 14);
        test(2, 36, 8);

        System.out.println("Test passed");
    }

}
