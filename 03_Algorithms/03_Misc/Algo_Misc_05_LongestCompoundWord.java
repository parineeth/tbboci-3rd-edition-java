/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class StringComparator implements Comparator<String> {
    /*Longer string should be stored first*/
    public int compare(String a, String b)  {
        if (a.length() < b.length())
            return 1;
        else if (a.length() == b.length())
            return 0;
        else
            return -1;
    }
}


class Algo_Misc_05_LongestCompoundWord {


    public static void handleError() {
        System.out.println(  "Error occured ");
        System.exit(1);
    }


    public static void printWords(String[] words) {
        for (String curWord: words) {
            System.out.println(curWord);
        }
    }

    public static boolean checkDictionary(String str1, HashSet<String> dictionary) {
         return dictionary.contains(str1);
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
            return true;
        }

        return false;
    }


    /*
    words: the input list of words
    Return value: the longest compound word if it exists, null otherwise
    */
    public static String  findLongestCompoundWord(String[] words) {
        HashSet<String> dictionary = new HashSet<String>();

        for (String curWord : words) {
            dictionary.add(curWord);
        }

        /*Sort the words so that the longest word appears first*/
        Arrays.sort(words, new StringComparator());

        /*Starting from the longest word, check if the word can be broken 
        into two or more words present in the dictionary. If yes, then we 
        have found the longest compound word*/
        for (String curWord: words) {
            if (wordBreak(curWord, dictionary))
                return curWord;
        }

        /*There is no compound word in the input*/
        return null;
    }


    public static void test01() {
        String[] words = {"hello", "lumber", "hellolumberjack", "hellojack", "jack"};

        printWords(words);

        String longest = findLongestCompoundWord(words);

        if (longest != null)
            System.out.println(  "Longest compound word = " + longest);

        if (!longest.equals("hellolumberjack")) {
            handleError();
        }


        System.out.println(  "________________________________________");
    }


    public static void test02() {
        String[] words = {"hello", "jack", "lumber"};

        printWords(words);

        String longest = findLongestCompoundWord(words);

        if (longest != null)
            System.out.println(  "Longest compound word = " + longest);
        else
            System.out.println(  "No compound word possible ");

        if (longest != null) {
            handleError();
        }


        System.out.println(  "________________________________________");
    }


    public static void main(String[] args) {
        test01();
        test02();

        System.out.println(  "Test passed ");
    }

}
