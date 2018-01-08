/*
Copyright (C) 2017 Interview Druid, Parineeth M. R.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*/

import java.util.*;

class Algo_Misc_06_TravelItinerary {



    public static void handleError() {
        System.out.println(  "Error occured");
        System.exit(1);
    }


    /*
    tickets: 2D array which stores the information about the tickets bought. 
        ticket[i][0] stores the starting city of the ith ticket
        ticket[i][1] stores the destination city of the ith ticket
        There should be no loop in the trip. 
        There should be atleast 1 ticket 
    Return value: ArrayList containing the names of cities in the order of travel 
    */
    public static ArrayList<String> reconstructTrip(String[][] tickets) {
        int numTickets = tickets.length;
        HashMap<String, String> nextHop = new HashMap<String, String>();
        HashSet<String> destinations = new HashSet<String>();

        /*Store the starting city (key) and destination city (value) in next hop 
        hash map. Store the destination cities in the destinations set*/
        int i;
        for (i = 0; i < numTickets; ++i) {
            nextHop.put(tickets[i][0], tickets[i][1]);
            destinations.add(tickets[i][1]);
        }

        /*Search the starting city of each ticket in the destinations set
        Only the first city of the entire trip will NOT be in destinations*/
        int startIndex = -1;
        for (i = 0; i < numTickets; ++i) {
            if (!destinations.contains(tickets[i][0])) {
                /*We didn't find the city in the destinations set.
                So this must be the first city of the entire trip*/
                startIndex = i;
                break;
            }
        }

        if (startIndex == -1)
            return null;

        ArrayList<String> result = new ArrayList<String>();

        /*add the first city of entire trip into the result*/
        result.add(tickets[startIndex][0]); 

        /*Search for the first city of the entire trip in the hash map*/
        String nextCity = nextHop.get(tickets[startIndex][0]);

        while (nextCity != null) {
            /*Store the destination city in the result*/
            result.add(nextCity); 

            /*make the destination city as the next starting city 
            and search for it in the hash map*/
            nextCity = nextHop.get(nextCity); 
        }

        return result;
    } 


    public static void verify(ArrayList<String> result) {
        String[] expectedResult = {"LA", "SF", "TOKYO", "BEIJING", "DELHI", "ROME"};

        int i = 0;
        for (String city : result) {
            if (!city.equals(expectedResult[i]))
                handleError();
            ++i; 
        }
    }

    public static void test() {
        String[][] tickets = {{"TOKYO", "BEIJING"}, {"LA", "SF"}, {"DELHI", "ROME"}, {"SF", "TOKYO"}, {"BEIJING", "DELHI"}};
        int numTickets = 5;

        for (int i = 0; i < numTickets; ++i) {
            System.out.println(  tickets[i][0] + " -> " + tickets[i][1] );
        }

        ArrayList<String> result = reconstructTrip(tickets);

        System.out.print(  "The order of visiting: ");
        for (String city : result) {
            System.out.print(city + " ");
        }
        System.out.println(  );

        verify(result);
    }


    public static void main(String[] args) {
        test();

        System.out.println(  "Test passed ");
    }

}
