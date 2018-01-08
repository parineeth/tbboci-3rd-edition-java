/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.Random;

class LinkedListNode {
    public static final int MAX_VALUE = 10;

    public int data;
    public LinkedListNode next;

    public LinkedListNode() {
        data = 0;
        next = null;
    }

    public LinkedListNode(int val) {
        data = 0;
        next = null;
    }

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    /*
    head: first node in the linked list
    */
    public static void removeDuplicates(LinkedListNode head) {
        /*If there are 0 or 1 nodes in linked list, then simply return*/
        if (head == null || head.next == null)
            return;
        
        LinkedListNode curNode = head;
        while (curNode != null) {
            /*Iterate from node after curNode to the end of linked list*/
            LinkedListNode iterNode = curNode.next;
            LinkedListNode prevNode = curNode;

            while (iterNode != null) {
                if (curNode.data == iterNode.data) {
                    /*iterNode is a duplicate of curNode. 
                    so remove it*/
                    prevNode.next = iterNode.next;
                    iterNode = iterNode.next;
                } else {
                    prevNode = iterNode;
                    iterNode = iterNode.next;
                }
            }
    
            curNode = curNode.next;
        }
    }



    public static LinkedListNode constructList(int numElements) {
        LinkedListNode head = null;
        Random randomGenerator = new Random();

        for (int i = 0; i < numElements; ++i) {
            LinkedListNode newNode = new LinkedListNode();
            newNode.data = randomGenerator.nextInt(MAX_VALUE);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }


    public static void printList(LinkedListNode head) {
        LinkedListNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
        System.out.println("");
    }

    public static void verifyList(LinkedListNode head) {

        /*If there are 0 or 1 elements in list, then simply return*/ 
        if (head == null || head.next == null)
            return;

            
        LinkedListNode curNode = head;
        while (curNode != null) {
            /*Iterate from node after curNode to the end of the list*/
            LinkedListNode iterNode = curNode.next;
            while (iterNode != null) {
                /*If there is a duplicate, then flag an error*/
                if (curNode.data == iterNode.data) 
                    handleError();
            
                iterNode = iterNode.next;
            }
            curNode = curNode.next;
        }

    }


}


class LinkedList_06_RemoveDuplicates {
    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    public static void main(String[] args) {

        /*Test for different list lengths*/
        for (int numElems = 0; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {
            /*Construct a list with random elements. The list can contain duplicates*/
            LinkedListNode head = LinkedListNode.constructList(numElems);

            System.out.print("Input  : ");
            LinkedListNode.printList(head);

            /*Remove the duplicates*/
            LinkedListNode.removeDuplicates(head);

            System.out.print("Output : ");
            LinkedListNode.printList(head);

            /*Verify the list*/
            LinkedListNode.verifyList(head);
        
            System.out.println("__________________________________________________________");
        }

        System.out.println("Test passed ");

    }

}
