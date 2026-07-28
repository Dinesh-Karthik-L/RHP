//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int q = sc.nextInt();
//
//        int[] arr = new int[n];
//        for (int i=0; i<n; i++) {
//            arr[i] = sc.nextInt();
//        }
//
//        int[] prefixSum = new int[n + 1];
//        prefixSum[1] = arr[0];
//        for (int i=2; i <= n; i++) {
//            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
//        }
//
//        while (q-- > 0) {
//            int i = sc.nextInt();
//            int j = sc.nextInt();
//            System.out.println(prefixSum[j] - prefixSum[i - 1]);
//        }
//    }
//}

import java.io.*;

public class StaticRangeSumQuery_CSES {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int q = fs.nextInt();

        long[] prefix = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + fs.nextInt();
        }

        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            sb.append(prefix[b] - prefix[a - 1]).append('\n');
        }

        System.out.print(sb);
    }
}
