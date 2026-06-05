public class GenerateCombination {
    public static void generateCombinations(String str) {
        int n = str.length();
        for (int i = 0; i < (1 << n); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(str.charAt(j));
                }
            }
            System.out.println(sb.toString());
        }
    }
    public static void main(String[] args) {
        generateCombinations("ABC");
    }
}