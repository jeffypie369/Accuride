package com.example.andy.accuride;

import java.util.*;
import java.io.*;

public class Dijkstra {
    public int timeTaken;
    public ArrayList<String> pathTaken;

    public Dijkstra() {
        this.timeTaken = -1;
        this.pathTaken = new ArrayList<String>();
    }

    public static void main(String args[]) throws IOException {
        Dijkstra dijkstra = new Dijkstra();
        String bv = "EW Buona Vista";
        String fc = "DT Fort Canning";
        dijkstra.run(bv, fc);
    }

    public void run(String startStation, String endStation) throws IOException {
        //Potential problem here because scanner always takes from my files. Cant be the case when its live.
        Scanner sc = new Scanner(
                new File("C:\\Users\\Andy\\Desktop\\MyProjects\\AndroidStudioProjects\\Accuride\\app\\src\\main\\java\\com\\example\\andy\\accuride\\EdgesTimeWait.txt")
        );
//        //Try to avoid using scanner
//        Scanner sc = new Scanner("com/example/andy/accuride/EdgesTimeWait.txt");
        int count = 0; 											 // count gives a unique value to each station
        ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
        HashMap<String, Integer> hmap = new HashMap<>();  		 // First hashmap for station name as key and count as value
        HashMap<Integer, String> hmap2 = new HashMap<>();		 // Second hashmap for count as key and station name as value
        int[] parents = new int[183]; 							 // parents array to track parent node (for path)
        for (int i = 0; i < 183; i++) {
            adjList.add(new ArrayList<>()); 					 // create adjList, 183 stations
        }

        // Obtain first 3 characters of each station. this is to recognize which line it is
        String initStationLine = startStation.substring(1,3);

        // The longest wait time for each line
        int initWaitTime =
                initStationLine.equals("EW ") ? 7
                        : initStationLine.equals("NS ") ? 7
                        : initStationLine.equals("NE ") ? 5
                        : initStationLine.equals("CC ") ? 7
                        : initStationLine.equals("DT ") ? 5
                        : initStationLine.equals("BP ") ? 8
                        : initStationLine.equals("STC") ? 6
                        : 7;

        while (sc.hasNextLine()) {
            // Adds all stations into hashmaps. If station is not in yet, add it
            String stationFrom = sc.nextLine();
            if (!hmap.containsKey(stationFrom)) {
                hmap.put(stationFrom, count);
                hmap2.put(count, stationFrom);
                count++;
            }
            String stationTo = sc.nextLine();
            if (!hmap.containsKey(stationTo)) {
                hmap.put(stationTo, count);
                hmap2.put(count, stationTo);
                count++;
            }
            int time = sc.nextInt();
            sc.nextLine();
            int fromKey = hmap.get(stationFrom);
            int toKey = hmap.get(stationTo);
            // Adds edges to adjList
            adjList.get(fromKey).add(new Edge(fromKey, toKey, time));
            adjList.get(toKey).add(new Edge(toKey, fromKey, time));
        }
        sc.close();

        int numStations = hmap.size();
        int inputFromKey = hmap.get(startStation);
        int inputToKey = hmap.get(endStation);
        int[] allTimeTaken = dfs_helper(numStations, inputFromKey, parents, adjList);
        parents[inputFromKey] = -1; // no parent

        this.timeTaken = allTimeTaken[inputToKey] + initWaitTime;

        printPath(inputToKey, parents, hmap2, 0);

//        System.out.println(this.timeTaken);
//
//        // To display path taken
//        Iterator itr = this.pathTaken.iterator();
//        while (itr.hasNext()) {
//            Object station = itr.next();
//            System.out.println(station);
//        }
    }

    private static int[] dfs_helper(int numStations, int inputFromKey, int[] parents,ArrayList<ArrayList<Edge>> adjList) {
        int[] timeFromSrc = new int[numStations];
        boolean[] visited = new boolean[numStations];
        timeFromSrc[inputFromKey] = 0;
        dfs(inputFromKey, timeFromSrc, visited, parents, adjList);
        return timeFromSrc;
    }

    private static int[] dfs(int from, int[] timeFromSrc, boolean[] visited, int[] parents, ArrayList<ArrayList<Edge>> adjList) {
        visited[from] = true;
        for (Edge edge : adjList.get(from)) {
            int to = edge.to;
            if (!visited[to] || (timeFromSrc[from] + edge.time) < timeFromSrc[to]) {
                parents[to] = from;
                timeFromSrc[to] = timeFromSrc[from] + edge.time;
                dfs(to, timeFromSrc, visited, parents, adjList);
            }
        }
        return timeFromSrc;
    }

    private void printPath(int to, int[] parents, HashMap<Integer, String> hmap2, int counter) {
        if (to == -1) {
            return;
        }
        counter++;
        printPath(parents[to], parents, hmap2, counter);
        this.pathTaken.add(hmap2.get(to));
    }

}
