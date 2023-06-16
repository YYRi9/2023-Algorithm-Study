import java.util.*;

public class Main {

    static int row, column;
    static int[][] paint;      // 그림을 표시할 배열
    static int[][] checked;     // bfs에서 확인했는지 체크하고 몇 번째 그림인지 표시할 배열
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Point {
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(Point point, int paintNum) {
        Queue<Point> queue = new LinkedList<>();

        // 처음 방문한 지점을 queue에 넣는다.
        queue.add(point);
        checked[point.x][point.y] = paintNum;

        while(!queue.isEmpty()) {
            Point p = queue.poll();     // queue에서 객체를 하나 꺼낸다.

            for(int i = 0; i < 4; i++){ // 상하좌우로 한 번 씩 이동하여 그림이 이어지는지 확인한다.
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                // 만약 새로운 위치가 그림 배열의 크기를 벗어난다면 continue
                if(newX < 0 || newX >= row || newY < 0 || newY >= column)
                    continue;

                // 만약 그림이 칠해져 있고, 아직 방문하지 않았다면 그 위치를 queue에 넣고 방문했음을 표시한다.
                if(paint[newX][newY] == 1 && checked[newX][newY] == 0){
                    queue.add(new Point(newX, newY));
                    checked[newX][newY] = paintNum;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        row = sc.nextInt();
        column = sc.nextInt();
        int paintNum = 0;   // 그림의 개수를 표시할 변수

        paint = new int[row][column];
        checked = new int[row][column];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                paint[i][j] = sc.nextInt(); // 그림을 가져온다
            }
        }


        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(paint[i][j] == 1 && checked[i][j] == 0){ // 그림이 있다면
                    paintNum++;
                    bfs(new Point(i, j), paintNum);
                }
            }
        }

        // 만약 그림이 하나도 없다면 개수와 넓이를 0으로 출력한다.
        if(paintNum == 0)
            System.out.print("0\n0");
        else {
            int[] paintSize = new int[paintNum];

            for(int i = 0; i < row; i++)
                for(int j = 0; j < column; j++)
                    if(checked[i][j] != 0)
                        paintSize[checked[i][j] - 1]++;

            // 가장 넓이가 큰 그림을 찾는다.
            int max = paintSize[0];
            for(int n : paintSize)
                if(n > max)
                    max = n;

            System.out.print(paintNum + "\n" + max);

        }
    }
}
