import java.io.*;

public class SaveToFile {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java SaveEmpToTextFile <serializedFilePath> <textFilePath>");
            return;
        }

        String serializedFilePath = args[0];
        String textFilePath = args[1];

        saveEmployeesToTextFile(serializedFilePath, textFilePath);
    }

    public static void saveEmployeesToTextFile(String serializedFilePath, String textFilePath) {
        try {
            File serializedFile = new File(serializedFilePath);
            File textFile = new File(textFilePath);

            if (!serializedFile.exists()) {
                System.out.println("Serialized file not found: " + serializedFilePath);
                return;
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(serializedFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));

            try {
                while (true) {
                    Employee employee = (Employee) objectInputStream.readObject();
                    writer.write(employee.getFirstname() + "/" + employee.getLastname() + "/" + employee.getAge());
                    writer.newLine();
                }
            } catch (EOFException e) {
                // End of file reached
            }

            objectInputStream.close();
            writer.close();

            System.out.println("Employees saved to text file successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
