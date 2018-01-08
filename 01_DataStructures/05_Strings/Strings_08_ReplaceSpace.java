/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Strings_08_ReplaceSpace {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*
    str1: char array which has spaces that should be replaced with %20
    Return value: output string where spaces are replaced with %20
    */
    public static String replaceSpace(char[] str1) {
        /*Count the number of spaces*/
        int numSpaces = 0;
        for(char c : str1) {
            if (c == ' ')
                numSpaces++;
        }
    
        int oldLength = str1.length;
        int newLength = str1.length + (2 * numSpaces);

        /*Since result will be longer, create a bigger char array
        which has the contents of the old char array*/
        str1 = Arrays.copyOf(str1, newLength);

        /*Fill position is now at original length + (2*numSpaces) - 1 */
        /*Keep copying characters from rear of original string to fillPos*/
        int fillPos = newLength - 1; 
        int i = oldLength - 1;
        while ( i >= 0) {
            if (str1[i] == ' ') {
                str1[fillPos] = '0';
                str1[fillPos - 1] = '2';
                str1[fillPos - 2] = '%';
                fillPos = fillPos - 3;
            } else {
                str1[fillPos] = str1[i];
                fillPos--;
            }
            --i;
        }

        /*Convert the char array to a String*/
        return new String(str1, 0, newLength);
    }



    public static void test(String str1, String expectedResult)     {
        System.out.print(str1);

        String result = replaceSpace(str1.toCharArray());

        System.out.println(" =>  " + result);

        if (!result.equals(expectedResult))
            handleError();


    }



    public static void main(String[] args)  {
        test("", "");
        test("a", "a");
        test(" Hello  how are you ", "%20Hello%20%20how%20are%20you%20");

        System.out.println("Test passed");
    }


}
