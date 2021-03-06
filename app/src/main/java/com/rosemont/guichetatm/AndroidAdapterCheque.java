package com.rosemont.guichetatm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import Class.Cheque;

public class AndroidAdapterCheque extends ArrayAdapter<Cheque> {

    private List<Cheque> listeCheque;
    private Context context;
    private int viewRes;
    private Resources res;

    // Constructeur pour AndroidAdapter pour l'affichage de la liste des comptes chèques
    public AndroidAdapterCheque(@NonNull Context context, int resource, @NonNull List<Cheque> listeCheque) {
        super(context, resource, listeCheque);

        this.listeCheque = listeCheque;
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
        final Cheque cheque = listeCheque.get(position);
        if (cheque != null) {

            final TextView numeroCompteCheque = (TextView) view.findViewById(R.id.txtNumeroCompteCheque);
            final TextView soldeCompteCheque = (TextView) view.findViewById(R.id.txtSoldeCompteCheque);

            String formatSolde = formatterDouble(position);

            String numCompteChequeStr = res.getString(R.string.numero_compte_cheque) + " : " + cheque.getNumero();
            String soldeChequeStr = res.getString(R.string.solde_compte_cheque) + " : " + formatSolde;

            numeroCompteCheque.setText(numCompteChequeStr);
            soldeCompteCheque.setText(soldeChequeStr);
        }
        return view;
    }

    @Override
    public int getCount() {
        return listeCheque.size();
    }

    // Permet de formatter les montant lors de l'affichage
    private String formatterDouble(int position) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
        double solde = listeCheque.get(position).getSolde();

        return formatter.format(solde);
    }

}
