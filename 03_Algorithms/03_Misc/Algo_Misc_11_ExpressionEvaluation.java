/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Misc_11_ExpressionEvaluation {

    public static void handleError() {
        System.out.println(  "Test failed");
        System.exit(1);
    }

    /*Helper function that picks the operator from the top of operator stack
    and applies them on the two values at the top of the numStack. The result
    will then be pushed back onto the numStack
    */
    public static void compute(Stack<Integer> numStack, Stack<Character> operatorStack) {
        char c = operatorStack.pop();

        /*Since stack is LIFO we will first pop value2 and then pop value1 */
        int value2 = numStack.pop();

        int value1 = numStack.pop();

        if (c == '+') 
            numStack.push(value1 + value2);
        else if (c == '-')
            numStack.push(value1 - value2);
        else if (c == '*')
            numStack.push(value1 * value2);
        else if (c == '/')
            numStack.push(value1 / value2);

    }

    /* Helper function to check priority of operators
    stackOperator: operator that is at the top of the operator stack
    expOperator: operator that is currently being examined in the expression
    Return value: true if the operator in the stack is higher priority than operator
    being examined in the expression
    */
    public static boolean isHigherPrecedence(char stackOperator, char expOperator) {
        if ((stackOperator == '*' || stackOperator == '/') && 
            (expOperator == '+' || expOperator == '-'))
            return true;

        return false;
    }

    /*Main function for evaluating an expression
    expression: String containing the expression to be evaluated
    Return value: the integer result value obtained by evaluating the expression
    */
    public static int evaluateExpression(String expr) {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<Character> operatorStack = new Stack<Character>();
        int length = expr.length();

        int i = 0;
        while (i < length) {
            /*Skip the white space characters*/
            if (expr.charAt(i) == ' ' || expr.charAt(i) == '\t' 
            || expr.charAt(i) == '\n') {
                ++i;
                continue;
            } 
    
            /*If we encounter an integer, then parse out the digits in it*/ 
            if (expr.charAt(i) >= '0' && expr.charAt(i) <= '9') {
                int startPos = i;
                while (i < length && expr.charAt(i) >= '0' 
                && expr.charAt(i) <= '9') {
                    ++i;
                }
                String tempStr = expr.substring(startPos, i);
                int curValue = Integer.parseInt(tempStr);
                numStack.push(curValue);

            } else if (expr.charAt(i) == '(') {
                operatorStack.push(expr.charAt(i));
                ++i;

            } else if (expr.charAt(i) == ')') {
                /*Till we encounter '(', process the two stacks*/
                while (operatorStack.peek() != '(') {
                    compute(numStack, operatorStack);
                }
                operatorStack.pop(); /*pop out '(' */
                ++i;            
            } else if (expr.charAt(i) == '+' || expr.charAt(i) == '-' ||
                expr.charAt(i) == '*' || expr.charAt(i) == '/') {
                /*
                As long as the operator in the stack is of higher 
                priority than the operator in the stack, keep processing  
                the two stacks 
                */
                while (!operatorStack.empty() 
                && isHigherPrecedence(operatorStack.peek(), expr.charAt(i))) {
                    compute(numStack, operatorStack);
                }
                operatorStack.push(expr.charAt(i));
                ++i; 
            }

        }

        /*If there are still operators in the operator stack, then process them*/
        while (!operatorStack.empty())
            compute(numStack, operatorStack);

        /*The result will be present at the top of the numStack*/
        return numStack.peek();
    }


    public static void test(String expression, int expectedResult) {
        int result = evaluateExpression(expression);

        System.out.println(  expression + " = " + result);

        if (result != expectedResult)
            handleError();
    }


    public static void main(String[] args) {
        test("20 * 40", 800);

        test("10 + 10 * 40", 410);

        test("(200 - (100 + 50)) * 30 / 10", 150); 

        System.out.println("Test passed ");
    }

}
