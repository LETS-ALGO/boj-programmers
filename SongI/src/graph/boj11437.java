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
        parent = new int[n + 1][17]; // 2^i > 50,000 인 i 는 16이다
        depth = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list[a].add(b);
            list[b].add(a);
        }
        // 루트 노드 1에서 부터 전체 depth 계산
        dfs(1, 1);

        for (int j = 1; j < 17; j++) {
            for (int i = 1; i <= n; i++) {
                //dp; i노드의 2^j번째 부모 계산
                // j 가 2 일때 -> parent[i][2] = parent[parent[i][1]][1]
                // i노드의 2^(j-1)번째 부모의 2^(j-1)번째 부모
                // 즉 현재 노드의 바
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            // 조상을 구할 두 노드
            int a = sc.nextInt();
            int b = sc.nextInt();

            // b의 depth가 더 깊도록
            int answer = depth[a] > depth[b] ? lca(b, a) : lca(a, b);
            System.out.println(answer);
        }
    }

    public static void dfs(int start, int cnt) {
        depth[start] = cnt;
        for (int next : list[start]) {
            if (depth[next] != 0) continue;
            parent[next][0] = start; // 바로 위 (2^0)1번쨰 부모는 start
            dfs(next, cnt += 1);
        }
    }

    public static int lca(int a, int b) {
        for(int i=16; i>=0; i--){
            if(depth[b]-depth[a] >= (1<<i)){
                // i만큼 왼쪽 시프트 : 1* (2^i)
                b = parent[b][i];
            }
        }
        if(a == b) return a;
        for(int i=16; i>=0; i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}
