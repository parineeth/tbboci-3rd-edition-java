/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Algo_Dyn_05_ThiefMaxLoot {


    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }

    public static int max(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }


    /*
    houseValue: value that the thief can steal from each house
    Return value: maximum loot value that the thief can steal from all the houses
    */
    public static int findMaxLoot(int[] houseValue) {
        int n = houseValue.length;
        int curVal = 0;

        if (n == 0)
            return 0;

        if (n == 1)
            return houseValue[0];

        if (n == 2)
            return max(houseValue[0], houseValue[1]);

        /*
        val1 has the max loot till the previous house,
        val2 has the max loot till the second previous house
        */
        int val1 = max(houseValue[0], houseValue[1]);
        int val2 = houseValue[0];

        for (int i = 2; i < n; ++i) {
            /*curVal stores the maximum loot till the current house (ith house)*/
            curVal = max(val2 + houseValue[i], val1);

            /*val2 now takes the value of val1 and val1 takes the current value*/
            val2 = val1;
            val1 = curVal;
        }

        return curVal;
    }



    public static void printArray(int[] a) {
        for (int curVal : a) {
            System.out.print(curVal + " ");
        }
        System.out.println("");
    }



    public static void test01() {
        int[] houseValue = {6, 1, 2, 7};

        System.out.print("House values : ");
        printArray(houseValue);

        int maxLoot = findMaxLoot(houseValue);

        System.out.println("Max loot = " + maxLoot);

        if (maxLoot != 13)
            handleError();

        System.out.println("__________________________________");
    }



    public static void test02() {
        int[] houseValue = {6};

        System.out.print("House values : ");
        printArray(houseValue);

        int maxLoot = findMaxLoot(houseValue);

        System.out.println("Max loot = " + maxLoot);

        if (maxLoot != 6)
            handleError();
    
        System.out.println("__________________________________");
    }


    public static void test03() {
        int[] houseValue = {6, 4};

        System.out.print("House values : ");
        printArray(houseValue);

        int maxLoot = findMaxLoot(houseValue);

        System.out.println("Max loot = " + maxLoot);

        if (maxLoot != 6)
            handleError();

        System.out.println("__________________________________");
    }


    public static void test04() {
        int[] houseValue = {6, 8};

        System.out.print("House values : ");
        printArray(houseValue);

        int maxLoot = findMaxLoot(houseValue);

        System.out.println("Max loot =  " + maxLoot);

        if (maxLoot != 8)
            handleError();

        System.out.println("__________________________________");
    }


    public static void test05() {
        int[] houseValue = {1, 6, 2, 8, 3};

        System.out.print("House values : ");
        printArray(houseValue);

        int maxLoot = findMaxLoot(houseValue);

        System.out.println("Max loot = " + maxLoot);

        if (maxLoot != 14)
            handleError();

        System.out.println("__________________________________");
    }



    public static void main(String[] args)  {
        test01();
        test02();
        test03();
        test04();
        test05();

        System.out.println("Test passed ");

    }

}
