package 성격_유형_검사하기;

import java.util.HashMap;

public class Solution {
    public String solution(String[] survey, int[] choices) {
        var scoreMap = new HashMap<Character, Integer>();

        for (int i = 0; i < survey.length; i++) {
            int choice = choices[i];
            if (choice == 4) continue;

            char type = choice < 4 ? survey[i].charAt(0) : survey[i].charAt(1);
            int score = Math.abs(choice - 4);

            scoreMap.merge(type, score, Integer::sum);
        }

        char[][] indicatorPairs = {
            {'R', 'T'},
            {'C', 'F'},
            {'J', 'M'},
            {'A', 'N'}
        };

        var sb = new StringBuilder();
        for (char[] pair : indicatorPairs) {
            int score1 = scoreMap.getOrDefault(pair[0], 0);
            int score2 = scoreMap.getOrDefault(pair[1], 0);

            sb.append(score1 >= score2 ? pair[0] : pair[1]);
        }

        return sb.toString();
    }
}
