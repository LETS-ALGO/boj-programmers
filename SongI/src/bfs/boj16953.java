package bfs;
import java.util.Scanner;

public class boj16953 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        // A -> B 필요한 연산의 최솟값
        int answer = 0;

        // B -> A 로 가면 최솟값을 찾을 수 있다
        while (true) {
            String bb = String.valueOf(b);
            if (b % 2 == 0) {
                b = b / 2;
            } else if (bb.charAt(bb.length() - 1) == '1') { // 끝이 1이라는것은 2로 나누어 떨어지지 않는것과 같다.
                b = (b - 1) / 10;
            }
            answer++;
            if (b == a) break;
            if (b < a || answer >= 30) {
                answer = -1;
                break;
            }
        }
        if (answer != -1) answer += 1;
        System.out.println(answer);
    }

}
