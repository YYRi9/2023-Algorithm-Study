import java.util.Scanner;

public class Main {

    static int[][] dp;
    static int cageSize;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        cageSize = sc.nextInt();
        dp = new int[cageSize + 1][3];

        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for(int i = 2; i <= cageSize; i++) {
            // 사자가 없는경우
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            // 사자가 왼쪽 케이지에 있는 경우
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
            // 사자가 오른쪽 케이지에 있는 경우
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
        }

        System.out.println((dp[cageSize][0] + dp[cageSize][1] + dp[cageSize][2]) % 9901);
    }
}
