package heap;

import java.io.*;
import java.util.*;

public class boj1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> min = new PriorityQueue<>(((o1, o2) -> o1 - o2));
        PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 1. 최대힙의 크기는 항상 최소힙의 크기보다 같거나 1만큼 더 크다.
        // 2. 최소힙의 모든 원소는 최대힙의 모든 원소보다 항상 크거나 같아야 한다.
        // 최소힙의 top() 은 항상 최대힙의 가장 top() 보다 크거나 같아야 한다.
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (min.size() == max.size()) {
                max.add(num);
            } else {
                min.add(num);
            }
            if(!max.isEmpty() && !min.isEmpty()){
                if(max.peek() > min.peek()){
                    int tmp = max.poll();
                    max.add(min.poll());
                    min.add(tmp);
                }
            }
            bw.write(max.peek()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
