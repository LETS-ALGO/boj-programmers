package binarySearch;

import java.util.*;

/*
https://programmers.co.kr/learn/courses/30/lessons/43238
 * */
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long minTime = 1;
        long maxTime = n*(long)times[times.length-1];
        answer = maxTime;
        while(minTime <= maxTime){
            long mid = (minTime+maxTime)/2;
            long count = 0;

            for(int t:times){
                count+= mid/t;
            }

            if(count >= n){
                answer = Math.min(answer, mid);
                maxTime = mid-1;
            }else {

                minTime = mid+1;
            }


        }

        return answer;
    }
}