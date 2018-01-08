/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_15_PrintAllSubstrings {

    /*
    str1: string whose substrings should be printed
    */
    public static void printAllSubStrings(char[] str1) {
        /*Generate all pairs (i,j) where i <= j*/
        for (int i = 0; i < str1.length; ++i) {
            for (int j = i; j < str1.length; ++j) {
                /*print the substring str1[i] to str1[j]*/
                System.out.print(new String(str1, i, j - i + 1));

                System.out.println("");
            }
        }
    }




    public static void main(String[] args)  {
        printAllSubStrings("ABCDE".toCharArray());

        System.out.println("Test passed");
    }

}
