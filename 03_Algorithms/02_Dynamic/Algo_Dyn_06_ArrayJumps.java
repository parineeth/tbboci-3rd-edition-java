/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Dyn_06_ArrayJumps {

    public static final int MAX_INT = 1000000;

    public static void handleError() {
        System.out.println("Test failed ");
        System.exit(1);
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }


    /*
    a: a[i] contains the maximum number of locations we can jump from position i
        array a should have atleast 1 element
    Return value: minimum number of jumps needed to reach the end of the array
    */
    public static int findMinJumps(int[] a) {
        int n = a.length;
        int[] minJumps = new int[n];

        for (int i = 1; i < n; ++i) {
            minJumps[i] = MAX_INT;
            /*Compute the minimum number of jumps to reach location i by looking
            at the previous locations 0 to i - 1*/
            for (int j = 0; j < i; ++j) {
                if (j + a[j] >= i && minJumps[j] + 1 < minJumps[i]) {
                    minJumps[i] = minJumps[j] + 1;
                }
            }
        }

        return minJumps[n-1];
    }





    public static void test() {
        int[] a = {2, 0, 2, 3, 1, 4, 2, 1, 2, 1};

        System.out.print("Input: ");
        printArray(a);

        int result = findMinJumps(a);
        int expectedResult = 4;

        System.out.println("Minimum jumps = " + result);

        if (result != expectedResult)
            handleError();
    
        System.out.println("");  
    }


    public static void main(String[] args) {
        test();

        System.out.println("Test passed");
    }

}
