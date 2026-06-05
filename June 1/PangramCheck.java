public class PangramCheck {
    public static void main(String[] args) {
        String str = "thequickbrownfoxjumpsoverthelazydog";
        int flag = 0;
        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                flag = flag | (1 << (ch - 'a'));
            }
            else if (ch >= 'A' && ch <= 'Z') {
                flag = flag | (1 << (ch - 'A'));
            }
        }
        System.out.println(flag == (1 << 26) - 1 ? "Pangram" : "Not pangram");
    }
}
