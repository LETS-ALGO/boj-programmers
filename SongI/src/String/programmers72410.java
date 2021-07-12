class Solution {
    public String solution(String new_id) {
        String answer = "";
        // 1 단계
        new_id = new_id.toLowerCase();
        // 2 단계
        for (int i = 0; i < new_id.length(); i++) {
            if ((new_id.charAt(i) >= 'a' && new_id.charAt(i) <= 'z') || new_id.charAt(i) == '_' ||
                    new_id.charAt(i) == '-' ||
                    new_id.charAt(i) == '.' || (
                    new_id.charAt(i) >= '0' && new_id.charAt(i) <= '9'))
                answer += String.valueOf(new_id.charAt(i));
        }

        // 3 단계
        while (true) {
            if (!answer.contains("..")) break;
            answer = answer.replace("..", ".");
        }

        // 4 단계
        if (answer.charAt(0) == '.')
            answer = answer.substring(1);
        if (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.')
            answer = answer.substring(0, answer.length() - 1);

        // 5 단계
        if (answer.equals("")) answer += "a";


        // 6 단계
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            if (answer.charAt(answer.length() - 1) == '.') answer = answer.substring(0, 14);
        }

        // 7 단계
        if (answer.length() <= 2) {
            String last = String.valueOf(answer.charAt(answer.length() - 1));
            while (true) {
                answer += last;
                if (answer.length() == 3) break;
            }
        }
        return answer;
    }
}