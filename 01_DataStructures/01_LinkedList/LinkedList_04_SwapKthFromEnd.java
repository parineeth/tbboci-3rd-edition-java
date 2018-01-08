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


    
    public static LinkedListNode findKthNodeFromBegin(LinkedListNode head, int k, LinkedListNode[] prevArray) {
        LinkedListNode curNode = head;
        LinkedListNode prev = null;

        if (prevArray != null)
            prevArray[0] = null;

        int i = 1;
        while (curNode != null) {
            if (i == k)  {
                if (prevArray != null)
                    prevArray[0] = prev;
                return curNode;
            }

            prev = curNode;
            curNode = curNode.next;
            ++i;
        }

        return null;
    
    }


    /* 
    head: the first element of the list
    k: which element from the end has to be fetched
    prevArray: prevArray[0] will contain the element previous to the kth element
    Return value: kth element from end if it exists, null otherwise
    */
    public static LinkedListNode findKthNodeFromEnd(LinkedListNode head, int k, LinkedListNode[] prevArray) {

        if (prevArray != null)
            prevArray[0] = null;
        
        int length = 0;
        LinkedListNode n1 = head;
        while (n1 != null) {
            length++;
            n1 = n1.next;
        }

        n1 = head;
        LinkedListNode prev = null;
        for (int i = 1; i <= length; ++i) {
            if (i == length - k + 1) {
                if (prevArray != null)
                    prevArray[0] = prev;
                return n1;  /*n1 is the kth element from end. So return it*/
            }
            prev = n1;
            n1 = n1.next;
        }

        /*k value passed doesn't match with the linked list. So return null */
        return null;

    }



    public static void printList(LinkedListNode head) {
        LinkedListNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }




    /*Helper function which swaps two neighbors n1 and n2
    head: first node in the linked list
    prev: node previous to n1
    n1: first node to be swapped
    n2: second node to be swapped. n2 occurs immediately after n1
    Return value: head of the result linked list after swapping neighbors
    */
    public static LinkedListNode swapNeighbors(LinkedListNode head,  
            LinkedListNode prev, LinkedListNode n1, LinkedListNode n2) {
        /*Swap n1 and n2*/
        n1.next = n2.next;
        n2.next = n1;

        if (prev != null) {
            prev.next = n2;
        } else {
            head = n2; /*If prev doesn't exist, update head to n2*/
        }

        return head; 
    }



    /*Main function for swapping the kth node from beginning and end
    head: first node in the linked list. 
    k: which node in the linked list should be swapped
    length: number of nodes in the linked list
    Return value: head of the result linked list on success, null on error
    */
    public static LinkedListNode swapKthNode(LinkedListNode head, int k, 
                        int length)  {
        LinkedListNode[] prevArray = new LinkedListNode[1];

        if (head == null || k < 1 || k > length)
            return null;

        /*k1 is the kth node from begining and prev1 is previous to k1*/
        LinkedListNode k1 = LinkedListNode.findKthNodeFromBegin(head, k, prevArray);
        LinkedListNode prev1 = prevArray[0];

        /*k2 is the kth node from end and prev2 is previous to k2*/
        LinkedListNode k2 = LinkedListNode.findKthNodeFromEnd(head, k, prevArray);
        LinkedListNode prev2 = prevArray[0];

        if (k1 == null || k2 == null)
            return null; /*the k value is incorrect*/

        if (k1 == k2)
            return head; /*both nodes are the same. So no need to swap*/

        /*If k1 and k2 are neighbors, then handle this case and return*/
        if (k1.next == k2) 
            return swapNeighbors(head, prev1, k1, k2);

        if (k2.next == k1) 
            return swapNeighbors(head, prev2, k2, k1);

        /*k1 and k2 are not neighbors. So swap k1.next with k2.next*/
        LinkedListNode temp = k1.next;
        k1.next = k2.next;
        k2.next = temp;

        if (prev1 != null) {
            prev1.next = k2; 
        } else  {
            head = k2; /* After swap, k2 becomes new head*/
        }

        if (prev2 != null) {
            prev2.next = k1; 
        } else  {
            head = k1; /* After swap, k1 becomes new head */
        }

        return head;
    }

    
}



class LinkedList_04_SwapKthFromEnd {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }



    public static void main(String[] args) {

        /*We test for different list lengths */
        for (int numElems = 0; numElems <= MAX_NUM_ELEMENTS_IN_LIST; ++numElems) {

            /*Construct the list having numElems. 
            If numElems = 1, the list will be 1
            If numElems = 2, the list will be 1->2 */
            LinkedListNode head = LinkedListNode.constructList(numElems);

            /*Passing k value of 0 should return in an error*/
            LinkedListNode retVal = LinkedListNode.swapKthNode(head, 0, numElems);
            if (retVal != null)
                handleError();

            /*We test for different k values*/
            for (int k = 1; k <= numElems; ++k) {
                
                System.out.println("Size = " + numElems + ", k = " + k);
                System.out.print("Input  : ");
                LinkedListNode.printList(head);

                /*Swap the kth element*/
                head = LinkedListNode.swapKthNode(head, k, numElems);

                System.out.print("Output : ");
                LinkedListNode.printList(head);

                /*Verify the result*/
                LinkedListNode kNode = LinkedListNode.findKthNodeFromBegin(head, k, null);
                if (kNode.data != numElems - k + 1)
                    handleError();

                /*Again swap the kth element to get back the original list*/
                head = LinkedListNode.swapKthNode(head, numElems - k + 1, numElems);

                /*Verify the result*/
                kNode = LinkedListNode.findKthNodeFromBegin(head, k, null);
                if (kNode.data != k)
                    handleError();

                System.out.println("_______________________________________");
            
            }

            /*Passing a value of k that is greater than length of list should return an error*/
            retVal = LinkedListNode.swapKthNode(head, numElems + 1, numElems);

            if (retVal != null)
                handleError();

        
        }

        System.out.println("Test passed");
    }

}











