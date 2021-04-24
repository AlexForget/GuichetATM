package Class;

import java.io.Serializable;

public class Cheque extends Compte implements Serializable {

    public Cheque(int nip, String numero, double solde) {
        super(nip, numero, solde);
    }

    public Cheque() {
        this(123, "xyz", 0);
    }

    public Cheque(Cheque autre) {
        this(autre.getNip(), autre.getNumero(), autre.getSolde());
    }
}
