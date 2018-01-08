/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_07_RemoveDuplicates {

    public static final int NUM_CHARACTERS = 256;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*
    str1: input string from which duplicate characters should be removed
    Returns: output string which doesn't contain any duplicates
    */
    public static String removeDuplicates(char[] str1) {
        boolean[] wasCharObserved = new boolean[NUM_CHARACTERS];
    
        if (str1 == null)
            return null;

        if (str1.length < 2)
            return new String(str1);


        /*Only if the current character was not observed so far, add the 
        current character to fill position and advance the fill position*/
        int fillPos = 0;
        for (int i = 0; i < str1.length; ++i) {
            char c = str1[i];
        
            if (!wasCharObserved[c]) {
                str1[fillPos] = c;
                fillPos++;
            }
            wasCharObserved[c] = true;
        }

        return new String(str1, 0, fillPos);
    }


    public static void test(String str1, String expectedResult) {
        System.out.print(str1);

        String result = removeDuplicates(str1.toCharArray());

        System.out.println(" => " + result);

        if (!result.equals(expectedResult))
            handleError();
    }




    public static void main(String[] args)  {
        test("", "");
        test("a", "a");
        test("1aaaaaa2bbbaaaaa3cccccaaaabbb", "1a2b3c");
        System.out.println("Test passed");
    }

}
