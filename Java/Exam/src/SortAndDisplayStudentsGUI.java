import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SortAndDisplayStudentsGUI {

    private JFrame frame;
    private JTextArea textArea;

    public SortAndDisplayStudentsGUI() {
        // Création de la JFrame principale
        frame = new JFrame("Affichage et Tri des Étudiants");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Création de la JTextArea pour afficher les résultats
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Création du bouton pour sélectionner et afficher les étudiants
        JButton selectFileButton = new JButton("Sélectionner un fichier...");
        selectFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Afficher la boîte de dialogue de sélection de fichier
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtention du fichier sélectionné
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getAbsolutePath();

                    // Appeler la méthode pour lire, trier et afficher les étudiants
                    displaySortedStudents(fileName);
                }
            }
        });

        // Ajout des composants à la JFrame
        frame.add(selectFileButton, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Affichage de la JFrame
        frame.setVisible(true);
    }

    private void displaySortedStudents(String fileName) {
        ArrayList<Student> students = new ArrayList<>();
        double totalMoyenne = 0.0;

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

                    // Calcul de la moyenne
                    double moyenne = 0.5 * ds + 0.5 * examen;
                    totalMoyenne += moyenne; // Ajouter à la moyenne totale

                    // Création de l'objet Student et ajout à la liste
                    Student student = new Student(numInscription, nom, prenom, ds, examen, moyenne);
                    students.add(student);
                }
            }
        } catch (IOException | NumberFormatException e) {
            textArea.setText("Erreur lors de la lecture du fichier : " + e.getMessage());
            return;
        }

        // Tri des étudiants par numéro d'inscription
        Collections.sort(students, Comparator.comparingInt(Student::getNumInscription));

        // Construction de la chaîne de résultats
        StringBuilder result = new StringBuilder();
        result.append("Liste des étudiants triés par numéro d'inscription :\n");
        for (Student student : students) {
            result.append(student).append("\n");
        }

        // Calcul du nombre d'étudiants
        int nombreEtudiants = students.size();

        // Calcul de la moyenne de la classe
        double moyenneClasse = (nombreEtudiants > 0) ? (totalMoyenne / nombreEtudiants) : 0.0;

        // Ajout du nombre d'étudiants et de la moyenne de la classe aux résultats
        result.append("\nNombre d'étudiants : ").append(nombreEtudiants);
        result.append("\nMoyenne de la classe : ").append(moyenneClasse);

        // Affichage des résultats dans la JTextArea
        textArea.setText(result.toString());
    }


    public static void main(String[] args) {
        // Utilisation de l'Event Dispatch Thread pour la création de l'interface graphique
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SortAndDisplayStudentsGUI(); // Création de l'instance de l'application GUI
            }
        });
    }
}

