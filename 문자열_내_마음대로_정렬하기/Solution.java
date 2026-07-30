package 문자열_내_마음대로_정렬하기;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, 
            Comparator.comparing((String str) -> str.charAt(n))
            .thenComparing(String::compareTo));
        return strings;
    }
}
