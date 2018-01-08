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
    head: the first node of the linked list
    k: node position from the end. k starts from 1 onwards
    Return value: kth node from end if it exists, null otherwise
    */
    public static LinkedListNode findKthNodeFromEnd(LinkedListNode head, int k) {
        int length = 0;

        LinkedListNode n1 = head;
        while (n1 != null) {
            length++;
            n1 = n1.next;
        }

        n1 = head;
        for (int i = 1; i <= length; ++i) {
            if (i == length - k + 1) {
                return n1;  /*n1 is the kth node from end. So return it*/
            }
            n1 = n1.next;
        }

        /*k value passed doesn't match with the linked list. So return null */
        return null;

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



class LinkedList_03_KthFromEnd {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    
    public static void main(String[] args) {

        /*We test for different list lengths ranging from 1 to MAX_NUM_ELEMENTS_IN_LIST*/
        for (int numElems = 1; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {

            /*Construct the list having numElems. 
            If numElems = 1, the list will be 1
            If numElems = 2, the list will be 1->2 */
            LinkedListNode head = LinkedListNode.constructList(numElems);

            /*We test for different k values ranging from 0 to MAX_NUM_ELEMENTS_IN_LIST+1*/
            for (int k = 0; k <= numElems + 1; ++k) {
                System.out.print("Input  : ");
                LinkedListNode.printList(head);

                /*Find the kth node from the end*/
                LinkedListNode kNode = LinkedListNode.findKthNodeFromEnd(head, k);

                System.out.print("Size = " + numElems + ", " + k + "th node from end is " );

                if (kNode != null)
                    System.out.println(kNode.data);
                else
                    System.out.println("Empty"); 

                /*Verify the kth node from the end*/
                if (k > 0 && k <= numElems) {
                    if (kNode.data != numElems - k + 1) {
                        handleError();
                    }
                } else {
                    if (kNode != null) {
                        handleError();
                    }
                }

                System.out.println("__________________________________________");
            }
            
        }

        System.out.println("Test passed");
    }

}











