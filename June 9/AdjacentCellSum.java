import java.util.Scanner;

public class AdjacentCellSum {
    public static int adjacentCellSum(int[][] matrix, int row, int col) {
        int rowTotal = matrix.length;
        int colTotal = matrix[0].length;
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int sum = 0;
        for (int[] i : dir) {
            int newRow = row + i[0];
            int newCol = col + i[1];
            if (newRow >= 0 && newRow < rowTotal && newCol >= 0 && newCol < colTotal) {
                sum += matrix[newRow][newCol];
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("No. of rows : ");
        int r = sc.nextInt();
        System.out.print("No. of cols : ");
        int c = sc.nextInt();
        int[][] matrix = new int[r][c];
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                System.out.print("Enter the number");
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.print("Give row : ");
        int row = sc.nextInt();
        System.out.print("Give col : ");
        int col = sc.nextInt();
        int ans = adjacentCellSum(matrix, row, col);
        System.out.println("Adjacent cell sum of row "+row+" & col "+col+" is : "+ ans);
    }
}

