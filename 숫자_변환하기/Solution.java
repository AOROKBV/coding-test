package 숫자_변환하기;

import java.util.Arrays;

public class Solution {
    private static final int DEFAULT = Integer.MAX_VALUE;

    public int solution(int x, int y, int n) {
        int[] dp = new int[y+1];
        Arrays.fill(dp, DEFAULT);

        dp[x] = 0;

        for (int i = x; i <= y; i++) {
            int times = dp[i];

            if (times == DEFAULT) {
                continue;
            }

            times++;

            if(i+n <= y) dp[i+n] = Math.min(times, dp[i+n]);
            if(i*2 <= y) dp[i*2] = Math.min(times, dp[i*2]);
            if(i*3 <= y) dp[i*3] = Math.min(times, dp[i*3]);
        }

        return dp[y] != DEFAULT ? dp[y] : -1;
    }
}
