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


    public LinkedListNode (int val) {
        data = val;
        next = null;
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

    /* 
    n1: the node to be deleted
    Return value: true on success, false on failure
    */
    public static boolean deleteNode(LinkedListNode n1) {

        if (n1.next != null) {
            /*Get the next node*/
            LinkedListNode n2 = n1.next;

            /*Copy the contents of the next node into the current node*/
            n1.data = n2.data;
            n1.next = n2.next;

            /*Return indicating the operation is successful*/
            return true;
        }

        /*return indicating the operation failed*/
        return false;
    }

    
}



class LinkedList_05_DeleteNode {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static void main(String[] args) {
        Random randomGenerator = new Random();

        /*We test for different list lengths */
        for (int numElems = 2; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {

            /*Construct the list having numElems. 
            If numElems = 2, the list will be 1->2 */
            LinkedListNode head = LinkedListNode.constructList(numElems);

            /*Choose a random position to delete. It should not be the last element*/
            int k = randomGenerator.nextInt(numElems - 1);

            System.out.println("Deleting " + k + "th node ");
            System.out.print("Input  : ");
            LinkedListNode.printList(head);

            /*Find the node at position k*/
            LinkedListNode curNode = head;
            for (int i = 0; i < k; ++i) {
                curNode = curNode.next;
            }

            /*delete the node*/
            boolean retVal = LinkedListNode.deleteNode(curNode);

            System.out.print("Output  : ");
            LinkedListNode.printList(head);
            
            /*Verify the result*/
            if (!retVal)
                handleError();

            System.out.println("_____________________________________________");
        }

        System.out.println("Test passed");

    }

}











