package com.example.vinicius.prefapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vinicius.prefapp.dominio.entidades.Cliente;
import com.example.vinicius.prefapp.dominio.entidades.respSMU;

/**
 * Created by vinicius on 21/06/17.
 */

public class CustomArrayAdapter extends ArrayAdapter<respSMU> {

    private int resource;
    private LayoutInflater inflater;

    public CustomArrayAdapter(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        View view = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(resource, parent, false);
            viewHolder.txtData = (TextView) view.findViewById(R.id.listaDataParecer);
            viewHolder.txtParecer = (TextView) view.findViewById(R.id.listaResultadoParecer);

            view.setTag(viewHolder);
            convertView = view;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            view = convertView;
        }

        respSMU resposta = getItem(position);

        viewHolder.txtData.setText(resposta.getDate());
        viewHolder.txtParecer.setText(resposta.getParecer());

        return view;
    }

    static class ViewHolder {
        TextView txtData;
        TextView txtParecer;
    }
}
