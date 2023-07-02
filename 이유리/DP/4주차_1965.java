import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int boxNum;
    static int[] boxSize;
    static int[] DP;
    public static void main(String[] args) throws IOException{

        BufferedReader bfw = new BufferedReader(new InputStreamReader(System.in));

        boxNum = Integer.parseInt(bfw.readLine());
        boxSize = new int[boxNum];
        DP = new int[boxNum];

        StringTokenizer st = new StringTokenizer(bfw.readLine());

        for(int i = 0; i < boxNum; i++) {
            boxSize[i] = Integer.parseInt(st.nextToken());
            DP[i] = 1;  // 모든 상자는 처음에 1개이기 때문에 1로 초기화
        }

        // i번째 이전의 상자를 모두 확인하고 DP값을 변경한다.
        for(int i = 1; i < boxNum; i++) {
            for(int j = 0; j < i; j++) {
                if(boxSize[j] < boxSize[i]) {
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                }
            }
        }

        int max = 0;
        for(int n : DP){
            if(n > max)
                max = n;
        }

        System.out.println(max);
    }
}
