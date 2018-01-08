/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_10_ReverseWords {


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*Helper function which reverses a string between indexes low and high
    str1: string which needs to be reversed
    low: lower index of region to be reversed
    high: higher index of region to be reversed
    */
    public static void reverseString(char[] str1, int low, int high) {
        while (low < high) {
            char temp = str1[low];
            str1[low] = str1[high];
            str1[high] = temp;
            low++;
            high--;
        }
    }


    /*Helper function which checks if a character is an alphabet(a-zA-Z)*/
    public static boolean isAlphabet(char c) {
        if (c >= 'A' && c <= 'Z')
            return true;

        if (c >= 'a' && c <= 'z')
            return true;

        return false;       
    }


    /*Main function to reverse the words in a string
    str1: the input string in which the words have to be reversed
    Returns: string in which the words have been reversed
    */
    public static String reverseWords(char[] str1) {
        if (str1.length < 2)
            return new String(str1);

        /*Reverse the entire string*/
        reverseString(str1, 0, str1.length - 1);

        /*Reverse the individual words in the string*/
        int pos = 0;
        while (pos < str1.length) {
            if (isAlphabet(str1[pos])) {
                int low = pos;
                while (pos < str1.length && isAlphabet(str1[pos])) {
                    pos++;
                }
                int high = pos - 1;
                reverseString(str1, low, high);
            } else {
                pos++;
            }   
        }
    
        return new String(str1);
    }


    public static void test(String str1, String expectedResult)     {
        System.out.print(str1);

        String result = reverseWords(str1.toCharArray());

        System.out.println(" =>  " + result);

        if (!result.equals(expectedResult))
            handleError();

    }



    public static void main(String[] args)  {
        test("", "");
        test("a", "a");
        test("Hello how are you", "you are how Hello");

        System.out.println("Test passed");
    }

}
