import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int PCNum;           // 총 컴퓨터의 개수
    static int networkNum;      // 네트워크 상 연결된 컴퓨터 쌍의 수
    static int[][] PCNetwork;   // 네트워크 컴퓨터 쌍을 저장할 배열
    static int infectionPC;     // 1번 컴퓨터로 인해 감염된 컴퓨터의 개수
    static boolean[] checkedPC; // 컴퓨터 바이러스 감염 여부를 확인했는지 표시할 배열

    static public void bfs(int VirusPC) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(VirusPC);         // 첫 번째로 감염된 PC를 큐에 넣는다. (이 문제에서는 1번 PC)
        checkedPC[VirusPC] = true;  // PC를 확인했음을 배열에 표시한다.

        while(!queue.isEmpty()) {   // 큐가 빌 때까지 반복
            int PC = queue.poll();  // 큐에서 꺼낸다.
            int nextPC = 0;

            // 순서쌍에서 현재 꺼낸 PC와 연결된 PC를 찾아 nextPC에 저장한다.
            for(int i = 0; i < networkNum; i++) {
                if(PCNetwork[i][0] == PC)
                    nextPC = PCNetwork[i][1];
                else if(PCNetwork[i][1] == PC)
                    nextPC = PCNetwork[i][0];
                else    // 연결된 PC가 없으면 continue.
                    continue;

                // 연결된 PC가 아직 검사하지 않은 PC면 큐에 추가
                if(checkedPC[nextPC] == false){
                    queue.add(nextPC);
                    checkedPC[nextPC] = true;
                    infectionPC++;  // 감염된 PC개수를 +1한다.
                }
            }
        }
        System.out.println(infectionPC);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        infectionPC = 0;
        PCNum = sc.nextInt();
        networkNum = sc.nextInt();
        PCNetwork = new int[networkNum][2]; // 열의 개수는 2로 고정
        checkedPC = new boolean[PCNum + 1];

        for(int i = 0; i < networkNum; i++) {
            PCNetwork[i][0] = sc.nextInt();
            PCNetwork[i][1] = sc.nextInt();
        }
        bfs(1);
    }
}
