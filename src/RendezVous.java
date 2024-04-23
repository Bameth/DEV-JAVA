public class RendezVous {
    private Patient patient;
    private EtatRV etat;
    private Specialite specialite;

    public RendezVous(Patient patient, Specialite specialite) {
        this.patient = patient;
        this.specialite = specialite;
        this.etat = EtatRV.ENCOURS;
    }

    public void valider() {
        etat = EtatRV.VALIDE;
    }

    public void annuler() {
        etat = EtatRV.ANNULE;
    }

    public EtatRV getEtat() {
        return etat;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "patient=" + patient +
                ", etat=" + etat +
                ", specialite=" + specialite +
                '}';
    }
}
