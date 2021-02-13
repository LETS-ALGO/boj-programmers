package graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
https://www.acmicpc.net/problem/1766
*/
public class boj1766 {

    // 위상정렬 문제
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] degree = new int[n + 1];

        LinkedList<Integer>[] list = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) list[i] = new LinkedList();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list[a].add(b);
            degree[b]++;
        }
        // ** priority 큐를 쓰지 않으면 오답으로 나온다.
        PriorityQueue<Integer> q = new PriorityQueue<>();
        // 1. 아무런 선행 조건 없는 정점 부터 큐에 추가 (진입 차수가 0인것들)
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) q.add(i);
        }
        while (!q.isEmpty()) {
            // 2. 큐에 저장된 정점을 하나 꺼낸다.
            int e = q.poll();
            System.out.print(e+" ");

            // 3. 꺼낸 정점과 연결된 정점을 찾아 선행되어야 할 정점을 처리했으므로 연결된 정점의 진입 차수를 하나 낮춘다
            for (int next : list[e]) {
                degree[next]--;
                if (degree[next] == 0) q.add(next);
            }
        }

    }
}
