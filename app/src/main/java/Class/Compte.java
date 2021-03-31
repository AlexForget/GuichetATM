package Class;

import java.text.DecimalFormat;
import java.util.Objects;

public abstract class Compte implements Comparable {

    private int nip;
    private String numero;
    private double solde;


    /**
     * Constructeur a deux arguments
     * @param nip numéro d'identification du compte
     * @param numeroCompte numéro de compte
     * @param soldeCompte solde du compte
     */
    Compte(int nip, String numeroCompte, double soldeCompte) {
        this.nip = nip;
        this.numero = numeroCompte;
        this.solde = soldeCompte;
    }

    /**
     * Constructeur sans arguments
     */
    Compte() {
        this(123, "xxx", 0);
    }

    /**
     * Constructeur par copie
     * @param autre Compte source pour faire une copie
     */
    Compte(Compte autre) {
        this(autre.nip, autre.numero, autre.solde);
    }

    /**
     * Effectuer un retrait par multiple de 10
     * @param montant Le montant du retrait a effectué. Le retrait ne peut pas dépassé le solde restant.
     * @return Retourne le résultat du retrait. Si le retrait n'a pas pu être effectué retourne la raison.
     */
    public String retrait(double montant) {
        String chaine;
        if (solde - montant < 0) {
            chaine = "Fond insufisant. Solde actuel " + solde;
            return chaine;
        }
        if (montant % 10 != 0) {
            chaine = "Les plus petits billets distribués par le guichet sont de 10$.\nVous ne pouvez pas retirer " + montant + "$";
            return chaine;
        }
        if (montant > 1000) {
            chaine = "Le retrait maximum est de 1 000$";
            return chaine;
        }
        solde -= montant;
        chaine = "Le retrait de " + montant + "$ a été completé.";
        return chaine;
    }

    /**
     * Effectuer un dépôt
     * @param montant Montant du dépôt à effectué.
     * @return Retourne le résultat du dépôt.
     */
    public String depot(double montant) {
        String chaine;

        solde += montant;
        chaine = "Le dépôt de " + montant + "$ a été completé.";
        return chaine;
    }

    @Override
    public String toString() {
        String chaine;
        String soldeAffichage = new DecimalFormat("#.##").format(solde);


        chaine = String.format("Numéro de compte : " + numero + " - Solde : " + soldeAffichage + " - NIP : " + nip);

        return chaine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Compte compte = (Compte) o;
        return nip == compte.nip && numero.equals(compte.numero);
    }

    @Override
    public int compareTo(Object o) {
        Compte autre = (Compte) o;

        double soldeCompteThis;
        double soldeCompteAutre;

        soldeCompteThis = this.getSolde();
        soldeCompteAutre = autre.getSolde();

        if (soldeCompteThis > soldeCompteAutre) {
            return 1;
        }
        if (soldeCompteThis < soldeCompteAutre) {
            return -1;
        }
        return 0;
    }

    public int getNip() {
        return nip;
    }

    public String getNumero() {
        return numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }









}
