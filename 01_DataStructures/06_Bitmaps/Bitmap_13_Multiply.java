/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Bitmap_13_Multiply {

    public static final int MAX_VALUE = 1000;
    public static final int MAX_NUM_TESTS = 100;

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /*x, y: two integers >= 0
    Return value: x multiplied with y */
    public static int multiply(int x, int y) {
        int result = 0;
        while (y != 0) {
            /*if the least significant bit of y is 1, then add x to result*/
            if ( (y & 1) == 1) {
                result += x;
            }
            y = y >> 1;/*shift out the least significant bit of y*/
            x = x << 1; /*Double the value of x*/
        }
        return result;
    }




    public static void test() {
        Random randomGenerator = new Random();

        int x = randomGenerator.nextInt(MAX_VALUE);
        int y = randomGenerator.nextInt(MAX_VALUE);

        int result = multiply(x, y);

        System.out.println(x + "*" + y + " = " + result);

        if (result != (x * y) ) {
            handleError();
        }

    }


    public static void main(String[] args) {

        for (int testNr = 0; testNr < MAX_NUM_TESTS; ++testNr) {
            test();
        }
    
        System.out.println("Test passed ");

    }

}
