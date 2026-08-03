package 지폐_접기;

public class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        while (true) {
            // 지갑에 들어가지는 체크
            var conditionA = wallet[0] >= bill[0] && wallet[1] >= bill[1];
            var conditionB = wallet[0] >= bill[1] && wallet[1] >= bill[0];

            if (conditionA || conditionB) {
                break;
            }

            if (bill[0] >= bill[1]) {
                bill[0] /= 2;
            } else {
                bill[1] /= 2;
            }

            answer++;
        }

        return answer;
    }
}
