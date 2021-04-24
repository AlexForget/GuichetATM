package Class;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Epargne extends Compte implements Serializable {

    final private double TAUX_INTERET = 0.0125;

    // Constructeur à trois arguments
    public Epargne(int nip, String numero, double solde) {
        super(nip, numero, solde);
    }

    // Constructeur sans argument
    public Epargne() {
        this(123, "xyz", 0);
    }

    // Constructeur par copie
    public Epargne(Cheque autre) {
        this(autre.getNip(), autre.getNumero(), autre.getSolde());
    }

    // Méthode pour payer l'intérêt des comptes épargnes
    public String payerInteret() {
        String chaine;
        double interet;

        interet = getSolde() * TAUX_INTERET;
        setSolde(getSolde() + interet);

        String interetAffichage = new DecimalFormat("#.##").format(interet);
        chaine = "Intérêts de " + interetAffichage + "$ on été déposé dans le compte.";

        return chaine;
    }

    // Affichage d'un object Epargne
    @Override
    public String toString() {
        String chaine;

        chaine = super.toString() + "taux d'intèrêt " + (TAUX_INTERET * 100) + "%";

        return chaine;
    }

    public double getTauxInteret() {
        return TAUX_INTERET;
    }
}
