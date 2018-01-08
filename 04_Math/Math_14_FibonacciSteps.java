/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Math_14_FibonacciSteps {


    public static final int MAX_N_STEPS = 20;


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*
    n: number of steps. n >= 1
    Returns: the number of ways to climb the steps
    */
    public static int climbSteps(int n) {
        /*Directly return the value for the first two fibonacci numbers*/
        if (n <= 2)
            return n;

        int x = 1;
        int y = 2;
        for (int i = 3; i <=n; ++i) {
            int temp = x + y;
            x = y;
            y = temp;
        } 
    
        return y;
    }

    /*
    n: number of steps. n >= 1
    Returns: the number of ways to climb the steps using recursion
    */
    public static int climbStepsR(int n) {
        if (n <= 2)
            return n;

        return climbStepsR(n-1) + climbStepsR(n-2);
    }



    public static void main(String[] args) {

    
        for(int i = 1; i <= MAX_N_STEPS; ++i) {
            /*Find the number of ways to climb steps non-recursively*/
            int a = climbSteps(i);

            /*Find the number of ways to climb steps recursively*/
            int b = climbStepsR(i);

            if (a != b)
                handleError();

            System.out.println("Number of steps = " + i + ", Number of ways to climb = " + a);
        }


        System.out.println("Test passed");
    }

}

