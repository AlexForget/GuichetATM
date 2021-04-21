package Class;

import java.io.Serializable;

public class Client implements Serializable {

    private String nom;
    private String prenom;
    private String nomUtilisateur;
    private int nip;

    public Client(String nom, String prenom, String nomUtilisateur, int nip) {
        this.nom = nom;
        this.prenom = prenom;
        this.nomUtilisateur = nomUtilisateur;
        this.nip = nip;
    }

    public Client() {
        this("Doe", "John", "Defaut", 123);
    }

    public Client(Client autre) {
        this(autre.nom, autre.prenom, autre.nomUtilisateur, autre.nip);
    }

    @Override
    public String toString() {
        String chaine;

        chaine = nom + ", " + prenom + " - Nom Utilisateur : " + nomUtilisateur + " - NIP : " + nip;

        return chaine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return nom.equals(client.nom) &&
                prenom.equals(client.prenom) &&
                nomUtilisateur.equals(client.nomUtilisateur);
    }


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public int getNip() {
        return nip;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }



}
