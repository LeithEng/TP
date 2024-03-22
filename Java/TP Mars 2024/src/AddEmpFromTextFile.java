import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddEmpFromTextFile {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java AddEmpFromTextFile <textFilePath> <serializedFilePath>");
            return;
        }

        String textFilePath = args[0];
        String serializedFilePath = args[1];

        List<Employee> employees = readEmployeesFromTextFile(textFilePath);

        for (Employee employee : employees) {
            addEmployeeToFile(employee, serializedFilePath);
        }
    }

    public static List<Employee> readEmployeesFromTextFile(String textFilePath) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(textFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the format of each line is: firstName lastName age
                String[] parts = line.split("/");
                if (parts.length == 3) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    int age = Integer.parseInt(parts[2]);

                    Employee employee = new Employee(firstName, lastName, age);
                    employees.add(employee);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static void addEmployeeToFile(Employee employee, String filename) {
        try {
            File file = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            AppendableObjectOutputStream objectOutputStream = new AppendableObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(employee);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
