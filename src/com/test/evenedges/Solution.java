package com.test.evenedges;

import java.io.IOException;
import java.util.*;

public class Solution {

    // Complete the evenForest function below.
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
        //find all the nodes with even edges to and fro
        List<Integer> count = new ArrayList<>();
        Map<Integer, Set<Integer>> connected = new HashMap<>();
        t_nodes--;
        for (int i = 0; i <= t_nodes; i++) {
            //count.set(i, 0);

            int node = i + 1;
            List<Integer> edges = new ArrayList<>();
            for (int j = 0; j < t_from.size(); j++) {
                if (t_from.get(j) == node) {

                    edges.add(t_to.get(j));
                }
                if (t_to.get(j) == node) {

                    edges.add(t_from.get(j));
                }
            }

            connected.put(node, new HashSet<>(edges));
        }
        int ans = 0;
        for (int i = 0; i <= t_nodes; i++) {
            //only consider nodes with even edges
            int node = i + 1;
            Set<Integer> nodes = connected.get(node);
            if (nodes.size() > 0 && nodes.size() % 2 == 0) {
                //i+1 contains even edges then a candidate to check further
                for (int num : nodes) {
                    int conNode = num;
                    Set<Integer> otherConnected = connected.get(conNode);
                    if (otherConnected.size() > 1 && otherConnected.size() % 2 == 1) {
                        ans++;
                        otherConnected.remove(conNode);
                        connected.put(conNode, otherConnected);
                        //nodes.remove(conNode);
                        Set<Integer> set = new HashSet<>();
                        set.addAll(nodes);
                        set.remove(conNode);
                        connected.put(node, set);
                    }
                }
            }
        }

        return ans;
    }


    public static void main(String[] args) throws IOException {

        int tNodes = 10;
        int tEdges = 9;

        List<Integer> tFrom = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> tTo = List.of(1, 1, 3, 2, 1, 2, 6, 8, 8);


        int res = evenForest(tNodes, tEdges, tFrom, tTo);
        System.out.println(res);

    }
}
