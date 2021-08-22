package permutation;

import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int[] answer = new int[n];
        // 처음 갯수
        int idx = 0;
        long count; // 각 그룹당 갯수
        long index; // 찾는 그룹 index;
        while (n > 0) {
            count = factorial / n; // 2/2 = 1
            index = (k - 1) / count; // (k - 1) = 1 / 1 = 1
            answer[idx] = list.remove((int) index);
            // System.out.println(factorial+" "+ count+" "+index + " " + answer[idx]);

            if(count * index <= k) k = k - count * index; // 6- 2*2 = 2
            factorial /= n; //6 / 3 = 2
            --n;
            idx++;
        }

        return answer;
    }
    public void solve(int n, boolean[] check, List<List<Integer>> list, List<Integer> arr) {
        if (n == arr.size()) {
            list.add(new ArrayList<>(arr));
            System.out.println(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                arr.add(i+1);
                check[i] = true;
                solve(n, check, list, arr);
                arr.remove(arr.size()-1);
                check[i] = false;
            }
        }
    }
}