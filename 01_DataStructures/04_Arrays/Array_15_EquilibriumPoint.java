/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Array_15_EquilibriumPoint {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    /*
    a: input array whose equilibrium point has to be found. 
    Return value: index of the equilibrium point if it exists, -1 otherwise
    */
    public static int findEquilibriumPoint(int[] a) {
        int n = a.length;

        /*Compute the sum of all elements and store in rightSum*/
        int rightSum = 0;
        for (int curVal : a) {
            rightSum += curVal;
        }

        /*Go on computing sum of all elements from the left to right and 
        compare with right sum */
        int leftSum = 0;
        for (int i = 0; i < n; ++i) {
            /*Subtract a[i] from rightSum to find out sum of the elements
            to the right of i*/
            rightSum -= a[i];

            if (leftSum == rightSum) {
                return i; /*We have found the equilibrium point*/
            }
            leftSum += a[i];
        }

        return -1;
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }


    public static void main(String[] args)  {
        int[] a = {-50, 100, 80, 30, -60, 10, 70};

        System.out.print("Input : ");
        printArray(a);

        int result = findEquilibriumPoint(a);

        System.out.println("Equilibrium point index = " + result);

        if (result != 2)
            handleError();

        System.out.println("Test passed");

    }

}
