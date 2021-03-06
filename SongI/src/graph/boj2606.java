package graph;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj2606 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        LinkedList<Integer>[] list = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) list[i] = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list[a].add(b);
            list[b].add(a);
        }
        boolean[] check = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        check[1] = true;
        int answer = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            answer++;
            for (int next : list[now]) {
                if (check[next]) continue;
                q.add(next);
                check[next] = true;
            }
        }
        System.out.println(answer - 1);
    }

}
