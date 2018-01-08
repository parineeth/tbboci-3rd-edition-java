/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Misc_15_WordTransform {

    public static void handleError() {
        System.out.println(  "Test failed ");
        System.exit(1);
    }

    /* 
    beginWord: starting word in the word transformation
    endWord: ending word in the word transformation
    dictionary: contains the permitted words that can be used in the transformation
    Result: the ArrayList that contains the sequence of words if word 
        transformation is possible, null if word transformation is not possible
    */
    public static ArrayList<String> transformWord(String beginWord, String endWord,
                        HashSet<String> dictionary) {
        Queue<String> q = new LinkedList<String>();

        /*Contains the words that have already been visited*/
        HashSet<String> visited = new HashSet<String>();    

        /*If we can transform word a to word b, then we store b->a mapping in 
        the reversePath. b is the key and a is the value. */
        HashMap<String, String> reversePath = new HashMap<String, String>();


        q.add(beginWord);
        visited.add(beginWord);

        while (!q.isEmpty()) {
            /*Get the word at the beginning of the queue*/
            String curWord = q.remove();

            /*If the current word matches the ending word, we have found
            the word transformation. Store the sequence of transformation 
            in the result list*/
            if (curWord.equals(endWord)) {
                ArrayList<String> result = new ArrayList<String>();
                result.add(0, curWord); /*Add to beginning of list*/

                /*Find the previous word from where we reached the 
                current word and add the previous word to the result*/
                curWord = reversePath.get(curWord);
                while (curWord != null) {
                    /*Add to beginning of list*/
                    result.add(0, curWord); 
                    curWord = reversePath.get(curWord);
                }

                return result;
            }


            /*Look at all possible words that can be generated from 
            the current word by changing a single character*/
            for (int i = 0; i < curWord.length(); ++i) {
                char[] charArray = curWord.toCharArray(); 
        
                /*Generate new word by changing the character 
                at position i*/
                for (char c = 'a'; c <= 'z'; ++c) {
                    charArray[i] = c;
                    String newWord = new String(charArray);

                    /*If new word is present in dictionary and has  
                    not been visited so far, then add it to queue*/
                    if (dictionary.contains(newWord) && 
                        !visited.contains(newWord) ) {
                        q.add(newWord);
                        visited.add(newWord);

                        /*Store information that we reached 
                        newWord from curWord*/
                        reversePath.put(newWord, curWord);
                    }

                }
            }

        }

        return null; /*transformation is not possible*/
    }

    public static void test() {
        HashSet<String> dictionary = new HashSet<String>();
        String beginWord = new String("bell");
        String endWord = new String("walk");

        dictionary.add("tall");
        dictionary.add("bell");
        dictionary.add("walk");
        dictionary.add("ball");
        dictionary.add("talk");

        ArrayList<String> result = transformWord(beginWord, endWord, dictionary);

        if (result == null)
            handleError();

        /*Print out the word transformation*/
        for (String word: result)
            System.out.print(  word + " ");

        System.out.println(  );
    
    }


    public static void main(String[] args) {
        test();

        System.out.println(  "Test passed ");
    }

}
