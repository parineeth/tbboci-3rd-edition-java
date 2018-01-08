/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;
import java.lang.*;




class Stack_05_MinStack {

    public static final int MAX_NUM_STACK_ELEMS = 10;
    public static final int MAX_VALUE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /* 
    mainStack: main stack
    minStack: the additional stack for getting the minimum element
    dataToAdd: data to be added to the stack
    */
    public static void addElement(Stack<Integer> mainStack, Stack<Integer> minStack,
                     int dataToAdd) {
        /*Push the node being inserted onto the main stack*/
        mainStack.push(dataToAdd);

        /*If the min stack is empty or the data being inserted is <=  
        the top of the minStack, then add the data to the minStack 
        */
        if (minStack.isEmpty()) {
             minStack.push(dataToAdd);
        } else {
            int peekResult = minStack.peek();
            if (dataToAdd <= peekResult)
                minStack.push(dataToAdd);
        }

    }


    /* 
    mainStack: main stack
    minStack: the additional stack for getting the minimum element 
    Return value: data at the top of the main stack
    */
    public static int removeElement(Stack<Integer> mainStack, 
                    Stack<Integer> minStack) {
        /*If main stack is empty throw an exception*/
        if (mainStack.isEmpty())
            throw new RuntimeException("Stack is empty");

        /*Remove the topmost element from the main stack*/
        int poppedElement = mainStack.pop();

        /*Peek at the minimum value, which is stored at the top of the minStack*/
        int minVal = minStack.peek();

        /*If value popped from the main stack matches the value at the top
         of minStack, then remove the topmost element from the minStack  
        */
        if (poppedElement == minVal) 
            minStack.pop();

        return poppedElement;
    }

    public static int findSmallest(int[] numberArray, int maxIndex) {
        int smallest = 0xFFFFFFF;

        for (int i = 0; i <= maxIndex; ++i) {
            if (smallest > numberArray[i]) {
                smallest = numberArray[i];
            }
        }

        return smallest;
    }

    public static void testStack() {
        Stack<Integer> mainStack; 
        Stack<Integer> minStack;
        int[] numberArray = new int[MAX_NUM_STACK_ELEMS];
        Random randomGenerator = new Random();

        mainStack = new Stack<Integer>();
        minStack = new Stack<Integer>();

        /*Test for different sizes of the stacks*/
        for (int numElems = 0; numElems <= MAX_NUM_STACK_ELEMS; ++numElems) {
            int j, minVal, smallestElement;
            for (j = 0; j < numElems; ++j) {
                /*Generate a random value and store in numberArray*/
                numberArray[j] = randomGenerator.nextInt(MAX_VALUE);

                /*Add the value to the mainStack and minStack*/
                addElement(mainStack, minStack,  numberArray[j]);

                /*Peek the minimum value from the minStack*/
                minVal= minStack.peek();

                /*Verify if the minimum value given by minStack is correct*/
                smallestElement = findSmallest(numberArray, j);
                if (minVal != smallestElement) {
                    handleError();
                }
            }

            for (j = 0; j < numElems; ++j) {
                /*Peek the minimum value from the minStack*/
                minVal = minStack.peek();

                /*Verify if the minimum value given by minStack is correct*/
                smallestElement = findSmallest(numberArray, numElems - 1 - j);
                if (minVal  != smallestElement) {
                    handleError();
                }

                /*Remove an element from the stack*/
                removeElement(mainStack, minStack);
            }

            /*Verify that both minStack and mainStack are empty*/
            if (!minStack.isEmpty() || !mainStack.isEmpty())
                handleError();

        }


    }

    public static void main(String[] args) {
        testStack();
        System.out.println("Test passed");
    }

}

