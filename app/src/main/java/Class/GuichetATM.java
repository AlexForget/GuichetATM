package Class;

import android.content.Context;
import android.os.Bundle;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GuichetATM {

    public static GuichetATM instancheGuichet = null;
    private List<Client> clients = new ArrayList<Client>();
    private List<Cheque> comptesCheques = new ArrayList<Cheque>();
    private List<Epargne> comptesEpargnes = new ArrayList<Epargne>();

    public static synchronized GuichetATM getInstance() {
        return instancheGuichet;
    }

    // Constructeur sans arguments avec les clients et comptes "hard coder" pour tester l'application
    public GuichetATM() {
        clients.add(new Client("Forget", "Alexandre", "alex", 123));
        clients.add(new Client("Paquette", "Josée", "josee", 456));
        clients.add(new Client("Lavigne", "Michael", "mike", 789));
        clients.add(new Client("Lambert", "Jonathan", "jo", 159));
        clients.add(new Client("Turmel", "Caroline", "caro", 753));
        clients.add(new Client("Labelle", "Rémi", "remi", 258));
        clients.add(new Client("Paquette", "Maggie", "mag", 852));
        clients.add(new Client("Forget", "Mathieu", "math", 741));

        comptesCheques.add(new Cheque(123, "1-123-001", 500.50));
        comptesCheques.add(new Cheque(456, "2-456-001", 1111.11));
        comptesCheques.add(new Cheque(789, "3-789-001", 775.31));
        comptesCheques.add(new Cheque(159, "4-159-001", 2003.33));
        comptesCheques.add(new Cheque(753, "5-753-001", 125.33));
        comptesCheques.add(new Cheque(258, "6-258-001", 250.78));
        comptesCheques.add(new Cheque(852, "7-852-001", 1087.54));
        comptesCheques.add(new Cheque(741, "8-741-001", 357.91));

        comptesEpargnes.add(new Epargne(123, "1-123-002", 10000));
        comptesEpargnes.add(new Epargne(456, "2-456-002", 5000));
        comptesEpargnes.add(new Epargne(789, "3-789-002", 15000));
        comptesEpargnes.add(new Epargne(159, "4-159-002", 12000));
        comptesEpargnes.add(new Epargne(753, "5-753-002", 10));
        comptesEpargnes.add(new Epargne(258, "6-258-002", 120000));
        comptesEpargnes.add(new Epargne(852, "7-852-002", 25000));
        comptesEpargnes.add(new Epargne(741, "8-741-002", 10000));
    }

    // Méthode pour valider le num d'utilisateur et le NIP d'un client lors de la connection
    public boolean validerUtilisateur(String nomUtilisateur, String nip) {
        if (nomUtilisateur.equals("") || (nip.equals(""))){
            return false;
        }
        for (Client client : clients) {
            if (nomUtilisateur.equals(client.getNomUtilisateur()) && nip.equals(Integer.toString(client.getNip()))) {
                return true;
            }
        }
        return false;
    }

    // Permet d'obtenir les soldes et informations d'un client pour les passer à l'Activité
    // du guichet
    public Bundle getBundlePourGuichet(String utilisateur, int nip) {
        Bundle bundle = new Bundle();

        for (Client cl : clients) {
            if (cl.getNomUtilisateur().equals(utilisateur) && cl.getNip() == nip) {
                bundle.putString("utilisateur", cl.getNomUtilisateur());
                bundle.putInt("nip", cl.getNip());
                bundle.putString("prenom", cl.getPrenom());
                bundle.putString("nom", cl.getNom());
            }
        }
        for (Cheque chqs : comptesCheques) {
            if (chqs.getNip() == nip) {
                bundle.putString("numCptChqs", chqs.getNumero());
                bundle.putDouble("soldeChqs", chqs.getSolde());
            }
        }
        for (Epargne epa : comptesEpargnes) {
            if (epa.getNip() == nip) {
                bundle.putString("numCptEpa", epa.getNumero());
                bundle.putDouble("soldeEpa", epa.getSolde());
            }
        }
        return bundle;
    }

    // Permet d'obtenir les soldes et informations d'un client pour les passer à l'Activité
    // de la fenêtre de l'administrateur
    public Bundle getBundlePourAdministrateur() {
        Bundle bundle = new Bundle();
        double[] soldesCheques = new double[comptesCheques.size()];
        double[] soldesEpargne = new double[comptesEpargnes.size()];

        for (int i = 0; i < comptesCheques.size(); i++) {
            soldesCheques[i] = comptesCheques.get(i).getSolde();
        }

        for (int i = 0; i < comptesEpargnes.size(); i++) {
            soldesEpargne[i] = comptesEpargnes.get(i).getSolde();
        }

        bundle.putDoubleArray("soldeCheques", soldesCheques);
        bundle.putDoubleArray("soldeEpargnes", soldesEpargne);

        return bundle;
    }

    // Gestion du retrait du compte chèque fait dans l'activité guichet
    public String retraitCheque(int nip, double montant) {
        String chaineRetour;
        String retrait = "";

        for (Cheque cmptCheque : comptesCheques) {
            if (cmptCheque.getNip() == nip) {
                retrait = cmptCheque.retrait(montant);
            }
        }
        chaineRetour = retrait;

        return chaineRetour;
    }

    // Gestion du retrait du compte épargne fait dans l'activité guichet
    public String retraitEpargne(int nip, double montant) {
        String chaineRetour;
        String retrait = "";

        for (Epargne cmptEpargne : comptesEpargnes) {
            if (cmptEpargne.getNip() == nip) {
                retrait = cmptEpargne.retrait(montant);
            }
        }
        chaineRetour = retrait;

        return chaineRetour;
    }

    // Gestion du dépôt du compte chèque fait dans l'activité guichet
    public String depotCheque(int nip, double montant) {
        String chaineRetour;
        String depot = "";

        for (Cheque cmptCheque : comptesCheques) {
            if (cmptCheque.getNip() == nip) {
                depot = cmptCheque.depot(montant);
            }
        }
        chaineRetour = depot;

        return chaineRetour;
    }

    // Gestion du retrait du compte épargne fait dans l'activité guichet
    public String depotEpargne(int nip, double montant) {
        String chaineRetour;
        String depot = "";

        for (Epargne cmptEpargne : comptesEpargnes) {
            if (cmptEpargne.getNip() == nip) {
                depot = cmptEpargne.depot(montant);
            }
        }
        chaineRetour = depot;

        return chaineRetour;
    }

    // Gestion du virement vers lecompte chèque fait dans l'activité guichet
    public String virementVersCheque(int nip, double montant) {
        Cheque cptChqs = null;
        Epargne cptEpa = null;

        for (Cheque ch : comptesCheques) {
            if (nip == ch.getNip()) {
                cptChqs = ch;
            }
        }
        for (Epargne ep : comptesEpargnes) {
            if (nip == ep.getNip()) {
                cptEpa = ep;
            }
        }
        if (cptEpa.getSolde() - montant < 0) {
            return "Solde insuffisant pour faire le transfert";
        }
        if (montant > 100000) {
            return "Le montant de transfert maximal est de 100 000$";
        }
        cptEpa.setSolde(cptEpa.getSolde() - montant);
        cptChqs.setSolde(cptChqs.getSolde() + montant);

        String montantFormatter = formatterDouble(montant);

        return "Le transfert de "+ montantFormatter +" vers le compte chèque a bien été completé.";
    }

    // Gestion du virement vers lecompte épargne fait dans l'activité guichet
    public String virementVersEpargne(int nip, double montant) {
        Cheque cptChqs = null;
        Epargne cptEpa = null;

        for (Cheque ch : comptesCheques) {
            if (nip == ch.getNip()) {
                cptChqs = ch;
            }
        }
        for (Epargne ep : comptesEpargnes) {
            if (nip == ep.getNip()) {
                cptEpa = ep;
            }
        }
        if (cptChqs.getSolde() - montant < 0) {
            return "Solde insuffisant pour faire le transfert";
        }
        if (montant > 100000) {
            return "Le montant de transfert maximal est de 100 000$";
        }
        cptEpa.setSolde(cptEpa.getSolde() + montant);
        cptChqs.setSolde(cptChqs.getSolde() - montant);

        String montantFormatter = formatterDouble(montant);

        return "Le transfert de "+ montantFormatter +" vers le compte épargne a bien été completé.";
    }

    // Permet de faire le paiement des intérêts aux comptes épargne à partir de
    // l'acitivité de l'écran administrateur
    public double paiementInterets() {
        Epargne e = new Epargne();
        double interet = e.getTauxInteret();

        for (Epargne epargne : comptesEpargnes) {
            epargne.setSolde(epargne.getSolde() + epargne.getSolde() * interet);
        }
        return interet;
    }

    // Permet de formatter les montants pour l'affichage
    private String formatterDouble(double montant) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);

        return formatter.format(montant);
    }

    // Set les valeurs des soldes après avoir quitter l'activité guichet
    public void setGuichetPourTransaction(int nip, double soldeChqs, double soldeEpargne, Context ctx) {
        for (Cheque cheque : comptesCheques) {
            if (cheque.getNip() == nip) {
                cheque.setSolde(soldeChqs);
            }
        }
        for (Epargne epargne : comptesEpargnes) {
            if (epargne.getNip() == nip) {
                epargne.setSolde(soldeEpargne);
            }
        }
    }

    // Set les valeurs des soldes après avoir quitter l'activité de l'écran administrateur
    public void setGuichetPourAdministrateur(double[] soldesCheques, double[] soldesEpargne, Context ctx) {

        for (int i = 0; i < comptesCheques.size(); i++) {
            comptesCheques.get(i).setSolde(soldesCheques[i]);
        }
        for (int i = 0; i < comptesEpargnes.size(); i++) {
            comptesEpargnes.get(i).setSolde(soldesEpargne[i]);
        }
    }

    public List<Client> getClient() {
        return clients;
    }

    public List<Cheque> getComptesCheque() {
        return comptesCheques;
    }

    public List<Epargne> getComptesEpargne() {
        return comptesEpargnes;
    }

}
