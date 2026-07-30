package 리프_노드_수_최대화;

public class Solution {
    private long maxLeaves = 0;
    private int dist_limit;
    private int split_limit;

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 문제 예시 테스트
        int dist_limit = 3;
        int split_limit = 6;
        
        long result = sol.solution(dist_limit, split_limit);
        System.out.println("최대 리프 노드 수: " + result); // 출력: 6
    }

    public long solution(int dist_limit, int split_limit) {
        if (dist_limit == 0) return 1;

        this.dist_limit = dist_limit;
        this.split_limit = split_limit;
        
        dfs(1, 0, 1, 0);

        return maxLeaves;
    }

    private void dfs(long newNode, long leaves, long splits, long dists) {
        // 최대 리프 노드 갱신
        leaves += newNode;
        maxLeaves = Math.max(maxLeaves, leaves);

        // 만들 수 있는 최대 분배 노드
        long newDists = Math.min(newNode, dist_limit - dists);

        if (splits * 2 <= split_limit) {
            dfs(newDists * 2, leaves - newDists, splits*2, dists + newDists);
        }

        if (splits * 3 <= split_limit) {
            dfs(newDists * 3, leaves - newDists, splits*3, dists + newDists);
        }
    }
}

/*
AI 힌트

---

"분배도가 10^9 이하이므로 트리의 높이는 최대 30 정도밖에 안 됩니다. 
깊이별로 2-분배와 3-분배를 조합하는 작은 탐색 공간(DP/완전탐색) 속에서, 
한정된 dist_limit으로 리프를 가장 많이 남기는 그리디한 선택을 고민해보세요!"
*/