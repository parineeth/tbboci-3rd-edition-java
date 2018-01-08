/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Strings_05_RomanNumeral {

    public static void handleError() {
        System.out.println(  "Error occured ");
        System.exit(1);
    }

    /*Helper function that returns the numeric value of a Roman alphabet*/
    public static int getNumericValue(char c) {
        int result;

        switch(c) {
            case 'I': result = 1; break;
            case 'V': result = 5; break;
            case 'X': result = 10; break;
            case 'L': result = 50; break;
            case 'C': result = 100; break;
            case 'D': result = 500; break;
            case 'M': result = 1000; break;
            default: result = 0; break;
        }
        return result;
    }

    /* 
    Main function that converts a Roman string into an integer
    str1: valid input string with Roman alphabets
    Return value: integer equivalent of the Roman string
    */
    public static int romanToInt(char[] str1) {
        /*Process the string from the rear*/        
        int i = str1.length - 1;
        if (i < 0)
            return 0;

        int result = getNumericValue(str1[i]);
        --i;
        while (i >= 0) {
            int curDigitVal = getNumericValue(str1[i]);
            int nextDigitVal = getNumericValue(str1[i+1]);

            if (curDigitVal < nextDigitVal)
                result -= curDigitVal;
            else
                result += curDigitVal;      
        
            --i;
        }

        return result;
    }

    


    public static void test(String s1, int expectedResult) {
        int result = romanToInt(s1.toCharArray());

        System.out.println(s1 + " => "  + result );

        if (result != expectedResult)
            handleError();
    }


    public static void main(String[] args)  {
        test("MMCX", 2110);
        test("MMXC", 2090);
        test("LIX", 59);
        test("LVIIII", 59);
        test("X", 10);
        test("", 0);

        System.out.println(  "Test passed ");
    }

}
