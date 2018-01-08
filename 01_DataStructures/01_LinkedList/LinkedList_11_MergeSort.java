/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class LinkedListNode {
    
    public int data;
    public LinkedListNode next;

    public LinkedListNode() {
        next = null;
    }

    public LinkedListNode (int val) {
        data = val;
        next = null;
    }

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void printList(LinkedListNode head) {
        LinkedListNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }


    public static LinkedListNode constructList(int numElements, int[] array) {
        LinkedListNode head = null;

        for (int i = 0; i < numElements; ++i) {
            int value = array[numElements - 1 - i];
            LinkedListNode newNode = new LinkedListNode(value);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }


    public static void verifyList(LinkedListNode head) {
        LinkedListNode curNode = head;
        LinkedListNode prevNode = null;

        while (curNode != null) {
            if (prevNode != null) {
                if (curNode.data < prevNode.data) {
                    handleError();
                }
            }

            prevNode = curNode;
            curNode = curNode.next;
        }
    }

    

    /* 
    n1: head of the first linked list
    n2: head of the second linked list
    Return value: head of the merged linked list
    */
    public static LinkedListNode mergeLists( LinkedListNode n1, LinkedListNode n2) {
        if (n1 == null) {
            return n2; /*If linked list1 is empty, return n2 */
        }
        if (n2 == null) {
            return n1; /*If linked list2 is empty, return n1*/
        }

        /*make the result refer to the node with the smaller value */
        LinkedListNode result, prevMergeNode;
        if (n1.data <= n2.data) {
            result = n1;
            prevMergeNode = n1;
            n1 = n1.next;
        } else {
            result = n2;
            prevMergeNode = n2;
            n2 = n2.next;
        }

        /*Process the two linked lists*/
        while (n1 != null && n2 != null) {
            if (n1.data <= n2.data) {
                prevMergeNode.next = n1;
                n1 = n1.next;
                prevMergeNode = prevMergeNode.next;
            } else {
                prevMergeNode.next = n2;
                n2 = n2.next;
                prevMergeNode = prevMergeNode.next;
            }
        }

        /*
        If there are still nodes present in linked list1 or linked list2, then
        append them to the result list
        */
        if (n1 != null) {
            prevMergeNode.next = n1;
        } else {
            prevMergeNode.next = n2;
        }

        return result;
    }

    

}



class LinkedList_11_MergeSort {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;
    public static final int MAX_NUM_LOOPS = 10;
    public static final int MAX_VALUE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static int testMergeSort() {
        Random randomGenerator = new Random();

        /*randomly decide the size of the lists*/
        int numElems1 = randomGenerator.nextInt(MAX_NUM_ELEMENTS_IN_LIST);
        int numElems2 = randomGenerator.nextInt(MAX_NUM_ELEMENTS_IN_LIST);

        int[] array1 = new int[numElems1];
        int[] array2 = new int[numElems2];


        /*Store random values in the arrays*/
        int i;
        for (i = 0; i < numElems1; ++i) {
            array1[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        for (i = 0; i < numElems2; ++i) {
            array2[i] = randomGenerator.nextInt(MAX_VALUE);
        }

        /*Sort the arrays*/
        Arrays.sort(array1);
        Arrays.sort(array2);

        /*Construct the lists based on the elements in the array
        The list will contain the elements in ascending order*/
        LinkedListNode list1 = LinkedListNode.constructList(numElems1, array1);
        LinkedListNode list2 = LinkedListNode.constructList(numElems2, array2);

        System.out.print("Input1 : ");
        LinkedListNode.printList(list1);

        System.out.print("Input2 : ");
        LinkedListNode.printList(list2);

        /*Merge the lists*/
        LinkedListNode mergedList = LinkedListNode.mergeLists(list1, list2);

        System.out.print("Output : ");
        LinkedListNode.printList(mergedList);

        /*Verify the result*/
        LinkedListNode.verifyList(mergedList);

        System.out.println("____________________________________________");

        return 0;
    }   


    public static void main(String[] args) {

        for (int numLoops = 0; numLoops < MAX_NUM_LOOPS; ++numLoops) {
            testMergeSort();
        }

        System.out.println("Test passed");

    }

}











