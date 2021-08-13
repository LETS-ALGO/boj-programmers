package stack;
import java.util.Scanner;
import java.util.Stack;

public class boj2504 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(solve(input));
    }

    public static int solve(String input) {
        int answer = 0;
        Stack<String> st = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            String now = String.valueOf(input.charAt(i));

            // push 할 때 :: 여는 괄호 일 때 닫는 괄호를 push 한다
            if (now.equals("(")) {
                st.push(")"); // 닫는 괄호
                continue;
            }
            if (now.equals("[")) {
                st.push("]");
                continue;
            }

            // pop 할때 ::  스택이 빌까지 계속 pop
            int ret = 0;
            while (true) {
                if (st.isEmpty()) {
                    // 올바르지 않는 괄호 : [, (
                    return 0;
                }
                String peekN = st.peek();
                if (!peekN.equals(")") && !peekN.equals("]")) {
                    // ) 나 ] 가 아니면 무조건 숫자
                    ret += Integer.parseInt(st.pop());
                } else {
                    // ) 또는 ]
                    if (now.equals(peekN)) {
                        st.pop();
                        // ) 면 *2 ] 면 *3 한다
                        int n = now.equals(")") ? 2 : 3;
                        if (ret == 0) {
                            st.push(String.valueOf(n));
                        } else {
                            st.push(String.valueOf(n * ret));
                        }
                        break;
                    }else {
                        return 0;
                    }
                }
            }// while(pop)
        }// for

        while (!st.isEmpty()){
            String peekN = st.peek();
            if(!peekN.equals(")") && !peekN.equals("]")){
                // 숫자면
                answer += Integer.parseInt(st.pop());
            }else {

                return 0; // 괄호가 있다면 올바른 문자열이 아니다.
            }
        }
        return answer;
    }
}