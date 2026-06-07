import java.util.*;

class Check_0_1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String str = s.next();
        long flag = 0;

        for (char ch : str.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                flag |= (1L << (ch - '0'));
            }
        }

        System.out.println((flag == (1L << 10) - 1) ? "yes" : "no");
        s.close();
    }
}
