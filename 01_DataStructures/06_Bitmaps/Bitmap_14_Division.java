/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Int {
    public int value;

    public Int() {
        value = 0;
    }
    
}


class Bitmap_14_Division {

    public static final int MAX_NUMERATOR = 100;
    public static final int MAX_DENOMINATOR = 20;


    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    /*
    a, b: a is an integer >= 0. b is an integer > 0
    resultQ: quotient of a / b will be returned here
    resultR: remainder of a / b will be returned here
    */
    public static void integerDivision(int a, int b, Int resultQ, 
                Int resultR) throws IllegalArgumentException {
        int maxBitPos = 31; 

        if (b == 0) 
            throw new IllegalArgumentException();
    
        int quotient = 0;
        int remainder = 0;
        for (int i = maxBitPos; i >= 0; --i) {
            remainder = remainder << 1; /*Double the remainder*/

            /*Find the value of the next bit in the dividend a. 
            In first iteration, we find value of the Most Significant Bit*/ 
            char nextBit = 0;
            if ((a & (1 << i)) != 0) {
                nextBit = 1;
            }

            /*Copy the value of the next bit into the least significant 
            bit of remainder*/  
            if (nextBit == 1)
                remainder = remainder | 1;
    
            /* If the remainder is now greater than the divisor b, 
            then subtract the divisor b from the remainder and 
            set the appropriate quotient bit*/
            if (remainder >= b) {
                remainder = remainder - b;
                quotient = quotient | (1 << i);
            }
        }
        resultQ.value = quotient;
        resultR.value = remainder;
    }




    public static void main(String[] args) {
        Int quotient = new Int();
        Int remainder = new Int();


        for (int a = 0; a < MAX_NUMERATOR; ++a) {
            for (int b = 1; b < MAX_DENOMINATOR; ++b) {
                integerDivision(a, b, quotient, remainder);

                System.out.println(a + " / " + b + " => quotient = " + quotient.value + 
                        " remainder = " + remainder.value);

                int expectedQuotient = a / b;
                int expectedRemainder = a % b;

                if (quotient.value != expectedQuotient)
                    handleError();

                if (remainder.value != expectedRemainder) 
                    handleError();
            }
        }

        System.out.println("Test passed ");

    }

}


