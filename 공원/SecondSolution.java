// https://school.programmers.co.kr/learn/courses/30/lessons/340198
package 공원;

import java.util.Arrays;

public class SecondSolution {
    private int[][] dp;

    public int solution(int[] mats, String[][] park) {
        int height = park.length;
        int width = park[0].length;
        dp = new int[height][width];
        int possibleMaxSize = -1;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!park[y][x].equals("-1")) {
                    dp[y][x] = 0;
                    continue;
                }

                // 첫 행 혹은 첫 열은 정사각형일 수 없음
                if (x == 0 || y == 0) {
                    dp[y][x] = 1;
                } else {
                    dp[y][x] = Math.min(Math.min(dp[y][x-1], dp[y-1][x]), dp[y-1][x-1]) + 1;
                }

                possibleMaxSize = Math.max(possibleMaxSize, dp[y][x]);
            }
        }

        Arrays.sort(mats);

        for (int i = mats.length - 1; i >= 0; i--) {
            if (possibleMaxSize >= mats[i]) {
                // possibleMaxSize 에 가장 가까운 최대 사이즈
                return mats[i];
            }
        }

        return -1;
    }
}

/*
이렇게 풀어봤는데 재밌는 사실은 평균 실행 속도가 수정 이전 버전이 무려 4~5배가 더 빠르다.

---

### 1. 자바의 'Zero-Cost Exception'

자바(HotSpot JVM)의 최신 예외 처리는 **Zero-Cost Exception** 방식을 사용합니다.

* **예외가 발생하지 않는 일반적인 상황(Happy Path):** `try-catch` 블록이 있든 없든 **CPU 연산 비용이 정확히 0(Zero)**입니다. 성능 저하가 전혀 없습니다!
* 공원 내부의 대부분 세포($x > 0, y > 0$)에서는 예외가 발생하지 않습니다.
* **결과적으로:** $300 \times 300$ 번 도는 동안 `if`문으로 $x, y$ 범위를 매번 검사하는 조건문(Branch) 비용조차 없어서, CPU 파이프라인이 멈추지 않고 미친 듯한 속도로 일직선 실행을 한 것입니다.

### 2. 분기(Branch)가 없는 코드의 위력

* 제가 제안한 방식은 루프 안에서 `if (x == 0 || y == 0)` 같은 **조건 비교 연산**을 매번 실행합니다.
* 반면, 작성하신 `getOrZero`는 **조건문 없이 일단 메모리를 읽으려고 시도**합니다.
* CPU 입장에서는 "조건을 물어보고 판단하는 작업" 자체가 없어서 파이프라인 연산이 극도로 효율적으로 동작한 것입니다.

### 3. `HashSet`의 L1 캐시 적중률

* 이 문제에서 돗자리 종류(`mats`)는 많아봐야 몇 개 안 됩니다. (보통 3~5개)
* 이렇게 매우 작은 크기의 `HashSet`은 CPU의 가장 빠른 메모리인 **L1 캐시 메모리에 통째로 올라갑니다.**
* 또한, 자바 JIT 컴파일러가 반복문 안의 `HashSet.contains()` 메서드를 통째로 기계어로 펼쳐서(Inlining) 넣어버리기 때문에, 배열 정렬 연산보다 오히려 빨랐던 것입니다.
*/