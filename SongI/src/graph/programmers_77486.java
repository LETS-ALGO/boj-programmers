package graph;

import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // enroll의 index와 이름을 mapping
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], i);
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < seller.length; i++) {
            int money = amount[i] * 100;
            int pay = money / 10;

            String name = seller[i];
            int idx = map.get(name);
            answer[idx] += money - pay;

            while (true) {
                if (referral[idx].equals("-")) break;
                name = referral[idx];
                idx = map.get(name);
                money = pay;
                pay = money / 10;
                answer[idx] += money - pay;
            }
        }

        return answer;
    }

}