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
    n1 - head of first sorted list
    n2 - head of second sorted list
    Result - head of merged sorted list
    */
    public static LinkedListNode mergeLists( LinkedListNode n1, LinkedListNode n2) {

        /*If list1 is empty, return n2 */
        if (n1 == null)
            return n2;

        /*If list2 is empty, return n1*/
        if (n2 == null)
            return n1;

        /*Since we are sorting in ascending order, result refers to
        the node with the smaller value */
        LinkedListNode result;
        if (n1.data <= n2.data) {
            result = n1;
            n1 = n1.next;
        } else {
            result = n2;
            n2 = n2.next;
        }

        /*Process the two lists*/
        LinkedListNode mergeNode = result;
        while (n1 != null && n2 != null) {

            if (n1.data <= n2.data) {
                mergeNode.next = n1;
                n1 = n1.next;
                mergeNode = mergeNode.next;
            } else {
                mergeNode.next = n2;
                n2 = n2.next;
                mergeNode = mergeNode.next;
            }
        }

        /*If there are still nodes present in list1 or list2, then
        append them to the result list*/
        if (n1 != null)
            mergeNode.next = n1;
        else
            mergeNode.next = n2;

        return result;

    }



    /*
    firstNode: head of the list to be sorted
    numElements: number of elements in the list
    Return value: head of the merged and sorted list
    */
    public static LinkedListNode sortList(LinkedListNode firstNode, 
                        int numElements) {
        if (numElements == 0)
            return null;
        /*
        If there is only a single node in the list, then disconnect next 
        and return the node as the result without any further recursive calls
        */
        if (numElements == 1) {
            firstNode.next = null;
            return firstNode;
        }

        /*
        Divide the list into two lists. list1 has count1 elements and
        list2 has count2 elements 
        */
        LinkedListNode list1 = firstNode;
        int count1 = numElements / 2;

        LinkedListNode curNode = firstNode;
        for (int i = 0; i < count1; ++i)
            curNode = curNode.next;

        LinkedListNode list2 = curNode;
        int count2 = numElements - count1;

        /*Call sortList recursively on list1 and list2*/
        list1 = sortList(list1, count1);
        list2 = sortList(list2, count2);

        /*
        list1 and list2 are now sorted. So merge the two lists into a single
        sorted list and return its head node
        */
        return mergeLists(list1, list2);
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



class Sort_05_SortLinkedList {

    public static final int MAX_NUM_ELEMENTS = 10;
    public static final int MAX_NUM_LOOPS = 10;
    public static final int MAX_VALUE = 100;

    public static int test() {
        Random randomGenerator = new Random();

        int numElems1 = randomGenerator.nextInt(MAX_NUM_ELEMENTS);

        LinkedListNode list1 = LinkedListNode.constructList(numElems1, MAX_VALUE);

        System.out.print("Input  : ");
        LinkedListNode.printList(list1);

        list1 = LinkedListNode.sortList(list1, numElems1);

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
