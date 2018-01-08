/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_03_StrToInt
{
    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }   

    /*
    str1: string to be converted to integer
    result: integer value of string
    */
    public static int strToInt(char[] str1) {
        int result = 0;
        boolean isNegative = false;
        int count = 0;
    
        for (char c : str1) {
            if (c == '-' && count == 0)
                isNegative = true;
        
            if ('0' <= c && c <= '9') 
                result = (result * 10) + (c - '0');

            count++; 
        }

        if (isNegative)
            result = -1 * result;

        return result;
    }

    public static void test(String s1, int expected_result) {
        int result = strToInt(s1.toCharArray());

        System.out.println(s1 + " => " + result);

        if (result != expected_result)
            handleError();
    }

    public static void main(String[] args) {
        test("-47", -47);
        test("987", 987);
        test("", 0);
        System.out.println("Test passed");
    }
}
