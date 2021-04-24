package Class;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Compte implements Serializable {

    private int nip;
    private String numero;
    private double solde;


    Compte(int nip, String numeroCompte, double soldeCompte) {
        this.nip = nip;
        this.numero = numeroCompte;
        this.solde = soldeCompte;
    }

    Compte() {
        this(123, "xxx", 0);
    }

    Compte(Compte autre) {
        this(autre.nip, autre.numero, autre.solde);
    }

    public String retrait(double montant) {
        String chaine;

        if (solde - montant < 0) {
            chaine = "Fond insufisant.";
            return chaine;
        }
        if (montant % 10 != 0) {
            chaine = "Les plus petits billets distribués par le guichet sont de 10$.";
            return chaine;
        }
        if (montant > 1000) {
            chaine = "Le retrait maximum est de 1 000$";
            return chaine;
        }
        solde -= montant;
        String montantFormatter = formatterDouble(montant);
        chaine = "Le retrait de " + montantFormatter + " a été completé.";
        return chaine;
    }

    public String depot(double montant) {
        String chaine;

        solde += montant;
        String montantFormatter = formatterDouble(montant);
        chaine = "Le dépôt de " + montantFormatter + " a été completé.";
        return chaine;
    }

    private String formatterDouble(double montant) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);

        return formatter.format(montant);
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
