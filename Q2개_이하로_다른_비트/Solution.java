package Q2개_이하로_다른_비트;

public class Solution {
    private long findNext(long number) {
        var binary = Long.toBinaryString(number).toCharArray();
        long add = 1;

        for (int i = binary.length - 1; i >= 0; i--) {
            if (binary[i] == '0') {
                try {
                    var test = binary[i+1];
                    return number + add / 2;
                } catch (Exception e) {
                    // pass
                }

                return number + add;
            }

            add *= 2;
        }

        return number + add / 2;
    } 

    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = findNext(numbers[i]);
        }

        return answer;
    }
}
