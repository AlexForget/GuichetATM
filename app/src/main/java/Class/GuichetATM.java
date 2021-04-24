package Class;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class GuichetATM {

    public static GuichetATM instancheGuichet = null;
    private List<Client> clients = new ArrayList<Client>();
    private List<Cheque> comptesCheques = new ArrayList<Cheque>();
    private List<Epargne> comptesEpargnes = new ArrayList<Epargne>();

    public static synchronized GuichetATM getInstance() {
        return instancheGuichet;
    }

    public GuichetATM() {
        clients.add(new Client("Forget", "Alexandre", "alex", 123));
        clients.add(new Client("Paquette", "Josée", "josée", 456));
        clients.add(new Client("Lavigne", "Michael", "mike", 789));
        clients.add(new Client("Lambert", "Jonathan", "jo", 159));

        comptesCheques.add(new Cheque(123, "1-123-001", 500));
        comptesCheques.add(new Cheque(456, "2-456-001", 1000));
        comptesCheques.add(new Cheque(789, "3-789-001", 750));
        comptesCheques.add(new Cheque(159, "4-159-001", 2000));

        comptesEpargnes.add(new Epargne(123, "1-123-002", 10000));
        comptesEpargnes.add(new Epargne(456, "2-456-002", 5000));
        comptesEpargnes.add(new Epargne(789, "3-789-002", 15000));
        comptesEpargnes.add(new Epargne(159, "4-159-002", 12000));
    }

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

    public Bundle getBundlePourAdministrateur() {
        Bundle bundle = new Bundle();
        double[] soldesEpargne = new double[comptesEpargnes.size()];
        String[] clientPrenom = new String[clients.size()];
        String[] clientNom = new String[clients.size()];

        for (int i = 0; i < comptesEpargnes.size(); i++) {
            soldesEpargne[i] = comptesEpargnes.get(i).getSolde();
        }
        for (int i = 0; i < clients.size(); i++) {
            clientPrenom[i] = clients.get(i).getPrenom();
            clientNom[i] = clients.get(i).getNom();
        }

        bundle.putDoubleArray("soldeEpargnes", soldesEpargne);
        bundle.putStringArray("clientPrenom", clientPrenom);
        bundle.putStringArray("clientNom", clientNom);

        return bundle;
    }


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

        return "Le transfert de "+ montant +" a bien été completé.";
    }

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

        return "Le transfert de "+ montant +" a bien été completé.";
    }

    public double paiementInterets() {
        Epargne e = new Epargne();
        double interet = e.getTauxInteret();

        for (Epargne epargne : comptesEpargnes) {
            epargne.setSolde(epargne.getSolde() + epargne.getSolde() * interet);
        }
        return interet;
    }

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

    public void setGuichetPourAdministrateur(double[] soldesEpargne, Context ctx) {
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

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void setCompteCheques(List<Cheque> compteCheques) {
        this.comptesCheques = compteCheques;
    }

    public void setComptesEpargne(List<Epargne> comptesEpargnes) {
        this.comptesEpargnes = comptesEpargnes;
    }
}
