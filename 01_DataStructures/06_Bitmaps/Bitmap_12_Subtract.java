/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Bitmap_12_Subtract {


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*
    x, y: two integers. can be negative. x may be bigger, equal or smaller than y
    Return value: x - y
    */
    public static int subtract(int x, int y) {
        while (y != 0) {
            /*compute the borrow bits for all the bits in x and y*/
            int borrow = (~x) & y;

            /*compute the difference bits for all the bits in x and y*/
            x = x ^ y;

            /*If a borrow is needed at current bit position, we would have 
            marked a 1 in current bit position. But the borrow needs to be  
            subtracted from the next bit position. So left shift the borrow
            bits by 1 and then subtract the borrow in the next iteration
            */
            y = borrow << 1;
        }

        return x;
    }


    public static void test(int x, int y) {
        int result = subtract(x, y);

        System.out.println(x + " - " + y + " = " + result);

        /*Verify the result*/
        if (result != (x - y))
            handleError();
    }


    public static void main(String[] args) {
        test(20, 13);

        test(20, 20);

        test(78, 100);

        test(50, -7);

        test(-8, 17);

        test(-8, -17);

        test(-25, -25);
    

        System.out.println("Test passed");

    }

}



