/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Math_13_Sqrt {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }



    /*
    n: number >= 0 whose square root has to be computed
    accuracy: how accurate should the result be
    Return value: square root of n
    */
    public static double findSqrt(int n, double accuracy)  {
        double low = 1.0, mid = 1.0, high = n * 1.0;

        if (n == 0) 
            return 0.0;

        if (n == 1)
            return 1.0;

        while (low < high) {
            mid = (low + high) / 2;
            double square = mid * mid;

            /*Find absolute difference between (mid * mid) and n*/
            double difference =  Math.abs(square - n);
    
            /*If the absolute difference is within the required accuracy
            then mid contains the square root. So break out of the loop*/
            if (difference < accuracy)
                break;
        
            if (square > n) 
                high = mid;
            else
                low = mid;
    
        }

        return mid; /*Return the square root*/
    }


    public static void main(String[] args) {

        for (int i = 0; i < 1000; ++i) {

            double computedSqrt = findSqrt(i, 0.001);

            System.out.println("Square root of " + i + " = "  + computedSqrt);

            double expectedSqrt = Math.sqrt(i);

            double difference = (computedSqrt - expectedSqrt);

            /*Find the absolute difference*/
            if (difference < 0)
                difference = difference * (-1);

            if (difference > 0.1) 
                handleError();

        }

        System.out.println("Test passed ");

    }

}


