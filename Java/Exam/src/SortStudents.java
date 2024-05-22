import java.io.*;
import java.util.*;

public class SortStudents {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java SortStudents <fichier_etudiants>");
            return;
        }

        String fileName = args[0];
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 5) {
                    int numInscription = Integer.parseInt(parts[0]);
                    String nom = parts[1];
                    String prenom = parts[2];
                    double ds = Double.parseDouble(parts[3]);
                    double examen = Double.parseDouble(parts[4]);

                    // Calculate average
                    double moyenne = 0.5 * ds + 0.5 * examen;

                    // Create Student object and add to list
                    Student student = new Student(numInscription, nom, prenom, ds, examen, moyenne);
                    students.add(student);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            return;
        }

        // Sort students by numInscription
        Collections.sort(students, Comparator.comparingInt(Student::getNumInscription));

        // Display sorted students
        System.out.println("Liste des étudiants triés par numéro d'inscription :");
        for (Student student : students) {
            System.out.println(student); // Utilisation de toString() pour afficher les informations de l'étudiant
        }
    }
}


