/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class LinkedListNode {
    
    public char data;
    public LinkedListNode next;

    public LinkedListNode() {
        next = null;
    }

    public LinkedListNode (char val) {
        data = val;
        next = null;
    }


    public static LinkedListNode constructList(String str) {
        LinkedListNode head = null;
        int numElements = str.length();

        for (int i = 0; i < numElements; ++i) {
            LinkedListNode newNode = new LinkedListNode(str.charAt(numElements - 1 - i));
            newNode.next = head;
            head = newNode;
        }

        return head;
    }


    /* 
    head: first element in the list 
    Return value: first element of the reversed link list
    */
    public static LinkedListNode reverseLinkedList(LinkedListNode head) {

        LinkedListNode curNode = head;
        LinkedListNode prevNode = null;

        while (curNode != null) {
            /*Store the next node in a temporary variable*/
            LinkedListNode tempNode = curNode.next;

            /*Reverse the link so that current node refers to the previous node*/
            curNode.next = prevNode;

            /*Update the previous node to the current node */
            prevNode = curNode;

            /*Proceed to the next node in the original list*/
            curNode = tempNode;
        }

        /*
        Once the list has been reversed, prevNode will be
        refering to the new head. So return it
        */
        return prevNode;
    }



    public static void printList(LinkedListNode head) {
        LinkedListNode curNode = head;

        while (curNode != null) {
            System.out.print(curNode.data);
            curNode = curNode.next;
        }
        
    }


    /* 
    head: first node of linked list.
    Returns: true if linked list is a palindrome, false otherwise
    */
    public static boolean isListPalindrome(LinkedListNode head) {
        if (head == null)
            return false;

        /*Advance p by two nodes and q by one node in each loop.
        So when p reaches the end of linked list, q will refer to middle 
        of the linked list */
        LinkedListNode p = head;
        LinkedListNode q = head;
        int length = 0;
        while (p != null) {
            ++length;
            p = p.next;
            if (p != null) {
                ++length;
                p = p.next;
            }

            if (p != null) {
                q = q.next;
            }
        }

        /*Reverse the second half of the linked list*/
        LinkedListNode r = reverseLinkedList(q.next);
        LinkedListNode temp = r;
        p = head;

        /*Compare first half with reverse of second half*/
        boolean isPalindrome = true;
        for (int i = 0; i < length / 2; ++i) {
            if (p.data != r.data) {
                isPalindrome = false;
                break;
            }
            p = p.next;
            r = r.next;
        }

        /*Reverse the second half of linked list to get back original 
        linked list*/
        reverseLinkedList(temp); 

        return isPalindrome;
    }   

    
}



class LinkedList_13_Palindrome {

    public static final int MAX_NUM_ELEMENTS_IN_LIST = 10;

    private static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    private static void test(String str, boolean expectedResult) {

        /*Construct the list based on the given string*/
        LinkedListNode head = LinkedListNode.constructList(str); 

        /*Check if list is palindrome*/
        boolean result = LinkedListNode.isListPalindrome(head);

        LinkedListNode.printList(head);
        if (result)
            System.out.println(" ----> is palindrome ");
        else
            System.out.println(" ----> is not palindrome ");

        /*Verify the result*/
        if (result != expectedResult)
            handleError();

        System.out.println("______________________________________");
    }

    public static void main(String[] args) {
        test("a", true);
        test("abba", true);
        test("level", true);
        test("Hello", false); 

        System.out.println("Test passed");
    }

}











