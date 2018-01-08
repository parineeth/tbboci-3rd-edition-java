/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Misc_03_FillEquation {

    public static void handleError() {
        System.out.println("Test failed ");
        System.exit(1);
    }

    /* Helper function that evaluates the numbers and operators
    a: array of numbers. Should have atleast one element
    operators: array of operators (+, -) to be applied on numbers
    */
    public static int evaluate(int[] a, char[] operators) {
        int n = a.length;
        int result = a[0];

        for (int i = 1; i < n; ++i) {
            if (operators[i-1] == '+')
                result += a[i];
            else 
                result -= a[i];
        }

        return result;
    }

    /*
    a: array of numbers. Should have atleast one element
    rhs: right hand side of the equation
    operators: array for storing the operators to be applied on the numbers
    numOperators: number of operators that have been filled in so far
    Return value: true if we can get the rhs by placing operators between numbers
    */
    public static boolean fillOperators(int[] a, int rhs, char[] operators, int numOperators) {
        int n = a.length; 

        if (numOperators == n - 1) {
            /*We have filled in all the operators. So evaluate the result and
            terminate the recursion*/
            int result = evaluate(a, operators);
            if (result == rhs) 
                return true;
            else
                return false;
        }

        /*Fill in + as the next operator and try out*/
        operators[numOperators] = '+';
        boolean isPossible = fillOperators(a, rhs, operators, numOperators + 1);
        if (isPossible)
            return true;

        /*Fill in - as the next operator and try out*/
        operators[numOperators] = '-';
        isPossible = fillOperators(a, rhs, operators, numOperators + 1);

        return isPossible;
    }



    public static void test() {
        int n = 3;
        int[] a = {10, 20, 30};
        char[] operators = new char[n];
        int rhs = -40;

        boolean isPossible = fillOperators(a, rhs, operators, 0);

        /*We should be able to get -40 since 10 - 20 - 30 = -40 */
        if (!isPossible)
            handleError();

        System.out.print(a[0] + " ");
        for (int i = 1; i <= n - 1; ++i) {
            System.out.print(operators[i-1] + " ");
            System.out.print(a[i] + " ");
        }
        System.out.println(" = " + rhs);
    }








    public static void main(String[] args) {
        test();

        System.out.println("Test passed");
    }

}
