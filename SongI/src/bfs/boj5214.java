package bfs;

import java.util.*;

public class boj5214 {
    /*
    * https://www.acmicpc.net/problem/5214
    * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 역의 갯수
        int k = sc.nextInt(); // 하이퍼가 연결하는 역의 갯수
        int m = sc.nextInt(); // 하이퍼의 갯수

        List<Integer>[]lists = new LinkedList[n+m+1];
        for(int i=1; i<=n+m; i++) lists[i] = new LinkedList<>();

        for(int i=n+1; i<n+m+1; i++){
            for(int j=0; j<k; j++) {
                int a = sc.nextInt();
                lists[i].add(a);
                lists[a].add(i);
            }
        }
        int []dis = new int[n+m+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dis[1] = 1;
        while (!q.isEmpty()){
            int now = q.poll();

            if(now == n) break;
            for(int next : lists[now]){
                if(dis[next]!=0)continue;
                q.add(next);
                dis[next] = dis[now]+1;
            }
        }
        System.out.println(dis[n] == 0 ? -1: dis[n]/2+1);
    }
}
