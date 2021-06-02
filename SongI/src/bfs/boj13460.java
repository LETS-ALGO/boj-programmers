package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj13460 {
    //    https://www.acmicpc.net/problem/13460
    // 이동가능 한 방법을 비트마스크를 이용해 4(이동할 수 있는 방향)^10(최대 10번이동)가지 만든다.
        /*
        1. 같은 방향으로 연속해서 두 번 이상 이동하는 건 의미없다.
        2. 한 방향으로 이동한 다음 반대 방향으로 바로 이동하는 것도 의미 없다.
        -> 가능한 이동 방법 4*2^9
        * */
    static int n, m, bx, by, rx, ry;
    static final int MAX = 10;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][][][] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new char[n][m];
        arr = new int[MAX + 1][MAX + 1][MAX + 1][MAX + 1]; // blue, red 공의 위치를 체크하는 배열
        for (int i = 0; i < n; i++) {
            String input = sc.next();
            map[i] = input.toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                }
            }
        }
        System.out.println(solve());
    }

    public static int solve() {
        Queue<Pair> red = new LinkedList<>();
        Queue<Pair> blue = new LinkedList<>();
        red.add(new Pair(rx, ry));
        blue.add(new Pair(bx, by));
        arr[bx][by][rx][ry] = 1;
        int cnt = 0;
        while (!blue.isEmpty()) {
            int size = blue.size();
            while (size-- > 0) { // 한 사이클을 다 돈후 cnt ++
                Pair b = blue.poll();
                Pair r = red.poll();
                if (map[r.x][r.y] == 'O' && map[r.x][r.y] != map[b.x][b.y]) return cnt;
                for (int i = 0; i < 4; i++) {
                    int nrx = r.x, nry = r.y, nbx = b.x, nby = b.y;
                    // i 번 방향으로 이동한다.
                    while (map[nrx + dx[i]][nry + dy[i]] != '#' && map[nrx][nry] != 'O') {
                        nrx += dx[i];
                        nry += dy[i];
                    }
                    while (map[nbx + dx[i]][nby + dy[i]] != '#' && map[nbx][nby] != 'O') {
                        nbx += dx[i];
                        nby += dy[i];
                    }
                    // 이동한 자리 blue 와 red 가 같을 때
                    if (nrx == nbx && nry == nby) {
                        // Red 가 O에 빠졌을 때
                        if (map[nrx][nry] == 'O') continue;
                        // 새로 이동한 곳과 거리를 계산하고 blue가 더 거리가 멀때 blue 의 이동거리를 하나 뺀다
                        // (red 뒤에 blue 가 있으므로 red 먼저 이동)
                        if (Math.abs(nrx - r.x) + Math.abs(nry - r.y) < Math.abs(nbx - b.x) + Math.abs(nby - b.y)) {
                            nbx -= dx[i];
                            nby -= dy[i];
                        } else {
                            // blue 먼저 이동 후 red 이동
                            nrx -= dx[i];
                            nry -= dy[i];
                        }
                    }
                    if (arr[nrx][nry][nbx][nby] == 1) continue; // 똑같은 위치
                    red.add(new Pair(nrx, nry));
                    blue.add(new Pair(nbx, nby));
                    arr[nrx][nry][nbx][nby] = 1;
                }
            }
            if (cnt == 10) return -1;
            cnt++;
        }
        return -1;
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}