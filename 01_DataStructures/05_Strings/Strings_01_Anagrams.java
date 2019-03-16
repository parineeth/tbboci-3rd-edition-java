/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_01_Anagrams {

    public static final int NUM_CHARACTERS = 256;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }




    /*
    str1, str2: the two strings which we want to compare
    Return value: true if the two strings are anagrams, false otherwise
    */
    public static boolean areAnagrams( char[] str1, char[] str2)    {
        /*Initialize the counts to zero */
        int[] count1 = new int[NUM_CHARACTERS]; //NUM_CHARACTERS is 256
        int[] count2 = new int[NUM_CHARACTERS];

        /*Compute the character counts for str1 and str2*/
        for (char c: str1) 
            count1[c]++;

        for (char c : str2) 
            count2[c]++;

        /*Compare the counts*/
        boolean isAnagram = true;
        for (int i = 0; i < NUM_CHARACTERS; ++i) {
            if (count1[i] != count2[i]) {
                isAnagram = false;
                break;
            }
        }

        return isAnagram;
    }

    public static void test(String s1, String s2, boolean expectedResult) {

        boolean result = areAnagrams(s1.toCharArray(), s2.toCharArray());

        System.out.print(s1 + " and " + s2 + " are ");
        if (result)
            System.out.println("anagrams");
        else
            System.out.println("not anagrams");

        if (result != expectedResult) {
            handleError();
        }
        
    }

    public static void main(String[] args)  {
        test("ABCDE", "CDBAE", true);
        test("AAAAA", "BBBBB", false);

        System.out.println("Test passed ");
    }


}
