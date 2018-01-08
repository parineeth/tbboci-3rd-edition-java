/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Array_07_PowerSet {

    /*Helper function for printing a subset
    input: array containing the input elements 
    selection: if bit i is 1 in selection, then element i is present in subset
    */
    public static void printSubset(int[] input, int selection) {
        int i;
        System.out.print("{");

        for (i = 0; i < input.length; ++i) {
            if ((selection & (1 << i)) != 0) 
                System.out.print(input[i] + " ");
        }

        System.out.println("}");    
    }

    /*Main function for generating the subsets
    input: array containing the input elements
    */
    public static void generateSubsets(int[] input) {
        int i = 0;
        int numSubsets = 1 << input.length;

        while (i < numSubsets) {
            printSubset(input, i);
            ++i;
        }
    }

    public static void test(int[] input) {
        generateSubsets(input);

        System.out.println("______________________________\n");
    }

    /*print subset if recursion is used*/
    public static void printSubsetR(int[] a, boolean[] isSelected) {
        int length = a.length;

        System.out.print("{");
    
        for(int i = 0; i < length; ++i) {
            if (isSelected[i]) {
                System.out.print(a[i] + " ");
            }
        } 

        System.out.println("}");
    }


    /*Generate power set recursively
    a: array containing the elements for which we have to find the power set 
    isSelected: array used for computation. If isSelected[i] = true, then
    the ith element of the array is present in the current subset
    pos: current position in the input which is being processed
    */
    public static void generateSubsetsR(int[] a, boolean[] isSelected, int pos) {
        int length = a.length;

        /*Recursion termination condition*/
        if (pos >= length) {
            printSubsetR(a, isSelected);
            return ;
        }

        isSelected[pos] = false;
        generateSubsetsR(a, isSelected, pos+1);

        isSelected[pos] = true;
        generateSubsetsR(a, isSelected, pos+1);

    }







    public static void testR(int[] a) {
        int length = a.length;
        boolean[] isSelected = new boolean[length];

        generateSubsetsR(a, isSelected, 0);

        System.out.println("______________________________\n");
    }


    public static void main(String[] args)  {
        int[] array1 = {};
        int[] array2 = {0};
        int[] array3 = {0, 1, 2};

        test(array1);

        test(array2);

        test(array3);

        System.out.println("Test passed");

    }


}
