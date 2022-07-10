package com.test.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Result {

    static class Node implements Cloneable {
        public Node(int node, List<Node> nodes) {
            this.node = node;
            this.connectedNodes = nodes;
        }

        public Node(int node) {
            this.node = node;
            this.connectedNodes = new ArrayList<>();
        }

        @Override
        public Object clone() {
            List<Node> listOfNodes = new ArrayList<>();
            Node newNode = new Node(this.node);
            for (Node tmpConnectedNode : this.connectedNodes) {
                Node new1 = new Node(tmpConnectedNode.node);
                newNode.connectedNodes.add(new1);
            }
            return newNode;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else if (other instanceof Node) {
                Node otherNode = (Node) other;
                if (this.node == otherNode.node) {
                    return true;
                }
            }
            return false;
        }

        int node;
        List<Node> connectedNodes;
    }

    /*
     * Complete the 'journeyToMoon' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY astronaut
     */


    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here
        int[] connectedComponent = new int[n];
        for (int i = 0; i < n; i++) {
            connectedComponent[i] = 1;
        }

        boolean[] dfsVisited = new boolean[n];

        List<Node> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Node> connectedNodes = new ArrayList<>();
            adjList.add(new Node(i, connectedNodes));
        }
        for (int i = 0; i < astronaut.size(); i++) {
            int fromNodeInt = astronaut.get(i).get(0);
            int toNodeInt = astronaut.get(i).get(1);
            Node currNode = adjList.get(fromNodeInt);
            Node toNode = adjList.get(toNodeInt);
            currNode.connectedNodes.add(toNode);
            toNode.connectedNodes.add(currNode);
        }
        int[] group = new int[n];
        for (int j = 0; j < n; j++) {
            group[j] = 1;
        }
        int count = 1;
        for (int l = 0; l < n; l++) {
            dfs(adjList, adjList.get(l).node, dfsVisited, group, count++);
        }

        int answer = 0;
        Map<Integer, Integer> connectedComponents = new HashMap<>();
        for (int l = 0; l < group.length; l++) {
            if (connectedComponents.get(group[l]) == null) {
                connectedComponents.put(group[l], 1);
            } else {
                connectedComponents.put(group[l], connectedComponents.get(group[l]) + 1);
            }
        }

        for (int num : connectedComponents.keySet()) {
            int val = connectedComponents.get(num);
            if (val > 1) {
                int calVal = val * (val - 1) / 2;
                answer += calVal;
            }
        }
        long total = (((long) n * (long) (n - 1)) / 2) - answer;
        return total;
    }

    private static void dfs(List<Node> adjList, int currentNode, boolean[] visitedList, int[] group, int groupCount) {
        Node node = adjList.get(currentNode);
        if (visitedList[node.node]) {
            return;
        }
        visitedList[node.node] = true;
        group[node.node] = groupCount;
        //  System.out.println("Visited:" + (node.node));
        for (int i = 0; i < node.connectedNodes.size(); i++) {
            dfs(adjList, node.connectedNodes.get(i).node, visitedList, group, groupCount);
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {

        long result = Result.journeyToMoon(100000, List.of(List.of(1, 2), List.of(3, 4)));
        System.out.println(result);
    }
}
