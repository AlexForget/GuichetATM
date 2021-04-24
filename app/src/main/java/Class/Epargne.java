package Class;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Epargne extends Compte implements Serializable {

    final private double TAUX_INTERET = 0.0125;

    public Epargne(int nip, String numero, double solde) {
        super(nip, numero, solde);
    }

    public Epargne() {
        this(123, "xyz", 0);
    }

    public Epargne(Cheque autre) {
        this(autre.getNip(), autre.getNumero(), autre.getSolde());
    }

    public String payerInteret() {
        String chaine;
        double interet;

        interet = getSolde() * TAUX_INTERET;
        setSolde(getSolde() + interet);

        String interetAffichage = new DecimalFormat("#.##").format(interet);
        chaine = "Intérêts de " + interetAffichage + "$ on été déposé dans le compte.";

        return chaine;
    }

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
