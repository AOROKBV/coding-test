package 비밀_코드_해독;

import java.util.ArrayList;
import java.util.HashSet;

class FirstSolution {
    class Combination {
        private int[] comb = new int[5];
        private int curIdx = 0;
        private int maxN = 0;
        private int last = 0;

        public Combination(int maxN) {
            this.maxN = maxN;
        }

        public int[] next() {
            while (true) {
                comb[curIdx] = ++last;

                if (comb[curIdx] > maxN - (4 - curIdx)) {
                    curIdx--;
                    if (curIdx > -1) last = comb[curIdx];
                    else return null;
                    continue;
                }

                if (curIdx == -1) {
                    return null;
                }

                if (curIdx == 4) {
                    return comb;
                }

                curIdx++;
            }
        }
    }

    public int solution(int n, int[][] q, int[] ans) {
        var tries = new ArrayList<HashSet<Integer>>();

        for (int[] tri : q) {
            var set = new HashSet<Integer>();
            for (int i : tri) {
                set.add(i);
            }
            tries.add(set);
        }

        var combination = new Combination(n);

        int answer = 0;

        while (true) {
            int[] comb = combination.next();

            if (comb == null) {
                break;
            }

            int totalCnt = 0;

            for (int i = 0; i < ans.length; i++) {
                int cnt = 0;

                for (int e : comb) {
                    cnt += tries.get(i).contains(e) ? 1 : 0;
                }

                if (cnt == ans[i]) totalCnt++;
            }

            if (totalCnt == ans.length) answer++;
        }

        return answer;
    }
}

/*
테스트 1 〉	통과 (0.86ms, 73.4MB)
테스트 2 〉	통과 (7.39ms, 83.4MB)
테스트 3 〉	통과 (0.44ms, 81.5MB)
테스트 4 〉	통과 (0.70ms, 84.6MB)
테스트 5 〉	통과 (0.69ms, 85.1MB)
테스트 6 〉	통과 (0.64ms, 83.5MB)
테스트 7 〉	통과 (5.72ms, 79.7MB)
테스트 8 〉	통과 (3.74ms, 73MB)
테스트 9 〉	통과 (7.79ms, 82.2MB)
테스트 10 〉	통과 (16.75ms, 88MB)
테스트 11 〉	통과 (13.87ms, 87.5MB)
테스트 12 〉	통과 (19.20ms, 86.1MB)
테스트 13 〉	통과 (38.85ms, 91.7MB)
테스트 14 〉	통과 (41.72ms, 87.9MB)
테스트 15 〉	통과 (28.95ms, 87.5MB)
테스트 16 〉	통과 (46.22ms, 77.9MB)
테스트 17 〉	통과 (46.42ms, 78.3MB)
테스트 18 〉	통과 (56.41ms, 89.6MB)
테스트 19 〉	통과 (67.23ms, 87.1MB)
테스트 20 〉	통과 (58.95ms, 78.8MB)

---

몰라서 Ai 힌트 받음...

최대 조합의 수가 142,506 개 (30C5) 
-> (모든 비밀코드는 어차피 오름차순으로 정렬되어 있기에 무순서 무중복 조합)
조합 전부 돌며 최대 m개의 시도를 모두 검증한다 하면 O(mn) = O(n)
근데 n이 142,506으로 작음
그래서 그냥 완전탐색으로 풀면 된다고 한다.
근데 iterator 패턴 보다 dfs 가 더 빠름.
*/