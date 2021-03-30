package Class;

public abstract class Compte {

    private int nip;
    private String numeroCompte;
    private float soldeCompte;


    /**
     * Constructeur a deux arguments
     * @param nip numéro d'identification du compte
     * @param numeroCompte numéro de compte
     * @param soldeCompte solde du compte
     */
    public Compte(int nip, String numeroCompte, float soldeCompte) {
        this.nip = nip;
        this.numeroCompte = numeroCompte;
        this.soldeCompte = soldeCompte;
    }

    /**
     * Constructeur sans arguments
     */
    public Compte() {
        this(123, "xxx", 0);
    }

    /**
     * Constructeur par copie
     * @param autre Compte source pour faire une copie
     */
    public Compte(Compte autre) {
        this(autre.nip, autre.numeroCompte, autre.soldeCompte);
    }

    public String toString() {
        String chaine;

        chaine = "Numéro de compte : " + numeroCompte + " - Solde : " + soldeCompte + " - NIP : " + nip ;

        return chaine;
    }





}
