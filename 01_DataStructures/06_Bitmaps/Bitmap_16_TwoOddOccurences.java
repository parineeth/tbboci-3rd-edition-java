/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_16_TwoOddOccurences {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }



    public static void printArray(int[] a) {
    
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + " ");
        }
    
        System.out.println("");
    }

    /*
    a: input array where 2 elements occur odd number of times and the remaining
        occur even number of times
    result: the two numbers that occur odd number of times will be returned here
    */
    public static void findOddOccurences( int[] a, int[] result) {
        int n = a.length;

        int i, allXor = 0;
        for (i = 0; i < n; ++i) {
            allXor = allXor ^ a[i];
        }

        /*Find the first bit in the XOR result that is set to 1. The two odd 
        occuring numbers will differ at this bit position. So if difference 
        is at bit position 3, then mask will be ...00001000
        */  
        int mask = allXor & ~(allXor - 1);

        /*Separate out values in list a such that, values that have a 1 at the
        different bit will be XORed with result[0] and values that have a 0 
        at the different bit will be XORed with result[1] 
        */
        result[0] = result[1] = 0;
        for (i = 0; i < n; ++i) {
            if ( (a[i] & mask) != 0)
                result[0] = result[0] ^ a[i];
            else
                result[1] = result[1] ^ a[i];
        }

        /*result[0] and result[1] will now contain the two numbers that 
        occur odd number of times*/
    }


    public static void test(int[] a, int expectedResult1, int expectedResult2) {
        int[] result = new int[2];
        printArray(a);

        findOddOccurences(a, result);

        System.out.println("The odd occuring elements are " + result[0] + ", " + result[1]);

        if (Math.min(result[0], result[1]) != Math.min(expectedResult1, expectedResult2))
            handleError();

        if (Math.max(result[0], result[1]) != Math.max(expectedResult1, expectedResult2))
            handleError();

        System.out.println("_____________________________________________");
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 3, 2, 4, 3};
        test(a, 1, 4);

        System.out.println("Test passed");
    }

}
