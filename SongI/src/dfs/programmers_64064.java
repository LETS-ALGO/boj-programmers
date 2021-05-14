package dfs;

import java.util.*;
class Solution {
    public Set<Set<String>> result;
    public int solution(String[] user_id, String[] banned_id) {
        // 중복 제거를 위한 HashSet 사용
        result = new HashSet<>();
        // 가능한 banned_id 를 DFS 로 탐색
        dfs(user_id, banned_id, new LinkedHashSet<>());
        return result.size();
    }
    public void dfs(String[] user_id, String[] banned_id, Set<String> set){
        if(set.size() == banned_id.length) {
            // set의 userId 들이 ban할 수 있는 아이디 인지 검사
            if(canBan(set, banned_id)){
                // set 깊은 복사
                result.add(new HashSet<>(set));
            }
            return;
        }

        for(String userId: user_id){
            // 방문하지 않은 userId
            if(!set.contains(userId)){
                set.add(userId);
                dfs(user_id, banned_id, set);
                set.remove(userId);
            }
        }
    }

    public boolean canBan(Set<String> set, String[] banned_id){
        int i = 0;
        for(String user : set){
            if(!isSameString(user, banned_id[i++]))
                return false;
        }
        return true;
    }

    public boolean isSameString(String userId, String banId){
        String[] _userId = userId.split("");
        String[] _banId = banId.split("");
        if(userId.length()!=banId.length()) return false;
        for(int i=0; i<userId.length(); i++){
            if(_banId[i].equals("*")) continue;
            if(_userId[i].equals(_banId[i])) continue;
            return false;
        }
        return true;
    }
}