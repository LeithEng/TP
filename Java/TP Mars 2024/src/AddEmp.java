import java.io.*;

public class AddEmp{
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("4 arguments needed: <firstName> <lastName> <age> <filePath>");
            return;
        }

        String firstName = args[0];
        String lastName = args[1];
        int age = Integer.parseInt(args[2]);
        String filePath = args[3];

        Employee employee = new Employee(firstName, lastName, age);
        addEmployeeToFile(employee, filePath);
        System.out.println("Employee added successfully");
    }
    public static void addEmployeeToFile(Employee employee, String filename) {
        try {
            File file = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            AppendableObjectOutputStream objectOutputStream = new AppendableObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(employee);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // Do not write a header, but reset:
        // this line added to fix an issue with appending objects to a file
        reset();
    }
}

