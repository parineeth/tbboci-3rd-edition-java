/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Bitmap_10_CompareIntegers {


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*Returns the maximum of x and y without using if-else and comparison*/
    public static int findMax(int x, int y) {
        int difference = x - y;
        int signBit =   (difference >> 31) & 0x1;
        /*
        Sign bit can be 0 or 1
        If sign bit is 0, max = x - (0 * difference) = x
        If sign bit is 1, max = x - (1 * (x-y)) = x - x + y = y 
        */
        int max = x  - (signBit * difference);
        return max;
    }



    public static void test(int x, int y) {
        int result = findMax(x, y);

        System.out.println("maximum of " + x + ", " + y + " = " + result);

        int expectedResult = x > y ? x : y;

        if (result != expectedResult)
            handleError();

    }


    public static void main(String[] args) {
        test(-10, -20);
        test(-10, 20);
        test(10, -20);
        test(10, 20);
        test(0, 10);
        test(0, -20);

        System.out.println("Test passed ");
    }

}
