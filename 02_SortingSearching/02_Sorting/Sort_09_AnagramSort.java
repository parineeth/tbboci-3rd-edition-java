/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class AnagramHelper {
    char[] word;
    int index;
}


class AnagramComparator implements Comparator<AnagramHelper> {
    public int compare(AnagramHelper first, AnagramHelper second)   {
        char[] a = first.word;
        char[] b = second.word;
        int numChar = Math.min(a.length, b.length);

        for (int i = 0; i < numChar; ++i) {
            if (a[i] > b[i])
                return 1;
            else if (a[i] < b[i]) 
                return -1; 
        }
        if (a.length == b.length)
            return 0;
        else if (a.length > b.length)
            return 1;
        else 
            return -1;
    }
}



class Sort_09_AnagramSort {


    public static void handleError() {
        System.out.println("Test failed ");
        System.exit(1);
    }



    /*
    wordList: array of words which should be sorted so that the anagrams occur together
    */
    public static void anagramSort(String[] wordList) {
        int numWords = wordList.length;
        AnagramHelper[] helper = new AnagramHelper[numWords];

        int i;
        for (i = 0; i < numWords; ++i) {
            helper[i] = new AnagramHelper();

            /*copy the original word from wordList into helper */
            helper[i].word = wordList[i].toCharArray();
    
            /*First sort the characters of the word in the helper*/
            Arrays.sort(helper[i].word);
    
            /*Store the original index of the word in the helper*/
            helper[i].index = i;
        }

        /*Sort all the words in the helper*/
        Arrays.sort(helper, new AnagramComparator() );

        /*We need to move the words in wordList based on the indexes in the 
        helper. We can't directly move the strings in the wordList array.
        First we will copy the strings into a scratchpad array
        based on the indexes in the helper and then copy the scratchpad
        array into the wordList array.*/
        String[] scratchpad = new String[numWords];

        for (i = 0; i < numWords; ++i) {
            int index = helper[i].index;
            scratchpad[i] = wordList[index];
        }

        for (i = 0; i < numWords; ++i) {
            wordList[i] = scratchpad[i];
        }
    }


    public static void printWords(String[] words) {
        for (String curWord : words) {
            System.out.print(curWord + " ");
        }

        System.out.println("");
    }

    public static void test() {
        int numWords = 6;
        String[] wordList = { "rat", "atm", "hill", "art", "mat", "tar"};
        String[] expectedResult = {"atm", "mat", "rat", "art", "tar", "hill"}; 

        System.out.print("Before sorting: ");
        printWords(wordList);   

        anagramSort(wordList);

        System.out.print("After  sorting: ");
        printWords(wordList);   

        for (int i = 0; i < numWords; ++i) {
            if (! wordList[i].equals(expectedResult[i])) {
                handleError();
            }
        }

    }


    public static void main(String[] args) {
        test();
        System.out.println("Test passed");
    }

}
