public class Patient {
    private static int compteurPatients = 0;
    private String numeroPatient;
    private String nom;
    private String telephone;

    public Patient(String nom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
        this.numeroPatient = String.format("PAT%04d", ++compteurPatients);
    }

    public String getNumeroPatient() {
        return numeroPatient;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "numeroPatient='" + numeroPatient + '\'' +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
