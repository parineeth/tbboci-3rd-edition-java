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
        data = 0;
        next = null;
    }

    public static LinkedListNode constructList(int numElements, int startValue) {
        LinkedListNode head = null;
        int value = startValue;

        LinkedListNode prev = null;
        for (int i = 0; i < numElements; ++i) {
            LinkedListNode newNode = new LinkedListNode();

            if (prev == null)
                head = newNode;
            else 
                prev.next = newNode;
        
            newNode.data = value;
            newNode.next = null;

            prev = newNode;     

            ++value;
        }

        return head;
    }



    public static void printList(LinkedListNode head) {
        LinkedListNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }

    public static int findLength(LinkedListNode n1) {
        int length = 0;
        while (n1 != null) {
            n1 = n1.next;
            ++length;
        }
        return length;
    }

    /*
    head1: first node of linked list1
    head2: first node of linked list2
    Return value: first common node between the two linked lists
    */
    public static LinkedListNode findIntersectionNode(LinkedListNode head1, 
                    LinkedListNode head2) {
        /*Find the length of the two linked lists*/
        int length1 = findLength(head1);
        int length2 = findLength(head2);
        LinkedListNode n1, n2;

        /*store head of the longer linked list in n1 and head of 
        shorter linked list in n2*/
        if (length1 >= length2) {
            n1 = head1;
            n2 = head2;
        } else {
            n1 = head2;
            n2 = head1;
        }

        /*Find the difference in length of the two linked lists. Then advance
        n1 by the difference*/
        int diff = Math.abs(length1 - length2);
        while (diff > 0) {
            n1 = n1.next;
            --diff;
        }

        /*Go on comparing the nodes in linked list1 starting from n1 and
        linked list2 starting from n2 till n1 and n2 match*/
        while (n1 != null && n2 != null && n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }

        /*n1 will have the common node if it exists, otherwise n1 will be null*/
        return n1;
    }

}


class LinkedList_08_FindIntersectionNode {

    public static final int MAX_NUM_ELEMS = 10;
    public static final int MAX_NUM_TESTS = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static void test() {
        Random randomGenerator = new Random();

        /*Randomly generate the length of list1 and generate the elements of the list
        If there are two elements in the list, then list is 0.1*/
        int length1 = randomGenerator.nextInt(MAX_NUM_ELEMS);
        int startValue = 0;
        LinkedListNode head1 = LinkedListNode.constructList(length1, startValue);

        /*Randomly generate the length of list2 and generate the elements of the list
        If there are two elements in the list, then list is 100.101*/
        int length2 = randomGenerator.nextInt(MAX_NUM_ELEMS);
        startValue = 100;
        LinkedListNode head2 = LinkedListNode.constructList(length2, startValue);

        /*Randomly generate the length of list3 and generate the elements of the list
        If there are two elements in the list, then list is 200.201*/
        int length3 = randomGenerator.nextInt(MAX_NUM_ELEMS);
        startValue = 200;
        LinkedListNode head3 = LinkedListNode.constructList(length3, startValue);

        /*Find the last element in list1 and store it in n1*/
        LinkedListNode n1 = head1;
        while (n1 != null && n1.next != null) {
            n1 = n1.next;
        }

        /*Find the last element in list2 and store it in n2*/
        LinkedListNode n2 = head2;
        while (n2 != null && n2.next != null) {
            n2 = n2.next;
        }

        /*Connect the last element of list1 to first element of list3*/
        if (n1 != null)
            n1.next = head3;

        /*Connect the last element of list2 to first element of list3*/
        if (n2 != null)
            n2.next = head3;


        System.out.print("List 1 : ");
        LinkedListNode.printList(head1);

        System.out.print("List 2 : ");
        LinkedListNode.printList(head2);

        /*Find the first common node between list1 and list2*/
        LinkedListNode result = LinkedListNode.findIntersectionNode(head1, head2);

        if (result != null)
            System.out.println("Intersection node = " + result.data);
        else 
            System.out.println("No intersection node");

        /*The expected result is the first element in list3*/
        LinkedListNode expectedResult = null;
        if (n1 != null && n2 != null)
            expectedResult = head3;

        if (result != expectedResult)
            handleError();
    
        System.out.println("________________________________________________");
    }

    public static void main(String[] args)  {

        for (int i = 0; i < MAX_NUM_TESTS; ++i) {
            test();
        }

        System.out.println("Test passed ");
    }

}
