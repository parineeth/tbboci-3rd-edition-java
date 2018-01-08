/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.lang.*;
import java.util.*;



class StackUsingQueues <T> {
    private Queue<T> q1;
    private Queue<T> q2;

    public StackUsingQueues () {
        q1 = new LinkedList<T>();
        q2 = new LinkedList<T>();
    }

    public boolean isEmpty() {
        /*Stack is empty if q1 is empty*/
        if (q1.isEmpty())
            return true;
        return false;
    }
    
    public void push(T newElement) {
        /*Add elements only to queue q1*/
        q1.add(newElement);
    }

    public T pop() {
        if (isEmpty()) 
            return null;

        /*Remove all elements from q1 and add it to q2 except the last item*/
        T e;
        while (q1.size() > 1) {
            e = q1.remove();
            q2.add(e);
        }
        
        /*Remove the last element in q1. It will contain the top of stack*/
        e = q1.remove();

        /*Swap q1 and q2*/
        Queue<T> temp = q1;
        q1 = q2;
        q2 = temp;
        
        return e; /*Return the top of the stack*/
    }

}



class Stack_02_StackWithQueues {

    public static final int MAX_NUM_ELEMS = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void test() {
        StackUsingQueues<Integer> s = new StackUsingQueues<Integer>();

        /*Test for different number of elements in the stack*/
        for (int numElems = 0; numElems <= MAX_NUM_ELEMS; ++numElems) {

            /*Add all the elements to the stack*/
            int j;
            for (j = 0; j < numElems; ++j) {
                Integer input = new Integer(j);
                s.push(input);
            }

            System.out.println("Stack size = " + numElems + ", Elements are : ");

            /*Remove one element at a time from the stack and print it*/
            Integer output;
            for (j = 0; j < numElems; ++j) {
                output = s.pop();

                int expectedVal = numElems - 1- j;
                if (output != expectedVal) {
                    handleError();
                }
                System.out.println(output);
            }

            /*stack is now empty. So we should get null*/
            output = s.pop();
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











