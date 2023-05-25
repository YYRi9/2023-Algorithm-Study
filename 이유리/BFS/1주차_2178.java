import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class Main {

    // 클래스를 외부에서 선언하면 백준에서 인식을 못하는 문제가 있어, 내부클래스로 선언
    // 현재 위치를 저장할 클래스
    static class Point {

        private int x = 0;
        private int y = 0;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY() {
            return y;
        }
    }

    // 상하좌우 이동을 위한 dx, xy
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;      // 미로
    static boolean[][] visit; // 방문한 곳 표시
    static int[][] distance;  // 해당 노드까지 방문하는데 걸린 거리

    static int n, m;

    public static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(x,y));
        visit[x][y] = true; // 방문했음을 표시
        distance[x][y] = 1; // 거리는 1부터 시작


        while(!queue.isEmpty()) {
            Point p = queue.poll(); // 큐에서 꺼낸다.

            // p의 주변 노드들을 큐에 집어넣는데, 미로에서 1인 경우만 집어넣도록 한다.
            // 만약 이미 방문했다면 집어넣지 않는다.
            // 방문한 노드를 표시한다.
            // 거리값은 p의 값에서 +1한다.

            for(int i = 0; i < 4; i++) {
                int newX = p.getX()+dx[i];
                int newY = p.getY()+dy[i];
               
                // 미로의 범위를 벗어나면 제외시킴
                if(newX < 0 || newX >= n || newY < 0 || newY >= m)
                    continue;
                
                if(visit[newX][newY] == false && map[newX][newY] == '1'){
                    queue.add(new Point(newX, newY));
                    visit[newX][newY] = true;     // 방문했음을 표시
                    distance[newX][newY] = distance[p.getX()][p.getY()] + 1;    // 방문 거리를 표시
                }
            }

        }

        System.out.println(distance[n-1][m-1]);   // 도착지까지 걸린 거리를 출력한다.

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new char[n][m];
        visit = new boolean[n][m];
        distance = new int[n][m];
        String tmp;


        for(int i = 0; i < n; i++) {
            tmp = sc.next();
            for(int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        bfs(0,0);

    }
}
