package 햄버거_만들기;

import java.util.*;

public class Solution {
    private class Hamburger {
        private int state;

        public Hamburger() {
            state = 1;
        }

        public void next() {
            state++;
        }

        public boolean isThatNextIngredient(int ingredient) {
            return state % 3 + 1 == ingredient;
        }

        public boolean isDone() {
            return state == 4;
        }
    }

    public int solution(int[] ingredient) {
        Deque<Hamburger> stack = new ArrayDeque<>();
        int count = 0;

        for (int ing : ingredient) {
            if (stack.isEmpty() && ing == 1) {
                // 기존 햄버거가 없고 재료가 빵이라면 새 햄버거 추가
                stack.addLast(new Hamburger());
                continue;
            }

            if (!stack.isEmpty()) {
                // 만들던 빵이 있다면 계속 이어나가기 시도
                Hamburger lastHamburger = stack.getLast();

                if (lastHamburger.isThatNextIngredient(ing)) {
                    // 다음 재료라면 빵을 계속 만듦
                    lastHamburger.next();

                    if (lastHamburger.isDone()) {
                        count++;
                        stack.removeLast();
                        continue;
                    }
                    
                    continue;
                }

                if (ing == 1) {
                    // 다음 재료는 아닌데 빵이라면 새 햄버거 조립 시작
                    stack.addLast(new Hamburger());
                    continue;
                }
                
                // 기존의 햄버거들을 완성할 수 없음으로 폐기처분
                stack.clear();
                continue;
            }
        }

        return count;
    }
}
