/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.lang.Math;


class Point {
    public int x;
    public int y;

    Point() {
        x = 0;
        y = 0;
    }
} 

class Math_17_RectangleOverlapArea {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*
    left1: upper left corner of rectangle 1
    right1: lower right corner of rectangle 1
    left2: upper left corner of rectangle 2
    right2: lower right corner of rectangle 2
    Return value: area of the overlapping rectangles
    */
    public static int findOverlapArea(Point left1, Point right1, 
                    Point left2, Point right2) {
        /*one rectangle lies completely to the right or left of the other*/
        if (right1.x < left2.x || right2.x < left1.x)
            return 0;

        /*one rectangle lies completely above or below the other*/
        if (left1.y < right2.y || left2.y < right1.y)
            return 0;

        /*the rectangles overlap*/
        Point resultLeft = new Point();
        Point resultRight = new Point();

        resultLeft.x = Math.max(left1.x, left2.x);
        resultLeft.y = Math.min(left1.y, left2.y); 
        resultRight.x = Math.min(right1.x, right2.x);
        resultRight.y = Math.max(right1.y, right2.y);
    
        return (resultRight.x - resultLeft.x) 
            * (resultLeft.y - resultRight.y);
    }


    public static void test(Point left1, Point right1, Point left2, Point right2, int expectedResult) {

        int result = findOverlapArea(left1, right1, left2, right2);

        if (result != expectedResult)
            handleError();

    }


    public static void main(String[] args) {
        Point left1 = new Point();
        Point right1 = new Point();
        Point left2 = new Point();
        Point right2 = new Point();

        left1.x = 0;
        left1.y = 20;
        right1.x = 20;
        right1.y = 0;

        left2.x = 10;
        left2.y = 30;
        right2.x = 30;
        right2.y = 10;

        test(left1, right1, left2, right2, 100);

    
        left1.x = 0;
        left1.y = 10;
        right1.x = 10;
        right1.y = 0;

        left2.x = 100;
        left2.y = 10;
        right2.x = 110;
        right2.y = 0;

        test(left1, right1, left2, right2, 0);
    

        System.out.println("Test passed");

    }

}


