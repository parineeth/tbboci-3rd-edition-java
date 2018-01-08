/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;




class Algo_Misc_14_AlienDictionary {


    public static final int MAX_NUM_CHARACTERS = 256;


    /* Helper function for performing topological sorting
    curChar: current character that we are processing
    adjacencyTable: hash-table that maps a character to its adjacent characters. 
    The adjacent characters are stored in an ArrayList. 
    isVisited: indicates if a character has already been visited or not
    s: stack for storing the result of the topological sort
    */
    public static void topologicalSortHelper(char curChar, 
                HashMap<Character, ArrayList<Character>> adjacencyTable, 
                boolean[] isVisited, Stack<Character> s) {

        if (isVisited[curChar])
            return;

        /*make isVisited to true here so that we don't run into loops*/
        isVisited[curChar] = true;


        /*Process all the characters that are neighbors of the current  
        character (ie  adjacent to current character) in the graph*/
        ArrayList<Character> neighborList = adjacencyTable.get(curChar);
        if (neighborList != null) {
            for (int i = 0; i < neighborList.size(); ++i) {
                char neighborChar = neighborList.get(i);

                if (!isVisited[neighborChar])
                    topologicalSortHelper(neighborChar, 
                            adjacencyTable, isVisited, s);

            }
        }

        /*Push the current character onto the stack only after all the 
        characters reachable from it have been recursively added to the stack*/
        s.push(curChar);
    }

    /*Function that performs topological sorting
    adjacencyTable: hash-table that maps a character to its adjacent characters. 
    The adjacent characters are stored in an ArrayList.
    */
    public static void topologicalSort(HashMap<Character, 
                ArrayList<Character>> adjacencyTable) {
        boolean[] isVisited = new boolean[MAX_NUM_CHARACTERS];
        Stack<Character> s = new Stack<Character>();
    
        int i;
        /*Process all the characters*/
        for (i = 0; i < MAX_NUM_CHARACTERS; ++i) {
            ArrayList<Character> neighborList = adjacencyTable.get((char)i);
            if (neighborList != null)
                topologicalSortHelper((char)i, adjacencyTable, 
                            isVisited, s);
        }

        /*Pop out the contents of the stack to get the result of topological 
        sort. This is the order of characters in the alien language*/
        while (!s.empty()) {
            char curChar = s.pop();
            System.out.println(curChar);
        }

    }


    /*Main function to find the order of characters in an alien language
    words: the words present in the dictionary
    numWords: number of words in the dictionary
    */
    public static void getAlphabetOrder(String[] words) {
        /*For each character in the language we maintain a ArrayList*/
        HashMap<Character, ArrayList<Character>> adjacencyTable = 
                    new HashMap<Character, ArrayList<Character>>();
        int numWords = words.length;

        /*Go through the consecutive pairs of words in dictionary*/
        for (int i = 0; i < numWords - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i+1];
            int pos = 0;
    
            while (pos < word1.length() && pos < word2.length()) {
                char first = word1.charAt(pos);
                char second = word2.charAt(pos);

                /*Find first mismatching position between the two words*/
                if (first != second) {
                    /*In the graph, we have an edge from first char
                    to second char. Fetch the ArrayList for first 
                    char and store the second char in it, since 
                    second character is adjacent to first char
                    */
                    ArrayList<Character> neighborList = 
                            adjacencyTable.get(first);
                    if (neighborList == null) {
                        neighborList = new ArrayList<Character>();
                        adjacencyTable.put(first, neighborList); 
                    }
                    neighborList.add(second);
                    break;
                }
                pos++;
            }
        }

        /*Perform the topological sort*/
        topologicalSort(adjacencyTable);
    }


    public static void test01() {
        String[] words = {"aaa", "abc", "acb", "dad"}; /*Words in the alien dictionary*/
    
        System.out.print(  "Dictionary: ");
        for (int i = 0; i < words.length; ++i) 
            System.out.print(words[i] + " ");

        System.out.println("\nOrder of characters:");

        getAlphabetOrder(words);

        System.out.println(  "_____________________________");
    
    }


    public static void test02() {
        String[] words = {"aba", "abb", "abd", "ac", "ad", "pq"}; /*Words in the alien dictionary*/

        System.out.print(  "Dictionary: ");
        for (int i = 0; i < words.length; ++i) 
            System.out.print(words[i] + " ");

        System.out.println("\nOrder of characters: ");

        getAlphabetOrder(words);

        System.out.println("_____________________________");
    
    }


    public static void main(String[] args)  {
        test01();
        test02();

        System.out.println(  "Test passed");
    }

}
