/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class CacheNode {
    public int key;
    public int value;
    public CacheNode prev;
    public CacheNode next;

    public CacheNode(int inputKey, int inputValue) {
        key = inputKey;
        value = inputValue;
        prev = null;
        next = null;
    }
}


class LruCache {
    public int maxSize; /*Maximum number of nodes that the cache can hold*/
    public int count; /*current number of nodes in the cache*/
    public CacheNode head; /*Head of the doubly linked list*/
    public CacheNode tail; /*Tail of the doubly linked list*/
    public HashMap<Integer, CacheNode> map;/*hash map*/


    public LruCache (int totalSize) {
        map = new HashMap<Integer, CacheNode>();
        head = null;
        tail = null;
        maxSize = totalSize;
    }


    /*
    key - key for the node that should be fetched from the cache
    */
    public CacheNode lruGet(int key) {
        CacheNode curNode;

        /*Get the node from the key using the hash table*/
        curNode = map.get(key);

        if (curNode == null)
            return null;

        /*If the node being fetched is at the head of the linked list,
        then simply return*/
        if (head == curNode)
            return curNode;

        /*
        The node being fetched is not at the front. So detach it from the
        linked list and add it to the beginning. If the node was removed from
        the tail, then update the tail
        */
        if (tail == curNode)
            tail = curNode.prev;

        curNode.prev.next = curNode.next;
        if (curNode.next != null)
            curNode.next.prev = curNode.prev;

        curNode.prev = null;
        curNode.next = head;
        head.prev = curNode;
        head = curNode;

        return curNode;
    }


    /*
    newItem - new node to be added to the LRU cache
    */
    public int lruAdd(CacheNode newItem) {
        if (count == maxSize) {
            /*The cache is full. So remove the last node from the 
            linked list*/
            CacheNode temp = tail;

            tail = tail.prev;

            if (tail == null)
                head = null;
            else
                tail.next = null;

            count--;

            /*Remove the last node from the hash table*/
            map.remove(temp.key);

        }

        /*Add the new node to the front of the linked list*/
        newItem.prev = null;
        newItem.next = head;

        if (head != null)
            head.prev = newItem;

        head = newItem;

        if (tail == null)
            tail = newItem;

        count++;

        /*Add the new node to the hash table*/
        map.put(newItem.key, newItem);

        return 0;
    }


}







class Application_03_LruCache {


    public static final int MAX_NUM_TESTS = 50;
    public static final int MAX_CACHE_SIZE = 20;


    public static void handleError() {
        System.out.println("Test Failed");
        System.exit(1);
    }


    public static int lruVerify(LruCache cache) {
        CacheNode curNode;

        /*If there are no items in the cache, then head and tail should be null*/
        if (cache.count == 0) {
            if (cache.head != null || cache.tail != null)
                handleError();
            return 0;
        }

        /*head.prev should be null*/
        if (cache.head.prev != null)
            handleError();

        /*tail.next should be null*/
        if (cache.tail.next != null)
            handleError();

        /*If there is only 1 item in the cache, then head and tail should be identical*/
        if (cache.count == 1) {
            if (cache.head != cache.tail)
                handleError();

            return 0;
        }

        /*Count the number of nodes in cache from the head and verify if it matches cache.count*/
        curNode = cache.head;
        int count = 0;
        while (curNode != null) {
            count++;
            curNode = curNode.next;
        }

        if (count != cache.count)
            handleError();


        /*Count the number of nodes in cache from the tail and verify if it matches cache.count*/
        curNode = cache.tail;
        count = 0;
        while (curNode != null) {
            count++;
            curNode = curNode.prev;
        }

        if (count != cache.count)
            handleError();

        return 0;
    }



    public static void test01(int maxSize) {
        LruCache cache = new LruCache(maxSize);
        int key = 0, value = 100;
        CacheNode newNode, curNode;
        Random randomGenerator = new Random();

        for ( int i = 0; i < MAX_NUM_TESTS; ++i) {

            /*Perform either the add or the get operation based on random number*/
            int randNum = randomGenerator.nextInt(2);

            if (randNum == 0) {
                /*Perform the add operation*/
                newNode = new CacheNode(key, value);
                key++;
                value++;
                cache.lruAdd(newNode);
            } else {
                /*Perform the get operation*/
                int searchKey = randomGenerator.nextInt(key + 3);
                curNode = cache.lruGet(searchKey);

                if (curNode != null) {
                    /*Since we just accessed the curNode in the cache, the head of cache
                    should refer to it*/
                    if (cache.head != curNode)
                        handleError();
                } else {
                    /*We didn't find the key in the cache. Verify that the key doesn't
                    exist in the cache*/
                    curNode = cache.head;
                    while (curNode != null) {
                        if (curNode.key == searchKey)
                            handleError();
                        curNode = curNode.next;
                    }
                }

            }


            lruVerify(cache);
        }

    }


    public static void main(String[] args)  {

        for (int maxSize = 1; maxSize <= 20; ++maxSize)
            test01(maxSize);

        System.out.println("Test passed ");
    }


}
