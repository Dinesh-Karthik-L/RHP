public class LongestCommonSubstring {
    public static int lcs(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1+1][len2+1];
        int cnt = 0;
        for (int i=1; i<len1+1; i++) {
            for (int j=1; j<len2+1; j++) {
                if (s1.charAt(i-1) == s2.charAt(i-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    cnt = Math.max(cnt, dp[i][j]);
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        String str1 = "morning";
        String str2 = "evening";
        int res = lcs(str1, str2);
        System.out.println("Longest Common Substring length : " + res);
    }
}
