package 땅_따먹기;

import java.util.Arrays;

public class SecondSolution {
    private static final int MAX_COL = 4;

    int solution(int[][] land) {
        int[][] dp = new int[land.length][MAX_COL];

        for (int idx = 0; idx < MAX_COL; idx++) {
            dp[0][idx] = land[0][idx];
        }

        for (int row = 1; row < land.length; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                int maxPrev = 0;

                for (int beforeCol = 0; beforeCol < MAX_COL; beforeCol++) {
                    if (beforeCol == col)
                        continue;
                    maxPrev = Math.max(maxPrev, dp[row-1][beforeCol]);
                }

                dp[row][col] = Math.max(dp[row][col], maxPrev);
            }
        }

        return Arrays.stream(dp[land.length - 1]).max().orElse(0);
    }
}