/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class LinkedListNode {
    
    public int data;
    public LinkedListNode next;


    public LinkedListNode (int val) {
        data = val;
        next = null;
    }

    /*Reverses the linked list without using recursion
    head: first node in the original linked list 
    Return value: the first node of the reversed linked list
    */
    public static LinkedListNode reverseLinkedList(LinkedListNode head) {
        LinkedListNode curNode = head;
        LinkedListNode prevNode = null;

        while (curNode != null) {
            /*Store the next node in a temporary variable*/
            LinkedListNode nextNode = curNode.next;

            /*Reverse the link */
            curNode.next = prevNode;

            /*Update the previous node to the current node */
            prevNode = curNode;

            /*Proceed to the next node in the original linked list*/
            curNode = nextNode;
        }

        /*
        Once the linked list has been reversed, prevNode will have
        the new head. So return it
        */
        return prevNode;
    }

    
    /*Recursively reverses the linked list 
    curNode: current node of the linked list being processed 
    Return value: first node of the reversed link list*/
    public static LinkedListNode reverseLinkedListR(LinkedListNode curNode) {
        if (curNode == null || curNode.next == null)
            return curNode;/*return last node in original linked list as new head*/

        /*Recursively reverses the remaining nodes in the linked list*/
        LinkedListNode newHead = reverseLinkedListR(curNode.next);

        /*Connect up the current node to the reversed linked list*/
        curNode.next.next = curNode;
        curNode.next = null;

        return newHead;
    }



    public static LinkedListNode constructList(int numElements) {
        LinkedListNode head = null;
        int value = numElements;

        /*If numElems = 1, the list will be 1->NULL
        If numElems = 2, the list will be 1->2->NULL 
        */
        for (int i = 0; i < numElements; ++i) {
            LinkedListNode newNode = new LinkedListNode(value);
            newNode.next = head;
            head = newNode;
            --value;
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


    

    
}



class LinkedList_01_ReverseList {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    

    public static void testNonRecursive() {

        System.out.println("Testing Non Recursive Linked List Reverse");

        /*We test for different list lengths ranging from 0 to MAX_NUM_ELEMENTS_IN_LIST*/
        for (int numElems = 0; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {

            /*Construct the list having numElems. 
            If numElems = 1, the list will be 1->NULL
            If numElems = 2, the list will be 1->2->NULL */
            LinkedListNode head = LinkedListNode.constructList(numElems);

            System.out.print("Input  : ");
            LinkedListNode.printList(head);

            /*Reverse the linked list*/
            head = LinkedListNode.reverseLinkedList(head);

            System.out.print("Output : ");
            LinkedListNode.printList(head);

            /*Iterate the reversed list and check if the list is in reverse order*/
            LinkedListNode curNode = head;
            for (int j = 0; j < numElems; ++j) {

                if (curNode.data != numElems - j) {
                    handleError();
                }

                curNode = curNode.next;
            }

            System.out.println("_______________________________________________");
        }
    }

    public static void testRecursive() {

        System.out.println("Testing Recursive Linked List Reverse");

        /*We test for different list lengths ranging from 0 to MAX_NUM_ELEMENTS_IN_LIST*/
        for (int numElems = 0; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {

            /*Construct the list having numElems. 
            If numElems = 1, the list will be 1->NULL
            If numElems = 2, the list will be 1->2->NULL */
            LinkedListNode head = LinkedListNode.constructList(numElems);

            System.out.print("Input  : ");
            LinkedListNode.printList(head);

            /*Reverse the linked list recursively*/
            head = LinkedListNode.reverseLinkedListR(head);

            System.out.print("Output : ");
            LinkedListNode.printList(head);

            /*Iterate the reversed list and check if the list is in reverse order*/
            LinkedListNode curNode = head;
            for (int j = 0; j < numElems; ++j) {

                if (curNode.data != numElems - j) {
                    handleError();
                }

                curNode = curNode.next;
            }

            System.out.println("_______________________________________________");
        }

    }

    public static void main(String[] args) {
        testNonRecursive();
        testRecursive();
        System.out.println("Test passed");
    }

}











