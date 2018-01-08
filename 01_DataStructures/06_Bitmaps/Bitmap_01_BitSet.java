/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Bitmap_01_BitSet {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    public static int setBit(int n,  int pos)  {
        n = n | (1 << pos);
        return n;
    }

    public static int resetBit(int n,  int pos) {
        n = n & ~(1 << pos);
        return n;
    }


    public static int toggleBit(int n,  int pos)  {
        n = n ^ (1 << pos);
        return n;
    }



    public static void main(String[] args) {
        if (setBit(0xf0, 3) != 0xf8)
            handleError();

        if (resetBit(0xf8, 3) != 0xf0)
            handleError();

        if (toggleBit(0xf0, 7) != 0x70)
            handleError();

        System.out.println("Test passed");

    }


}
