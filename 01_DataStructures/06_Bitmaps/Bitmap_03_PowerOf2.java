/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_03_PowerOf2 {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    public static boolean isPowerOfTwo(int x) {
        return (x != 0) && ((x & (x-1)) == 0);
    }



    public static void main(String[] args)  {
        /*0 is not a power of 2*/
        if (isPowerOfTwo(0)) 
            handleError();

        /*10 is not a power of 2*/
        if (isPowerOfTwo(10)) 
            handleError();

        /*15 is not a power of 2*/
        if (isPowerOfTwo(15)) 
            handleError();


        /*1 is power of 2*/
        if (!isPowerOfTwo(1)) 
            handleError();

        /*2 is power of 2*/
        if (!isPowerOfTwo(2)) 
            handleError();

        /*4 is a power of 2*/
        if (!isPowerOfTwo(4)) 
            handleError();

        /*0x80000000 is a power of 2*/
        if (!isPowerOfTwo(0x80000000)) 
            handleError();


        System.out.println("Test passed");
    }

}
