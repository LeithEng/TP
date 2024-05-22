import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppStudents {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java AppStudents <fichier_etudiants>");
            return;
        }

        String fileName = args[0];

        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length ==5 ) {
                    // Extract student information
                    int numInscription = Integer.parseInt(parts[0]);
                    String nom = parts[1];
                    String prenom = parts[2];
                    double ds = Double.parseDouble(parts[3]);
                    double examen = Double.parseDouble(parts[4]);

                    // Calculate student's average
                    double moyenne = 0.5 * ds + 0.5 * examen;

                    // Create Student object and add to list
                    Student student = new Student(numInscription, nom, prenom, ds, examen, moyenne);
                    students.add(student);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
            return;
        }

        // Display number of students
        System.out.println("Nombre d'étudiants : " + students.size());

        if (students.size() > 0) {
            // Calculate class average
            double classTotal = 0.0;
            for (Student student : students) {
                classTotal += student.getMoyenne();
            }
            double classAverage = classTotal / students.size();

            // Display class average
            System.out.println("Moyenne de la classe : " + classAverage);
        }
    }
}


