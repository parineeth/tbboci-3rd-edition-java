/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_18_StringInterleave {

    public static final int MAX_STRING_LENGTH = 100;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*Helper function for printing the result*/
    public static void printResult(char[] buffer, int bufPos, String remainder) {
        String result = new String(buffer, 0, bufPos);
        result = result + remainder;
        System.out.println(result);
    }

    /*
    str1, str2: two valid input strings that have to be interleaved
    buffer: result of interleaving the two strings
    pos1: current position in string str1
    pos2: current position in string str2
    bufPos: current position in the buffer
    */
    public static void stringInterleave(String str1, String str2, char[] buffer, 
            int pos1, int pos2, int bufPos) {
        /*If we have finished processing both strings, print the buffer and
        terminate the recursion*/
        if (pos1 == str1.length() && pos2 == str2.length()) {
            printResult(buffer, bufPos, "");
            return;
        }

        /*If we have finished processing str2, concatenate remaining str1 to
        the buffer, print the buffer and terminate the recursion*/
        if (pos2 == str2.length()) {
            printResult(buffer, bufPos, str1.substring(pos1));
            return;
        }

        /*If we have finished processing str1, concatenate remaining str2 to 
        the buffer, print the buffer and terminate the recursion*/
        if (pos1 == str1.length()) {
            printResult(buffer, bufPos, str2.substring(pos2));
            return;
        }

        /*Include the next character of str1 into the buffer*/
        buffer[bufPos] = str1.charAt(pos1);
        stringInterleave(str1, str2, buffer, pos1 + 1, pos2, bufPos + 1);

        /*Include the next character of str2 into the buffer*/
        buffer[bufPos] = str2.charAt(pos2);
        stringInterleave(str1, str2, buffer, pos1, pos2 + 1, bufPos + 1);   
    }



    public static void main(String[] args)  {
        char[] buffer = new char[MAX_STRING_LENGTH];

        stringInterleave("abcde", "123", buffer, 0, 0, 0);

        System.out.println("Test passed");
    }


}
