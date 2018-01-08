/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;




class Activity  {
    int id;
    int startTime;
    int endTime;

    Activity(int inputId, int inputStartTime, int inputEndTime) {
        id = inputId;
        startTime = inputStartTime;
        endTime = inputEndTime;
    }


}

/*This class is used for comparing two elements during sorting*/
class ActivityComparator implements Comparator<Activity> {
    public int compare(Activity a, Activity b)  {

        if (a.endTime > b.endTime)
            return 1;
        else if (a.endTime == b.endTime)
            return 0;
        else
            return -1;
    }
}


class Algo_Greedy_02_ActivitySelection {

    public static void handleError() {
        System.out.println("Error occured ");
        System.exit(1);
    }



    /*
    a: array of activities, where each activity has a start time and end time
    Returns: ArrayList having indexes of the selected activities 
    */
    public static ArrayList<Integer> activitySelection(Activity[] a) {
        ArrayList<Integer> selected = new ArrayList<Integer>(); 

        /*Sort the activities in non-decreasing order of their end time*/
        Arrays.sort(a, new ActivityComparator());

        /*Keep a track of the current time as we process the activities*/
        int curTime = 0;
        int i = 0;
        for (Activity curActivity : a) {
            /*Pick the activity whose start time is on or after current time*/
            if (curActivity.startTime >= curTime) {
                selected.add(i);

                /*Update the current time to the end time of the activity*/
                curTime = curActivity.endTime;
            }
            ++i;
        }

        return selected;
    }




    public static void test01() {
        Activity[] a = new Activity[3];
        Activity obj;

        obj = new Activity(1000, 0, 5);
        a[0] = obj;

        obj = new Activity(1001, 1, 2);
        a[1] = obj;

        obj = new Activity(1002, 3, 6);
        a[2] = obj;

        ArrayList<Integer> selected = activitySelection(a);

        for (int i = 0; i < selected.size(); ++i) {
            int index = selected.get(i);

            System.out.println("Perform Activity : " + a[index].id + 
                ", Start time = " +  a[index].startTime + 
                ", End time = " + a[index].endTime);
        }

        int expectedResult = 2;

        if (selected.size() != expectedResult)
            handleError();

        System.out.println("__________________________________");
    
    }


    public static void test02() {
        Activity[] a = new Activity[4];
        Activity obj; 


        obj = new Activity(1000, 0, 1);
        a[0] = obj;

        obj = new Activity(1002, 1, 5);
        a[1] = obj;

        obj = new Activity(1001, 2, 3);
        a[2] = obj;

        obj = new Activity(1003, 4, 7);
        a[3] = obj;



        ArrayList<Integer> selected = activitySelection(a);

        for (int i = 0; i < selected.size(); ++i) {
            int index = selected.get(i);

            System.out.println("Perform Activity : " + a[index].id + 
                ", Start time = " +  a[index].startTime + 
                ", End time = " + a[index].endTime);
        }


        int expectedResult = 3;

        if (selected.size() != expectedResult)
            handleError();

        System.out.println("__________________________________");
    
    }


    public static void main(String[] args)  {
        test01();
        test02();

        System.out.println("Test passed");
    }

}
