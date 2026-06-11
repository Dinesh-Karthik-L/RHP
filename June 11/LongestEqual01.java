import java.util.HashMap;
import java.util.Map;

public class LongestEqual01 {
    public static int longestSubstring(String str) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;

        // Initially maintain a map with (0, -1)
        map.put(0, -1);

        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == '1') sum++;
            else sum--;

            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            }
            else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
    public static void main(String[] args) {
        String str = "000001010111001011";
        System.out.println(longestSubstring(str));
    }
}
