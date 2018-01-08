/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_08_CopyBits {



    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    /*
    dest: destination integer into which the bits have to be copied
    src: source integer from which the bits have to be copied 
    endPos: Most Significant bit position upto where the bits should be copied
    startPos: Least Significant bit position from where the bits should be copied
        endPos should be >= startPos
    Return value: result integer after copying bits from source to destination
    */
    public static int copyBits(int dest, int src,  int endPos,  int startPos) {
         int numBitsToCopy = endPos - startPos + 1;
         int numBitsInInt = 32;
         int onesMask = ~(0); /*Initialize the mask to all 1's*/

        /*Use the bit-wise right shift operator to remove the excess 1's 
        in the mask*/
        onesMask = onesMask >>> (numBitsInInt - numBitsToCopy);

        /*Left shift the 1's to the starting position. onesMask will contain 
        1's from startPos to endPos*/
        onesMask = onesMask << startPos;

        /*zeroesMask will contain 0's from startPos to endPos*/
        int zeroesMask = ~onesMask;

        /*clear the bits in destination from startPos to endPos*/
        dest = dest & zeroesMask;

        /*retain the bits in source from startPos to endPos and clear 
        the remaining bits*/
        src = src & onesMask;

        /*copy the source bits into the destination*/
        dest = dest | src;

        return dest;
    }



    public static void main(String[] args) {

        /*Copy bits 0f b into a from position 7 to position 4*/
        int a = 0xABCDEF;
        int b = 0xBBBBAB;
        int result = copyBits(a, b, 7, 4);
        if (result != 0xABCDAF)
            handleError();


        System.out.println("Test passed");

    }

}
