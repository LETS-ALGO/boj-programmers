package dp;
import java.util.Scanner;

public class boj2098 {
    static int[][] map = new int[16][16];
    static int[][] dp = new int[16][1<<16];
    static int n;
    static int INF = Integer.MAX_VALUE - 1_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                map[i][j] = sc.nextInt();
        }
        System.out.println(tsp(0, 1));
    }
    // cur: 현재 도시, visited: 방문한 도시 들의 집합
    static int tsp(int cur, int visited) {
        //모두 방문 : 모든 비트의 값이 1인가
        if ((visited == (1 << n) - 1)) {
            //마지막 도시에서 0번도시로 돌아갈 수 없을 경우
            if (map[cur][0] == 0) return INF;
            return map[cur][0];
        }
        // 이미 방문한 적이 있다 : i번째 비트의 값이 1인가
        if (dp[cur][visited] != 0) return dp[cur][visited];

        dp[cur][visited] = INF;
        for (int k = 0; k < n; k++) {
            int next = 1 << k; // k 번 도시째 도시
            // 갈수 없거나, 방문했을때 (방문했던 도시집합 AND 연산 하면 포함되어 있으면 0 이상이다)
            if (map[cur][k] == 0 || (visited & next) > 0) continue;

            // 다음 도시를 방문할 경우 i 번째 비트의 값을 1로 변경 (visited 와 next OR 연산)
            dp[cur][visited] = Math.min(dp[cur][visited], tsp(k, visited | next) + map[cur][k]);
        }
        return dp[cur][visited];
    }
}

