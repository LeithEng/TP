import java.util.regex.*;

class RegexSmall {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("ab");
        Matcher m = p.matcher("abaaaba");
        boolean b = false;
        while (b=m.find())
        {
            System.out.print(m.start() + " ");
        }
    }
}