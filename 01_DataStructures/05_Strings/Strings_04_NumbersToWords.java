/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_04_NumbersToWords {


    /* 
    Helper function to print number from 1 to 999
    number: number from 1 to 999
    */
    public static void print3Digits(int number) {
        /*basicLookup[0] is empty. We want basicLookup[1] to map to "One"
        and so on. */
        String[] basicLookup = {"", "One", "Two", "Three", "Four", "Five", 
                "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", 
                "Twelve", "Thirteen", "Fourteen", "Fifteen", 
                "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

        /*tensLookup[0] and tensLookup[1] are empty.
        We want tensLookup[2] to map to "Twenty" and so on. */
        String[] tensLookup = {"", "","Twenty", "Thirty", "Fourty", "Fifty",
                "Sixty", "Seventy", "Eighty", "Ninety"};

        /*Suppose number is 987, then hundredsDigit is 9*/
        int hundredsDigit = number / 100;
        if (hundredsDigit > 0) {
            System.out.print(basicLookup[hundredsDigit] + " Hundred ");
        }

        /*Suppose number is 987, then remainder will be 87*/
        int remainder = number % 100;
        if (remainder > 0) {
            if (remainder <= 19) {
                System.out.print(basicLookup[remainder] + " ");         
            } else {
                int tensDigit = remainder / 10;
                int unitDigit = remainder % 10;
                System.out.print(tensLookup[tensDigit] + " ");
                System.out.print(basicLookup[unitDigit] + " ");
            }
        }
    }


    /*
    Main function to print the number in words
    number: any number from 0 to 999999999
    */
    public static void printNumInWords(int number) {
        /*If number is 0, handle it here and return*/
        if (number == 0) {
            System.out.println("Zero ");
            return;
        }

        /*Suppose number is 123456789, then millions = 123, remainder = 456789*/
        int millions = number / 1000000;
        int remainder = number - (millions * 1000000);

        /*Suppose remainder = 456789, then thousands = 456, remainder = 789*/
        int thousands = remainder / 1000;
        remainder = remainder - (thousands * 1000);

        if (millions > 0) {
            print3Digits(millions);
            System.out.print("Million ");
        }

        if (thousands > 0) {
            print3Digits(thousands);
            System.out.print("Thousand ");
        }

        if (remainder > 0) {
            print3Digits(remainder);
        } 

        System.out.println("");
    }


    public static void test(int x) {
        System.out.print(x + " in words = ");
        printNumInWords(x);
    }



    public static void main(String[] args) {
        test(0);
        test(8);
        test(15);
        test(75);
        test(100);
        test(512);
        test(987);
        test(1001);
        test(450012);
        test(816280);
        test(1000001);
        test(10011284);
        test(90145012);
        test(987654321);

        System.out.println("Test passed");
    }

}
