package 의상;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> clothMap = new HashMap<>();

        for (String[] cloth : clothes) {
            clothMap.merge(cloth[1], 1, Integer::sum);
        }

        for (int count : clothMap.values()) {
            // 가짓 수 + 안 입음
            answer *= count + 1;
        }

        // 전부 안입는 단 하나의 경우를 제외
        return answer - 1;
    }
}
