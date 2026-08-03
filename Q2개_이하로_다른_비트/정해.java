package Q2개_이하로_다른_비트;

public class 정해 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];

            // 1. 짝수인 경우 (맨 끝 비트가 0)
            // 맨 끝의 0을 1로 바꾸기만 하면 (x + 1) 비트 1개만 다른 가장 작은 수가 됩니다.
            if (x % 2 == 0) {
                answer[i] = x + 1;
            } 
            // 2. 홀수인 경우 (맨 끝 비트가 1)
            else {
                /*
                 * [비트 연산 원리 상세 설명]
                 * 
                 * 예시: x = 7 (이진수: 0111)
                 * 
                 * ① (x + 1)
                 *    - x에 1을 더하면 맨 오른쪽 연속된 1들이 0으로 바뀌고, 
                 *      가장 처음 만난 0자리에 1이 들어갑니다. (7 + 1 = 8 -> 1000)
                 * 
                 * ② (~x)
                 *    - x의 비트를 뒤집어 원래 0이었던 자리를 1로 만듭니다. (~7 -> 1111 1000)
                 * 
                 * ③ lowestUnsetBit = ~x & (x + 1)
                 *    - 둘을 AND(&) 연산하면, 원래 x에서 "가장 처음 나왔던 0의 위치"만 1로 남습니다.
                 *    - 7 기준 결과: 8 (이진수: 1000)
                 */
                long lowestUnsetBit = ~x & (x + 1);

                /*
                 * ④ (lowestUnsetBit >> 1)
                 *    - 비트를 오른쪽으로 1칸 밀어(Shift) 값을 절반으로 만듭니다.
                 *    - '01' 패턴을 '10'으로 바꾸는 효과와 같습니다.
                 *    - 8 기준 결과: 4 (이진수: 0100)
                 * 
                 * ⑤ x + (lowestUnsetBit >> 1)
                 *    - 원래 값 x에 절반 값을 더해주면 최종 정답이 됩니다.
                 *    - 7 + 4 = 11 (이진수: 1011)
                 */
                answer[i] = x + (lowestUnsetBit >> 1);
            }
        }

        return answer;
    }
}
