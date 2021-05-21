package greedy;

import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String [s.length];
        int i = 0;
        for(String input : s){
            answer[i++] = solve(input);
        }
        return answer;
    }

    public String solve(String input){
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            if(st.size()>=2){
                char b = st.pop();
                char a = st.pop();
                if(a=='1' && b=='1' && c =='0')  {
                    cnt++;
                    continue;
                }
                st.push(a);
                st.push(b);
                st.push(c);
            }else st.push(c);
        }
        System.out.println(st);
        if(cnt==0) return input;

        int idx = st.size(); // 남은 문자열의 길이
        boolean insert = false;

        while(!st.isEmpty()){
            if(!insert && st.peek()== '1') idx--;
            if(!insert && st.peek()== '0') insert = true; // 110 삽입 위치
            sb.insert(0, st.pop());
        }

        while(cnt>0){
            sb.insert(idx, "110");
            idx += 3;
            cnt --;
        }
        return sb.toString();
    }
}