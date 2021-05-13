package heap;

import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int [2];
        int max = 0, min = 0;
        ArrayList<Integer> doublePQ = new ArrayList<>();
        for(String op : operations){
            char c = op.charAt(0);
            int num = Integer.parseInt(op.split(" ")[1]);

            if(c=='I'){
                doublePQ.add(num);
                Collections.sort(doublePQ);
            }else{
                if(doublePQ.isEmpty()) continue;
                Collections.sort(doublePQ);
                if(num == 1){
                    // max delete
                    doublePQ.remove(doublePQ.size()-1);
                }else {
                    // min delete
                    doublePQ.remove(0);
                }
            }

        }
        if(doublePQ.isEmpty()) {answer[0]=0;answer[1]=0;}
        else {
            answer[1]=doublePQ.get(0);
            answer[0]=doublePQ.get(doublePQ.size()-1);
        }
        return answer;
    }
}