package 성지현.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WEEK1_2178 {
    static int[][] Miro;
    static boolean[][] visited;
    static int N;
    static int M;
    static int[] dx = { -1, 1, 0, 0 }; 
    static int[] dy = { 0, 0, -1, 1 }; 
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Miro = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String miro = st.nextToken();
            for(int ii = 0; ii < M; ii++){
                Miro[i][ii] = miro.charAt(ii) - '0';
            }
        }
        visited = new boolean[N][M];
		visited[0][0] = true;
        move(0, 0);
        System.out.print(Miro[N-1][M-1]);
    }
    public static void move(int n, int m){
    	Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {n, m});
		
		while(!q.isEmpty()) {
			int now[] = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                	continue;
        		if (visited[nextX][nextY] || Miro[nextX][nextY] == 0)
                    continue;
                    
		        q.add(new int[] {nextX, nextY});
        		Miro[nextX][nextY] = Miro[nowX][nowY] + 1;
                visited[nextX][nextY] = true;
			}
		}
	}
}	