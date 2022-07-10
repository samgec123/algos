package com.test.librariesandroad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Result {
    static class Node {
        int node;
        List<Node> connectedNodes;

        public Node(int node, List<Node> connectedNodes) {
            this.node = node;
            this.connectedNodes = connectedNodes;

        }

        public Node(int node) {
            this.node = node;
        }
    }

    /*
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */

    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        // Write your code here
        if (c_road >= c_lib) {
            return n * c_lib;
        }
        //create a graph to find connected components
        List<Node> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Node> connNodes = new ArrayList<>();
            Node node = new Node(i + 1, connNodes);
            graph.add(node);
        }
        for (List<Integer> edge : cities) {
            graph.get(edge.get(0) - 1).connectedNodes.add(new Node(edge.get(1) - 1));
            graph.get(edge.get(1) - 1).connectedNodes.add(new Node(edge.get(0) - 1));
        }
        //found connected components then number of connected edges in * c_road + number of connected components * c_lib
        int[] group = new int[n];
        boolean[] visited = new boolean[n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            group[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            dfs(graph, i, group, count++, visited);
        }
        Map<Integer, Integer> connectedComponents = new HashMap<>();
        for (int i = 0; i < group.length; i++) {
            if (connectedComponents.get(group[i]) == null) {
                connectedComponents.put(group[i], 1);
            } else {
                connectedComponents.put(group[i], connectedComponents.get(group[i]) + 1);
            }
        }
        long ans = 0;
        for (int num : connectedComponents.keySet()) {
            int connectedNodes = connectedComponents.get(num);
            if (connectedNodes == 1) {
                ans = ans + c_lib;
            } else {
                ans = ans + (long)(connectedComponents.get(num) - 1) * c_road + c_lib;
            }
        }
        return ans;

    }

    private static void dfs(List<Node> graph, int currentNode, int[] group, int count, boolean[] visited) {
        if (visited[currentNode]) {
            return;
        }
        visited[currentNode] = true;
        group[currentNode] = count;
        List<Node> connectedNodes = graph.get(currentNode).connectedNodes;
        for (int i = 0; i < connectedNodes.size(); i++) {
            dfs(graph, connectedNodes.get(i).node, group, count, visited);
        }
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        long result = Result.roadsAndLibraries(5, 6, 1, List.of(List.of(1, 2), List.of(3, 1), List.of(1, 4)));
        System.out.println(result);
    }
}
