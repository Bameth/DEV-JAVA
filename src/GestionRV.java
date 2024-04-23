import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionRV {
    private List<Patient> patients = new ArrayList<>();
    private List<RendezVous> rendezVous = new ArrayList<>();

    public void ajouterPatient(String nom, String telephone) {
        for (Patient patient : patients) {
            if (patient.getTelephone().equals(telephone)) {
                System.out.println("Le numéro de téléphone est déjà utilisé.");
                return;
            }
        }

        Patient patient = new Patient(nom, telephone);
        patients.add(patient);
        System.out.println("Patient ajouté : " + patient);
    }

    public void listerPatients() {
        System.out.println("Liste des patients:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    public void enregistrerRV(String numeroPatient, Specialite specialite) {
        Patient patient = null;
        for (Patient p : patients) {
            if (p.getNumeroPatient().equals(numeroPatient)) {
                patient = p;
                break;
            }
        }

        if (patient == null) {
            System.out.println("Patient non trouvé.");
            return;
        }

        RendezVous rv = new RendezVous(patient, specialite);
        rendezVous.add(rv);
        System.out.println("Rendez-vous enregistré : " + rv);
    }

    public void listerRVParEtat(EtatRV etat) {
        System.out.println("Liste des rendez-vous par état : " + etat);
        for (RendezVous rv : rendezVous) {
            if (rv.getEtat() == etat) {
                System.out.println(rv);
            }
        }
    }

    public void listerRVParSpecialite(Specialite specialite) {
        System.out.println("Liste des rendez-vous par spécialité : " + specialite);
        for (RendezVous rv : rendezVous) {
            if (rv.getSpecialite() == specialite) {
                System.out.println(rv);
            }
        }
    }

    public void traiterRV(String numeroPatient, EtatRV nouvelEtat) {
        for (RendezVous rv : rendezVous) {
            if (rv.getPatient().getNumeroPatient().equals(numeroPatient)) {
                if (nouvelEtat == EtatRV.VALIDE) {
                    rv.valider();
                } else if (nouvelEtat == EtatRV.ANNULE) {
                    rv.annuler();
                }
                System.out.println("Rendez-vous mis à jour : " + rv);
                return;
            }
        }
        System.out.println("Rendez-vous non trouvé pour le patient : " + numeroPatient);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Ajouter un patient dans le tableau");
            System.out.println("2. Lister tous les patients du tableau");
            System.out.println("3. Enregistrer un RV pour un patient");
            System.out.println("4. Lister tous les RV par état");
            System.out.println("5. Lister tous les RV par spécialité");
            System.out.println("6. Traiter un RV");
            System.out.println("7. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    System.out.print("Entrez le nom du patient : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez le téléphone du patient : ");
                    String telephone = scanner.nextLine();
                    ajouterPatient(nom, telephone);
                    break;
                case 2:
                    listerPatients();
                    break;
                case 3:
                    System.out.print("Entrez le numéro du patient : ");
                    String numeroPatient = scanner.nextLine();
                    System.out.print("Entrez la spécialité (CARDIOLOGIE, OPHTALMOLOGIE, UROLOGIE) : ");
                    String specialiteStr = scanner.nextLine().toUpperCase();
                    Specialite specialite;
                    try {
                        specialite = Specialite.valueOf(specialiteStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Spécialité invalide.");
                        break;
                    }
                    enregistrerRV(numeroPatient, specialite);
                    break;
                case 4:
                    System.out.print("Entrez l'état (ENCOURS, VALIDE, ANNULE) : ");
                    String etatStr = scanner.nextLine().toUpperCase();
                    EtatRV etat;
                    try {
                        etat = EtatRV.valueOf(etatStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("État invalide.");
                        break;
                    }
                    listerRVParEtat(etat);
                    break;
                case 5:
                    System.out.print("Entrez la spécialité (CARDIOLOGIE, OPHTALMOLOGIE, UROLOGIE) : ");
                    String specStr = scanner.nextLine().toUpperCase();
                    Specialite spec;
                    try {
                        spec = Specialite.valueOf(specStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Spécialité invalide.");
                        break;
                    }
                    listerRVParSpecialite(spec);
                    break;
                case 6:
                    System.out.print("Entrez le numéro du patient : ");
                    String numPat = scanner.nextLine();
                    System.out.print("Entrez le nouvel état (VALIDE, ANNULE) : ");
                    String nouvelEtatStr = scanner.nextLine().toUpperCase();
                    EtatRV nouvelEtat;
                    try {
                        nouvelEtat = EtatRV.valueOf(nouvelEtatStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("État invalide.");
                        break;
                    }
                    traiterRV(numPat, nouvelEtat);
                    break;
                case 7:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        GestionRV gestionRV = new GestionRV();
        gestionRV.menu();
    }
}
