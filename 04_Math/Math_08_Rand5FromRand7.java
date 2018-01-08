/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;


class Math_08_Rand5FromRand7 {


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static int rand7() {
        Random randomGenerator = new Random();
        int result = 1 + randomGenerator.nextInt(7);
        return result;
    }


    public static int rand5() {
        int result;
        while(true) {
            result = rand7();
            if (result <= 5)
                break;

        }

        return result;
    }


    public static void main(String[] args)  {

        for (int i = 0; i < 100; ++i) {
            int result = rand5();
            System.out.println("Random value is " + result);
            if (result < 1 || result > 5)
                handleError();
        }

        System.out.println("Test passed");
    }

}



