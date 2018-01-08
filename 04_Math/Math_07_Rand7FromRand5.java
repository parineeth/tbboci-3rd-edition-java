/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class Math_07_Rand7FromRand5 {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static int rand5() {
        Random randomGenerator = new Random();
        int result = 1 + randomGenerator.nextInt(5);
        return result;
    }


    public static int rand7() {
        int result;
        while(true) {
            result = (rand5() - 1) + (5 * (rand5() - 1));
            if (result <= 20)
                break;
        }
        result = 1 + (result % 7);
        return result;
    }


    public static void main(String[] args)  {

        for (int i = 0; i < 100; ++i) {
            int result = rand7();
            System.out.println("Random value is " + result);
            if (result < 1 || result > 7)
                handleError();
        }

        System.out.println("Test passed");

    }

}
