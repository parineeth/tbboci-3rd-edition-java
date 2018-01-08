/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Math_06_PowerAlgo {


    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }


    /*x, y: two integers, x > 0, y >= 0
    Return value: x multiplied with itself y times*/
    public static int power(int x, int y) {
        int result = 1;
        while (y > 0) {
            /*look at least significant bit of y*/
            if ((y & 0x1) == 0x1)
                result = result * x;
            y = y >> 1;/*shift out the least significant bit of y*/
            x = x * x;
        }
        return result;
    }



    public static int bruteForcePower(int x, int y) {
        int result = 1;

        for (int i = 0; i < y; ++i) {
            result = result * x;
        }

        return result;
    }


    public static void main(String[] args)  {

        for (int x = 2; x <= 10; ++x) {
            int maxY;

            /*Based on the value of x determine the maximum y value so that
            x ^ y  does not overflow*/
            if (x == 2) {
                maxY = 30;
            }
            else if (x == 3) {
                maxY = 18;
            }
            else if (x == 4) {
                maxY = 15;
            }
            else if (x == 5) {
                maxY = 12;
            }
            else if (x == 6) {
                maxY = 11;
            }
            else if (x == 7) {
                maxY = 10;
            }
            else if (x == 8) {
                maxY = 10;
            }
            else if (x == 9) {
                maxY = 9;
            }
            else  {
                maxY = 8;
            }

            for (int y = 0; y <= maxY; ++y) {
                int optimalResult = power(x, y);

                System.out.println(x + " ^ " + y + " = " + optimalResult);

                int bruteForceResult = bruteForcePower(x, y);

                if (optimalResult != bruteForceResult) {
                    handleError();
                }
            }
        }

        System.out.println("Test passed ");
    }

}


