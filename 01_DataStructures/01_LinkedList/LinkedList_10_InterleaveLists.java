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


    public static LinkedListNode constructList(int numElements, int[] array) {
        LinkedListNode head = null;

        /*Construct list based on elements in the array 
        values[0] will be the last element in the list*/
        for (int i = 0; i < numElements; ++i) {
            int value = array[numElements - 1 - i];
            LinkedListNode newNode = new LinkedListNode(value);
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
        System.out.println();
    }


    /* 
    n1: head of the first linked list
    n2: head of the second linked list
    Return value: head of the result interleaved linked list
    */
    public static LinkedListNode interleaveLists( LinkedListNode n1, 
                        LinkedListNode n2) {

        if (n1 == null) {
            return n2; /*If linked list1 is empty, return n2 */
        }

        if (n2 == null) {
            return n1; /*If linked list2 is empty, return n1*/
        }

        /*Process the two linked lists*/
        LinkedListNode result = n1;
        while (n1 != null && n2 != null) {
            LinkedListNode temp1 = n1.next;
            LinkedListNode temp2 = n2.next;

            /*Place node of second linked list next to the node 
            of the first linked list*/
            if (n1.next != null)
                n2.next = n1.next;
            n1.next = n2;

            n1 = temp1; 
            n2 = temp2; 
        }
        return result;
    }   
    

}



class LinkedList_10_InterleaveLists {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;
    public static final int MAX_NUM_LOOPS = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static int testInterleave()  {
        int[] array1 = new int[MAX_NUM_ELEMENTS_IN_LIST];
        int[] array2 = new int[MAX_NUM_ELEMENTS_IN_LIST];
        Random randomGenerator = new Random();


        int numElems1 = randomGenerator.nextInt(MAX_NUM_ELEMENTS_IN_LIST);
        int numElems2 = randomGenerator.nextInt(MAX_NUM_ELEMENTS_IN_LIST);

        /*If numElems1 = 5, array1 will contain 1, 2, 3, 4, 5 */
        int i;
        for (i = 0; i < numElems1; ++i) {
            array1[i] = i + 1;
        }

        /*If numElems2 = 5, array2 will contain 101, 102, 103, 104, 105 */
        for (i = 0; i < numElems2; ++i) {
            array2[i] = 101 + i;
        }


        /*Use the arrays to construct the two lists
        If numElems1 = 5, list1 will contain 1->2->3->4->5
        If numElems2 = 5. list2 will contain 101->102->103->104->105*/
        LinkedListNode list1 = LinkedListNode.constructList(numElems1, array1);
        LinkedListNode list2 = LinkedListNode.constructList(numElems2, array2);

        System.out.print("Input 1: ");
        LinkedListNode.printList(list1);

        System.out.print("Input 2: ");
        LinkedListNode.printList(list2);

        /*Interleave the lists*/
        LinkedListNode resultList = LinkedListNode.interleaveLists(list1, list2);

        i = 0;
        LinkedListNode curNode = resultList;
        while (curNode != null) {
            /*If index is even, then the element should be less than 100*/
            if (i % 2 == 0 && i < 2 * numElems1) {
                if (curNode.data >= 100)
                    handleError();
            }

            /*If index is odd, then the element should be more than 100*/
            if (i % 2 == 1 && i < 2 * numElems2) {
                if (curNode.data < 100)
                    handleError();
            }
            ++i;
            curNode = curNode.next;
        }

        if (i != (numElems1 + numElems2))
            handleError();

        System.out.print("Output : ");
        LinkedListNode.printList(resultList);

        System.out.println("__________________________________________________");

        return 0;
    }


    


    public static void main(String[] args) {

        for (int numLoops = 0; numLoops < MAX_NUM_LOOPS; ++numLoops) {
                testInterleave();
        }

        System.out.println("Test passed");

    }

}











