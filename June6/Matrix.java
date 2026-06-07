import java.util.*;

class Matrix {

    public static void display(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static long[] getFSMAX(long[][] dp, int row, int col) {
        long fmax = Math.max(dp[row][0], dp[row][col - 1]);
        long smax = Math.min(dp[row][0], dp[row][col - 1]);

        for (int i = 1; i < col - 1; i++) {
            if (dp[row][i] > fmax) {
                smax = fmax;
                fmax = dp[row][i];
            } else if (dp[row][i] > smax) {
                smax = dp[row][i];
            }
        }

        return new long[] { fmax, smax };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows and columns:");
        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] matrix = new int[r][c];

        System.out.println("Enter the matrix elements:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println("Matrix:");
        display(matrix);

        long[][] dp = new long[r][c];

        // Base case
        for (int j = 0; j < c; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Fill DP table
        for (int i = 1; i < r; i++) {
            long[] fsmax = getFSMAX(dp, i - 1, c);

            for (int j = 0; j < c; j++) {
                if (dp[i - 1][j] == fsmax[0]) {
                    dp[i][j] = fsmax[1] + matrix[i][j];
                } else {
                    dp[i][j] = fsmax[0] + matrix[i][j];
                }
            }
        }

        // Answer = maximum value in last row
        long ans = getFSMAX(dp, r - 1, c)[0];

        System.out.println("Maximum Path Sum = " + ans);

        sc.close();
    }
}
