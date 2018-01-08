/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.lang.*;
import java.util.*;



class QueueUsingStacks <T> {
    private Stack<T> s1;
    private Stack<T> s2;

    public QueueUsingStacks () {
        s1 = new Stack<T>();
        s2 = new Stack<T>();
    }

    public void add(T newElement) {
        /*Add elements only to stack s1*/
        s1.push(newElement);
    }

    public T remove() {
        T e;
        if(s2.isEmpty()) {
            /*We remove elements only from stack s2. So
            if s2 is empty, then pop all the elements from s1 and  
            push them into s2.*/
            while(!s1.isEmpty()) {
                e = s1.pop();
                s2.push(e);
            }
        }
        
        /*If s2 is not empty, then remove the element from top of s2.
        This element corresponds to the head of the queue*/
        e = null;
        if(!s2.isEmpty())
            e = s2.pop();

        return e;
    }

    public boolean isEmpty() {
        /*Queue is empty only if both stacks are empty*/
        if (s1.isEmpty() && s2.isEmpty())
            return true;
        return false;
    }
    
}



class Queue_01_QueueWithStacks {

    public static final int MAX_NUM_QUEUE_ELEMS = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void test() {
        QueueUsingStacks<Integer> q = new QueueUsingStacks<Integer>();

        /*Test for different number of elements in the queue*/
        for (int numElems = 0; numElems <= MAX_NUM_QUEUE_ELEMS; ++numElems) {

            /*Add all the elements to the queue*/
            int j;
            for (j = 0; j < numElems; ++j) {
                Integer input = new Integer(j);
                q.add(input);
            }

            System.out.print("Queue size = " + numElems + ", Elements are : ");

            /*Remove one element at a time from the queue and print it*/
            Integer output;
            for (j = 0; j < numElems; ++j) {
                output = q.remove();

                int expectedVal = j;
                if (output != expectedVal) {
                    handleError();
                }
                System.out.print(output + " ");
            }

            output = q.remove();
            if (output != null)
                handleError();


            System.out.println();
            System.out.println("__________________________________________");   
            
        }

    }

    public static void main(String[] args) {
        test();
        System.out.println("Test passed ");
    }

}











