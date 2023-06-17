import java.io.*;
import java.util.*;

public class Main {

    static int[][] tomato;
    static int[][] checked;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int M, N;

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs() {

        Queue<Point> queue = new LinkedList<>();

        // 처음에 익은 토마토가 여러 개 인 경우 미리 큐에 넣어서 bfs할 수 있도록 한다.
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(tomato[i][j] == 1){
                    queue.add(new Point(i, j));
                    checked[i][j] = 1;
                }
            }
        }

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if(newX < 0 || newX >= M || newY < 0 || newY >=N)
                    continue;

                if(tomato[newX][newY] == 0 && checked[newX][newY] == 0){
                    queue.add(new Point(newX, newY));
                    tomato[newX][newY] = tomato[p.x][p.y] + 1;
                    checked[newX][newY] = 1;

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int day = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tomato = new int[M][N];
        checked = new int[M][N];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) {
                tomato[i][j] =Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        int max = 0;

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(tomato[i][j] == 0){  // 만약 안익은 토마토가 있다면 -1출력
                    System.out.print("-1");
                    return;
                }
                else if(tomato[i][j] > max)
                    max = tomato[i][j];
            }
        }

        System.out.print(max - 1);
    }
}
