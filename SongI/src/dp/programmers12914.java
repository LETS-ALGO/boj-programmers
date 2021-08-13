package dp;
//https://programmers.co.kr/learn/courses/30/lessons/12914
class Solution {
    public long solution(int n) {
        long[] dp = new long[n + 2];
        long mod = 1234567;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }
        return dp[n];
    }
}