/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_06_SwapBits {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }



    /*writes the bitValue (0/1) into position pos in x and returns the result*/
    public static int writeBit( int x, int bitValue, int pos)  {
        int mask = 1 << pos;
        if (bitValue == 1)
            x = x | mask;
        else
            x = x & ~mask;

        return x;
    }

    /*
    x: integer in which the bits should be swapped
    pos1: position of first bit to be swapped
    pos2: position of the second bit to be swapped
    */
    public static int swapBits(int x, int pos1, int pos2) {
        /*get the bits at position pos1 and pos2*/
        int bit1 = (x >> pos1) & 1;
        int bit2 = (x >> pos2) & 1;

        /*swap the bits*/
        if (bit1 != bit2) {
            x = writeBit(x, bit1, pos2);
            x = writeBit(x, bit2, pos1);
        }

        return x;
    }


    public static void test( int x, int pos1, int pos2,  int expectedResult) {
        int result = swapBits(x, pos1, pos2);

        System.out.println("Swapping bit " + pos1 + " and bit " + pos2 + 
                " on 0x" + Integer.toHexString(x) + " = 0x" + Integer.toHexString(result));

        if (result != expectedResult)
            handleError();
    }



    public static void main(String[] args) {
        test(0xB, 1, 2, 0xD);

        System.out.println("Test passed");
    }

}


