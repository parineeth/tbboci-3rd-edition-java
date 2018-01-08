/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Misc_09_WaterTrap {

    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }



    /*histogram: histogram[i] contains the height of the bar at location i
        histogram should consist of atleast one bar
    Return value: amount of water that can be trapped by the histogram */
    public static int waterTrap(int[] histogram) {
        int n = histogram.length; /*number of bars in the histogram*/
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        /*The left max of bar i is the height of the tallest bar in the 
        region (0, i). Note that region (0, i) includes 0 and i*/
        leftMax[0] = histogram[0];
        int i;
        for (i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i-1], histogram[i]);
        }

        /*The right max of bar i is the height of the tallest bar in the 
        region (i, n-1). Note that region (i, n-1) includes i and n-1*/
        int totalWater = 0;
        rightMax[n-1] = histogram[n-1];
        for (i = n - 2; i >= 0; --i) {
            /*Compute the right max and simultaneously calculate the 
            amount of water that can be trapped*/
            rightMax[i] = Math.max(rightMax[i+1], histogram[i]);
    
            int smallerMax = Math.min(leftMax[i], rightMax[i]);
            /*calculate the amount of water that can be stored
            on top of the histogram bar i*/
            totalWater += smallerMax - histogram[i];
        }


        return totalWater;
    }


    public static void test() {
        int[] histogram = { 3, 1, 4, 0, 2, 5, 0, 3, 2};
        int totalWater = waterTrap(histogram);

        System.out.println("Total water trapped = " + totalWater);

        if (totalWater != 11)
            handleError();
    
    }


    public static void main(String[] args)  {
        test();

        System.out.println("Test passed ");
    }

}


