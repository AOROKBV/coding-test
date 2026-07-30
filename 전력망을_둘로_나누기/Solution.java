package 전력망을_둘로_나누기;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    private Map<Integer, Set<Integer>> tree = new HashMap<>();
    private int[][] wires;

    public int solution(int n, int[][] wires) {
        this.wires = wires;

        for (int wireIdx = 0; wireIdx < wires.length; wireIdx++) {
            tree.putIfAbsent(wires[wireIdx][0], new HashSet<>());
            tree.get(wires[wireIdx][0]).add(wireIdx);

            tree.putIfAbsent(wires[wireIdx][1], new HashSet<>());
            tree.get(wires[wireIdx][1]).add(wireIdx);
        }

        int minDiff = Integer.MAX_VALUE;

        for (int bannedWire = 0; bannedWire < wires.length; bannedWire++) {
            int top = dfs(1, new HashSet<>(), bannedWire);
            int diff = Math.abs(((wires.length + 1) - top) - top);
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }

    private int dfs(int startNode, Set<Integer> visitedWire, int bannedWire) {
        var stack = new ArrayDeque<Integer>();
        stack.addLast(startNode);

        while (!stack.isEmpty()) {
            int node = stack.removeLast();

            for (int wireIdx : tree.get(node)) {
                if (visitedWire.contains(wireIdx)) continue;
                if (wireIdx == bannedWire) continue;

                visitedWire.add(wireIdx);
                stack.addLast(this.wires[wireIdx][0] == node ? 
                    this.wires[wireIdx][1] 
                    : this.wires[wireIdx][0]);
            }
        }

        return visitedWire.size() + 1;
    }
}
