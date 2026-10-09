
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Sofa {
    int fsr, fsc, ssr, ssc;
    char dir;
    int moves;

    public Sofa(int fsr, int fsc, int ssr, int ssc, char dir, int moves) {
        this.fsr = fsr;
        this.fsc = fsc;
        this.ssr = ssr;
        this.ssc = ssc;
        this.dir = dir;
        this.moves = moves;
    }
}

public class SofaPrb_TCS {

    static int fsr = -1, fsc = -1, ssr = -1, ssc = -1;
    static int tfr = -1, tfc = -1, tsr = -1, tsc = -1;
    static int sofaCnt = 0, targetCnt = 0;

    static int m, n;
    static char[][] mat;
    static boolean[][][] visited;

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void getCoordinates() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (mat[i][j] == 's') {
                    sofaCnt++;

                    if (sofaCnt == 1) {
                        fsr = i;
                        fsc = j;
                    } else {
                        ssr = i;
                        ssc = j;
                    }
                }

                if (mat[i][j] == 'S') {
                    targetCnt++;

                    if (targetCnt == 1) {
                        tfr = i;
                        tfc = j;
                    } else {
                        tsr = i;
                        tsc = j;
                    }
                }
            }
        }
    }

    static boolean isFree(int r, int c) {
        return r >= 0 && r < m &&
                c >= 0 && c < n &&
                (mat[r][c] == '0' ||
                        mat[r][c] == 's' ||
                        mat[r][c] == 'S');
    }

    static Sofa normalize(int r1, int c1, int r2, int c2, int moves) {
        char dir;

        if (r1 == r2) {
            dir = 'H';

            if (c1 > c2) {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
        } else {
            dir = 'V';

            if (r1 > r2) {
                int temp = r1;
                r1 = r2;
                r2 = temp;
            }
        }

        return new Sofa(r1, c1, r2, c2, dir, moves);
    }

    static void addState(Queue<Sofa> q, int r1, int c1,
                         int r2, int c2, int moves) {

        if (!isFree(r1, c1) || !isFree(r2, c2)) {
            return;
        }

        Sofa next = normalize(r1, c1, r2, c2, moves);

        int direction = next.dir == 'H' ? 0 : 1;

        if (!visited[next.fsr][next.fsc][direction]) {
            visited[next.fsr][next.fsc][direction] = true;
            q.add(next);
        }
    }

    static boolean reachedTarget(Sofa curr, Sofa target) {
        return curr.fsr == target.fsr &&
                curr.fsc == target.fsc &&
                curr.ssr == target.ssr &&
                curr.ssc == target.ssc;
    }

    static int bfs(Sofa start, Sofa target) {

        Queue<Sofa> q = new LinkedList<>();
        visited = new boolean[m][n][2];

        int direction = start.dir == 'H' ? 0 : 1;
        visited[start.fsr][start.fsc][direction] = true;
        q.add(start);

        while (!q.isEmpty()) {
            Sofa curr = q.poll();

            if (reachedTarget(curr, target)) {
                return curr.moves;
            }

            for (int k = 0; k < 4; k++) {
                int r1 = curr.fsr + dr[k];
                int c1 = curr.fsc + dc[k];

                int r2 = curr.ssr + dr[k];
                int c2 = curr.ssc + dc[k];

                addState(q, r1, c1, r2, c2, curr.moves + 1);
            }

            if (curr.dir == 'H') {
                int r = curr.fsr;
                int c = curr.fsc;

                if (isFree(r - 1, c) && isFree(r - 1, c + 1)) {
                    addState(q, r - 1, c, r, c, curr.moves + 1);
                    addState(q, r - 1, c + 1, r, c + 1, curr.moves + 1);
                }
                if (isFree(r + 1, c) && isFree(r + 1, c + 1)) {
                    addState(q, r, c, r + 1, c, curr.moves + 1);
                    addState(q, r, c + 1, r + 1, c + 1, curr.moves + 1);
                }

            } else {
                int r = curr.fsr;
                int c = curr.fsc;
                if (isFree(r, c - 1) && isFree(r + 1, c - 1)) {
                    addState(q, r, c - 1, r, c, curr.moves + 1);
                    addState(q, r + 1, c - 1, r + 1, c, curr.moves + 1);
                }
                if (isFree(r, c + 1) && isFree(r + 1, c + 1)) {
                    addState(q, r, c, r, c + 1, curr.moves + 1);
                    addState(q, r + 1, c, r + 1, c + 1, curr.moves + 1);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();

        mat = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.next().charAt(0);
            }
        }

        getCoordinates();

        if (sofaCnt != 2 || targetCnt != 2) {
            System.out.println("Impossible");
            return;
        }

        Sofa start = normalize(fsr, fsc, ssr, ssc, 0);
        Sofa target = normalize(tfr, tfc, tsr, tsc, 0);

        int result = bfs(start, target);

        if (result == -1) {
            System.out.println("Impossible");
        } else {
            System.out.println(result);
        }
    }
}