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
        int value = numElements - 1;

        /*If numElems = 1, the list will be 0
        If numElems = 2, the list will be 0->1 
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


    /*Adds a loop to the linked list at a random position*/
    public static LinkedListNode addLoopToList(LinkedListNode head, int numElems) {
        LinkedListNode n1 = null, n2 = null;
        Random randomGenerator = new Random();

        /*Randomly generate 2 values*/
        int low = randomGenerator.nextInt(numElems);
        int high = randomGenerator.nextInt(numElems);

        /*Make sure that low <= high*/
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }


        /*Find the nodes at positions low and high*/
        LinkedListNode curNode = head;
        int i = 0;
        while (curNode != null) {
            if (i == low)
                n1 = curNode;

            if (i == high) {
                n2 = curNode;
                break;
            }

            ++i;
            curNode = curNode.next;
        }

        System.out.println("Adding loop from pos " + high + " to " + low);
        
        /*Add loop from node at position high to node at position low*/ 
        n2.next = n1;
        return n1;
    }


    
    /*head: first node of the linked list 
    Return value: first node in loop if loop exists, null otherwise
    */
    public static LinkedListNode findLoop( LinkedListNode head)  {
        LinkedListNode n1 = head, n2 = head;
        boolean foundLoop = false;

        /*n1 moves fast. So advance it by two steps
        n2 is slow. So advance it by one step
        */
        while (n1 != null) {
            n1 = n1.next;
            if (n1 != null) {
                n1 = n1.next;
                n2 = n2.next;
            }

            /*If n1 and n2 meet then there is a loop in the linked list*/
            if (n1 == n2) {
                foundLoop = true;
                break;
            }
        }

        if (!foundLoop)
            return null;

        /*Find the beginning of the loop*/
        LinkedListNode n3 = head;
        while (n1 != n3) {
            n1 = n1.next;
            n3 = n3.next;
        }

        return n1;
    }   
    
}



class LinkedList_09_FindLoop {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }


    public static void main(String[] args) {

        /*We test for different list lengths */
        for (int numElems = 0; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {

            System.out.println("Number of elements = " + numElems);

            /*Construct the list without any loops  
            If numElems = 2, the list will be 0->1 */
            LinkedListNode head = LinkedListNode.constructList(numElems);

            /*Currently there are no loops in the list. If we find a loop, report error*/
            if (LinkedListNode.findLoop(head) != null)
                handleError();

            if (head != null) {
                /*Add a loop to the list*/
                LinkedListNode expectedResult = LinkedListNode.addLoopToList(head, numElems);

                /*We should find a loop in the list. If we don't find a loop, report error*/
                LinkedListNode result = LinkedListNode.findLoop(head);
                if (result != expectedResult)
                    handleError();

                System.out.println("Found loop in list at location " + result.data);
            }

            System.out.println("____________________________________________________");
        }
        
        System.out.println("Test passed");
    }

}











