package prefix;
import java.util.Scanner;

public class boj11441 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 1];
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            sum[i] += sum[i - 1] + a[i];
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt() - 1;
            int e = sc.nextInt();
            System.out.println(sum[e] - sum[s]); // 0부터 e 까지의 합 - 0부터 s 까지의 합
        }
    }
}