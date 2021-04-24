package com.rosemont.guichetatm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import Class.Epargne;

public class AndroidAdapterEpargne extends ArrayAdapter<Epargne> {

    private List<Epargne> listeEpargne;
    private Context context;
    private int viewRes;
    private Resources res;

    // Constructeur pour AndroidAdapter pour l'affichage de la liste des comptes épargnes
    public AndroidAdapterEpargne(@NonNull Context context, int resource, @NonNull List<Epargne> listeEpargne) {
        super(context, resource, listeEpargne);

        this.listeEpargne = listeEpargne;
        this.context = context;
        this.viewRes = resource;
        this.res = context.getResources();
    }

    // Permet de remplir les champs dans l'affichage de la liste
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Epargne epargne = listeEpargne.get(position);
        if (epargne != null) {

            final TextView numeroCompteEpargne = (TextView) view.findViewById(R.id.txtNumeroCompteEpargne);
            final TextView soldeCompteEpargne = (TextView) view.findViewById(R.id.txtSoldeCompteEpargne);

            String formatSolde = formatterDouble(position);

            String numCompteEpargneStr = res.getString(R.string.numero_compte_epargne) + " : " + epargne.getNumero();
            String soldeEpargneStr = res.getString(R.string.solde_compte_epargne) + " : " + formatSolde;

            numeroCompteEpargne.setText(numCompteEpargneStr);
            soldeCompteEpargne.setText(soldeEpargneStr);
        }
        return view;
    }

    @Override
    public int getCount() {
        return listeEpargne.size();
    }

    // Permet de formatter les montant lors de l'affichage
    private String formatterDouble(int position) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
        double solde = listeEpargne.get(position).getSolde();

        return formatter.format(solde);
    }
}
