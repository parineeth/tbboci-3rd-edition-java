/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_09_CircularShift {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /*
    input: input value which has to be circularly shifted left
    n: number of positions to shift
    Return value: result after circularly left shifting input
    */
    public static int circularLeftShift(int input, int n) {
        int numBitsInInt = 32;
        n = n % numBitsInInt;
        int result = input << n | input >>> (numBitsInInt - n);
        return result;
    }


    /*
    input: input value which has to be circularly shifted right
    n: number of positions to shift
    Return value: result after circularly right shifting input
    */
    public static int circularRightShift(int input, int n) {
        int numBitsInInt = 32;
        n = n % numBitsInInt;
        int result = input >>> n | input << (numBitsInInt - n);
        return result;
    }




    public static void main(String[] args)  {

        int result = circularRightShift(0x1, 1);
        if (result != 0x80000000)
            handleError();

    
        result = circularRightShift(0xFEDCBA98, 32);
        if (result != 0xFEDCBA98)
            handleError();

        result = circularRightShift(0xFEDCBA98, 16);
        if (result != 0xBA98FEDC)
            handleError();


        result = circularLeftShift(0x1, 0x80000000);
        if (result != 1)
            handleError();

    
        result = circularLeftShift(0xFEDCBA98, 32);
        if (result != 0xFEDCBA98)
            handleError();

        result = circularLeftShift(0xFEDCBA98, 16);
        if (result != 0xBA98FEDC)
            handleError();


        System.out.println("Test passed ");

    }

}
