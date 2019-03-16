/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_13_LongestPalindrome {


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*str1: valid input character string
    Return value: length of longest palindrome
    */
    public static int findLongestPalindrome(char[] str1)  {
        int maxPalLen = 0;
        for (int pos = 0; pos < str1.length; ++pos)  {
            /*Check for odd length palindromes by comparing the characters
            to the left of pos with the characters to the right of pos
            */
            int left = pos - 1;
            int right = pos + 1;
            int curPalLen = 1;
            while (left >= 0 && right <= str1.length - 1)  {
                if (str1[left] != str1[right])
                    break;
                curPalLen += 2;
                --left;
                ++right;
            }

            if (curPalLen > maxPalLen)
                maxPalLen = curPalLen;


            /*Check for even length palindromes. If str1[pos], matches
            with str1[pos+1], then compare the characters to the left of
            pos with the characters to the right of pos+1
            */
            if (pos < str1.length - 1 && str1[pos] == str1[pos + 1])  {
                left = pos - 1;
                right = pos + 2;
                curPalLen = 2;
                while (left >= 0 && right <= str1.length - 1)  {
                    if (str1[left] != str1[right])
                        break;
                    curPalLen += 2;
                    --left;
                    ++right;
                }

                if (curPalLen > maxPalLen)
                    maxPalLen = curPalLen;
            }
        }
        return maxPalLen;
    }



    public static void test01(String str1, int expectedResult) {

        int result = findLongestPalindrome(str1.toCharArray());

        System.out.println("Length of longest palindrome in " + str1 + " = " + result);

        if (result != expectedResult)
            handleError();
    }


    public static void main(String[] args)  {
        test01("RACECAR", 7);
        test01("AABB", 2);
        test01("ABBA", 4);
        test01("ACBBDA", 2);
        test01("ABCDE", 1);/*A single character in the string can be treated as a palindrome*/

        System.out.println("Test passed");
    }

}
