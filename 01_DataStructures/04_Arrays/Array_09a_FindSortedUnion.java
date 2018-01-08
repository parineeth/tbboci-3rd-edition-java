/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;
import java.util.Arrays;

class Array_09a_FindSortedUnion {


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
    a, b: two input arrays whose union has to be found
    Returns: array containing the result of union of a and b
    */
    public static int[] findUnion(int[] a, int[] b) {
        int length1 = a.length;
        int length2 = b.length;
        
        if (length1 + length2 == 0)
            return null;

        int[] result = new int [length1 + length2];


        /*sort a and b */
        Arrays.sort(a);
        Arrays.sort(b);

        /*Process as long as there are elements in both a and b. 
        Pick the smaller element among a[i] and b[j] and if it
        doesn't match with previous element in result, add it to result*/
        int i = 0, j = 0, pos = 0;
        while (i < length1 && j < length2) {
            if (a[i] <= b[j]) {
                if (pos == 0 || a[i] != result[pos - 1]) 
                    result[pos++] = a[i];

                if (a[i] == b[j]) 
                    ++j; /*advance b */
                ++i;
            
            } else {
                if (pos == 0 || b[j] != result[pos - 1])
                    result[pos++] = b[j];
                ++j;
            }
        }

        /*Process the remainder elements in a*/
        while (i < length1) {
            if (pos == 0 || a[i] != result[pos - 1])
                result[pos++] = a[i];
            ++i;
        }

        /*Process the remainder elements in b*/
        while (j < length2) {
            if (pos == 0 || b[j] != result[pos - 1])
                result[pos++] = b[j];
            ++j;
        }

        result = Arrays.copyOf(result, pos);
        return result;
    }





    public static void generateArray(int[] a) {
        Random randomGenerator = new Random();
    
        for (int i = 0; i < a.length; ++i) {
            a[i] = randomGenerator.nextInt(MAX_VALUE);
        }
    
    }

    /*
    Adds all the unique items of array a into the result array
    a: input array
    result: the result will have the unique elements of array a appended to the result
        result will store only unique elements
    resultPos: initial number of elements in the result
    Return: final number of elements in the result
    */
    public static int bruteForceUnique(int[] a, int[] result, int resultPos) {
        for (int i = 0; i < a.length; ++i) {
            boolean found = false;
            for (int j = 0; j < resultPos; ++j) {
                if (a[i] == result[j]) {
                    found = true;
                    break;
                }
            }
        
            if (!found) {
                result[resultPos] = a[i];
                resultPos++;
            }
        
        }

        return resultPos;
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

        /*Find the union of a and b. The result will be in c*/
        int[] c = findUnion(a,  b);
        int length3 = c.length;

        System.out.print ("Union : ");
        printArray(c);


        /*Find the union using brute force. The result will be in d*/
        int[] d = new int[length1 + length2];   
        int length4 = 0;
        length4 = bruteForceUnique(a, d, length4);
        length4 = bruteForceUnique(b, d, length4);

        d = Arrays.copyOf(d, length4);

        /*c and d should match. Since c is sorted, but d is not, we need to sort d*/
        Arrays.sort(d); 

        if (length3 != length4)
            handleError();

        for (int i = 0; i < length3; ++i) {
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
