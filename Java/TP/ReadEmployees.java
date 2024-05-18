import java.io.*;

public class ReadEmployees {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ReadEmployees <inputFile>");
            return;
        }

        String inputFile = args[0];

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(inputFile));
            System.out.println("Contents of " + inputFile + ":");
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Employeee) {
                        Employeee employee = (Employeee) obj;
                        System.out.println(employee);
                    }
                } catch (EOFException e) {
                    // End of file reached
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
