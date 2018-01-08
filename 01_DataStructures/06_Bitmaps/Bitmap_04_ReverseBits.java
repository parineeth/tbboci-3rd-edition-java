/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Bitmap_04_ReverseBits {

    public static final int MAX_NUM_TESTS = 1000;


    public static int getRandByte() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(256);    
    }


    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /*
    inputValue: the integer that has to be reversed
    reverseTable: lookup table that has the reversed values for every byte.
         Example - reverseTable[0x1] = 0x80, since reverse of 00000001 is 1000000
    Return value: integer that is the reverse of the input integer
    */
    public static int reverseInteger( int inputValue,  char[] reverseTable) {
        int result = 0;
        int i = 0;
        int numBytes = 4;

        for (i = 0; i < numBytes; ++i) {
            /*Get the least significant byte from the input*/
            int curByteValue = inputValue & 0xFF;

            /*Left shift the result by 8 and append the reverse of the 
            LS byte of input*/
            result = (result << 8) | reverseTable[curByteValue];

            /*Right shift out the least significant byte from the input*/
            inputValue = inputValue >> 8;
        }

        return result;
    }

    public static int normalReverseInteger( int input) {
        int sizeInBits = 32;

        int result = 0;
        int j = sizeInBits - 1;
        for (int i = 0; i < sizeInBits; ++i) {
            if ( (input & (1 << i)) != 0 ) {
                result = result | (1 << j);
            }
            --j;
        }

        return result;
    }


    public static char normalReverseChar( char input) {
        int sizeInBits =  8 ;

        char result = 0;
        int j = sizeInBits - 1;
        for (int i = 0; i < sizeInBits; ++i) {
            if ( (input & (1 << i)) != 0) {
                result = (char) (result | (1 << j));
            }
            --j;
        }

        return result;
    }


    public static void main(String[] args) {
        char[] lookupTable = new char[256];

        for (int i = 0; i <= 255; ++i) {
            lookupTable[i] = normalReverseChar(( char)i);
        }
    
        int randNum;
        for (int testNr = 0; testNr < MAX_NUM_TESTS; ++testNr) {
            randNum =  (getRandByte() << 24) | getRandByte() << 16 | getRandByte() << 8 | getRandByte();
        
            int normalResult = normalReverseInteger(randNum);

            int lookupResult = reverseInteger(randNum, lookupTable);

            if (normalResult != lookupResult) 
                handleError();
        }

        randNum = 0;
        if (randNum != reverseInteger(randNum, lookupTable))
            handleError();

        randNum = 0xffffffff;
        if (randNum != reverseInteger(randNum, lookupTable))
            handleError();


        System.out.println("Test passed");

    }

}
