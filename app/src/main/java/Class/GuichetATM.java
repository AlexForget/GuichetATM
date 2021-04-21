package Class;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.io.Serializable;
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
        clients.add(new Client("Forget", "Alex", "alex", 123));
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
        // user : Admin - nip : 001
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

    public Bundle getBundle(String utilisateur, int nip) {
        Bundle bundle = new Bundle();

        for (Client cl : clients) {
            if (cl.getNomUtilisateur().equals(utilisateur) && cl.getNip() == nip) {
                bundle.putString("utilisateur", cl.getNomUtilisateur());
                bundle.putInt("nip", cl.getNip());
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
        return "Virement vers le compte chéque";
    }

    public String virementVersEpargne(int nip, double montant) {
        return "Virement vers le compte épargne";
    }

    public void setGuichet(int nip, double soldeChqs, double soldeEpargne, Context ctx) {
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

    public List<Client> getClient() {
        return clients;
    }

    public List<Cheque> getComptesCheque() {
        return comptesCheques;
    }

    public List<Epargne> getComptesEpargne() {
        return comptesEpargnes;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setCompteCheques(List<Cheque> compteCheques) {
        this.comptesCheques = compteCheques;
    }

    public void setComptesEpargne(List<Epargne> comptesEpargnes) {
        this.comptesEpargnes = comptesEpargnes;
    }


    public String test() {
        return "test";
    }
}
