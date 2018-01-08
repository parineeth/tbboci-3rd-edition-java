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
    public LinkedListNode random;

    public LinkedListNode() {
        next = null;
        random = null;
    }

    public LinkedListNode (int val) {
        data = val;
        next = null;
    }


    public static LinkedListNode constructList(int numElements) {
        LinkedListNode head = null, newNode;
        int value = numElements;
        LinkedListNode[] nodeArray = new LinkedListNode[numElements];
        Random randomGenerator = new Random();

        /*If numElements is 2, the list created will be 1->2 */
        int i;
        for (i = 0; i < numElements; ++i) {
            newNode = new LinkedListNode();
            newNode.data = value;
            newNode.next = head;
            nodeArray[numElements - 1 - i] = newNode;
            head = newNode;
            --value;
        }

        /*Connect each node in the list to a random node*/
        newNode = head;
        for (i = 0; i < numElements; ++i) {
            int randVal = randomGenerator.nextInt(numElements) ;
            newNode.random = nodeArray[randVal];
            newNode = newNode.next;
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


    /*originalHead: head of the original linked list
    Return value: head of the newly cloned linked list
    */
    public static LinkedListNode cloneList(LinkedListNode originalHead) {
        LinkedListNode n1 = originalHead, n2, newHead = null;
        /*
        For each node in original linked list, create a new node. The new node
        initially will be placed next to the original node
        */
        while (n1 != null) {
            LinkedListNode nextNode = n1.next;

            n2 = new LinkedListNode();
            if (n2 == null) {
                    return null;
            }
            n2.data = n1.data;

            if (newHead == null) 
                newHead = n2;
            n1.next = n2;
            n2.next = nextNode;
            n1 = nextNode;
        }

        /*Set random correctly for the new nodes*/
        n1 = originalHead;
        while (n1 != null) {
            n1.next.random = n1.random.next;
            n1 = n1.next.next;
        }

        /*Disconnect new nodes from original linked list and 
        form a new linked list for them*/
        n1 = originalHead;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n1.next.next;
            if (n2.next != null)
                n2.next = n2.next.next;

            n1 = n1.next;
        }
        return newHead;
    }
    

}



class LinkedList_12_CloneList {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;
    public static final int MAX_NUM_LOOPS = 10;
    public static final int MAX_VALUE = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static void main(String[] args) {


        /*We test for different list lengths */
        for (int numElems = 0; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {

            /*create a list with numElems, where each node refers to a normal node
            and to a random node*/
            LinkedListNode head = LinkedListNode.constructList(numElems);

            /*Clone the original list to create another copy */
            LinkedListNode clone = LinkedListNode.cloneList(head);

            /*Verify if the clone is a proper copy of the original list*/
            LinkedListNode n1 = head;
            LinkedListNode n2 = clone;
            while (n1 != null && n2 != null) {
                if ( (n1.data != n2.data) || (n1.random.data != n2.random.data)) {
                    handleError();
                }

                n1 = n1.next;
                n2 = n2.next;
            }

            /*If the original list or the clone is longer than the other then return an error*/
            if ((n1 != null)|| (n2 != null))
                handleError();

        }
        System.out.println("Test passed");

    }

}











