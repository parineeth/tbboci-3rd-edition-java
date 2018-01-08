/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_19_TelephoneKeypad {


    /*Helper function for printing the words corresponding to the telephone number
    digits: array of digits from 0-9
    keyPad: contains the characters corresponding to each digit
    buf: contains the word formed corresponding to the telephone digits
    pos: current position in buf and digits
    */
    public static void keypadStringGen(int[] digits, String[] keypad, 
                    char[] buf, int pos) {
        if (pos == digits.length) {
            /*We have processed all the digits. So print the 
            word and terminate the recursion*/
            System.out.println(buf);
            return;
        }

        int curDigit = digits[pos];
        String keyString = keypad[curDigit];

        /*keyString is the string corresponding to the current digit
        So if current digit is 2, keyString will be "ABC".
        Cycle through all the characters in the keyString.*/
        int i = 0;
        while (i < keyString.length()) {
            buf[pos] = keyString.charAt(i);
            keypadStringGen(digits, keypad, buf, pos+1);
            i++;
        }

    }

    /*Main function for printing the words corresponding to the telephone number
    digits: array of digits from 0-9 in the telephone number
    */
    public static void telephoneDigitsToString(int[] digits) {
        int numDigits = digits.length;
        /*Create a temporary buffer for storing the words corresponding to 
        the digits*/
        char[] buf = new char[numDigits]; 

        /*digit 2 corresponds to ABC, 3 corresponds to DEF and so on*/
        String[] keypad = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", 
                "PQRS", "TUV", "WXYZ"};
    
        keypadStringGen(digits, keypad, buf, 0);
    }


    public static void main(String[] args) {
        int[] digits = {4, 2, 1, 1, 8, 9, 2, 5, 0, 8};

        telephoneDigitsToString(digits);

        System.out.println("Test passed");
    }

}
