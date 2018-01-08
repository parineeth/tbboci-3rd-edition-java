/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Dyn_12_WordBreak {


    public static void handleError() {
        System.out.println(  "Error occured ");
        System.exit(1);
    }

    public static boolean checkDictionary(String str1, HashSet<String> dictionary) {
         return dictionary.contains(str1);
    } 

    /* Helper function to print the words present in the string
    str1: input string
    wordStart: if the substring from position i to position j of the original string
        is a word in dictionary, then wordStart[j] will be i
    */
    public static void printResult(String str1, int[] wordStart) {
        int pos = str1.length() - 1;
        while (pos >= 0) {
            /*The current word ends at pos in the input string and
            starts at wordStart[pos] */
            System.out.print( str1.substring(wordStart[pos], pos + 1) + " ");
            pos = wordStart[pos] - 1;
        }
    }


    /*
    str1: string that we need to check if it can be broken
    dictionary: permitted words are stored in the dictionary 
    Return value: true if we can break str1 into words in the dictionary
    */
    public static boolean wordBreak(String str1, HashSet<String> dictionary) {
        int length = str1.length();

        if (length == 0)
            return false;

        /*if we can break the string from 0 to pos, then isBreakPossible[pos] 
        will be true*/
        boolean[] isBreakPossible = new boolean[length];

        /*if the substring from position i to position j of the original string
        is a word in dictionary, then wordStart[j] will be i*/
        int[] wordStart = new int[length];
        int i;
        for (i = 0; i < length; ++i) {
            wordStart[i] = -1;
        } 

        for (i = 0; i < length; ++i) {
            /*Check if the substring from 0 to i is in the dictionary*/
            String tempStr = str1.substring(0, i+1);
            if (!isBreakPossible[i] && checkDictionary(tempStr, 
                                dictionary)) {
                isBreakPossible[i] = true;
                wordStart[i] = 0;
            }

            /*If we can break the substring upto i into dictionary words, 
            then check if all substrings starting from i+1 can be broken 
            into dictionary words */
            if (isBreakPossible[i]) {
                for (int j = i + 1; j < length; ++j) {
                    tempStr = str1.substring(i+1, j+1);
                    if (!isBreakPossible[j]  
                    && checkDictionary(tempStr, dictionary)) {
                        /*We can form a word from i+1 to j*/
                        isBreakPossible[j] = true;
                        wordStart[j] = i+1;
                    }
                }
            }
        }

        /*
        If isBreakPossible[length-1] is true, then entire string can be
        broken into dictionary words. If the wordStart[length-1] is 0, then  
        it means the entire input word is present in the dictionary. But we
        want a compound word that has 2 or more dictionary words in it.
        So modify the result condition to check wordStart[length-1] != 0
        */
        if (isBreakPossible[length-1] && wordStart[length-1] != 0) {
            printResult(str1, wordStart);
            return true;
        }

        return false;
    }



    public static void test(String str1, boolean expectedResult) {

        HashSet<String> dictionary = new HashSet<String>();

        dictionary.add("i");
        dictionary.add("will");
        dictionary.add("play");
        dictionary.add("now");

        System.out.print(  str1 + " => ");

        boolean result = wordBreak(str1, dictionary);

        System.out.println();

        if (result) {
            System.out.println(  str1 + " can be broken ");
        } else {
            System.out.println(  str1 + " cannot be broken ");
        }

        if (result != expectedResult)
            handleError();

        System.out.println(  "________________________________________");
    }


    public static void main(String[] args) {
        test("iwilli", true);
        test("iwillplaynow", true);
        test("nowplayiwill", true);
        test("iiiiiiiiiii", true);
        test("iwpn", false);

        System.out.println(  "Test passed ");
    }

}
