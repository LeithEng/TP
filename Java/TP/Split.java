import java.util.regex.*;

public class Split {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Split <mainString> <subString>");
            return;
        }

        String mainString = args[0];
        String subString = args[1];

        String[] parts = mainString.split(Pattern.quote(subString));
        for (String part : parts) {
            System.out.println(part);
        }
    }
}
