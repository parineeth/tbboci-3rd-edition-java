/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Algo_Greedy_04_GasStations {

    public static final int MAX_NUM_TESTS = 100;
    public static final int MAX_NUM_STATIONS = 10;
    public static final int MAX_DISTANCE = 100;

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
    gas: the amount of gas available at each gas station. The total gas in all 
        stations should be sufficient to complete the circular trip 
    distance: distance[i] has the distance between gas station i and i+1
    numStations: number of gas stations. Should be >= 1
    mileage: how much distance can the car travel for 1 unit of gas consumed
    Return value: station from where to start so that we don't run out of fuel and
        complete the circular trip around all stations
    */
    public static int findStartingGasStation(int[] gas, int[] distance, 
                int numStations, int mileage) {
        /*Station from where to start the journey so that we don't run 
        out of fuel*/
        int startingStation = 0; 

        int leastGas = 0; /*Tracks the least amount of gas in fuel tank*/
        int gasInTank = 0; /*how much fuel is currently present in fuel tank*/
        for (int i = 0; i < numStations; ++i) {
            int gasRequired = distance[i] / mileage;

            /*At station i, we fill up gas[i] and then as we drive, we consume 
            gasRequired to reach the destination station = (i+1) % numStations */
            gasInTank += gas[i] - gasRequired; 
            if (gasInTank < leastGas) {
                leastGas = gasInTank;
                /*The starting station is the station where we have
                the least amount of gas in the tank just before we fill up*/
                startingStation = (i+1) % numStations;
            }
        }

        return startingStation;
    }

    /*Verifies if we start at startingStation, we can complete the journey without running out of fuel*/
    public static void verify(int startingStation, int[] gas, int[] distance, int numStations, int mileage) {

        /*Check if startingStation is out of range*/
        if (startingStation < 0 || startingStation >= numStations)
            handleError();

        int curStation = startingStation;
        int gasInTank = 0;
        for (int i = 1; i < numStations; ++i) {
            int gasRequired = distance[curStation] / mileage;
            gasInTank += gas[curStation] - gasRequired;

            /*gas in the fuel tank should always be >= 0*/
            if (gasInTank < 0) {
                handleError();
            }

            curStation = (curStation + 1) % numStations;
        }

    }


    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly pick the number of gas stations*/
        int numStations = 1 + randomGenerator.nextInt(MAX_NUM_STATIONS);

        int[] gas = new int[numStations];
        int[] distance = new int[numStations];

        int i, totalDistance = 0;
        for (i = 0; i < numStations; ++i) {
            /*Randomly pick the distance between the gas stations between
            1 and 10*/
            distance[i] = 1 + randomGenerator.nextInt(10);
            totalDistance += distance[i];
        }

        /*We are fixing the mileage to 1 mile/gallon since we will
        not have to deal with fractional values*/
        int mileage = 1;

        /*Compute the gas needed to complete the journey around all stations*/
        int totalGas = totalDistance / mileage;

        /*Randomly distribute the totalGas among the gas stations*/
        int remainingGas = totalGas;
        int perStationQuota = remainingGas / numStations;
        for (i = 0; i < numStations; ++i) {
            if (remainingGas > 0)
                gas[i] = randomGenerator.nextInt(perStationQuota);
            else 
                gas[i] = 0; 
            remainingGas -= gas[i];
        } 

        /*If there is any gas left over, then distribute the 
        remaining gas equally among the gas stations*/
        i = 0;
        perStationQuota = remainingGas / numStations;
        while (remainingGas > 0 && i < numStations - 1) {
            gas[i] += perStationQuota;
            remainingGas -= perStationQuota;
            ++i;
        }
        /*If there is still any gas left over, give it to the last gas station*/
        gas[numStations - 1] += remainingGas; 

        System.out.print("Gas      : ");
        printArray(gas);


        System.out.print("Distance : ");
        printArray(distance);

        /*Find the gas station from where to start the journey
        IMPORTANT: ensure that while calling this function that the sum of gas in all
        the stations should be sufficient to complete the journey*/
        int startingStation = findStartingGasStation(gas, distance, numStations, mileage);

        System.out.println("Starting station = " + startingStation);

        verify(startingStation, gas, distance, numStations, mileage);

        System.out.println("____________________________________________________");
    }




    public static void main(String[] args)  {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
