/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Stack_07_ParenthesisBracesValidate {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*
    Verify if the braces, brackets and parenthesis are properly nested 
    str1: input string containing braces, brackets and parenthesis
    Return value: true if the nesting is proper, false otherwise 
    */
    public static boolean validateNesting(String str1) {
        Stack<Character> s = new Stack<Character>();
        int i = 0;

        while (i < str1.length()) {
            char c = str1.charAt(i);

            if (c == '{' || c == '[' || c == '(') {
                /*If we get an opening brace, bracket or parenthesis 
                 in string, then push it on to the stack*/
                s.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                /*If we get a closing brace, bracket or parenthesis
                in string, then the character on top of stack should be 
                the corresponding opening character*/
                if (s.isEmpty())
                    return false;

                char topChar = s.peek();
                if (c == '}' && topChar != '{')
                    return false;
                else if (c == ']' && topChar != '[')
                    return false;
                else if (c == ')' && topChar != '(')
                    return false;

                /*Since we have matched the opening and closing character,
                remove the opening character from the stack*/
                s.pop();
            } else {
                /*We found a character other than a brace, bracket 
                or parenthesis*/
                return false;
            }
            ++i;
        }

        /*At the end of processing, the stack should be empty*/
        if (!s.isEmpty())
            return false;

        return true;
    }


    public static void test(String str1, boolean expectedResult) {

        boolean result = validateNesting(str1);

        if (result)
            System.out.println(str1 + " => is balanced ");
        else 
            System.out.println(str1 + " => is NOT balanced ");

        if (result != expectedResult)
            handleError();
    }



    public static void main(String[] args) {
        test("[{()}]", true);
        test("[{]", false);
        System.out.println("Test passed");
    }

}



