// https://school.programmers.co.kr/learn/courses/30/lessons/340198
package 공원;

public class FinalSolution {
    private int[][] dp;
    private static final int PADDING = 1;

    public int solution(int[] mats, String[][] park) {
        int height = park.length;
        int width = park[0].length;
        dp = new int[height+PADDING][width+PADDING];
        int possibleMaxSize = -1;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int dpY = y + PADDING;
                int dpX = x + PADDING;

                if (!park[y][x].equals("-1")) {
                    dp[dpY][dpX] = 0;
                    continue;
                }

                dp[dpY][dpX] = Math.min(Math.min(dp[dpY][dpX-1], dp[dpY-1][dpX]), dp[dpY-1][dpX-1]) + 1;
                possibleMaxSize = Math.max(possibleMaxSize, dp[dpY][dpX]);
            }
        }

        int answer = -1;

        for (int i = 0; i < mats.length; i++) {
            if (possibleMaxSize >= mats[i]) {
                // possibleMaxSize 에 가장 가까운 최대 사이즈
                answer = Math.max(answer, mats[i]);
            }
        }

        return answer;
    }
}