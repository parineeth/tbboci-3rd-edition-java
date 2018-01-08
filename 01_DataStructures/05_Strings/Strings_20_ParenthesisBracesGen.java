/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Strings_20_ParenthesisBracesGen {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*Verify if the braces, brackets and parenthesis are properly nested */
    public static void verify(char[] str1, int pos) {
        Stack<Character> s = new Stack<Character>();
        int i = 0;

        while (i < pos) {
        
            char c = str1[i];
            if (c == '{' || c == '[' || c == '(') {
                /*If we get an opening brace, bracket or parenthesis in string, 
                then push it on to the stack*/
                s.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                /*If we get a closing brace, bracket or parenthesis in string, 
                then the character on top of stack should be the corresponding opening
                character*/
                if (s.isEmpty())
                    handleError();

                char topChar = s.peek();
                if (c == '}' && topChar != '{')
                    handleError();
                else if (c == ']' && topChar != '[')
                    handleError();
                else if (c == ')' && topChar != '(')
                    handleError();

                /*Since we have matched the opening and closing character,
                remove the opening character from the stack*/
                s.pop();
            } else {
                /*We found a character other than a brace, bracket or parenthesis*/
                handleError();
            }
            ++i;
        }

        /*At the end of processing the stack, the stack should be empty*/
        if (!s.isEmpty())
            handleError();
    }


    public static void printString(char[] str1, int pos) {
        String output = new String(str1, 0, pos);
        System.out.println(output);
        verify(str1, pos);
    }

    /*Helper function for finding the nearest unmatched opening character
    str1: input string containing braces, brackets and parenthesis
    pos: we will search for unmatched character from pos - 1 to 0
    Return value: index of the first unmatched character when traversing from
        pos - 1 to 0 if it exists, -1 otherwise   
    */
    public static int findUnmatched(char[] str1, int pos) {
        int backPos = pos - 1;
        int nBraces = 0, nBrackets = 0, nParenthesis = 0;

        /*When we get a closing character, decrement the count by 1,
        when we get an opening character, increment the count by 1*/
        while (backPos >= 0) {
            if (str1[backPos] == '{') 
                nBraces++;
            else if (str1[backPos] == '[')
                nBrackets++;
            else if (str1[backPos] == '(')
                nParenthesis++;
            else if (str1[backPos] == '}')
                nBraces--;
            else if (str1[backPos] == ']')
                nBrackets--;
            else if (str1[backPos] == ')')
                nParenthesis--;

            /*If we encounter more opening characters than closing
            characters as we traverse backwards, then we have found
            the location of the mismatch*/
            if (nBraces > 0 || nBrackets > 0 || nParenthesis > 0)
                return backPos;

            backPos--;
        }

        return -1;
    }

    /*Main function for printing the braces, brackets and parenthesis
    str1: string used to store braces, brackets and parenthesis
    pos: next free position in the string str1
    nMax: maximum number of opening characters (equal to max closing characters)
    nOpen: number of opening characters currently in str1
    nClose: number of closing characters currently in str1
    */
    public static void printNesting(char[] str1, int pos, int nMax, 
                    int nOpen, int nClose) {
        /*Condition for terminating the recursion*/
        if (nClose == nMax) {
            printString(str1, pos);
            return;
        }

        if (nOpen < nMax) {
            /*Add an opening brace and call printNesting recursively*/
            str1[pos] = '{';
            printNesting(str1, pos+1, nMax, nOpen + 1, nClose);

            /*Add an opening bracket and call printNesting recursively*/
            str1[pos] = '[';
            printNesting(str1, pos+1, nMax, nOpen + 1, nClose);

            /*Add an opening parenthesis and call printNesting recursively*/
            str1[pos] = '(';
            printNesting(str1, pos+1, nMax, nOpen + 1, nClose); 
        }

        int unmatchedPos = findUnmatched(str1, pos);
        if (nOpen > nClose && unmatchedPos >= 0) {
            /*to balance the characters, add closing character corresponding 
            to the unmatched character and call printNesting recursively*/
            char unmatchedChar = str1[unmatchedPos];
    
            if (unmatchedChar == '{') {
                str1[pos] = '}';
                printNesting(str1, pos+1, nMax, nOpen, nClose + 1);
            } else if (unmatchedChar == '[') {
                str1[pos] = ']';
                printNesting(str1, pos+1, nMax, nOpen, nClose + 1);
            } else if (unmatchedChar == '(') {
                str1[pos] = ')';
                printNesting(str1, pos+1, nMax, nOpen, nClose + 1);
            }
        }
    }



    public static void test01() {
        char[] str1 = new char[10];

        for (int i = 1; i <= 4; ++i) {
            printNesting(str1,  0, i, 0, 0) ;
            System.out.println("______________________________________");

        }
    }


    public static void main(String[] args) {
        test01();
        System.out.println("Test passed");
    }

}



