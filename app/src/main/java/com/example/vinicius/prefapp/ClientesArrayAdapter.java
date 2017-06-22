package com.example.vinicius.prefapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vinicius.prefapp.dominio.entidades.Cliente;

/**
 * Created by vinicius on 21/06/17.
 */

public class ClientesArrayAdapter extends ArrayAdapter<Cliente> {

    private int resource;
    private LayoutInflater inflater;

    public ClientesArrayAdapter(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ClientesArrayAdapter.ViewHolder viewHolder = null;
        View view = null;

        if (convertView == null) {
            viewHolder = new ClientesArrayAdapter.ViewHolder();
            view = inflater.inflate(resource, parent, false);
            viewHolder.txtNomeCliente = (TextView) view.findViewById(R.id.listaNomeCliente);
            viewHolder.txtNumeroProtocolo = (TextView) view.findViewById(R.id.listaNumProtocolo);
            viewHolder.txtSetor = (TextView) view.findViewById(R.id.listaSetor);

            view.setTag(viewHolder);
            convertView = view;
        } else {
            viewHolder = (ClientesArrayAdapter.ViewHolder) convertView.getTag();
            view = convertView;
        }

        Cliente cliente = getItem(position);

        viewHolder.txtNomeCliente.setText(cliente.getNome());
        viewHolder.txtNumeroProtocolo.setText(cliente.getCodigo() + "-" + cliente.getNumero() + "/"
                + cliente.getAno());

        return view;
    }

    static class ViewHolder {
        TextView txtNomeCliente;
        TextView txtNumeroProtocolo;
        TextView txtSetor;
    }
}