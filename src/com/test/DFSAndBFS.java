package com.test;

import java.io.IOException;
import java.util.*;

class DFS {
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

    public static void main(String[] args) throws IOException {

        int tNodes = 20;
        int tEdges = 19;

        List<Integer> tFrom = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        List<Integer> tTo = List.of(1, 1, 3, 2, 5, 1, 1, 2, 7, 10, 3, 7, 8, 12, 6, 6, 10, 1, 8);

        int[] group = new int[tNodes];
        //create proper data structure which is arrays of pointers to node traversable
        List<Node> adjList = new ArrayList<>();
        for (int i = 0; i < tNodes; i++) {
            List<Node> connectedNodes = new ArrayList<>();
            adjList.add(new Node(i + 1, connectedNodes));
        }
        for (int i = 0; i < tFrom.size(); i++) {
            int fromNodeInt = tFrom.get(i);
            int toNodeInt = tTo.get(i);
            Node currNode = adjList.get(fromNodeInt - 1);
            Node toNode = adjList.get(toNodeInt - 1);
            currNode.connectedNodes.add(toNode);
            toNode.connectedNodes.add(currNode);
        }


        //Make a temporary graph
        List<Node> tmpAdjList = new ArrayList<>();
        for (Node node : adjList) {
            tmpAdjList.add((Node) node.clone());
        }

        int ans = 0;
        for (int i = 0; i < tFrom.size(); i++) {
            for (int j = 0; j < tNodes; j++) {
                group[j] = 1;
            }
            int count = 1;
            boolean[] dfsVisited = new boolean[tNodes];

            //remove first from the tmp
            int fromIndex = tFrom.get(i) - 1;
            int toIndex = tTo.get(i) - 1;
            tmpAdjList.get(fromIndex).connectedNodes.remove(tmpAdjList.get(toIndex));
            tmpAdjList.get(toIndex).connectedNodes.remove(tmpAdjList.get(fromIndex));

            for (int l = 0; l < tNodes; l++) {
                dfs(tmpAdjList, tmpAdjList.get(l).node, dfsVisited, group, count++);
            }
            //if every element in group is even
            Map<Integer, Integer> connectedComponents = new HashMap<>();
            for (int l = 0; l < group.length; l++) {
                if (connectedComponents.get(group[l]) == null) {
                    connectedComponents.put(group[l], 1);
                } else {
                    connectedComponents.put(group[l], connectedComponents.get(group[l]) + 1);
                }
            }
            boolean correctPartition = true;
            for (int num : connectedComponents.keySet()) {
                correctPartition = correctPartition && connectedComponents.get(num) % 2 == 0;
            }

            if (correctPartition) {
                adjList.clear();
                for (Node node : tmpAdjList) {
                    adjList.add((Node) node.clone());
                }
                ans++;
            } else {
                tmpAdjList.clear();
                for (Node node : adjList) {
                    tmpAdjList.add((Node) node.clone());
                }
            }
            count = 1;
        }
        System.out.println(ans);

    }

    private static void dfs(List<Node> adjList, int currentNode, boolean[] visitedList, int[] group, int groupCount) {
        Node node = adjList.get(currentNode - 1);
        if (visitedList[node.node - 1]) {
            return;
        }
        visitedList[node.node - 1] = true;
        group[node.node - 1] = groupCount;
        //  System.out.println("Visited:" + (node.node));
        for (int i = 0; i < node.connectedNodes.size(); i++) {
            dfs(adjList, node.connectedNodes.get(i).node, visitedList, group, groupCount);
        }
    }

    private static void bfs(List<Node> adjList, int currentNode, boolean[] visitedList) {
        Node node = adjList.get(currentNode - 1);
        Queue<Node> queue = new LinkedList<>();
        visitedList[node.node - 1] = true;
        for (int i = 0; i < node.connectedNodes.size(); i++) {
            queue.add(node.connectedNodes.get(i));
        }
        while (queue.peek() != null) {
            Node neighbour = queue.poll();
            if (!visitedList[neighbour.node - 1]) {
                visitedList[neighbour.node - 1] = true;
                for (int i = 0; i < neighbour.connectedNodes.size(); i++) {
                    queue.add(neighbour.connectedNodes.get(i));
                }
            }
        }
    }

}
