/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Search_05_SearchWithEmptyStrings {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*
    strings: sorted array of strings in which some of the strings can be empty ("")
    x: string to be searched
    Returns: index of x in the array strings if found, -1 otherwise
    */
    public static int search(String[] strings, String x) {
        int low = 0;
        int high = strings.length - 1;

        while (low <= high) {
            /*If we hit an empty string at position high, then keep 
            decreasing high till we get a non-empty string*/
            while (low <= high && strings[high] == "") 
                --high;

            /*If we have only empty strings between low and high, then return
             not found*/
            if (low > high)
                return -1;

            int mid = (low + high) / 2;

            /*If we get an empty element at mid, then keep incrementing mid. 
            We are guaranteed to find a non-empty string since strings[high]
            is non-empty*/ 
            while (strings[mid] == "")
                mid = mid + 1; 

            /*Compare the mid element with the element being searched*/
            int result = strings[mid].compareTo(x);

            if (result == 0) {
                return mid;
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }



    public static void test(String[] strings, String x, int expectedResult) {
        int result = search(strings, x);

        System.out.print("Input: [");
        for (String curString : strings) {
            System.out.print("\"" + curString + "\"" + ", " );
        }
        System.out.println("]");

        System.out.println("Searching " + x + ", Postion = " + result);

        if (result != expectedResult) {
            handleError();
        }
        System.out.println("______________________________________________");
    } 



    public static void main(String[] args) {
        String[] strings = {"", "apple", "", "", "ball", "cat", "", "dog", "", "", "", "egg", ""};

        test(strings, "apple", 1); 
        test(strings, "ball", 4);
        test(strings, "cat", 5);
        test(strings, "dog", 7);
        test(strings, "egg", 11);
        test(strings, "air", -1);
        test(strings, "film", -1);

        System.out.println("Test passed");
    }

}
