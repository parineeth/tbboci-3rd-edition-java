/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Strings_17_ScrabbleWords {


    /*
    str1: valid input string whose permutations have to be formed
    buf: array for storing the current permutation
    pos: current position in the buf array
    visited: indicates if character in input string has already been visited
    */
    public static void generatePermutations(char[] str1, char[] buf,  int pos,
                boolean[] visited) {

        /*print out the current permutation formed till now*/
        System.out.println(new String(buf, 0, pos));

        int length = str1.length;

        /*Recursion termination condition*/
        if (pos >= length) 
            return ;

        for (int i = 0; i < length; ++i) {
            if (visited[i] == false) {
                buf[pos] = str1[i];
                visited[i] = true;
                generatePermutations(str1, buf, pos+1, visited);
                visited[i] = false;
            }
        }
    }


    public static void test(char[] str1) {
        int length = str1.length;
        char[] buf = new char[length];
        boolean[] visited = new boolean[length];

        System.out.print("Input: ");
        System.out.println(str1);

        generatePermutations(str1, buf, 0, visited);

        System.out.println("______________________________");
    }


    public static void main(String[] args)  {
        test("".toCharArray());
        test("A".toCharArray());
        test("ABCDE".toCharArray());

        System.out.println("Test passed");
    }

}



