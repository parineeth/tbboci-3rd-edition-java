/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Array_08_NCR {

    public static void printCombination(int[] a, boolean[] isSelected, int subsetSize) {
        int length = a.length;

        System.out.print("{");
    
        int numPrinted = 0;
        for(int i = 0; i < length; ++i) {
            if (isSelected[i]) {
                System.out.print(a[i] + " ");
                ++numPrinted;
                if (numPrinted >= subsetSize)
                    break;
            }
        } 

        System.out.println("}");
    }


    /*
    a: input array containing the elements  
    isSelected: if isSelected[i] = true, then the ith element
         of the input array is present in the current subset
    pos: current position in the input 
    subsetSize: total number elements that should be present in the final subset
    curNumSelections: currently how many elements have been selected
    */
    public static void generateCombinations(int[] a, boolean[] isSelected, int pos, 
                int subsetSize, int curNumSelections) {
        int length = a.length;

        if (curNumSelections == subsetSize) {
            printCombination(a, isSelected, subsetSize);
            return; /*Terminate the recursion*/
        }

        if (pos >= length) {
            return; /*Terminate the recursion*/
        }

        /*Exclude the item from the subset*/
        isSelected[pos] = false;

        generateCombinations(a, isSelected, pos+1, subsetSize,
                    curNumSelections);

        /*Include the item in the subset*/
        isSelected[pos] = true;

        generateCombinations(a, isSelected, pos+1, subsetSize,
                    curNumSelections + 1);
    }



    public static void test(int[] a, int subsetSize) {
        int length = a.length;
        boolean[] isSelected = new boolean[length];

        System.out.println("Number of elements in subset = " + subsetSize);

        generateCombinations(a, isSelected, 0, subsetSize, 0);


        System.out.println("______________________________\n");
    }


    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4};
        int length = 5;

        /*Compute 5C0 to 5C5*/
        for (int subsetSize = 0; subsetSize < length + 1; ++subsetSize) {
            test(a, subsetSize);
        }

        System.out.println("Test passed");

    }

}

