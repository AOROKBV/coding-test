package 콜라츠_추측;

public class Solution {
    public int solution(int num) {
        long longNum = num;

        if (longNum == 1) return 0;

        for (int i = 0; i < 500; i++) {
            if (longNum % 2 == 0) {
                longNum /= 2;
            } else {
                longNum = longNum * 3 + 1;
            }

            if (longNum == 1) return i + 1;
        }

        return -1;
    }
}
