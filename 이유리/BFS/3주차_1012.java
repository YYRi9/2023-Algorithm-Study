import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map; // 배추를 재배하는 땅
    static int[][] visited; // 방문했는지 여부
    static int M, N, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // Queue에 넣기위해 Point 클래스를 만든다.
    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);
        visited[point.x][point.y] = 1;  // 방문했음을 표시

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                // map을 벗어난다면 continue
                if(newX < 0 || newX >= N || newY < 0 || newY >= M)
                    continue;

                // 배추가 있는곳인데 방문하지 않았다면
                if(map[newX][newY] == 1 && visited[newX][newY] == 0) {
                    queue.add(new Point(newX, newY));
                    visited[newX][newY] = 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine());
        int count;

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine());
            count = 0;

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new int[N][M];

            for(int j = 0; j < K; j++) {
                st = new StringTokenizer(bf.readLine());
                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                map[n][m] = 1;  // 배열에 배추의 위치를 표시한다.
            }

            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    // 만약 배추가 있고 아직 방문하지 않았다면
                    if(map[j][k] == 1 && visited[j][k] == 0) {
                        count++;    // 방문 횟수(배추흰지렁이의 개수와 같음)를 ++
                        bfs(new Point(j, k));   // bfs를 실행
                    }
                }
            }
            System.out.println(count);
        }
    }
}
