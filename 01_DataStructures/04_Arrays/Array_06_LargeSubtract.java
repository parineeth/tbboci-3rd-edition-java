/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Array_06_LargeSubtract {

    public static final int MAX_NUM_TESTS  = 10;
    public static final int MAX_NUM_DIGITS = 4;


    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    public static void printArray(char[] a) {
        for (char curVal : a) {
            System.out.print((int)curVal + " ");
        }
    }

    public static char[] getRandomNum() {
        Random randomGenerator = new Random();
        int numDigits = 1 + randomGenerator.nextInt(MAX_NUM_DIGITS);
        char[] result = new char[numDigits];
        
        for (int i = 0; i < numDigits; ++i) {
            if (i == 0)
                /*Most significant digit should be non-zero*/
                result[i] = (char) (1 + randomGenerator.nextInt(9));
            else
                result[i] = (char)randomGenerator.nextInt(10);
        }
        return result;
    }



    public static int convertArrayToNum(char[] a) {
        int num = 0;
        for (char curVal : a) {
            num = (num * 10) + curVal;
        }

        return num;
    }

    public static void printResult(char[] num1, char[] num2,  char[] result, boolean isNegative) {
        printArray(num1);

        System.out.print(" - ");

        printArray(num2);

        System.out.print (" = ");

        if (isNegative)
            System.out.print(" -");

        printArray(result);

        System.out.println("");
    }

    /*Helper function which returns 1 if num1 is smaller than num2*/
    public static boolean isSmaller(char[] num1, char[] num2) {

        if (num1.length > num2.length)
            return false;

        if (num1.length < num2.length)
            return true;

        for (int i = 0; i < num1.length; ++i) {
            if (num1[i] > num2[i])
                return false;

            if (num1[i] < num2[i])
                return true;
        }
    
        return false;   
    }

    /*
    num1 and num2: arrays which store the digits of the two numbers. 
        The two arrays store numeric value of the digits and not ascii values
    isNegative: indicates if the result is negative or not
    Returns: the result of the subtraction 
    */
    public static char[] largeSubtract(char[] num1, char[] num2, 
                    boolean[] isNegative) {
        char[] temp;

        isNegative[0] = false;

        /*Store larger number in num1
        So if num1 is smaller than num2, then swap num1 and num2*/
        if (isSmaller(num1, num2) ) {
            /*Swap num1 and num2*/
            temp = num1; num1 = num2; num2 = temp;

            /*If num1 was smaller than num2, then result will be negative*/
            isNegative[0] = true;
        } 

        char[] result = new char[num1.length];

        /*Perform the subtraction for all the digits in num2*/
        int pos1 = num1.length - 1;
        int pos2 = num2.length - 1;
        int borrow = 0, difference;
        while (pos2 >= 0) {
            difference = num1[pos1] - num2[pos2] - borrow;
            if (difference < 0) {
                difference += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result[pos1] = (char)difference; 
            pos1--;
            pos2--; 
        }

        /*Process any digits leftover in num1*/
        while (pos1 >= 0) {
            difference = num1[pos1] - borrow;
            if (difference < 0) {
                difference += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result[pos1] = (char)difference; 
            pos1--;
        }

        return result;  
    }

    public static void test() {
        boolean[] isNegative = new boolean[1];
        
        /*Generate a random set of digits in the array num1*/
        char[] num1 = getRandomNum();

        /*Generate a random set of digits in the array num2*/
        char[] num2 = getRandomNum();

        /*Perform the subtraction*/
        char[] result = largeSubtract(num1, num2, isNegative);

        printResult(num1, num2, result, isNegative[0]);

        int val1 = convertArrayToNum(num1);
        int val2 = convertArrayToNum(num2);
        
        int val3 = convertArrayToNum(result);
        if (isNegative[0])
            val3 = val3 * -1;

        /*Verify the result*/
        if (val3 != (val1 - val2))
            handleError();

    }


    public static void main(String[] args)  {
        for (int testNr = 0; testNr < MAX_NUM_TESTS; ++testNr) {
            test();
        }

        System.out.println("Test passed");
    }


}

