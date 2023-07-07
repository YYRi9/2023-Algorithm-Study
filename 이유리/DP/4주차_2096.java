import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[][] score;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bfr.readLine());

        score = new int[n+1][3];
        dp = new int[n+1][3];

        // 점수 정보를 가져와 저장
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bfr.readLine());
            for(int j = 0; j < 3; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();     // dp 배열 초기화
        getMax();   // 최대값 구하기
        System.out.print(" ");
        init();
        getMin();   // 최소값 구하기
    }
    
    // dp 배열 초기화
    static public void init() {
        dp[0][0] = score[0][0];
        dp[0][1] = score[0][1];
        dp[0][2] = score[0][2];
    }

    // 최소값을 구하고 출력하는 메서드
    static public void getMin() {
        for(int i = 1; i < n; i++) {
            dp[i][0] = score[i][0] + Math.min(dp[i-1][0], dp[i-1][1]);                          // i번째 줄의 1번째 값이 선택될 때 가장 작은 값인 경우
            dp[i][1] = score[i][1] + Math.min(Math.min(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);    // i번째 줄의 2번째 값이 선택될 때 가장 작은 값인 경우
            dp[i][2] = score[i][2] + Math.min(dp[i-1][1], dp[i-1][2]);                          // i번째 줄의 3번째 값이 선택될 때 가장 작은 값인 경우
        }
        System.out.print(Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]));               // n번째 줄에서 가장 작은 값을 출력
    }

    // 최대값을 구하고 출력하는 메서드
    static public void getMax() {
        for(int i = 1; i < n; i++) {
            dp[i][0] = score[i][0] + Math.max(dp[i-1][0], dp[i-1][1]);                          // i번째 줄의 1번째 값이 선택될 때 가장 큰 값인 경우
            dp[i][1] = score[i][1] + Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);    // i번째 줄의 2번째 값이 선택될 때 가장 큰 값인 경우
            dp[i][2] = score[i][2] + Math.max(dp[i-1][1], dp[i-1][2]);                          // i번째 줄의 3번째 값이 선택될 때 가장 큰 값인 경우
        }
        System.out.print(Math.max(Math.max(dp[n-1][0], dp[n-1][1]), dp[n-1][2]));               // n번째 줄에서 가장 큰 값을 출력
    }
}
