import java.io.*;

public class CatFile1 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java CatFile <filename>");
            return;
        }

        String filename = args[0];
        printFileContent(filename);
    }

    private static void printFileContent(String filename) {
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}