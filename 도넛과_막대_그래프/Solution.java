package 도넛과_막대_그래프;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class Solution {
    Map<Integer, Node> nodeMap = new HashMap<>();

    static class Node {
        int number;
        List<Edge> edges = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    static class Edge {
        int number;
        Node to;

        public Edge(int number, Node to) {
            this.number = number;
            this.to = to;
        }
    }

    static class TotalNodesEdges {
        int nodes = 0;
        int edges = 0;

        public TotalNodesEdges() {}

        public TotalNodesEdges(int nodes, int edges) {
            this.nodes = nodes;
            this.edges = edges;
        }
    }

    private TotalNodesEdges dfs(Node node, boolean[] visited, TotalNodesEdges totals) {
        totals.nodes++;
        
        boolean visitedBefore = false;

        for (Edge edge : node.edges) {
            if (visited[edge.number]) {
                visitedBefore = true;
                continue;
            }
            visited[edge.number] = true;
            totals.edges++;
            dfs(edge.to, visited, totals);
        }

        if (visitedBefore) {
            totals.nodes--;
        }

        return totals;
    }

    public int[] solution(int[][] edges) {
        var reachable = new HashSet<Node>();
        int totalNodes = 0;
        Node 정점;

        for (int i = 0; i < edges.length; i++) {
            totalNodes = Math.max(Math.max(edges[i][0], edges[i][1]), totalNodes);

            var finalI = i;
            var originNode = nodeMap.computeIfAbsent(edges[i][0], k -> new Node(edges[finalI][0]));
            var destNode = nodeMap.computeIfAbsent(edges[i][1], k -> new Node(edges[finalI][1]));
            var edge = new Edge(i, destNode);
            originNode.edges.add(edge);
            reachable.add(destNode);
        }

        정점 = nodeMap.values().stream()
            .filter(node -> !reachable.contains(node) && node.edges.size() >= 2)
            .findFirst()
            .orElse(null);

        int donuts = 0;
        int bars = 0;
        int eights = 0;

        for (var edge : 정점.edges) {
            var totals = dfs(edge.to, new boolean[edges.length], new TotalNodesEdges());

            var n = totals.nodes;

            if (totals.edges == n) {
                // 도넛 그래프
                donuts++;
            } else if (totals.edges == n-1) {
                // 막대 그래프
                bars++;
            } else {
                // 8자 그래프
                eights++;
            }
        }

        return new int[]{정점.number, donuts, bars, eights};
    }
}