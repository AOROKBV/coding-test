package 바이러스_파이프;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    class Culture {
        int number;
        HashMap<Integer, HashSet<Integer>> pipeByType;

        public Culture(int number) {
            this.number = number;
            pipeByType = new HashMap<Integer, HashSet<Integer>>();
        }
    }

    static class Dp {
        static int edges[][];
        static Map<Integer, Culture> cultureMap;
        int depth = 0;
        Set<Integer> latestCultures;
        boolean[] visitedPipes;
        int opened = 0;

        public Dp(int maxPipes) {
            latestCultures = new HashSet<>();
            visitedPipes = new boolean[maxPipes];
        }

        public Dp(Dp other) {
            this.latestCultures = new HashSet<>(other.latestCultures);
            this.visitedPipes = Arrays.copyOf(other.visitedPipes, other.visitedPipes.length);
            this.depth = other.depth;
            this.opened = other.opened;
        }

        public Dp next(int opened) {
            Dp nextDp = new Dp(this);
            nextDp.opened = opened;
            nextDp.depth++;
            return nextDp;
        }

        public void dfs(Culture culture, int opened) {
            
            var pipes = culture.pipeByType.get(opened);

            if (pipes == null) {
                return;
            }

            for (int pipe : pipes) {
                if (!visitedPipes[pipe]) {
                    visitedPipes[pipe] = true;
                    var nextCultureNumber = edges[pipe][0] == culture.number ? 1 : 0;
                    var nextCulture = edges[pipe][nextCultureNumber];
                    latestCultures.add(nextCulture);
                    dfs(cultureMap.get(nextCulture), opened);
                }
            }
        }
    }

    public int solution(int n, int infection, int[][] edges, int k) {
        Dp.edges = edges;

        var cultureMap = new HashMap<Integer, Culture>();
        Dp.cultureMap = cultureMap;

        int maxScore = 1;
        int maxPipes = edges.length;

        for (int i = 1; i <= n; i++) {
            cultureMap.put(i, new Culture(i));
        }

        for (int i = 0; i < edges.length; i++) {
            var cultureA = cultureMap.get(edges[i][0]);
            var cultureB = cultureMap.get(edges[i][1]);

            cultureA.pipeByType
                .computeIfAbsent(edges[i][2], key -> new HashSet<Integer>())
                .add(i);

            cultureB.pipeByType
                .computeIfAbsent(edges[i][2], key -> new HashSet<Integer>())
                .add(i);
        }

        var bfs = new ArrayDeque<Dp>();
        var startDp = new Dp(maxPipes);
        startDp.latestCultures.add(infection);
        bfs.addLast(startDp);

        while(!bfs.isEmpty()) {
            var dp = bfs.removeFirst();

            if (dp.depth >= k) {
                continue;
            }

            for (int open = 1; open <= 3; open++) {
                if (dp.opened != open) {                    
                    var latestCultures = new HashSet<>(dp.latestCultures);
                    var nextDp = dp.next(open);
                    for (int latestCulture : latestCultures) {
                        nextDp.dfs(cultureMap.get(latestCulture), open);
                    }
                    maxScore = Math.max(nextDp.latestCultures.size(), maxScore);
                    bfs.addLast(nextDp);
                }
            }
        }

        return maxScore;
    }
}