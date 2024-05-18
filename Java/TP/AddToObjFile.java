import java.io.*;

public class AddToObjFile {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java AddToObjFile <mainString> <subString> <outputFile>");
            return;
        }

        String mainString = args[0];
        String subString = args[1];
        String outputFile = args[2];

        String[] parts = mainString.split(subString);
        Employeee[] employees = new Employeee[parts.length / 3];

        // Assuming the parts are in the format: ID, firstName, lastName, ID, firstName, lastName, ...
        for (int i = 0; i < parts.length; i += 3) {
            int id = Integer.parseInt(parts[i]);
            String firstName = parts[i + 1];
            String lastName = parts[i + 2];
            employees[i / 3] = new Employeee(id, firstName, lastName);
        }

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(outputFile));
            for (Employeee employee : employees) {
                oos.writeObject(employee);
            }
            System.out.println("Serialization successful. Employees data saved to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
