/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

class CircularQueue {
    private int head; /*index of first element in queue. -1 if queue is empty*/
    private int tail; /*index of last element in queue. -1 if queue is empty*/
    private int count; /*Number of elements currently present in the queue*/
    private int maxSize; /*Max number of elements that can be stored in queue*/
    private Integer buffer[]; /*buffer for storing elements */

    CircularQueue() {
        head = -1;
        tail = -1;
        count = 0;

        maxSize = 1;
        buffer = new Integer[maxSize];
    }

    public int add(Integer newElement) {
        if (count == maxSize) {
            /*If the queue is full, then resize the queue*/
            Integer[] newBuffer = new Integer [maxSize * 2];
            int oldPos = head;
            int newPos = 0;

            /*Copy from the old queue buffer to the new buffer*/
            while (newPos < count) {
                newBuffer[newPos] = buffer[oldPos];
                ++newPos;
                oldPos = (oldPos + 1) % maxSize;
            } 

            buffer = newBuffer;
            head = 0;
            tail = count - 1;
            maxSize = maxSize * 2;
        
        }

        /*Advance the tail and insert the element at the tail of the queue*/
        tail = (tail + 1) % maxSize;
        buffer[tail] = newElement;

        if (count == 0)
            head = tail;

        count++;
    
        /*Return the result code indicating success*/
        return 0;
    }

    public Integer remove() {
        /*Can't remove an item from an empty queue*/
        if (count <= 0)
            return null;

        Integer removedElement = buffer[head];

        if (head == tail) {
            /*There was only 1 item in the queue and that item has 
            been removed. So reinitialize head and tail to -1*/
            head = -1;
            tail = -1;
        } else {
            /*Advance the head to the next location*/
            head = (head + 1) % maxSize; 
        }

        count--;

        return removedElement;
    }

    public void printQueue() {
    
        System.out.print("Queue: ");
        int pos = head;
        for (int i = 0; i < count; ++i) {
            System.out.print(buffer[pos] + " ");
            pos = (pos + 1) % maxSize;
        }

        System.out.println("\n___________________________________________________\n");
    }

}




class Queue_04_CircularQueue {


    public static final int MAX_NUM_QUEUE_ELEMS = 10;

    public static void handleError() {
        System.out.println("Test failed");
        System.exit(1);
    }

    public static void test() {
        CircularQueue q = new CircularQueue();
        
        /*Test for different number of elements in the circular queue*/
        for (int numElems = 0; numElems <= MAX_NUM_QUEUE_ELEMS; ++numElems) {

            /*Add elements to the queue*/
            int i;
            for (i = 0; i < numElems; ++i) {
                q.add(new Integer(i));
                System.out.println("Inserting int element " + i);
                q.printQueue();
            }

            /*Remove all elements from the queue*/
            Integer result;
            for (i = 0; i < numElems; ++i) {
                result = q.remove();

                System.out.println("Removing element " + result + " from queue");
                q.printQueue();

                if (result.intValue() != i) {
                    handleError();
                }
            }

            /*The queue is now empty. So trying to remove an element should return an error*/
            result = q.remove();
            if (result != null) {
                handleError();
            }
        }

        System.out.println("Test passed ");
    }


    public static void main(String[] args)  {
        test();
    }

}


