/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Misc_13_GraphCycle {

    public static final int WHITE = 0;
    public static final int GRAY = 1;
    public static final int BLACK = 2;


    public static void handleError() {
        System.out.println(  "Test failed");
        System.exit(1);
    }

    /*Helper function that performs depth first search on the graph
    curNode: the current node that we are searching
    adjacencyTable: an ArrayList of ArrayLists. If there is an edge between node 0
    and node 5, then adjacencyTable[0] is an ArrayList which stores 5 in it.
    color: this array indicates color assigned to the nodes
    numNodes: total number of nodes in the graph
    Return value: true if cycle exists in directed graph, false otherwise
    */
    public static boolean dfs(int curNode, 
            ArrayList<ArrayList<Integer>> adjacencyTable, 
            int[] color, int numNodes) {
        boolean doesCycleExist = false;
        ArrayList<Integer> neighbors = adjacencyTable.get(curNode);

        /*Assign the gray color to the node indicating that we have 
        started processing this node*/
        color[curNode] = GRAY;

        for (int j = 0; j < neighbors.size(); ++j) {
            int curNeighbor = neighbors.get(j);

            /*If we find a neighboring node with the gray color, then we 
            have found a loop*/
            if (color[curNeighbor] == GRAY) {
                return true;
            }

            /*If the neighboring node has a white color, then perform 
            DFS on it*/
            if (color[curNeighbor] == WHITE) {
                doesCycleExist = dfs(curNeighbor, adjacencyTable, 
                            color, numNodes);
                if (doesCycleExist)
                    return true;
            }
        }

        /*Assign the node the black color to indicate that we have 
        finished processing it*/
        color[curNode] = BLACK;
        return false;   
    }

    /* Main function that checks if cycle is present or not
    adjacencyTable: an ArrayList of ArrayLists. If there is an edge between node 0
    and node 5, then adjacencyTable[0] is an ArrayList which stores 5 in it.
    numNodes: total number of nodes in the graph
    Return value: true if cycle is present in directed graph, false otherwise
    */
    public static boolean isCyclePresent(
                ArrayList<ArrayList<Integer>> adjacencyTable, 
                int numNodes) {
        int[] color = new int[numNodes];
        boolean doesCycleExist = false;

        /*Assign the white color to all the nodes to indicate that we have not 
        started processing the nodes*/
        int i;
        for (i = 0; i < numNodes; ++i)
            color[i] = WHITE;

        /*Go through all the nodes in the graph and perform DFS on the 
        nodes whose color is white*/
        for (i = 0; i < numNodes; ++i) {
            if (color[i] == WHITE) {
                doesCycleExist = dfs(i, adjacencyTable, 
                            color, numNodes);
                if (doesCycleExist) 
                    break;
            }
        }

        return doesCycleExist;
    }


    public static void test01() {
        /*Construct a graph without a cycle
        The edges in the graph are 0->1, 0->2, 0->3, 1->2, 1->3, 2->3, 4->1*/

        int numNodes = 5;

        ArrayList<ArrayList<Integer>> adjacencyTable = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < numNodes; ++i) {
            adjacencyTable.add(new ArrayList<Integer>());
        }

        adjacencyTable.get(0).add(1);
        adjacencyTable.get(0).add(2);
        adjacencyTable.get(0).add(3);

        adjacencyTable.get(1).add(2);
        adjacencyTable.get(1).add(3);
    
        adjacencyTable.get(2).add(3);

        adjacencyTable.get(4).add(1);
    
        boolean result = isCyclePresent(adjacencyTable, numNodes);

        if (result != false)
            handleError();

    }



    public static void test02() {
        /*Construct a graph with a cycle
        The edges in the graph are 0->1, 0->2, 0->3, 1->2, 1->3, 2->3, 3->4 and 4->2
        The cycle is 2->3, 3->4, 4->2
        */
        int numNodes = 5;

        ArrayList<ArrayList<Integer>> adjacencyTable = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < numNodes; ++i) {
            adjacencyTable.add(new ArrayList<Integer>());
        }

        adjacencyTable.get(0).add(1);
        adjacencyTable.get(0).add(2);
        adjacencyTable.get(0).add(3);

        adjacencyTable.get(1).add(2);
        adjacencyTable.get(1).add(3);
    
        adjacencyTable.get(2).add(3);

        adjacencyTable.get(3).add(4);

        adjacencyTable.get(4).add(2);
    
        boolean result = isCyclePresent(adjacencyTable, numNodes);

        if (result != true)
            handleError();

    }



    public static void main(String[] args) {
        test01();

        test02();

        System.out.println(  "Test passed");
    }

}
