package bitMask;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i=0; i<numbers.length; i++){
            answer [i] = find(numbers[i]);
        }
        return answer;
    }
    public long find(long num){
        if(num%2 == 0) return num+1;
        String binary = Long.toBinaryString(num);
        String[] ans = binary.split("");
        boolean flag = false;
        String answer = "";
        for(int i=binary.length()-1; i>=0; i--){
            if(binary.charAt(i)=='0'){
                ans[i] = "1";
                ans[i+1] = "0";
                flag = true;
            }
            if(flag)break;
            if(!flag && i==0){
                answer+="1";
                ans[0] ="0";
            }
        }
        for(String a : ans){
            answer +=a;
        }

        return Long.parseLong(answer, 2);
    }
}