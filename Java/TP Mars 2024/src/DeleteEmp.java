import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteEmp {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java DeleteEmp <employeeFirstNameToDelete> <employeeLastNameToDelete> <filePath>");
            return;
        }

        String first = args[0];
        String last = args[1];
        String filePath = args[2];
        deleteEmployeeFromFile(first, last, filePath);
    }

    public static void deleteEmployeeFromFile(String first, String last, String filename) {
        try {
            File file = new File(filename);

            if (!file.exists()) {
                System.out.println("File not found: " + filename);
                return;
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            List<Employee> employees = new ArrayList<>();

            try {
                while (true) {
                    Employee employee = (Employee) objectInputStream.readObject();
                    employees.add(employee);
                }
            } catch (EOFException e) {
                // End of file reached
            }

            objectInputStream.close();
            fileInputStream.close();

            // Remove the employee with the specified first and last name
            Iterator<Employee> iterator = employees.iterator();
            while (iterator.hasNext()) {
                Employee emp = iterator.next();
                if (emp.getFirstname().equals(first) && emp.getLastname().equals(last)) {
                    iterator.remove();
                    System.out.println("The employee named " + first + " " + last + " has been removed from the file");
                }
            }

            // Create a temporary file
            File tempFile = new File(filename + ".tmp");

            // Write the updated list to the temporary file
            ObjectOutputStream tempObjectOutputStream = new ObjectOutputStream(new FileOutputStream(tempFile));
            for (Employee employee : employees) {
                tempObjectOutputStream.writeObject(employee);
            }
            tempObjectOutputStream.close();

            // Replace the original file with the temporary file
            if (file.delete() && tempFile.renameTo(file)) {
                System.out.println("File updated successfully.");
            } else {
                System.out.println("Failed to update the file.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
