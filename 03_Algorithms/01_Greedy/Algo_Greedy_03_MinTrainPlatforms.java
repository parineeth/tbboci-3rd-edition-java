/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Greedy_03_MinTrainPlatforms {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }


    /*
    arrival: array containing the arrival time of the trains
    departure: array containing the departure time of the trains
    Return value: minimum number of train platforms needed
    */
    public static int findMinPlatforms(int[] arrival, int[] departure) {
        int n = arrival.length;

        if (n == 0)
            return 0;

        /*Sort arrival and departure time independantly in non-decreasing order*/
        Arrays.sort(arrival);
        Arrays.sort(departure);

        int curNumPlatforms = 1, minNumPlatforms = 1;
        int i = 1; /*i is used for traversing arrival*/
        int j = 0; /*j is used for traversing departure*/

        while (i < n && j < n) {
            if (arrival[i] < departure[j]) {
                /*A new train is coming in before a train departs.  
                So we need an extra platform*/
                curNumPlatforms++;
                if (curNumPlatforms > minNumPlatforms)
                    minNumPlatforms = curNumPlatforms;
                ++i;
            } else if (arrival[i] == departure[j]) {
                /*A train arrives at the same time as a departing  
                train. So we don't need an extra platform*/
                ++i;
                ++j;
            } else {
                /*A train departs before the new train arrives. 
                So a platform is freed up*/
                curNumPlatforms--;
                j++;
            }
        }

        return minNumPlatforms;
    }




    public static void test01() {
        int[] arrival = {800, 900, 945, 1300, 1500, 1530, 1545 };
        int[] departure = {1030, 915, 1100, 1400, 1545, 1830, 1715 };

        System.out.print("Arrival: ");
        printArray(arrival);

        System.out.print("Departure: ");
        printArray(departure);

        int result = findMinPlatforms(arrival, departure);

        System.out.println("Minimum number of platforms = " + result);

        int expectedResult = 2;

        if (result != expectedResult)
            handleError();

        System.out.println("__________________________________");
    
    }




    public static void main(String[] args)  {
        test01();

        System.out.println("Test passed");
    }

}
