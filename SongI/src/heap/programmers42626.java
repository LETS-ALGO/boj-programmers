package heap;

import java.util.*;
class Solution {
    public int solution(int[] scoville, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2); // 오름차순
        for (int s : scoville) pq.add(s);
        int answer = 0;
        while (!pq.isEmpty()) {
            int min = pq.poll();
            if (min > k) break;
            if (pq.isEmpty()) return -1;
            int min_two = pq.poll();
            answer ++;
            int next = min + min_two * 2;
            pq.add(next);
        }
        return answer;
    }
}