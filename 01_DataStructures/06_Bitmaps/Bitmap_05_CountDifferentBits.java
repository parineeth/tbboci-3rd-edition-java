/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_05_CountDifferentBits {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /*
    a, b: the two input integers
    Return value: Number of bits that have different values in a and b
    */
    public static int countDifferentBits( int a,  int b) {
         int c = a ^ b;
         int count = 0;

        /*Since c = a xor b, the positions where a and b are different will 
        be set to 1 in c. So by counting the number 1's in c, we will get the
        number of bits that are different between a and b*/
        while (c != 0) {
            count++;
            c = c & (c - 1);
        }

        return count;
    }



    public static void main(String[] args) {

        int a = 0xF0F;
        int b = 0x0F0;
        int result = countDifferentBits(a, b);
        if (result != 12)
            handleError();

        a = 4;
        b = 5;
        result = countDifferentBits(a, b);
        if (result != 1)
            handleError();
    

        System.out.println("Test passed");

    }

}
