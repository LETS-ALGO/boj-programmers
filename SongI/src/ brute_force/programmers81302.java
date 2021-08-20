class Solution {
    static int[] dx = {1, 0, -1, 0, 1, -1, 1, -1};
    static int[] dy = {0, 1, 0, -1, 1, -1, -1, 1}; // 0~3 은 동서남북 방향, 4~7 은 대각선 방향
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int tc = 0; tc < places.length; tc++) {
            char[][] map = new char[5][5];
            for (int i = 0; i < 5; i++) {
                map[i] = places[tc][i].toCharArray();
            }
            boolean[][] c = new boolean[5][5];
            boolean flag = true;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!c[i][j] && map[i][j] == 'P') {
                        if (!solve(map, c, i, j)) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (!flag) break;
            }
            if (!flag) answer[tc] = 0;
            else answer[tc] = 1;
        }
        return answer;
    }

    public boolean solve(char[][] map, boolean[][] c, int x, int y) {
        c[x][y] = true;
        // 대각선에 있을 때 맨하튼 거리 2이다
        for (int i = 4; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
            if (c[nx][ny]) continue;
            if (map[nx][ny] == 'P'){
                // 파티션이 2 개여야 한다.
                if(map[nx][y] != 'X' || map[x][ny] != 'X')
                    return false; // 거리두기를 지키지 않았다.
            }
        }
        // 동서남북 방향에 있을 때 파티션을 고려해야 한다.
        for (int i = 0; i < 4; i++) {
            // 일단 맨하튼 거리 1인 것부터 검사
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
            if (map[nx][ny] == 'P') {
                return false; // 거리두기 안지킴
            }
            if (map[nx][ny] == 'O') {
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (map[nx][ny] == 'P'){
                    return false;
                }
            }
            if (map[nx][ny] == 'X') {
                // 파티션이 있는 경우 맨하튼 거리 2 인것을 검사할 수 있다.
                // X, O, P 다 올 수 있다. 굳이 q에 안넣어도 다음에 P 인 경우에 검사 할 수 있으니 pass
            }
        }

        return true;
    }
}