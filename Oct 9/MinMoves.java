import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MinMoves {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    public static int bfs(char[][] grid, int R, int C, int sr, int sc) {
        LinkedList<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[R][C][4];
        int[][][] dist = new int[R][C][4];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        for (int d = 0; d < 4; d++) {
            q.add(new int[] {sr, sc, d, 0});
            dist[sr][sc][d] = 0;
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int dir = curr[2];
            int turns = curr[3];

            if (grid[r][c] == 'T') {
                return turns;
            }

            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr >= 0 && nc >= 0 && nr < R && nc < C && grid[nr][nc] == '0') {
                    int newTurn = turns + (i == dir ? 0 : 1);
                    if (newTurn < dist[nr][nc][i]) {
                        dist[nr][nc][i] = newTurn;
                        visited[nr][nc][i] = true;

                        if (i == dir) {
                            q.addFirst(new int[] {nr, nc, i, newTurn});
                        } else {
                            q.addLast(new int[] {nr, nc, i, newTurn});
                        }
                    }
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        char[][] grid = new char[m][n];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }

        int startRow = -1, startCol = -1;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        int res = bfs(grid, m, n, startRow, startCol);

        System.out.println(res != -1 ? res : "Not possible");
    }
}
