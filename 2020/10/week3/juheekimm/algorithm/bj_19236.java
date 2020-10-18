import java.io.*;
import java.util.*;

public class Main {
    static class Fish {
        int x, y, dir;
        public Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Fish(int x, int y, int dir) {
            this(x, y);
            this.dir = dir;
        }
    }

    static int max, enableMax;
    static Fish[] fishs;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[4][4];
        fishs = new Fish[17];
        fishs[0] = new Fish(0, 0);

        for (int i = 1; i <= 16 ; i++) {
            enableMax += i;
        }

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                fishs[map[i][j]] = new Fish(i, j, Integer.parseInt(st.nextToken()) - 1);
            }
        }
        backTracking(new Fish(0, 0), map.clone(), fishs.clone(), 0, 1);
        System.out.println(max);
    }

    private static boolean backTracking(Fish shark, int[][] tempMap, Fish[] tempFish, int total, int tern) {
        //1. 상어가 먹고 시작
        total += tempMap[shark.x][shark.y];
        shark.dir = tempFish[tempMap[shark.x][shark.y]].dir;
        tempFish[tempMap[shark.x][shark.y]].x = -1;
        tempMap[shark.x][shark.y] = 100;

        if (total == enableMax) return true;
        if (max < total) max = total;

        allFishMove(tempMap, tempFish);

        int nx = shark.x;
        int ny = shark.y;
        //상어 움직이기 전 지워주기, 저기 가서 또 새로 그려야 하니까
        tempMap[shark.x][shark.y] = 0;

        for (int i = 0; i < 4; i++) {
            nx += dx[shark.dir];
            ny += dy[shark.dir];

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break;
            if (tempMap[nx][ny] == 0) continue;

            int[][] newMap = new int[4][4];
            for (int j = 0; j < 4; j++)
                newMap[j] = tempMap[j].clone();

            Fish[] newFish = new Fish[17];
            for (int j = 0; j <= 16; j++)
                newFish[j] = new Fish(tempFish[j].x, tempFish[j].y, tempFish[j].dir);

            if (backTracking(new Fish(nx, ny), newMap, newFish, total, tern + 1)) return true;
        }
        return false;
    }

    private static void allFishMove(int[][] tempMap, Fish[] tempFish) {
        for (int i = 1; i <= 16; i++) {
            if (tempFish[i].x == -1) continue;

            for (int d = 0; d < 8; d++) {
                int nx = tempFish[i].x + dx[(tempFish[i].dir + d) % 8];
                int ny = tempFish[i].y + dy[(tempFish[i].dir + d) % 8];

                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || tempMap[nx][ny] == 100)
                    continue;

                tempFish[i].dir = (tempFish[i].dir + d) % 8;
                fishLocChange(tempFish[i].x, tempFish[i].y, i, nx, ny, tempMap[nx][ny], tempMap, tempFish);
                break;
            }
        }
    }

    //기존애, 옮길애
    private static void fishLocChange(int px, int py, int pNum, int nx, int ny, int nNum, int[][] tempMap, Fish[] tempFish) {
        tempMap[px][py] = nNum;
        tempMap[nx][ny] = pNum;

        tempFish[pNum].x = nx;
        tempFish[pNum].y = ny;

        if (nNum != 0) {
            tempFish[nNum].x = px;
            tempFish[nNum].y = py;
        }
    }
}