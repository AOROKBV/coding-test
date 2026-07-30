package 땅_따먹기;

import java.util.ArrayDeque;

public class FirstSolution {
    private static final int MAX_COL = 4;
    class Node {
        public int score;
        public int currentRow;
        public int bannedIdx;

        public Node(int score, int currentRow, int bannedIdx) {
            this.score = score;
            this.currentRow = currentRow;
            this.bannedIdx = bannedIdx;
        }
    }

    int solution(int[][] land) {
        int answer = 0;
        var queue = new ArrayDeque<Node>();

        for (int idx = 0; idx < MAX_COL; idx++) {
            queue.addLast(new Node(land[0][idx], 1, idx));
        }

        while (!queue.isEmpty()) {
            var node = queue.removeFirst();

            for (int idx = 0; idx < MAX_COL; idx++) {
                if (idx == node.bannedIdx) continue;
                var newNode = new Node(node.score + land[node.currentRow][idx], node.currentRow+1, idx);
                if (newNode.currentRow < land.length) queue.addLast(newNode);
                answer = Math.max(answer, newNode.score);
            }
        }

        return answer;
    }
}

// 메모리 초과로 실패