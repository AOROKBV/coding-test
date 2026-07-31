package 비밀_코드_해독;

class Solution {
    int answer = 0;

    public int solution(int n, int[][] q, int[] ans) {
        dfs(1, 0, new int[5], n, q, ans);
        return answer;
    }

    private void dfs(int start, int depth, int[] comb, int n, int[][] q, int[] ans) {
        if (depth == 5) {
            if (isValid(comb, q, ans)) answer++;
            return;
        }

        for (int i = start; i <= n; i++) {
            comb[depth] = i;
            dfs(i + 1, depth + 1, comb, n, q, ans);
        }
    }

    private boolean isValid(int[] comb, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int cnt = 0;
            for (int num : comb) {
                for (int target : q[i]) {
                    if (num == target) cnt++;
                }
            }
            if (cnt != ans[i]) return false;
        }
        return true;
    }
}