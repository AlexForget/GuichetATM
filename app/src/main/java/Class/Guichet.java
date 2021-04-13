package Class;

import android.view.animation.CycleInterpolator;

public class Guichet {

    private Client clients;
    private Cheque comptesCheque;
    private Epargne comptesEpargne;

    public Guichet(Client clients, Cheque comptesCheque, Epargne comptesEpargne) {
        this.clients = clients;
        this.comptesCheque = comptesCheque;
        this.comptesEpargne = comptesEpargne;
    }

    public Guichet(Guichet autre) {
        this.clients = new Client(autre.clients.getNom(), autre.clients.getPrenom(), autre.clients.getNomUtilisateur(), autre.clients.getNip());
        this.comptesCheque = new Cheque(autre.comptesCheque.getNip(), autre.comptesCheque.getNumero(), autre.comptesCheque.getSolde());
        this.comptesEpargne = new Epargne(autre.comptesEpargne.getNip(), autre.comptesEpargne.getNumero(), autre.comptesEpargne.getSolde());
    }

    @Override
    public String toString() {
        return "Guichet" +
                "\nclients : " + clients +
                "\nComptesCheque : " + comptesCheque +
                "\nComptesEpargne : " + comptesEpargne;
    }

    public Client getClient() {
        return clients;
    }

    public Cheque getComptesCheque() {
        return comptesCheque;
    }

    public Epargne getComptesEpargne() {
        return comptesEpargne;
    }

    public void setClient(Client clients) {
        this.clients = clients;
    }

    public void setCompteCheque(Cheque compteCheque) {
        this.comptesCheque = compteCheque;
    }

    public void setComptesEpargne(Epargne comptesEpargne) {
        this.comptesEpargne = comptesEpargne;
    }




}
