/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_09_RotatedString {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*
    str1 and str2 are the two strings which need to be checked
    Return value: true if the two strings are rotations of each other, false otherwise
    */
    public static boolean isStringRotation(String str1, String str2) {
        if (str1 == null || str2 == null)
            return false;

        /*If lengths of two strings are not equal, then they can't be rotations*/
        if (str1.length() != str2.length())
            return false;

        String str3 = str1 + str1;

        /*if str2 is a substring of str3, then str1 and str2 are rotations 
        of each other*/
        boolean isRotation = str3.contains(str2);
        return isRotation;
    }


    public static void test(String str1, String str2, boolean expectedResult) {

        boolean result = isStringRotation(str1, str2);

        System.out.print(str1 + " and " + str2);
        if (result)
            System.out.println(" are rotations of each other");
        else 
            System.out.println(" are NOT rotations of each other");


        if (result != expectedResult)
            handleError();
    }


    public static void main(String[] args)  {

        test("ABCDE", "CDEAB", true);
        test("ABCDE", "EABCD", true);
        test("AAA", "AAB", false);

        System.out.println("Test passed ");
    }

}
