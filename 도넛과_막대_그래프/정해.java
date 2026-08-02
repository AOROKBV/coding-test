package 도넛과_막대_그래프;

public class 정해 {
    public int[] solution(int[][] edges) {
        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        int[] inDegree = new int[maxNode + 1];
        int[] outDegree = new int[maxNode + 1];

        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }

        int createdNode = 0;
        int donuts = 0;
        int bars = 0;
        int eights = 0;

        // 1. 생성된 정점 찾기
        for (int i = 1; i <= maxNode; i++) {
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                createdNode = i;
                break;
            }
        }

        // 2. 각 그래프의 특징 정점 카운트
        for (int i = 1; i <= maxNode; i++) {
            if (i == createdNode) continue;
 
            if (outDegree[i] == 0 && inDegree[i] > 0) {
                bars++; // 막대 그래프의 끝점
            } else if (outDegree[i] == 2 && inDegree[i] >= 2) {
                eights++; // 8자 그래프의 교차점
            }
        }

        // 3. 도넛 그래프 = 전체 그래프 수 - (막대 + 8자)
        donuts = outDegree[createdNode] - (bars + eights);

        return new int[]{createdNode, donuts, bars, eights};
    }
}
