package 성격_유형_검사하기;

import java.util.HashMap;

public class FirstSolution {
    public String solution(String[] survey, int[] choices) {
        var scoreMap = new HashMap<Character, Integer>();

        for (int idx = 0; idx < survey.length; idx++) {
            if (choices[idx] == 4) {
                // 아무 점수도 얻지 않는 선택지
                continue;
            }

            var type = choices[idx] < 4 ? survey[idx].charAt(0) : survey[idx].charAt(1); 
            int score = 0;

            switch (choices[idx]) {
                case 1:
                case 7:
                    score = 3;
                    break;
                case 2:
                case 6:
                    score = 2;
                    break;
                case 3:
                case 5:
                    score = 1;
                    break;
                default:
                    throw new RuntimeException("오류!");
            }

            scoreMap.merge(type, score, Integer::sum);            
        }

        var sb = new StringBuilder();

        if (scoreMap.getOrDefault('T', 0) > scoreMap.getOrDefault('R', 0)) {
            sb.append('T');
        } else {
            sb.append('R');
        }

        if (scoreMap.getOrDefault('F', 0) > scoreMap.getOrDefault('C', 0)) {
            sb.append('F');
        } else {
            sb.append('C');
        }

        if (scoreMap.getOrDefault('M', 0) > scoreMap.getOrDefault('J', 0)) {
            sb.append('M');
        } else {
            sb.append('J');
        }

        if (scoreMap.getOrDefault('N', 0) > scoreMap.getOrDefault('A', 0)) {
            sb.append('N');
        } else {
            sb.append('A');
        }

        return sb.toString();
    }
}
