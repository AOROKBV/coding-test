package 당구_연습;

public class Solution {
    private int calculation(int width, int height, int startX, int startY, int ballX, int ballY) {
        int minDist = Integer.MAX_VALUE;
        
        // 상
        if (startX != ballX || startY > ballY) {
            var mirrorX = ballX;
            var mirrorY = 2 * height - ballY;

            var dist = (int)(Math.pow(startX - mirrorX, 2) + Math.pow(startY - mirrorY, 2));
            minDist = Math.min(minDist, dist);
        }

        // 하
        if (startX != ballX || startY < ballY) {
            var mirrorX = ballX;
            var mirrorY = -ballY;

            var dist = (int)(Math.pow(startX - mirrorX, 2) + Math.pow(startY - mirrorY, 2));
            minDist = Math.min(minDist, dist);
        }

        // 좌
        if (startY != ballY || startX > ballX) {
            var mirrorX = 2 * width - ballX;
            var mirrorY = ballY;

            var dist = (int)(Math.pow(startX - mirrorX, 2) + Math.pow(startY - mirrorY, 2));
            minDist = Math.min(minDist, dist);
        }

        // 우
        if (startY != ballY || startX < ballX) {
            var mirrorX = -ballX;
            var mirrorY = ballY;

            var dist = (int)(Math.pow(startX - mirrorX, 2) + Math.pow(startY - mirrorY, 2));
            minDist = Math.min(minDist, dist);
        }

        return minDist;
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            answer[i] = calculation(m, n, startX, startY, balls[i][0], balls[i][1]);
        }

        return answer;
    }
}
