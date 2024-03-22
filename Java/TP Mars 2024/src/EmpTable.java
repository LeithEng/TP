import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class EmpTable extends JFrame {
    private JTable employeeTable;

    public EmpTable() {
        setTitle("Employee Manager");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"First Name", "Last Name", "Age"};
        Object[][] rowData = getEmployeeData();

        employeeTable = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(employeeTable);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private Object[][] getEmployeeData() {
        ArrayList<Object[]> employeeList = new ArrayList<Object[]>();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/employees.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                while (true) {
                    Employee employee = (Employee) objectInputStream.readObject();
                    Object[] row = {employee.getFirstname(), employee.getLastname(), employee.getAge()};
                    employeeList.add(row);
                }
            } catch (EOFException e) {}

            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return employeeList.toArray(new Object[0][]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmpTable().setVisible(true);
            }
        });
    }
}