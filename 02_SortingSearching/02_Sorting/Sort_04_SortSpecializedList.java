/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;



class LinkedListNode {
    public int data;
    public LinkedListNode next;

    LinkedListNode() {
        data = 0;
        next = null;
    }

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    /*
    head: array of heads of the seperated lists
    tail: array of tails of the seperated lists
    curNode: current node being processed
    i: data value of the node
    */
    public static void addNode(LinkedListNode[] head, LinkedListNode[] tail, 
                LinkedListNode curNode, int i) {
        curNode.next = head[i];
        head[i] = curNode;
        if (tail[i] == null)
            tail[i] = curNode;

    }

    /*firstNode: first node in the list to be sorted 
    numDistinctValues: number of distinct values 
    Return value: head of the sorted list
    */
    public static LinkedListNode sortList(LinkedListNode firstNode, 
                    int numDistinctValues) {
        LinkedListNode[] head = new LinkedListNode[numDistinctValues]; 
        LinkedListNode[] tail = new LinkedListNode[numDistinctValues];
        LinkedListNode result = null;

        if (firstNode == null)
            return null;

        int i;
        for (i = 0; i < numDistinctValues; ++i) {
            head[i] = null;
            tail[i] = null;
        }

        /*Partition the list into seperate lists (0-list, 1-list and 2-list)
        based on the data in the node*/
        LinkedListNode curNode = firstNode;
        while (curNode != null) {
            LinkedListNode nextNode = curNode.next;
            addNode(head, tail, curNode, curNode.data);
            curNode = nextNode;
        }

        /*Connect the tail of first linked list with head of second linked list
        and so on*/
        result = head[0];
        LinkedListNode lastElement = tail[0];
        for (i = 1; i < numDistinctValues; ++i) {
            if (result == null)
                result = head[i];

            /*Link last element of previous list with head of current list*/
            if (lastElement != null)
                lastElement.next = head[i];

            /*update the last element to the tail of current list*/
            if (tail[i] != null)
                lastElement = tail[i];
        }

        return result;
    }


    public static LinkedListNode constructList(int numElements, int maxVal) {
        LinkedListNode head = null;
        Random randomGenerator = new Random();

        for (int i = 0; i < numElements; ++i) {
            LinkedListNode newNode = new LinkedListNode();
            newNode.data = randomGenerator.nextInt(maxVal);
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


    public static void printList(LinkedListNode head) {
        LinkedListNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
        System.out.println("");
    }

}



class Sort_04_SortSpecializedList {

    public static final int MAX_NUM_ELEMENTS = 10;
    public static final int MAX_NUM_LOOPS = 10;
    public static final int MAX_VALUE = 3;

    public static int test() {
        Random randomGenerator = new Random();

        int numElems1 = randomGenerator.nextInt(MAX_NUM_ELEMENTS);

        LinkedListNode list1 = LinkedListNode.constructList(numElems1, MAX_VALUE);

        System.out.print("Input  : ");
        LinkedListNode.printList(list1);

        list1 = LinkedListNode.sortList(list1, MAX_VALUE);

        System.out.print("Output : ");
        LinkedListNode.printList(list1);

        LinkedListNode.verifyList(list1);

        System.out.println("__________________________________________________");

        return 0;
    }


    public static void main(String[] args) {
        for (int numLoops = 0; numLoops < MAX_NUM_LOOPS; ++numLoops) {
            test();
        }

        System.out.println("Test passed");

    }

}
