import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// DP개념 이해를 위해 풀어본 문제

public class Main {

    static int stepNum;
    static int[] score;
    static int[] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        stepNum = Integer.parseInt(bfr.readLine());
        score = new int[301];
        DP = new int[301];

        // 인덱스는 0을 버리고 1부터 시작한다.
        for(int i = 1; i <= stepNum; i++) {
            score[i] = Integer.parseInt(bfr.readLine());
        }

        // DP 문제에서 중요한 것은 부분문제를 어떻게 구할 것인지이다.
        // 연속으로 3개의 계단을 밟으면 안된다.

        // 초기값 세팅
        DP[1] = score[1];
        DP[2] = Math.max(score[1] + score[2], score[2]);    // max를 사용하긴 했지만, 더 큰 점수를 얻어야 하기에 1번째 계단은 무조건 밟는 것이 좋다.

        for(int i = 3; i <= stepNum; i++) {
            // i-2번째 계단을 밟고 올라온 경우 (i-1번째 계단을 건너뛰기 때문에 DP[i-2]에 score[i]값을 추가해주면 됨)
            // i-1번째 계단을 밟고 올라온 경우 (i-2번째 계단을 반드시 건너뛰어야 하기 때문에 DP[i-3]+score[i-1]을 해주고 socre[i]값을 추가하면 됨)
            DP[i] = score[i] + Math.max(DP[i-2], DP[i-3] + score[i-1]);
        }
        // 마지막 계단을 반드시 밟아야 하므로 stepNum번째 값을 출력한다.
        System.out.println(DP[stepNum]);
    }
}
