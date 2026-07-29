package 예산;

import java.util.Arrays;

public class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);

        int maxD = 0;
        int currentTotal = 0;

        for (int dep : d) {
            currentTotal += dep;

            if (budget < currentTotal) {
                break;
            }

            maxD += 1;
        }

        return maxD;
    }
}
