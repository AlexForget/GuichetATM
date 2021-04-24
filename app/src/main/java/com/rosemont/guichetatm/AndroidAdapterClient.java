package com.rosemont.guichetatm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import Class.Client;

public class AndroidAdapterClient extends ArrayAdapter<Client> {

    private List<Client> listeClients;
    private Context context;
    private int viewRes;
    private Resources res;

    // Constructeur pour AndroidAdapter pour l'affichage de la liste des clients
    public AndroidAdapterClient(@NonNull Context context, int resource, @NonNull List<Client> listeClients) {
        super(context, resource, listeClients);

        this.listeClients = listeClients;
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
        final Client client = listeClients.get(position);
        if (client != null) {
            final TextView prenom = (TextView) view.findViewById(R.id.txtPrenomClient);
            final TextView nom = (TextView) view.findViewById(R.id.txtNomClient);

            String prenomStr = res.getString(R.string.prenom_client) + " : " + client.getPrenom();
            String nomStr = res.getString(R.string.nom_client) + " : " + client.getNom();

            prenom.setText(prenomStr);
            nom.setText(nomStr);
        }

        return view;
    }

    @Override
    public int getCount() {
        return listeClients.size();
    }
}
