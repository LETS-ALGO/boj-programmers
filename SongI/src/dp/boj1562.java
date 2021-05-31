package dp;
import java.util.Scanner;

public class boj1562 {
    static long[][][] dp;
    static long MOD = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new long[n + 1][11][1 << 11];
        // dp [자리수][j 번째수][비트]
        // dp[i][j][k] = dp[i-1][j+1][k|(1<<j)]+dp[i-1][j-1][k|(1>>j)]

        // 1<<i : 0~9 비트 마스킹
        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1; //1의 자리는 모두 계단수다.
        }
        // i : i 자리 숫자
        // j : 끝나는 숫자
        // k : 마킹된 숫자
        /*
        예를 들어 2자리 숫자고 i=2, 4로 끝날때 j=4, 234가 포함된 k=0000011100 : 28,
        * */
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j);
                    if (j == 0) dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k]) % MOD;
                    else if (j == 9) dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k]) % MOD;
                    else dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k] + dp[i - 1][j - 1][k]) % MOD;
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++)
            sum = (sum + dp[n][i][1023]) % MOD;
        System.out.println(sum);
    }
}
