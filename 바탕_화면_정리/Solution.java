package 바탕_화면_정리;

public class Solution {
    public int[] solution(String[] wallpaper) {
        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;

        int endX = 0;
        int endY = 0;

        for (int x = 0; x < wallpaper.length; x++) {
            char[] files = wallpaper[x].toCharArray();

            for (int y = 0; y < files.length; y++) {
                if (files[y] != '#') continue;

                startX = Math.min(startX, x);
                startY = Math.min(startY, y);

                endX = Math.max(endX, x+1);
                endY = Math.max(endY, y+1);
            }
        }

        return new int[]{startX, startY, endX, endY};
    }
}
