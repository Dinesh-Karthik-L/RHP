import java.util.Scanner;

public class KarenAndCoffee {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int recipes = in.nextInt();
        int limit = in.nextInt();
        int queries = in.nextInt();

        int range = 200000;

        int[] mark = new int[range + 2];

        for (int x = 0; x < recipes; x++) {
            int start = in.nextInt();
            int end = in.nextInt();

            mark[start]++;
            mark[end + 1]--;
        }

        int[] freq = new int[range + 1];

        for (int pos = 1; pos <= range; pos++) {
            freq[pos] = freq[pos - 1] + mark[pos];
        }

        int[] valid = new int[range + 1];

        for (int pos = 1; pos <= range; pos++) {
            if (freq[pos] >= limit) {
                valid[pos] = 1;
            }
        }

        int[] pref = new int[range + 1];

        for (int pos = 1; pos <= range; pos++) {
            pref[pos] = pref[pos - 1] + valid[pos];
        }

        while (queries-- > 0) {
            int left = in.nextInt();
            int right = in.nextInt();

            System.out.println(pref[right] - pref[left - 1]);
        }
    }
}
