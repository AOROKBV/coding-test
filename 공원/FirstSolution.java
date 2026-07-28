// https://school.programmers.co.kr/learn/courses/30/lessons/340198
package 공원;

import java.util.HashSet;
import java.util.Set;

public class FirstSolution {
    private int[][] dp;

    public int solution(int[] mats, String[][] park) {
        int height = park.length;
        int width = park[0].length;
        dp = new int[height][width];

        Set<Integer> matWidths = new HashSet<>();

        for (int matWidth : mats) {
            matWidths.add(matWidth);
        }

        int possibleMaxSize = -1;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!park[y][x].equals("-1")) {
                    dp[y][x] = 0;
                    continue;
                }

                dp[y][x] = Math.min(Math.min(getOrZero(dp, x-1, y), getOrZero(dp, x, y-1)), getOrZero(dp, x-1, y-1)) + 1;

                if (matWidths.contains(dp[y][x])) {
                    possibleMaxSize = Math.max(possibleMaxSize, dp[y][x]);
                }
            }
        }

        return possibleMaxSize;
    }

    private static int getOrZero(int[][] dp, int x, int y) {
        try {
            return dp[y][x];
        } catch(Exception e) {
            return 0;
        }
    }
}

/*
Ai의 리뷰

---

1. **`try-catch`를 통한 경계선 처리 (성능 저하의 주원인)**
   * 이중 반복문 안에서 `try-catch` 예외 처리를 이용해 배열 범위를 체크하고 있습니다.
   * 자바에서 예외(Exception) 발생 및 포착은 **스택 트레이스 생성 등으로 인해 매우 무거운 연산**입니다.
   * **해결법:** DP 배열의 크기를 `[height + 1][width + 1]`로 1칸씩 늘려 패딩(Padding)을 주면, 조건문이나 예외 처리 없이 깔끔하게 인덱스 접근이 가능합니다.

2. **불필요한 `HashSet` 사용 및 박싱(Boxing) 비용**
   * `int`를 `Integer`로 박싱하여 `HashSet`에 넣는 과정에서 불필요한 메모리와 시간이 소모됩니다.
   * `mats` 배열의 길이가 매우 작으므로, 정렬 후 뒤에서부터 탐색하는 편이 훨씬 빠르고 간결합니다.
*/