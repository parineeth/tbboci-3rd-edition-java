/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class Math_15_LineIntersect {

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*
    slope1: slope of the first line
    c1: y-intercept of the first line
    slope2: slope of the second line
    c2: y-intercept of the second line
    Return value: true if the lines intersect, false otherwise
    */
    public static boolean doLinesIntersect(double slope1, double c1, 
                    double slope2, double c2) {
        double epsilon = 0.0001;
        boolean intersect = false;

        if (Math.abs(slope1 - slope2) < epsilon) { 
            /*Both lines have the same slope. Check the y-intercept*/
            if (Math.abs(c1 - c2) < epsilon) {
                /*The y-intercepts are the same. 
                So both lines are the same lines.
                We consider such lines to intersect with each other*/
                intersect = true;       
            } else {
                /*The lines are parallel and not coincident on each other
                So these lines will not intersect*/
                intersect = false;
            }
        } else {
            /*The two lines have different slopes. They will intersect*/
            intersect = true;
        }

        return intersect;
    }


    public static void test(double slope1, double c1, double slope2, double c2, boolean expectedResult) {
        boolean result = doLinesIntersect(slope1, c1, slope2, c2);

        System.out.print("Lines (slope = " + slope1 + ", y-intercept = " + c1 + 
                ") and (slope = " + slope2 + ", y-intercept = " + c2 + ") " ); 
        if (result)
            System.out.println("intersect");
        else
            System.out.println("do NOT intersect");

        if (result != expectedResult)
            handleError();  
    }


    public static void main(String[] args)  {

        /*Same slope, different y-intercept. Should NOT intersect*/ 
        double slope1 = 1.0;
        double slope2 = 1.0;
        double c1 = 1.0;
        double c2 = 2.0;
        boolean expectedResult = false;
        test(slope1, c1, slope2, c2, expectedResult);


        /*Same y-intercept, different slope. Should intersect*/ 
        slope1 = 2.0;
        slope2 = 1.0;
        c1 = 1.0;
        c2 = 1.0;
        expectedResult = true;
        test(slope1, c1, slope2, c2, expectedResult);


        /*Different y-intercept, different slope. Should intersect*/    
        slope1 = 1.0;
        slope2 = 2.0;
        c1 = 3.0;
        c2 = 4.0;
        expectedResult = true;
        test(slope1, c1, slope2, c2, expectedResult);


        /*Same y-intercept, same slope. Identical lines, should intersect*/ 
        slope1 = 1.0;
        slope2 = 1.0;
        c1 = 1.0;
        c2 = 1.0;
        expectedResult = true;
        test(slope1, c1, slope2, c2, expectedResult);

        System.out.println("Test passed");

    }

}




