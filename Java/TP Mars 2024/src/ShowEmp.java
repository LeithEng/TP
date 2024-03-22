import java.io.*;

public class ShowEmp {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ShowEmployeesFromFile <filePath>");
            return;
        }

        String filePath = args[0];
        showEmployeesFromFile(filePath);
    }

    public static void showEmployeesFromFile(String filename) {
        try {
            File file = new File(filename);

            if (!file.exists()) {
                System.out.println("File not found: " + filename);
                return;
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                while (true) {
                    Employee employee = (Employee) objectInputStream.readObject();
                    employee.printEmp();
                }
            } catch (EOFException e) {
                // End of file reached
            }

            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
