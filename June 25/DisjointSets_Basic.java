import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DisjointSets_Basic {
    public static int find(int[] arr, int n) {
        if (arr[n] != n) {
            arr[n] = find(arr, arr[n]);
        }
        return arr[n];
    }
    public static void join(int[] arr, int x, int y) {
        int left = find(arr, x);
        int right = find(arr, y);
        arr[right] = left;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter value for N : ");
        int n = sc.nextInt();
        System.out.println("Enter value for M : ");
        int m = sc.nextInt();

        int[] leader = new int[n + 1];

        for (int i=1; i<=n; i++) {
            leader[i] = i;
        }

        for (int i=0; i<m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            join(leader, x, y);
        }

        Set<Integer> set = new HashSet<>();

        for (int i=1; i<=n; i++) {
            set.add(find(leader, i));
        }

        System.out.println(Arrays.toString(leader));

        System.out.println("Disjoint set size : " + set.size());
    }
}
