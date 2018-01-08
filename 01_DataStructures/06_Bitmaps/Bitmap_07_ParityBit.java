/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Bitmap_07_ParityBit {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }



    /*
    x: input integer
    Return value: parity bit, 1 if there are odd number of 1's, 0 otherwise
    */
    public static int computeParity( int x) {
        /*for each bit set to 1 in x, toggle the parity bit*/
        int parity = 0;
        while (x != 0) {
            parity = parity ^ 1;
            x = x & (x - 1);
        }

        return parity;
    }


    public static void test( int x,  int expectedResult) {
        int result = computeParity(x);

        System.out.println("Parity bit for 0x" + Integer.toHexString(x) + " = " + result);

        if (result != expectedResult)
            handleError();
    }


    public static void main(String[] args) {
        test(0xB, 1);
        test(0xA, 0);

        System.out.println("Test passed");
    }

}
      
