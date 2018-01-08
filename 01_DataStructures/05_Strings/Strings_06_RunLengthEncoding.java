/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_06_RunLengthEncoding {


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*Performs run length encoding on a string
    str1: input string (example: "aaabb")
    Returns: output string (example: "a3b2")
    */
    public static String runLengthEncode(String str1) {
        String result = "";

        if (str1 == null)
            return result;

        int pos1 = 0;
        while (pos1 < str1.length()) {
            char c = str1.charAt(pos1);

            /*Count the number of consecutive occurences of character c*/
            int count = 0;
            while (pos1 < str1.length() && c == str1.charAt(pos1)) {
                count++;
                pos1++;
            }

            /*Store character c and the count in the output string*/
            result = result + Character.toString(c);
            result = result + Integer.toString(count);
        }
        return result;
    }


    public static void test(String str1, String expectedResult) {
        String result = runLengthEncode(str1);
        
        System.out.println(str1 + " => " + result);
        
        if (!result.equals(expectedResult))
            handleError();
    
    }

    public static void main(String[] args)  {
        test("", "");
        test("a", "a1");
        test("abcde", "a1b1c1d1e1");
        test("aaabcccbba", "a3b1c3b2a1");
        System.out.println("Test passed");
    }

}
