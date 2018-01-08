/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_14_IsPermutationPalindrome {

    public static final int NUM_CHARACTERS = 256;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*Returns true if there is at least one permutation of string str1 which
    is a palindrome, false otherwise*/
    public static boolean isPermutationPalindrome(String str1) {
        int[] count = new int[NUM_CHARACTERS];

        /*Find out how many times a character appears in the string*/
        int i = 0;
        while(i < str1.length()) {
            count[str1.charAt(i)]++;
            i++;
        }

        int numOddChar = 0;
        for (i = 0; i < NUM_CHARACTERS; ++i) {
            if (count[i] % 2 == 1)
                numOddChar++;

            /*If there are 2 or more characters that appear odd number 
            of times then we can't form a palindrome with any permutation 
            of the string*/
            if (numOddChar >= 2)
                return false;
        }

        return true;
    }


    public static void test(String str1, boolean expectedResult) {
        boolean result = isPermutationPalindrome(str1);

        if (result)
            System.out.println("Permutation of " + str1 + " is a palindrome");
        else
            System.out.println("Permutation of " + str1 + " is NOT a palindrome");
    
        if (result != expectedResult)
            handleError();
    }


    public static void main(String[] args) {
        test("elelv", true); /*level is a palindrome */
        test("ab", false);

        System.out.println("Test passed");
    }

}
