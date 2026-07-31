package 문자열_나누기;

class Solution {
    public int solution(String s) {
        char current = ' ';
        int match = 0;
        int cnt = 0;

        for (char c : s.toCharArray()) {
            if (current == ' ') {
                current = c;
                match = 1;
                continue;
            }

            if (c == current) {
                match++;
            } else {
                match--;
            }

            if (match == 0) {
                current = ' ';
                cnt++;
            }
        }

        return match == 0 ? cnt : ++cnt;
    }
}