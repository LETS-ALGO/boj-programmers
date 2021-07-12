package bfs;

import java.util.*;

public class boj14395 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int t = sc.nextInt();
        if (s == t) System.out.println(0);
        else System.out.println(solve(s, t));
    }

    static String solve(int s, int t) {
        Queue<Long> q = new LinkedList<>();
        Queue<String> opQ = new LinkedList<>();
        Set<Long> set = new HashSet<>();
        q.add((long) s);
        opQ.add("");
        String[] ops = {"*", "+", "-", "/"};
        while (!q.isEmpty()) {
            long now = q.poll();
            String op = opQ.poll();
            if (now == t) {
                return op;
            }
            for (int i = 0; i < 4; i++) {
                if(now==0 && i==3) continue;
                long next = calc(now, i);
                if (set.contains(next)) continue;
                set.add(next);
                q.add(next);
                opQ.add(op + ops[i]);
            }
        }
        return "-1";
    }

    private static long calc(long n, int i) {
        switch (i) {
            case 0:
                return n * n;
            case 1:
                return n + n;
            case 2:
                return n - n;
            case 3:
                return n / n;
        }
        return 0;
    }

}