package TwoPointers;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int a = 0; // left
        int b = arr.length - 1; //right;
        Arrays.sort(arr); // 정렬
        int diff = 2000000000;
        int[] ans = new int[2];
        while (a < b) { // left < right
            int d = Math.abs(arr[b] + arr[a]);
            if (d == 0) {
                System.out.println(arr[a] + " " + arr[b]);
                return;
            }
            if (diff > d) { // 갱신
                diff = d;
                ans[0] = arr[a];
                ans[1] = arr[b];
            }
            if (arr[a] + arr[b] > 0) // 합이 양수
                b--; // 더 작은 수를 만들기 위해 right --
            else // 합이 음수
                a++; // 큰수를 만들기 위해 left ++;
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}

