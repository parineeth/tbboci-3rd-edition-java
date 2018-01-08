/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_02_CountBits {


    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }



    public static int countNumBitsSet(int n) {
        int count = 0;

        while (n != 0) {
            n &= n - 1;
            count++;
        }

        return count;
    }




    public static void main(String[] args) {

        int numBitsSet = countNumBitsSet(0);

        if (numBitsSet != 0)
            handleError();

        numBitsSet = countNumBitsSet(0xffffffff);

        if (numBitsSet != 32)
            handleError();


        numBitsSet = countNumBitsSet(0x70ff00f);

        if (numBitsSet != 15)
            handleError();

        System.out.println("Test passed");

    }

}
