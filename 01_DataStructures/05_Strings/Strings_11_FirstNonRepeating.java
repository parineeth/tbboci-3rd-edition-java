/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Strings_11_FirstNonRepeating {

    public static final int NUM_CHARS = 256;

    public static void handleError() {
        System.out.println("Error occured");
        System.exit(1);
    }

    /*
    str1: character array to be searched
    Return value: first unique character if it exists, '\0' otherwise
    */
    public static char findFirstUniqueChar(char[] str1) {
        int[] count = new int[NUM_CHARS];

        /*count the number of occurences of each character*/
        for (char c : str1) 
            count[c]++;

        /*traverse str1 and find first character which occurs only once*/
        char firstUniqueChar = '\0';
        for (char c : str1) {
            if (count[c] == 1) {
                firstUniqueChar = c;
                break;
            }
        }

        return firstUniqueChar;
    }


    public static void test(String str1, char expectedResult) {
        char result = findFirstUniqueChar(str1.toCharArray());
    
        System.out.println("First unique in \"" + str1 + "\" is " + result); 

        if (result != expectedResult)
            handleError();
    }


    public static void main(String[] args)  {

        test("aabbbccccddeffffgg", 'e');
        test("abcdefab", 'c');
        test("abab", '\0');

        System.out.println("Test passed");
    }

}
