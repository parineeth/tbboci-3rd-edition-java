/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Box {
    int height;
    int length;
    int breadth;

    Box() {
        height = 0;
        length = 0;
        breadth = 0;
    }

    Box(int x, int y, int z) {
        height = x;
        length = y;
        breadth = z;
    }
}


class BoxComparator implements Comparator<Box>
{
    /*Higher area boxes should be stored first after sorting*/
    public int compare(Box box1, Box box2)  {
        return (box2.length * box2.breadth) - (box1.length * box1.breadth);
    }
}


class Algo_Dyn_13_BoxStacking {

    public static void handleError() {
        System.out.println("Test failed ");
        System.exit(1);
    }


    /*
    a: array of boxes of different dimensions. Should contain atleast one box 
    Result: maximum height of the stack of boxes that can be constructed.
    Assumption is that multiple instances of each box are available
    */
    public static int maxStackHeight(Box[] a) {
        int numInputBoxes = a.length;
        Box[] boxes = new Box[3 * numInputBoxes];
        int[] bestHeight = new int[3 * numInputBoxes];
    

        /*For each box, all 3 orientations are possible. Length will always be
        greater than breadth */
        int i;
        for (i = 0; i < numInputBoxes; ++i) {
            boxes[3*i] = new Box();
            boxes[3*i].height = a[i].height;
            boxes[3*i].length = Math.max(a[i].length, a[i].breadth);
            boxes[3*i].breadth = Math.min(a[i].length, a[i].breadth);
    
            boxes[3*i + 1] = new Box();
            boxes[3*i + 1].height  = a[i].length;
            boxes[3*i + 1].length  = Math.max(a[i].breadth, a[i].height);
            boxes[3*i + 1].breadth = Math.min(a[i].breadth, a[i].height);

            boxes[3*i + 2] = new Box();
            boxes[3*i + 2].height  = a[i].breadth;
            boxes[3*i + 2].length  = Math.max(a[i].length, a[i].height);
            boxes[3*i + 2].breadth = Math.min(a[i].length, a[i].height);
        }

        int numBoxes = 3 * numInputBoxes;

        /*Sort the boxes so that the boxes with larger base area appear first*/
        Arrays.sort(boxes, new BoxComparator());

        for (i = 0; i < numBoxes; ++i) {
            bestHeight[i] = boxes[i].height;
        }

        for (i = 1; i < numBoxes; ++i) {
            for (int j = 0; j < i; ++j) {
                /*We can place box i on box j, only if base of box i 
                is smaller than the base of box j*/
                if (boxes[i].length < boxes[j].length 
                && boxes[i].breadth < boxes[j].breadth) {
                    if (bestHeight[i] < 
                    bestHeight[j] + boxes[i].height) {
                        bestHeight[i] = bestHeight[j] + 
                                boxes[i].height;
                    }
                }
            }
        }

        /*Find the stack with the maximum height*/
        int result = bestHeight[0];
        for (i = 1; i < numBoxes; ++i) {
            if (bestHeight[i] > result)
                result = bestHeight[i];
        }

        return result;
    }


    public static void test() {
        Box[] a = { new Box(100, 120, 320), new Box(10, 20, 30), new Box(40, 60, 70), new Box(40, 50, 60) };

        int result = maxStackHeight(a);

        System.out.println("Max stack height = " + result);

        if (result != 600)
            handleError();
    }



    public static void main(String[] args) {
        test();

        System.out.println("Test passed ");
    }

}
