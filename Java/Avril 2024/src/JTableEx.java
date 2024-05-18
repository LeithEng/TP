import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class JTableEx extends JFrame {
    private JTable employeeTable;
    private Object[][] rowData;

    public JTableEx() {
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"First Name", "Last Name", "Age"};
        rowData = getEmployeeData();

        employeeTable = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(employeeTable);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton sortButton = new JButton("Sort by Age");
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByAge();
            }
        });
        buttonPanel.add(sortButton);

        JButton sortFirstNameButton = new JButton("Sort by First Name");
        sortFirstNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByFirstName();
            }
        });
        buttonPanel.add(sortFirstNameButton);

        JButton sortLastNameButton = new JButton("Sort by Last Name");
        sortLastNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByLastName();
            }
        });
        buttonPanel.add(sortLastNameButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
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

    private void sortByAge() {
        Arrays.sort(rowData, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                Integer age1 = (Integer) o1[2];
                Integer age2 = (Integer) o2[2];
                return age1.compareTo(age2);
            }
        });
        refreshTable();
    }

    private void sortByLastName() {
        Arrays.sort(rowData, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                String lastName1 = (String) o1[1];
                String lastName2 = (String) o2[1];
                return lastName1.compareTo(lastName2);
            }
        });
        refreshTable();
    }

    private void sortByFirstName() {
        Arrays.sort(rowData, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                String firstName1 = (String) o1[0];
                String firstName2 = (String) o2[0];
                return firstName1.compareTo(firstName2);
            }
        });
        refreshTable();
    }

    private void refreshTable() {
        getContentPane().removeAll();

        String[] columnNames = {"First Name", "Last Name", "Age"};
        employeeTable = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout()); // Panel for buttons
        JButton sortButton = new JButton("Sort by Age");
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByAge();
            }
        });
        buttonPanel.add(sortButton);

        JButton sortFirstNameButton = new JButton("Sort by First Name");
        sortFirstNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByFirstName();
            }
        });
        buttonPanel.add(sortFirstNameButton);

        JButton sortLastNameButton = new JButton("Sort by Last Name");
        sortLastNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByLastName();
            }
        });
        buttonPanel.add(sortLastNameButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        repaint();
    }
    private void saveDataToFile(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Object[] row : rowData) {
                int age = Integer.parseInt((String)row[2]);
                Employee employee = new Employee((String)row[0], (String)row[1], age);

                objectOutputStream.writeObject(employee);
            }

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDataFromTextFile(String filename) {
        ArrayList<Object[]> employeeList = new ArrayList<Object[]>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Object[] row = {parts[0], parts[1], Integer.parseInt(parts[2])};
                    employeeList.add(row);
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        rowData = employeeList.toArray(new Object[0][]);
    }

    private void saveDataToTextFile(String filename) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            for (Object[] row : rowData) {
                bw.write(row[0] + "," + row[1] + "," + row[2]); // Add commas to separate the fields
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void loadDataFromSerializedFile(String filename) {
        ArrayList<Object[]> employeeList = new ArrayList<Object[]>();


        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
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

        rowData = employeeList.toArray(new Object[0][]);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final JTableEx jTableEx = new JTableEx();
                // Charger les données depuis un fichier texte
                jTableEx.loadDataFromSerializedFile("src/employees.ser");
                // Afficher la fenêtre
                jTableEx.setVisible(true);
                // Ajouter un événement pour sauvegarder les données lorsque la fenêtre est fermée
                jTableEx.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        jTableEx.saveDataToTextFile("src/employees.txt"); // Sauvegarder les données dans un fichier texte
                    }
                });
            }
        });
    }
}


