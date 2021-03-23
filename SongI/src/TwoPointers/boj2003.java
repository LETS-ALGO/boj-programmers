package TwoPointers;

import java.util.Scanner;

public class boj2003  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        // 1. 포인터 2개를 준비한다. 현재 부분 배열의 시작과 끝을 가리키는 역할을 한다.
        // 2. 맨 처음에는 start = end = 0 이며, 항상 start <= end 여야 한다. (start 포함, end 미포함 기준)
        // 3. start = end 일 경우, 아무것도 포함하지 않는 부분 배열이라는 뜻이다.
        // 4. start < N 동안 다음을 반복한다.
        /** 1. 만약 현재의 부분합이 M 이상이거나 이미 end = N이면 start++
         * 2. 그렇지 않다면 end++
         * 3. 현재 부분합이 M과 같으면 결과(횟수) ++
         * */
        int answer = 0;
        int start = 0, end = 0;
        int sum = 0;
        while (true) {
            if (sum >= m) { // sum 이 m 보다 크면 start 를 증가시킨다.
                sum -= a[start++];
            } else if(end == n) break;// 원하는 결과 값을 찾았을 때 answer ++
            else { // sum 이 m 보다 작기 때문에 end 를 증가시킨다.
                sum += a[end++];
            }
            if (sum == m) answer++;
        }
        System.out.println(answer);
    }
}