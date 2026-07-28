// https://school.programmers.co.kr/learn/courses/30/lessons/82612

public class Solution {
    public long solution(int price, int money, int count) {
        long total = 0;

        // 일반적인 반복문

        // for (int i = 1; i <= count; i++) {
        //     total += price * i;
        // }

        // 가우스

        total = (long) count * (count + 1) / 2 * price;

        long lack = total - money;

        if (lack > 0) {
            return lack;
        } else {
            return 0;
        }
    }
}