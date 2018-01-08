/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Dyn_03_Lis {


    /*a: array in which we need to find the longest increasing sequence.
        Should have atleast 1 element
    Return value: array having the longest increasing sequence is returned
    */
    public static int[] findLis(int[] a) {
        int n = a.length;

        /*seqLength stores length of LIS for each position of array a*/
        int[] seqLength = new int[n];

        /*prevIx stores the index of previous element in the LIS sequence*/
        int[] prevIx = new int[n];


        /*Each element by itself forms a sequence of length 1*/
        int i, j;
        for (i = 0; i < n; ++i)
            seqLength[i] = 1;

        /*Find the LIS for each position in array a*/
        for (i = 1; i < n; ++i) {
            for (j = 0; j < i; ++j) {
                if ( a[j] < a[i] && seqLength[i] < seqLength[j] + 1 ) {
                    seqLength[i] = seqLength[j] + 1;
                    prevIx[i] = j;
                }
            }
        }

        /*The longest LIS amongst all positions of array a will be the LIS
        for the whole array*/
        int lisLength = 1;
        int lisEnd = 0;
        for (i = 1; i < n; ++i) {
            if (lisLength < seqLength[i]) {
                lisLength = seqLength[i];
                lisEnd = i;
            }
        }

    
        int[] lis = new int[lisLength]; 

        /*Use the prevIx array to reconstruct the LIS for the whole array
        lisEnd has the index of the last element in the LIS for whole array*/
        j = lisEnd;
        for (i = lisLength - 1; i >= 0; --i) {
            lis[i] = a[j];
            j = prevIx[j];
        }

        return lis;
    }



    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }

        System.out.println("");
    }


    public static void test01() {
        int[] lis;
        int[] input = {6, 3, 2, 4, 5};

        System.out.print("Input : ");
        printArray(input);

        lis = findLis(input);

        System.out.print("LIS : ");
        printArray(lis);

        System.out.println("___________________________________________");
    }


    public static void test02() {
        int[] lis;
        int[] input = {2, 4, 3,  1, 7 , 7, 9 };

        System.out.print("Input : ");
        printArray(input);

        lis = findLis(input);

        System.out.print("LIS : ");
        printArray(lis);

        System.out.println("___________________________________________");
    }



    public static void main(String[] args)  {
        test01();
        test02();

        System.out.println("Test passed");
    }

}
