import java.util.Scanner;

public class StaticRangeMinimumQuery_CSES {
    static int[] arr;
    static int[] seg;
    public static void build(int node, int start, int end) {
        if (start == end) {
            seg[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        seg[node] = Math.min(seg[2 * node], seg[2 * node + 1]);
    }
    public static int query(int node, int start, int end, int l, int r) {
        // No Overlap
        if (end < l || start > r) {
            return Integer.MAX_VALUE;
        }
        // Full Overlap
        if (l <= start && end <= r) {
            return seg[node];
        }
        // Partial Overlap
        int mid = (start + end) / 2;
        int left = query(2 * node, start, mid, l, r);
        int right = query(2 * node + 1, mid + 1, end, l, r);
        return Math.min(left, right);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        arr = new int[n + 1];
        seg = new int[4 * (n + 1)];

        for (int i=1; i<=n; i++) {
            arr[i] = sc.nextInt();
        }

        build(1, 1, n);

        while (q-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(query(1, 1, n, l, r));
        }
    }
}

//import java.io.*;
//import java.util.*;
//
//public class Main {
//
//    static int[] arr;
//    static int[] seg;
//
//    static class FastScanner {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        String next() throws IOException {
//            while (st == null || !st.hasMoreElements()) {
//                st = new StringTokenizer(br.readLine());
//            }
//            return st.nextToken();
//        }
//
//        int nextInt() throws IOException {
//            return Integer.parseInt(next());
//        }
//    }
//
//    public static void build(int node, int start, int end) {
//        if (start == end) {
//            seg[node] = arr[start];
//            return;
//        }
//
//        int mid = (start + end) / 2;
//
//        build(2 * node, start, mid);
//        build(2 * node + 1, mid + 1, end);
//
//        seg[node] = Math.min(seg[2 * node], seg[2 * node + 1]);
//    }
//
//    public static int query(int node, int start, int end, int l, int r) {
//
//        // No overlap
//        if (end < l || start > r) {
//            return Integer.MAX_VALUE;
//        }
//
//        // Complete overlap
//        if (l <= start && end <= r) {
//            return seg[node];
//        }
//
//        // Partial overlap
//        int mid = (start + end) / 2;
//
//        int left = query(2 * node, start, mid, l, r);
//        int right = query(2 * node + 1, mid + 1, end, l, r);
//
//        return Math.min(left, right);
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        FastScanner sc = new FastScanner();
//
//        int n = sc.nextInt();
//        int q = sc.nextInt();
//
//        arr = new int[n + 1];
//        seg = new int[4 * (n + 1)];
//
//        for (int i = 1; i <= n; i++) {
//            arr[i] = sc.nextInt();
//        }
//
//        build(1, 1, n);
//
//        StringBuilder sb = new StringBuilder();
//
//        while (q-- > 0) {
//            int l = sc.nextInt();
//            int r = sc.nextInt();
//
//            sb.append(query(1, 1, n, l, r)).append('\n');
//        }
//
//        System.out.print(sb);
//    }
//}