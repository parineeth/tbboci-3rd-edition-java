/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Math_05_PrimeSieve {


    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*
    n: Upto what number the primes should be generated
    */
    public static void generatePrimes(int n) {
        /*isMultiple will be initialized with false since we have not yet 
        identified the multiples*/
        boolean[] isMultiple = new boolean[n+1];

        /*We don't consider 0 and 1 as prime. Start from 2*/
        for (int i = 2; i <= n; ++i) {
            if (isMultiple[i]) 
                continue; /*i is a multiple, so it can't be prime*/
    
            System.out.println(i + " is prime ");

            /*Mark all multiples of i (2i, 3i, 4i, etc) starting from 2i */
            for (int j = 2*i; j <= n; j += i) {
                isMultiple[j] = true;
            } 
        }

    }


    public static void main(String[] args)  {
        generatePrimes(100);

        System.out.println("Test passed ");
    }

}




