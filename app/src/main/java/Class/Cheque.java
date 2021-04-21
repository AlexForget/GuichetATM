package Class;

import java.io.Serializable;

public class Cheque extends Compte implements Serializable {

    /**
     * Constructeur à trois arguments
     * @param nip NIP du compte
     * @param numero Numéro du compte
     * @param solde Solde en dollars du compte
     */
    public Cheque(int nip, String numero, double solde) {
        super(nip, numero, solde);
    }

    /**
     * Constructeur sans arguments
     */
    public Cheque() {
        this(123, "xyz", 0);
    }

    /**
     * Constructeur par copie
     * @param autre Compte copié pour construire le nouveau compte.
     */
    public Cheque(Cheque autre) {
        this(autre.getNip(), autre.getNumero(), autre.getSolde());
    }
}
