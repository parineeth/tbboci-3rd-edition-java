/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;


class Stack_06_SortStack {

    public static final int MAX_NUM_STACK_ELEMS = 10;
    public static final int MAX_VALUE = 20;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    /*
    Input elements are stored in originalStack. At the end of the operation, 
    originalStack will be empty and sorted stack will have elements in sorted order
    */
    public static void stackSort(Stack<Integer> originalStack, Stack<Integer> sortedStack) {
        while (!originalStack.isEmpty()) {
            Integer e1 = originalStack.pop();
            Integer e2 = null;
            if(!sortedStack.isEmpty())
                e2 = sortedStack.peek();
    
            /*If sorted stack is empty OR e1 <= top element of  
            sorted stack, then push e1 onto the sorted stack */
            if (e2 == null || e1 <= e2) {
                sortedStack.push(e1);
                continue;
            } 
    
            /*While e1 > top element of sorted stack, remove the top 
            element of sorted stack and push it onto the original stack. 
            */
            while (!sortedStack.isEmpty()) {
                e2 = sortedStack.peek();
                if (e1 > e2) {
                    e2 = sortedStack.pop();
                    originalStack.push(e2);
                } 
                else {
                    break;
                }
            }
            sortedStack.push(e1); /*Push e1 onto the sorted stack */
        }
    }
     



    public static void testStack() {
        Stack<Integer> originalStack, sortedStack;
        int[] numberArray = new int[MAX_NUM_STACK_ELEMS];
        Random randomGenerator = new Random();
        
        originalStack = new Stack<Integer>();
        sortedStack = new Stack<Integer>();

        /*Test for different sizes of the stack*/
        for (int numElems = 0; numElems <= MAX_NUM_STACK_ELEMS; ++numElems) {
            int j;
            for (j = 0; j < numElems; ++j) {
                /*Generate a random value and push it on to original stack*/
                numberArray[j] = randomGenerator.nextInt(MAX_VALUE);
                originalStack.push(numberArray[j]);
            }

            /*Sort the stack. The sorted results will be in sortedStack*/
            stackSort(originalStack, sortedStack);

            System.out.println("Sorted stack contents are : ");

            int prevData = -1;
            for (j = 0; j < numElems; ++j) {
                /*Pop an element from the sorted stack*/
                int output = sortedStack.pop();
                System.out.println(output);

                /*Verify if the data in stack is in ascending order*/
                if (prevData > output) {
                    handleError();
                }

                prevData = output;
            }

            System.out.println("\n_________________________________________________");
        }


    }
    

    public static void main(String[] args) {
        testStack();
        System.out.println("Test passed");
    }

}

