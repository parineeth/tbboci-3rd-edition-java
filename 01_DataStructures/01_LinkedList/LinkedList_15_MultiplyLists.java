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

    public LinkedListNode() {
        next = null;
    }

    public LinkedListNode (int val) {
        data = val;
        next = null;
    }

    public static LinkedListNode constructList(int numElements) {
        LinkedListNode head = null;
        Random randomGenerator = new Random();

        for (int i = 0; i < numElements; ++i) {
            int value = randomGenerator.nextInt(10);
            LinkedListNode newNode = new LinkedListNode(value);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }


    public static int convertListToValue(LinkedListNode head) {
        LinkedListNode curNode = head;
        int result = 0;

        while (curNode != null) {
            result = result*10 + curNode.data;
            curNode = curNode.next;
        }

        return result;
    }   



    /* 
    head: first element in the list 
    Return value: first element of the reversed link list
    */
    public static LinkedListNode reverseLinkedList(LinkedListNode head) {

        LinkedListNode curNode = head;
        LinkedListNode prevNode = null;

        while (curNode != null) {
            /*Store the next node in a temporary variable*/
            LinkedListNode tempNode = curNode.next;

            /*Reverse the link so that current node refers to the previous node*/
            curNode.next = prevNode;

            /*Update the previous node to the current node */
            prevNode = curNode;

            /*Proceed to the next node in the original list*/
            curNode = tempNode;
        }

        /*
        Once the list has been reversed, prevNode will be
        refering to the new head. So return it
        */
        return prevNode;
    }



    public static void printList(LinkedListNode head) {
        LinkedListNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
        
    }


    /*
    n1, n2: head of the first and second linked list
    count1, count2: num elements in first and second linked list
    Return value: head of new linked list having the result of multiplication
    */
    public static LinkedListNode multiplyLists(LinkedListNode n1, LinkedListNode n2,
                    int count1, int count2) {

        /*Reverse the two input linked lists*/
        LinkedListNode h1 = reverseLinkedList(n1);
        LinkedListNode h2 = reverseLinkedList(n2);

        /*Pre-create the result linked list*/
        int i = 0;
        LinkedListNode resultHead = null, curResNode;
        while (i < count1 + count2 ) {
            curResNode = new LinkedListNode();
            curResNode.next = resultHead;
            resultHead = curResNode;
            ++i;
        }

        /*Perform the multiplication*/
        LinkedListNode resultStartNode = resultHead;
        LinkedListNode p1 = h1;
        while (p1 != null) {
            curResNode = resultStartNode;
            LinkedListNode p2 = h2;
            int carry = 0;
            while (p2 != null) {

                int product = p1.data * p2.data;
                int sum = product + curResNode.data + carry;
                curResNode.data = sum % 10;
                carry = sum / 10;

                p2 = p2.next;
                curResNode = curResNode.next;
            }

            curResNode.data = carry;

            p1 = p1.next;
            resultStartNode = resultStartNode.next;
        }
        /*Reverse back the two input linked lists*/
        reverseLinkedList(h1);
        reverseLinkedList(h2);

        /*Reverse the result linked list*/
        resultHead = reverseLinkedList(resultHead);

        return resultHead;
    }

    
}



class LinkedList_15_MultiplyLists {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 5;
    public static final int MAX_NUM_TESTS = 100;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    
    public static void main(String[] args) {
        Random randomGenerator = new Random();

        /*Try out several tests where the inputs are randomly generated*/
        for (int iter = 0; iter < MAX_NUM_TESTS; ++iter) {

            /*Generate a random list with a random number of elements*/
            int numElems1 = randomGenerator.nextInt(MAX_NUM_ELEMENTS_IN_LIST);
            LinkedListNode head1 = LinkedListNode.constructList(numElems1);

            /*Generate a random list with a random number of elements*/
            int numElems2 = randomGenerator.nextInt(MAX_NUM_ELEMENTS_IN_LIST);
            LinkedListNode head2 = LinkedListNode.constructList(numElems2);

            /*Perform the multiplication*/
            LinkedListNode result = LinkedListNode.multiplyLists(head1, head2, numElems1, numElems2);

            /*Obtain the integer value of the two input lists and the result list*/
            int val1 = LinkedListNode.convertListToValue(head1);

            int val2 = LinkedListNode.convertListToValue(head2);

            int val3 = LinkedListNode.convertListToValue(result);

            System.out.println( val1 + " * " + val2 + " = " + val3);

            /*Verify the result*/
            if (val3 != (val1 * val2)) {
                handleError();
            }

        }

        System.out.println("Test passed");

    }

}











