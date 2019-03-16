/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/


class Search_06_InfiniteSearch {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /* Helper function that performs binary search on an array of unknown length
    a: array which should be searched
    x: element which we are trying to find
    low: start position of region in array for searching
    high: end position of region in array for searching 
    */
    public static int binarySearch(int[] a, int x, int low, int high) {
        int mid = 0;

        while (low <= high) {
            try {
                mid = (low + high) / 2;

                /*if mid is greater than actual array length, then 
                an exception is raised*/
                int value = a[mid];

                if (value == x)
                    return mid;
                else if (value > x)
                    high = mid - 1;
                else 
                    low = mid + 1;
            } 
            catch(ArrayIndexOutOfBoundsException e) {
                /*mid has crossed the boundary of the array. So reduce 
                the search region to (low, mid - 1)*/
                high = mid - 1;
            }
        
        }

        return -1;
    }


    /*Main function for performing search on array whose length is not known
    a: input array
    x: item to be searched
    Returns: if x is found, index of x is returned, otherwise -1 is returned
    */
    public static int search(int[] a, int x) {
        /*Perform exponential search to first find the upper bound. Start with  
        high = 0 and then increase high to 1, 2, 4, 8, 16 and so on*/
        int low = 0, high = 0;
        while (true) {
            try {
                int value = a[high];

                if (value == x) 
                    return high; /*We found the element x*/
                else if (value > x) 
                    break;/*Found range (low, high) to search*/
                    
                low = high + 1;

                if (high == 0)
                    high = 1;
                else
                    high = high * 2;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                /*We have crossed the boundary of the array. So we 
                have found the upper bound for high. */
                break;
            }
        } 

        /*Perform binary search in range(low, high). Note that high may  
        still be outside the array bounds*/
        return binarySearch(a, x, low, high);
    }


    public static void test(int length, int x, int expectedResult) {
        int[] a = new int[length];

        for (int i = 0; i < length; ++i) {
            a[i] = i;
        }
    
        int result = search(a, x);

        if (length > 0)
            System.out.println("Location of " + x + " in array [0 to " + (length - 1) + "] is " + result);
        else
            System.out.println("Location of " + x + " in empty array is " + result);

        if (result != expectedResult)
            handleError();
    }


    public static void main(String[] args)  {
        test(20, 0, 0);
        test(20, 15, 15); 
        test(20, 19, 19);
        test(20, 100, -1);
        test(20, -1, -1);
        test(0, 0, -1);

        System.out.println("Test passed");
    }

}
