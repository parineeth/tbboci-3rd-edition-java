/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Misc_12_ConnectedComponents {

    public static void handleError() {
        System.out.println(  "Test failed");
        System.exit(1);
    }

    /* Helper function that performs depth first search on the graph
    curNode: the curNode that we are searching
    adjacencyTable: an ArrayList of ArrayLists. If there is an edge between node 0 
        and node 5, then adjacencyTable[0] is an ArrayList which stores 5 in it.
    isVisited: this array indicates if a node has already been visited or not
    numNodes: total number of nodes in the graph
    */
    public static void dfs(int curNode, 
                ArrayList<ArrayList<Integer>> adjacencyTable,
                boolean[] isVisited, int numNodes) {
        ArrayList<Integer> neighbors = adjacencyTable.get(curNode);

        isVisited[curNode] = true;

        /*Go through all the neighbors of the current node*/
        for (int j = 0; j < neighbors.size(); ++j) {
            int curNeighbor = neighbors.get(j);

            /*If the current neighbor has not been visited, then recursively 
            call dfs on it*/
            if (!isVisited[curNeighbor])
                dfs(curNeighbor, adjacencyTable, isVisited, numNodes);
        }

    }

    /*Main function to find number of connected components in an undirected graph
    adjacencyTable: an ArrayList of ArrayLists. If there is an edge between node 0 
        and node 5, then adjacencyTable[0] is an ArrayList which stores 5 in it. 
    numNodes: total number of nodes in the graph
    Return value: number of connected components in the graph
    */
    public static int connectedComponents(
                ArrayList<ArrayList<Integer>> adjacencyTable,
                int numNodes) {
        boolean[] isVisited = new boolean[numNodes];
        int i;

        /*Traverse through all the nodes in the graph and perform Depth First 
        Search. Each time we perform DFS on a node that has not been visited 
        so far, increment the number of connected components*/
        int count = 0;
        for (i = 0; i < numNodes; ++i) {
            if (!isVisited[i]) {
                dfs(i, adjacencyTable, isVisited, numNodes);
                ++count;
            }
        }

        return count;
    }


    public static void test01() {
        int numNodes = 4;

        ArrayList<ArrayList<Integer>> adjacencyTable = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < numNodes; ++i) {
            adjacencyTable.add(new ArrayList<Integer>());
        }

        /*Create an undirected graph where there is only one connected component*/
        /*The edges in the graph are 0-1, 0-2, 0-3, 1-2, 1-3 and 2-3*/
        adjacencyTable.get(0).add(1);
        adjacencyTable.get(0).add(2);
        adjacencyTable.get(0).add(3);

        adjacencyTable.get(1).add(2);
        adjacencyTable.get(1).add(3);
    
        adjacencyTable.get(2).add(3);
    
        int result = connectedComponents(adjacencyTable, numNodes);

        if (result != 1)
            handleError();


    }



    public static void test02() {
        int numNodes = 8;

        ArrayList<ArrayList<Integer>> adjacencyTable = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < numNodes; ++i) {
            adjacencyTable.add(new ArrayList<Integer>());
        }

        /*
        Create an undirected graph where there are 3 connected component
        The edges in the graph are 0-1, 0-2, 0-3, 
        4-5, 5-6 
        7-7
        */

        adjacencyTable.get(0).add(1);
        adjacencyTable.get(0).add(2);
        adjacencyTable.get(0).add(3);

        adjacencyTable.get(4).add(5);
        adjacencyTable.get(5).add(6);
    
        adjacencyTable.get(7).add(7);

        int result = connectedComponents(adjacencyTable, numNodes);

        if (result != 3)
            handleError();

    }


    public static void test03() {
        int numNodes = 8;

        /*Create a graph with 8 nodes and no edges. So there will be 8 components */
        ArrayList<ArrayList<Integer>> adjacencyTable = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < numNodes; ++i) {
            adjacencyTable.add(new ArrayList<Integer>());
        }


        int result = connectedComponents(adjacencyTable, numNodes);

        if (result != 8)
            handleError();

    }



    public static void main(String[] args) {
        test01();

        test02();

        test03();

        System.out.println(  "Test passed");

    }

}
