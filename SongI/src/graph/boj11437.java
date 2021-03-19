package graph;

import java.util.ArrayList;
import java.util.Scanner;

/*https://www.acmicpc.net/problem/11437
 * */
public class boj11437 {
    static ArrayList<Integer>[] list;
    static int[][] parent;
    static int[] depth;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        list = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) list[i] = new ArrayList<>();
        parent = new int[n + 1][17]; // 2^i > 50,000 인 depth i 는 16이다
        depth = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list[a].add(b);
            list[b].add(a);
        }
        int tmp = 1;
        int MAX_DEPTH = 0;
        while (tmp <= n) {
            tmp <<= 1;
            MAX_DEPTH++;
        } // 최대 depth 알아내는 식

        // 루트 노드 1에서 부터 전체 depth 계산
        dfs(1, 1);

        for (int j = 1; j < MAX_DEPTH; j++) {
            for (int i = 1; i <= n; i++) {
                //dp; i노드의 2^j번째 부모 계산
                // j 가 2 일때 -> parent[i][2] = parent[parent[i][1]][1]
                // i노드의 2^(j-1)번째 부모의 2^(j-1)번째 부모
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            // 조상을 구할 두 노드
            int a = sc.nextInt();
            int b = sc.nextInt();

            // b의 depth가 더 깊도록
            int answer = depth[a] > depth[b] ? lca(b, a, MAX_DEPTH) : lca(a, b, MAX_DEPTH);
            System.out.println(answer);
        }
    }

    public static void dfs(int start, int dep) {
        depth[start] = dep;
        for (int next : list[start]) {
            if (depth[next] != 0) continue;
            dfs(next, dep + 1);
            parent[next][0] = start; // 바로 위 (2^0)1번쨰 부모는 start
        }
    }

    public static int lca(int a, int b, int MAXD) {
        // depth[a] < depth[b] 일 때
        // 더 깊은 b 를 2승씩 점프하며 두 노드의 depth를 맞춘 후, 맞춘 depth의 조상 노드로 대체한다.
        for (int i = MAXD - 1; i >= 0; i--) {
            if (depth[b] - depth[a] >= (1 << i)) {
                // i만큼 왼쪽 시프트 : 1* (2^i)
                b = parent[b][i]; // b를 b의 2^i번째 조상 노드로 대체한다.
            }
        }
        // depth를 맞춘 후 대체한 조상노드가 a와 같으면 == b의 조상노드가 a라면 종료.
        if (a == b) return a;

        // 이제 두 노드는 같은 depth를 가지기 때문에
        // 같이 2승씩 점프시키며 공통부모 바로 아래까지 올린다.
        for (int i = MAXD - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}
