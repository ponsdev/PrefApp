package com.example.vinicius.prefapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vinicius.prefapp.dominio.entidades.respSMU;

/**
 * Created by vinic on 22/06/2017.
 */

public class UltimosArrayAdapter extends ArrayAdapter<respSMU> {

    private int resource;
    private LayoutInflater inflater;

    public UltimosArrayAdapter(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UltimosArrayAdapter.ViewHolder viewHolder = null;

        View view = null;
        if (convertView == null) {
            viewHolder = new UltimosArrayAdapter.ViewHolder();
            view = inflater.inflate(resource, parent, false);

            viewHolder.txtNomeClienteUltimos = (TextView) view.findViewById(R.id.txtNomeClienteUltimos);
            viewHolder.txtDataUltimos = (TextView) view.findViewById(R.id.txtDataUltimos);
            viewHolder.txtParecerUltimos = (TextView) view.findViewById(R.id.txtParecerUltimos);

            view.setTag(viewHolder);
            convertView = view;
        } else {
            viewHolder = (UltimosArrayAdapter.ViewHolder) convertView.getTag();
            view = convertView;
        }

        respSMU resposta = getItem(position);

        viewHolder.txtNomeClienteUltimos.setText(resposta.getNome());
        viewHolder.txtDataUltimos.setText(resposta.getDate());
        viewHolder.txtParecerUltimos.setText(resposta.getParecer());

        return view;
    }

    static class ViewHolder {
        TextView txtNomeClienteUltimos;
        TextView txtDataUltimos;
        TextView txtParecerUltimos;
    }
}
