/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;
import java.util.Arrays;


class Array_09b_SortedIntersection {

    public static final int MAX_NUM_TESTS = 10;
    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_VALUE = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
    
        System.out.println("");
    }



    /*
    a, b: two input arrays whose intersection has to be found
    Returns: array containing the result of intersection of a and b
    */
    public static int[] findIntersection(int[] a,  int[] b) {
        int length1 = a.length;
        int length2 = b.length;

        int[] result = new int[length1 + length2];

        /*Sort the two arrays*/
        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0, j = 0, resultPos = 0;
        while (i < length1 && j < length2) {
            /*Check if the elements in a and b match*/
            if (a[i] == b[j]) {
                /*Add only unique elements to the result*/
                if (i == 0 || a[i] != a[i - 1]) {
                    result[resultPos] = a[i];
                    ++resultPos;
                }
                ++i; 
                ++j; 

            } else if (a[i] < b[j]) {
                ++i;
            } else {
                ++j;
            }
        }

        result = Arrays.copyOf(result, resultPos);

        return result;
    }


    public static void generateArray(int[] a) {
        Random randomGenerator = new Random();
        for (int i = 0; i < a.length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }
    }

    /*
    a, b: two input arrays whose intersection has to be found
    Returns: array containing the result of intersection of a and b
    */
    public static int[] bruteForceIntersection(int[] a,  int[] b) {
        int length1 = a.length;
        int length2 = b.length;
        int[] result = new int[length1 + length2];
    
        int resultPos = 0;
        for (int i = 0; i < length1; ++i) {
            /*Search for a[i] in array b*/
            boolean found = false;
            int j;
            for (j = 0; j < length2; ++j) {
                if (a[i] == b[j]) {
                    found = true;
                    break;
                }
            }

            if (!found)
                continue;

            /*We have found a[i] in b. Now make sure that a[i] is not 
            already present in the result*/
            found = false;
            for (j = 0; j < resultPos; ++j) {
                if (a[i] == result[j]) {
                    found = true;
                    break;
                }
            }
        
            if (!found) {
                /*a[i] is present in b and a[i] is not present in result. So add 
                it to result*/
                result[resultPos] = a[i];
                resultPos++;
            }
        
        }
        
        result = Arrays.copyOf(result, resultPos);
        return result;
    }


    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly decide the number of elements in the two arrays*/
        int length1 = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);
        int length2 = 1 + randomGenerator.nextInt(MAX_NUM_ELEMS);

        int[] a = new int[length1];
        int[] b = new int[length2];

        /*Fill the arrays with random values*/
        generateArray(a);
        generateArray(b);


        System.out.print ("A : ");
        printArray(a);

        System.out.print ("B : ");
        printArray(b);

        /*Find the intersection of a and b. The result will be in c*/
        int[] c = findIntersection(a, b);

        System.out.print ("Intersection : ");
        printArray(c);

        /*Apply brute force to find the intersection. The result will be in d*/
        int[] d = bruteForceIntersection(a, b);

        /*The two results should match*/
        if (c.length != d.length)
            handleError();

        for (int i = 0; i < c.length; ++i) {
            if (c[i] != d[i])
                handleError();
        }
    

        System.out.println("________________________________________________");

    }


    public static void main(String[] args)  {
        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed");
    }

}
