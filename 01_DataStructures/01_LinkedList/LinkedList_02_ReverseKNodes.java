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


    /* 
    head: first node of the linked list
    k: how many nodes should be reversed
    Return value: first node of the new linked list after reversing every k nodes
    */
    public static LinkedListNode kReverseList(LinkedListNode head, int k) {
        int i = 0;

        if (head == null || k == 0)
            return head;

        LinkedListNode curNode = head;
        LinkedListNode prevNode = null;

        while (curNode != null && i < k) {
            /*Store the next node in a temporary variable*/
            LinkedListNode tempNode = curNode.next;

            /*Reverse the link */
            curNode.next = prevNode;

            /*Update the previous node to the current node */
            prevNode = curNode;

            /*Proceed to the next node in the original linked list*/
            curNode = tempNode;

            ++i;
        }

        /*
        We have reversed k nodes. So curNode refers to the (k+1)th node.
        and head now refers to the kth node.
        Now recursively process the remaining nodes from curNode onwards 
        and assign the result to head.next.
        */
        if (curNode != null)
            head.next = kReverseList(curNode, k);

        /*prevNode will refer to first node in linked list after reversal*/
        return prevNode;
    }


    public static LinkedListNode constructList(int numElements) {
        LinkedListNode head = null;
        int value = numElements;

        /*If numElems = 1, the list will be 1
        If numElems = 2, the list will be 1->2 
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



class LinkedList_02_ReverseKNodes {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    private static void handleError() {
        System.out.println("Reverse List test failed");
        System.exit(1);
    }


    public static void main(String[] args) {

        /*We test for different list lengths ranging from 0 to MAX_NUM_ELEMENTS_IN_LIST*/
        for (int numElems = 0; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {

            /*We test for different k values ranging from 0 to MAX_NUM_ELEMENTS_IN_LIST+1*/
            for (int k = 0; k <= MAX_NUM_ELEMENTS_IN_LIST+1; ++k) {

                /*Construct the list having numElems. 
                If numElems = 1, the list will be 1
                If numElems = 2, the list will be 1->2 */
                LinkedListNode head = LinkedListNode.constructList(numElems);

                System.out.println("Size = " + numElems + ", K = " + k);

                LinkedListNode.printList(head);

                head = LinkedListNode.kReverseList(head, k);

                LinkedListNode.printList(head);

                System.out.println("_________________________________");
            }
        }

        System.out.println("Test passed");
    }

}











