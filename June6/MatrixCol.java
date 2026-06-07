import java.util.*;

class MatrixCol {

    public static long[] getFSMAX(long[][] dp, int rows, int col) {
        long fmax = Long.MIN_VALUE;
        long smax = Long.MIN_VALUE;
        int maxRow = -1;

        for (int i = 0; i < rows; i++) {
            if (dp[i][col] > fmax) {
                smax = fmax;
                fmax = dp[i][col];
                maxRow = i;
            } else if (dp[i][col] > smax) {
                smax = dp[i][col];
            }
        }

        return new long[] {fmax, smax, maxRow};
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of rows and columns:");
        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] matrix = new int[r][c];
        long[][] dp = new long[r][c];

        System.out.println("Enter matrix values:");

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = sc.nextInt();

                if (j == 0) {
                    dp[i][j] = matrix[i][j];
                }
            }
        }

        for (int j = 1; j < c; j++) {

            long[] fsmax = getFSMAX(dp, r, j - 1);

            long firstMax = fsmax[0];
            long secondMax = fsmax[1];
            int maxRow = (int) fsmax[2];

            for (int i = 0; i < r; i++) {

                if (i == maxRow) {
                    dp[i][j] = secondMax + matrix[i][j];
                } else {
                    dp[i][j] = firstMax + matrix[i][j];
                }
            }
        }

        long answer = getFSMAX(dp, r, c - 1)[0];

        System.out.println("The maximum path sum is: " + answer);

        sc.close();
    }
}