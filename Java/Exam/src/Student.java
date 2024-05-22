public class Student {
    private int numInscription;
    private String nom;
    private String prenom;
    private double ds;
    private double examen;
    private double moyenne;

    public Student(int numInscription, String nom, String prenom, double ds, double examen, double moyenne) {
        this.numInscription = numInscription;
        this.nom = nom;
        this.prenom = prenom;
        this.ds = ds;
        this.examen = examen;
        this.moyenne = moyenne;
    }

    public int getNumInscription() {
        return numInscription;
    }

    public double getMoyenne() {
        return moyenne;
    }
    public String toString() {
        return "Numéro d'inscription : " + numInscription +
                ", Nom : " + nom +
                ", Prénom : " + prenom +
                ", Moyenne : " + moyenne;
    }
}