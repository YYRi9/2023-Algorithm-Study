import java.util.*;

/*

수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

입력:
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

출력:
수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

 */

public class Main {

    static int N, K;
    static boolean[] visited = new boolean[100001];

    static class Point {
        int position, time;

        public Point(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N, 0));
        visited[N] = true;      // 방문했음을 표시
        int newPosition = 0;

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int i = 0; i < 3; i++) {
                if(i == 0)
                    newPosition = p.position * 2;
                else if(i == 1)
                    newPosition = p.position + 1;
                else
                    newPosition = p.position - 1;

                // 각 위치가 범위를 벗어난 경우 혹은 이미 방문했다면 continue.
                if(newPosition < 0 || newPosition > 100000 || visited[newPosition] == true)
                    continue;

                // 동생을 찾았다면 걸린 시간을 return
                if(newPosition == K){
                    System.out.println(p.time + 1);
                    return;
                }
                else{   // 동생을 찾지 못했다면 새로운 위치를 queue에 추가하고 방문표시
                    queue.add(new Point(newPosition, p.time + 1));
                    visited[newPosition] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();   // 수빈이의 위치
        K = sc.nextInt();   // 동생의 위치
        
        // 처음 수빈이의 위치가 동생보다 클 경우 1초에 -1만큼 움직여 동생을 찾는 것이 가장 빠르다.
        if(N >= K){
            System.out.println(N - K);
        }
        else{
            bfs();
        }
    }
}
