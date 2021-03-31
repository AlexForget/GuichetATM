package Class;

import java.text.DecimalFormat;

public class Epargne extends Compte {

    final private double TAUX_INTERET = 0.0125;

    /**
     * Constructeur à trois arguments
     * @param nip NIP du compte
     * @param numero Numéro du compte
     * @param solde Solde en dollars du compte
     */
    public Epargne(int nip, String numero, double solde) {
        super(nip, numero, solde);
    }

    /**
     * Constructeur sans arguments
     */
    public Epargne() {
        this(123, "xyz", 0);
    }

    /**
     * Constructeur par copie
     * @param autre Compte copié pour construire le nouveau compte.
     */
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
}
