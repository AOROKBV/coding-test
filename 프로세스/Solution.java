package 프로세스;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution {
    static record Process(int idx, int priority) {}

    public int solution(int[] priorities, int location) {
        int answer = 0;
        var queue = new ArrayDeque<Process>();

        for (int i = 0; i < priorities.length; i++) {
            queue.addLast(new Process(i, priorities[i]));
        }

        Arrays.sort(priorities);
        int prioritiesIdx = priorities.length - 1;

        while (!queue.isEmpty()) {
            var process = queue.removeFirst();

            if (process.priority() == priorities[prioritiesIdx]) {
                answer++;

                if (process.idx() == location) {
                    return answer;
                } 

                prioritiesIdx--;

                continue;
            }
            queue.addLast(process);
        }

        return answer;
    }
}
