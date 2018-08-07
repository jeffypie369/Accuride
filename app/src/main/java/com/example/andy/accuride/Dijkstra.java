package com.example.andy.accuride;

import android.content.Context;
import android.content.ContextWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Dijkstra extends ContextWrapper {

    int fastestTime;
    int leastTransfers;
    int numOfTransfersFastest;
    int numOfTransfersLeast;
    ArrayList<String> fastestPath;
    ArrayList<String> leastTransferspath;

    public Dijkstra(Context context) {
        super(context);
        this.fastestTime = -1;
        this.leastTransfers = -1;
        this.numOfTransfersFastest = -1;
        this.numOfTransfersLeast = -1;
        this.fastestPath = new ArrayList<>();
        this.leastTransferspath = new ArrayList<>();
    }

    public void run(String inputFrom, String inputTo) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("EdgesTimeWait.txt")));
        int count = 0; // count gives a unique value to each station
        ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
        // first hashmap for station name as key and count as value
        HashMap<String, Integer> hmap = new HashMap<>();
        // second hashmap for count as key and station name as value
        HashMap<Integer, String> hmap2 = new HashMap<>();
        int[] parents = new int[183]; // parents array to track parent node (for path)
        String[] lines = new String[9];
        lines[0] = "EW ";
        lines[1] = "NS ";
        lines[2] = "NE ";
        lines[3] = "CC ";
        lines[4] = "DT ";
        lines[5] = "BP ";
        lines[6] = "ST ";
        lines[7] = "PT ";
        lines[8] = "CG ";
        for (int i = 0; i < 183; i++) {
            adjList.add(new ArrayList<>()); // create adjList, 183 stations
        }

//        String inputFrom = br.readLine();
//        String inputTo = br.readLine();

        String stationFrom;

        while ((stationFrom = br.readLine()) != null) {
            // add all stations into hashmaps. if station is not in yet, add it
            if (!hmap.containsKey(stationFrom)) {
                hmap.put(stationFrom, count);
                hmap2.put(count, stationFrom);
                count++;
            }
            String stationTo = br.readLine();
            if (!hmap.containsKey(stationTo)) {
                hmap.put(stationTo, count);
                hmap2.put(count, stationTo);
                count++;
            }
            int time = Integer.parseInt(br.readLine());
            //br.readLine();
            int fromKey = hmap.get(stationFrom);
            int toKey = hmap.get(stationTo);
            // add edges to adjList
            adjList.get(fromKey).add(new Edge(fromKey, toKey, time));
            adjList.get(toKey).add(new Edge(toKey, fromKey, time));
        }
        br.close();

        // Calculate fastest route
        int numStations = hmap.size();
        ArrayList<Integer> possibleStarts = possibleLines(inputFrom, lines, hmap, hmap2);
        ArrayList<Integer> possibleEnds = possibleLines(inputTo, lines, hmap, hmap2);
        int[][] possibleParents = new int[30][183];
        Map.Entry<Integer, StartEnd> allTimeTaken = dfs_helper(numStations, parents, adjList, possibleStarts, possibleEnds, possibleParents);
        int shortestTime = allTimeTaken.getKey();
        StartEnd confirmed = allTimeTaken.getValue();
        int inputFromKey = confirmed.start;
        int inputToKey = confirmed.end;
        String initStation = hmap2.get(confirmed.start);
        possibleParents[5*confirmed.iValue + confirmed.jValue][inputFromKey] = -1; // no parent
        int initWaitTime = getInitWaitTime(initStation.substring(0, 3));
        //System.out.println("Time taken: " + (shortestTime + initWaitTime) + " minutes");
        ArrayList<String> pathForAndy = new ArrayList<>();
        recordPath(inputToKey, possibleParents[5*confirmed.iValue + confirmed.jValue], hmap2, pathForAndy);
        //System.out.println(pathForAndy); // ARRAYLIST OF STRING FOR FASTEST TRANSFERS

        this.fastestTime = shortestTime + initWaitTime;
        this.fastestPath = pathForAndy;

        // Calculate least transfers
        int numTransfers = numTransfers(pathForAndy, 0);

        this.numOfTransfersFastest = numTransfers;
        //System.out.println("Number of transfers: " + numTransfers);
        leastTransfersHelper(adjList, hmap2, possibleStarts, possibleEnds);
        // ARRAYLIST OF STRING FOR LEAST TRANSFERS IS INSIDE METHOD FOR LeastTransfersHelper
    }

//    public static void main(String args[]) throws IOException {
//        Dijkstra dijkstra = new Dijkstra();
//        dijkstra.run();
//    }


    private static Map.Entry<Integer, StartEnd> dfs_helper(int numStations, int[] parents, ArrayList<ArrayList<Edge>> adjList, ArrayList<Integer> possibleStarts, ArrayList<Integer> possibleEnds, int[][] possibleParents) {
        TreeMap<Integer, StartEnd> fastest = new TreeMap<>();
        for (int i = 0; i < possibleStarts.size(); i++) {
            for (int j = 0; j < possibleEnds.size(); j++) {
                StartEnd startAndEnd = new StartEnd(possibleStarts.get(i), possibleEnds.get(j), i, j);
                int[] timeFromSrc = new int[numStations];
                boolean[] visited = new boolean[numStations];
                timeFromSrc[possibleStarts.get(i)] = 0;
                dfs(possibleStarts.get(i), timeFromSrc, visited, parents, adjList);
                for (int k = 0; k < parents.length; k++) {
                    possibleParents[5*i+j][k] = parents[k];
                }
                fastest.put(timeFromSrc[possibleEnds.get(j)], startAndEnd);
            }
        }
        return fastest.pollFirstEntry();
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

    private static int getInitWaitTime(String initStationLine) {
        return initStationLine.equals("EW ") ? 6
                : initStationLine.equals("NS ") ? 6
                : initStationLine.equals("NE ") ? 6
                : initStationLine.equals("CC ") ? 6
                : initStationLine.equals("DT ") ? 6
                : initStationLine.equals("BP ") ? 6
                : initStationLine.equals("ST ") ? 6
                : initStationLine.equals("CG ") ? 12
                : 7;
    }

    private static void recordPath(int to, int[] parents, HashMap<Integer, String> hmap2, ArrayList<String> path) {
        if (to == -1) {
            return;
        }
        recordPath(parents[to], parents, hmap2, path);
        path.add(hmap2.get(to));
    }

    public void leastTransfersHelper(ArrayList<ArrayList<Edge>> adjList, HashMap<Integer, String> hmap2, ArrayList<Integer> possibleStarts, ArrayList<Integer> possibleEnds) {
        boolean[] visited = new boolean[183];
        ArrayList<Integer> path = new ArrayList<>();
        HashMap<Integer, ArrayList<ArrayList<Integer>>> allPaths = new HashMap<>(); // arraylist of paths
        for (int m = 0; m < 5; m++) {
            allPaths.put(m, new ArrayList<>());
        }
        for (int i = 0; i < possibleStarts.size(); i++) {
            for (int j = 0; j < possibleEnds.size(); j++) {
                int start = possibleStarts.get(i);
                int end = possibleEnds.get(j);
                leastTransfers(start, end, adjList, hmap2, allPaths, visited, path, 0);
            }
        }
        for (int j = 0; j < 5; j++) {
            if (!allPaths.get(j).isEmpty()) {
                int least = j;
                ArrayList<ArrayList<Integer>> possiblePaths = allPaths.get(j);
                TreeMap<Integer, Integer> durations = new TreeMap<>();
                ArrayList<ArrayList<Integer>> copiedPaths = new ArrayList<>();
                for (int k = 0; k < possiblePaths.size(); k++) {
                    copiedPaths.add(copyPath(possiblePaths.get(k)));
                    durations.put(getDuration(possiblePaths.get(k), adjList, 0), k);
                }
                System.out.println("Least Transfers: " + least);
                Map.Entry<Integer, Integer> lowest = durations.pollFirstEntry();
                int index = lowest.getValue();
                //System.out.println("Time taken: " + (lowest.getKey() + getInitWaitTime(hmap2.get(copiedPaths.get(index).get(0)).substring(0, 3))));
                ArrayList<String> pathForAndy = new ArrayList<>();
                for (int l = 0; l < copiedPaths.get(index).size(); l++) {
                    int stationInt = copiedPaths.get(index).get(l);
                    String stationString = hmap2.get(stationInt);
                    pathForAndy.add(stationString);
                }
                //System.out.println(pathForAndy); // ARRAYLIST OF STRING FOR LEAST TRANSFERS
                this.leastTransfers = lowest.getKey() + getInitWaitTime(hmap2.get(copiedPaths.get(index).get(0)).substring(0, 3));
                this.leastTransferspath = pathForAndy;
                this.numOfTransfersLeast = least;

                return;
            }
        }
    }

    public static void leastTransfers(int start, int end, ArrayList<ArrayList<Edge>> adjList, HashMap<Integer, String> hmap2, HashMap<Integer, ArrayList<ArrayList<Integer>>> allPaths, boolean[] visited, ArrayList<Integer> path, int numTransfers) {
        if (numTransfers > 4) {
            return;
        }
        ArrayList<Edge> neighbours = adjList.get(start);
        for (int k = 0; k < neighbours.size(); k++) {
            Edge nb = neighbours.get(k);
            if (nb.to == end && numTransfers < 5 && path.size() == 0) {
                path.add(start);
                path.add(end);
                allPaths.get(numTransfers).add(path);
                return;
            }
            if (nb.to == end && numTransfers < 5) {
                path.add(end);
                allPaths.get(numTransfers).add(path);
                return;
            }
        }
        if (path.size() == 0) {
            for (int i = 0; i < neighbours.size(); i++) {
                Edge nb = neighbours.get(i);
                if(changeLinePresent(nb, hmap2)) {
                    continue;
                } else if (visited[nb.to] == false) {
                    boolean[] clonedVisited = copyVisited(visited);
                    ArrayList<Integer> clonedPath = copyPath(path);
                    clonedVisited[nb.from] = true;
                    clonedVisited[nb.to] = true;
                    clonedPath.add(nb.from);
                    clonedPath.add(nb.to);
                    leastTransfers(nb.to, end, adjList, hmap2, allPaths, clonedVisited, clonedPath, numTransfers);
                } else {
                    continue;
                }
            }
        } else {
            for (int j = 0; j < neighbours.size(); j++) {
                Edge nb = neighbours.get(j);
                if (visited[nb.to] == false) {
                    if (changeLinePresent(nb, hmap2)) {
                        boolean[] clonedVisited = copyVisited(visited);
                        ArrayList<Integer> clonedPath = copyPath(path);
                        clonedVisited[nb.to] = true;
                        clonedPath.add(nb.to);
                        leastTransfers(nb.to, end, adjList, hmap2, allPaths, clonedVisited, clonedPath, numTransfers+1);
                    } else if (!changeLinePresent(nb, hmap2)) {
                        boolean[] clonedVisited = copyVisited(visited);
                        ArrayList<Integer> clonedPath = copyPath(path);
                        clonedVisited[nb.to] = true;
                        clonedPath.add(nb.to);
                        leastTransfers(nb.to, end, adjList, hmap2, allPaths, clonedVisited, clonedPath, numTransfers);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public static boolean changeLinePresent(Edge e, HashMap<Integer, String> hmap2) {
        int first = e.from;
        int second = e.to;
        String fromLine = hmap2.get(first).substring(0,3);
        String toLine = hmap2.get(second).substring(0,3);
        if (!fromLine.equals(toLine)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean[] copyVisited(boolean[] visited) {
        boolean[] newVisited = new boolean[183];
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == true) {
                newVisited[i] = true;
            } else {
                newVisited[i] = false;
            }
        }
        return newVisited;
    }

    public static ArrayList<Integer> copyPath(ArrayList<Integer> path) {
        ArrayList<Integer> newPath = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            newPath.add(null);
        }
        for (int j = 0; j < path.size(); j++) {
            int index = path.get(j);
            newPath.set(j, index);
        }
        return newPath;
    }

    public static int getDuration(ArrayList<Integer> possiblePath, ArrayList<ArrayList<Edge>> adjList, int duration) {
        if (possiblePath.size() == 1) {
            return duration;
        } else {
            int time = 0;
            ArrayList<Edge> list = adjList.get(possiblePath.get(0));
            for (Edge e : list) {
                if (e.to == possiblePath.get(1)) {
                    time = e.time;
                }
            }
            possiblePath.remove(0);
            return getDuration(possiblePath, adjList, duration+time);
        }
    }

    private static int numTransfers(ArrayList<String> path, int counter) {
        for (int i = 1; i < path.size(); i++) {
            String child = path.get(i).substring(3);
            String parent = path.get(i-1).substring(3);
            if (child.equals(parent)) {
                counter++;
            }
        }
        return counter;
    }

    private static ArrayList<Integer> possibleLines(String station, String[] lines, HashMap<String, Integer> hmap, HashMap<Integer, String> hmap2) {
        ArrayList<Integer> possible = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String fullName = lines[i] + station;
            if (hmap.containsKey(fullName)) {
                int fullNameKey = hmap.get(fullName);
                if (hmap2.containsKey(fullNameKey)) {
                    possible.add(fullNameKey);
                }
            } else {
                continue;
            }
        }
        return possible;
    }

//	private static void printPath(int to, int[] parents, HashMap<Integer, String> hmap2, int counter, ArrayList<String> path) {
//		if (to == -1) {
//			return;
//		}
//		counter++;
//		printPath(parents[to], parents, hmap2, counter, path);
//		if (counter == 1) { // to get rid of "->" for the last station
//			System.out.print(hmap2.get(to));
//			path.add(hmap2.get(to));
//		} else {
//			System.out.print(hmap2.get(to) + "->");
//			path.add(hmap2.get(to));
//		}
//	}

}


//        private void run() throws IOException {
//        Scanner sc = new Scanner(new File("EdgesTimeWait.txt")); // reads text file
//        int count = 0; // count gives a unique value to each station
//        ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
//        // first hashmap for station name as key and count as value
//        HashMap<String, Integer> hmap = new HashMap<>();
//        // second hashmap for count as key and station name as value
//        HashMap<Integer, String> hmap2 = new HashMap<>();
//        int[] parents = new int[183]; // parents array to track parent node (for path)
//        String[] lines = new String[9];
//        lines[0] = "EW ";
//        lines[1] = "NS ";
//        lines[2] = "NE ";
//        lines[3] = "CC ";
//        lines[4] = "DT ";
//        lines[5] = "BP ";
//        lines[6] = "ST ";
//        lines[7] = "PT ";
//        lines[8] = "CG ";
//        for (int i = 0; i < 183; i++) {
//            adjList.add(new ArrayList<>()); // create adjList, 183 stations
//        }
//        String inputFrom = sc.nextLine();
//        String inputTo = sc.nextLine();
//
//        while (sc.hasNextLine()) {
//            // add all stations into hashmaps. if station is not in yet, add it
//            String stationFrom = sc.nextLine();
//            if (!hmap.containsKey(stationFrom)) {
//                hmap.put(stationFrom, count);
//                hmap2.put(count, stationFrom);
//                count++;
//            }
//            String stationTo = sc.nextLine();
//            if (!hmap.containsKey(stationTo)) {
//                hmap.put(stationTo, count);
//                hmap2.put(count, stationTo);
//                count++;
//            }
//            int time = sc.nextInt();
//            sc.nextLine();
//            int fromKey = hmap.get(stationFrom);
//            int toKey = hmap.get(stationTo);
//            // add edges to adjList
//            adjList.get(fromKey).add(new Edge(fromKey, toKey, time));
//            adjList.get(toKey).add(new Edge(toKey, fromKey, time));
//        }
//        sc.close();
//
//        // Calculate fastest route
//        int numStations = hmap.size();
//        ArrayList<Integer> possibleStarts = possibleLines(inputFrom, lines, hmap, hmap2);
//        ArrayList<Integer> possibleEnds = possibleLines(inputTo, lines, hmap, hmap2);
//        int[][] possibleParents = new int[30][183];
//        Map.Entry<Integer, StartEnd> allTimeTaken = dfs_helper(numStations, parents, adjList, possibleStarts, possibleEnds, possibleParents);
//        int shortestTime = allTimeTaken.getKey();
//        StartEnd confirmed = allTimeTaken.getValue();
//        int inputFromKey = confirmed.start;
//        int inputToKey = confirmed.end;
//        String initStation = hmap2.get(confirmed.start);
//        possibleParents[5*confirmed.iValue + confirmed.jValue][inputFromKey] = -1; // no parent
//        int initWaitTime = getInitWaitTime(initStation.substring(0, 3));
//        System.out.println("Time taken: " + (shortestTime + initWaitTime) + " minutes");
//        ArrayList<String> pathForAndy = new ArrayList<>();
//        recordPath(inputToKey, possibleParents[5*confirmed.iValue + confirmed.jValue], hmap2, pathForAndy);
//        System.out.println(pathForAndy); // ARRAYLIST OF STRING FOR FASTEST TRANSFERS
//
//        // Calculate least transfers
//        int numTransfers = numTransfers(pathForAndy, 0);
//        System.out.println("Number of transfers: " + numTransfers);
//        leastTransfersHelper(adjList, hmap2, possibleStarts, possibleEnds);
//        // ARRAYLIST OF STRING FOR LEAST TRANSFERS IS INSIDE METHOD FOR LeastTransfersHelper
//    }