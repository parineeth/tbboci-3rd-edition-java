/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_02_WordCount {



    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*Helper function which checks if a character is an alphabet(a-zA-Z)*/
    public static boolean isAlphabet(char c)    {
        if (c >= 'A' && c <= 'Z')
            return true;

        if (c >= 'a' && c <= 'z')
            return true;

        return false;       
    }

    /*
    str1: string in which the number of words have to be counted
    Return value: number of words in the string
    */
    public static int countWords(char[] str1) {
        if (str1 == null)
            return 0;

        int numWords = 0;
        boolean isPrevCharAlphabet = false;
        for (char c : str1) {
            boolean isCurCharAlphabet = isAlphabet(c);

            /*If previous character is not an alphabet and current character is 
            an alphabet then we have found a new word*/
            if (!isPrevCharAlphabet && isCurCharAlphabet) 
                ++numWords;

            isPrevCharAlphabet = isCurCharAlphabet;
        }

        return numWords;
    }


    public static void test(String s1, int expectedResult) {

        int result = countWords(s1.toCharArray());

        if (result != expectedResult)
            handleError();

        System.out.println("Word count = " + result + ", for string: " + s1);
    }


    public static void main(String[] args)  {

        int expectedWordCount = 1;
        test("hello", expectedWordCount);

        expectedWordCount = 0;
        test("", expectedWordCount);
    

        expectedWordCount = 5;
        test("   hello,how are    you doing?", expectedWordCount);

        System.out.println("Test passed");
    }

}
