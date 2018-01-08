/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/



class LinkedListNode {
    public int data;
    public LinkedListNode next;

    public LinkedListNode() {
        data = 0;
        next = null;
    }

    public LinkedListNode(int val) {
        data = 0;
        next = null;
    }

    public static void  handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void printList(LinkedListNode head)  {
        LinkedListNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data);
            curNode = curNode.next;
        }
        System.out.println("");
    }


    public static LinkedListNode constructList(int numElements) {
        LinkedListNode head = null;
        int value = numElements;

        /*If numElements is 2, then list will be 1->2 */
        for (int i = 0; i < numElements; ++i) {
            LinkedListNode newNode = new LinkedListNode();
            newNode.data = value;
            newNode.next = head;
            head = newNode;
            --value;
        }

        return head;
    }




    /* 
    head: the the first element of the list
    k: which element from the end.
    length: number of elements in the list
    prevPp: element previous the kth element is returned here 
    Return value: kth element from end if it exists, null otherwise
    */
    public static LinkedListNode findKthNodeFromEnd(LinkedListNode head, int k, int length, LinkedListNode[] prevArray) {

        prevArray[0] = null;

        LinkedListNode n1 = head;
        LinkedListNode prev = null;
        for (int i = 1; i <= length; ++i) {
            if (i == length - k + 1) {
                prevArray[0] = prev;
                return n1;  /*n1 is the kth element from end. So return it*/
            }
            prev = n1;
            n1 = n1.next;
        }

        /*k value passed doesn't match with the linked list. So return null */
        return null;
    }


    /*
    head: first node of the linked list
    k: by how many positions the linked list should be rotated
    length: number of nodes in the linked list
    Return value: first node of the rotated linked list
    */
    public static LinkedListNode rotateList(LinkedListNode head, int k, int length) {
        LinkedListNode[] prevArray = new LinkedListNode[1];

        /*If there are 0 or 1 nodes in the linked list, then simply return*/
        if (length < 2)
            return head;

        /*If we shift by k times, then we get back the same linked list. So we 
        just have to shift k % length times*/
        k = k % length;

        /*If k is 0, then no need to shift*/
        if (k == 0)
            return head;

        /*Find the kth node from the end. If k = 1, then pivot will have
        the last node and prev will be the node previous to last node*/
        LinkedListNode pivot = findKthNodeFromEnd(head, k, length, prevArray);
        LinkedListNode prev = prevArray[0];

        /*Find the last node in the linked list*/
        LinkedListNode last = pivot;
        while (last.next != null)
            last = last.next;

        /*Make the last node point to head and the node previous to pivot
        point to null*/
        last.next = head;
        prev.next = null;

        /*pivot will be the new head*/
        return pivot;
    }


    public static void compareList(LinkedListNode head, int[] expectedResult, int length) {
        LinkedListNode curNode = head;
    
        for (int i = 0; i < length; ++i) {
            if (curNode == null)
                handleError();

            if (curNode.data != expectedResult[i]) 
                handleError();
        
            curNode = curNode.next; 
        }

        if (curNode != null)
            handleError();
    }   

}

class LinkedList_07_RotateList {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    public static void  handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void performTest(LinkedListNode head, int length, int numRotations, int[] expectedResult) {
        System.out.println("Num Rotations = " + numRotations);

        System.out.print("Before Rotating: ");
        LinkedListNode.printList(head);

        head = LinkedListNode.rotateList(head, numRotations, length);
        LinkedListNode.compareList(head, expectedResult, length);

        System.out.print("After  Rotating: ");
        LinkedListNode.printList(head); 

        System.out.println("________________________________________"); 
    }


    public static void test1() {
        int[] expectedResult = {1};
        int length = 1, numRotations = 1;

        /*list initially contains 1->null */
        LinkedListNode head = LinkedListNode.constructList(length);
        performTest(head, length, numRotations, expectedResult);
    }


    public static void test2() {
        int[] expectedResult = {5, 1, 2, 3, 4};
        int length = 5, numRotations = 1;

        /*list initially contains 1->2->3->4->5 */
        LinkedListNode head = LinkedListNode.constructList(length);
        performTest(head, length, numRotations, expectedResult);

    }


    public static void test3() {
        int[] expectedResult = {4, 5, 1, 2, 3};
        int length = 5, numRotations = 2;

        /*list initially contains 1->2->3->4->5 */
        LinkedListNode head = LinkedListNode.constructList(length);
        performTest(head, length, numRotations, expectedResult);

    }


    public static void test4() {
        int[] expectedResult = {2, 3, 4, 5, 1};
        int length = 5, numRotations = 4;

        /*list initially contains 1->2->3->4->5 */
        LinkedListNode head = LinkedListNode.constructList(length);
        performTest(head, length, numRotations, expectedResult);

    }

    public static void test5() {
        int[] expectedResult = {1, 2, 3, 4, 5};
        int length = 5, numRotations = 5;

        /*list initially contains 1->2->3->4->5 */
        LinkedListNode head = LinkedListNode.constructList(length);
        performTest(head, length, numRotations, expectedResult);

    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();

        System.out.println("Test passed");
    }

}

