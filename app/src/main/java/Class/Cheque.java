package Class;

import java.io.Serializable;

public class Cheque extends Compte implements Serializable {

    // Constructeur à trois arguments
    public Cheque(int nip, String numero, double solde) {
        super(nip, numero, solde);
    }

    // Constructeur sans arguments
    public Cheque() {
        this(123, "xyz", 0);
    }

    // constructeur par copie
    public Cheque(Cheque autre) {
        this(autre.getNip(), autre.getNumero(), autre.getSolde());
    }
}
