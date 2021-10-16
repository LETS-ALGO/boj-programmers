package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int number = Integer.valueOf(br.readLine());

        int[][] map = new int[n][n];
        int x = n / 2;
        int y = x;
        int num = 1;
        map[x][y] = 1;

        int count = 1;
        int answerX = -1;
        int answerY = -1;

        int MAXIMUM = n * n;
        while (true) {
            //  count 칸 위 1  3
            for (int i = 0; i < count; i++) {
                map[--x][y] = ++num;
                if (num == MAXIMUM) break;
            }
            if (num == MAXIMUM) break;

            // count 칸 오른쪽 1  3
            for (int i = 0; i < count; i++) {
                map[x][++y] = ++num;
            }
            count++;
            // count+1 칸 아래 2 4
            for (int i = 0; i < count; i++) {
                map[++x][y] = ++num;
            }

            // count 칸 왼쪽  2  4
            for (int i = 0; i < count; i++) {
                map[x][--y] = ++num;
            }

            count++; // 3  5
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(" ");
                if(map[i][j] == number) {
                    answerX = i+1;
                    answerY = j+1;
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
        System.out.println(answerX + " " + answerY);
    }
}
