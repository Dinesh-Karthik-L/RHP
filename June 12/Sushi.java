import java.util.Arrays;
import java.util.Scanner;

public class Sushi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] shari = new int[N];
        int[] neta = new int[M];

        for (int i=0; i<N; i++) shari[i] = sc.nextInt();

        for (int i=0; i<M; i++) neta[i] = sc.nextInt();

        Arrays.sort(shari);
        Arrays.sort(neta);

        int i=0, j=0, cnt=0;

        while (i < N && j < M) {
            if (neta[j] <= 2*shari[i]) {
                cnt++;
                i++;
                j++;
            }
            else {
                i++;
            }
        }

        System.out.print(cnt);
    }
}
