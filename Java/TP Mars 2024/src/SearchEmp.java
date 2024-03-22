import java.io.*;

public class SearchEmp {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java SearchEmp <searchTerm> <filePath>");
            return;
        }

        String searchTerm = args[0];
        String filePath = args[1];
        searchEmployeeFromFile(searchTerm, filePath);
    }

    public static void searchEmployeeFromFile(String searchTerm, String filename) {
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
                    if (employeeContainsSearchTerm(employee, searchTerm)) {
                        employee.printEmp();
                    }
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

    private static boolean employeeContainsSearchTerm(Employee employee, String searchTerm) {
            String f=employee.getFirstname();
            String l= employee.getLastname();
            int age=employee.getAge();
            String a=Integer.toString(age);
            return (f.contains(searchTerm)||l.contains(searchTerm)||a.contains(searchTerm));
    }
}
