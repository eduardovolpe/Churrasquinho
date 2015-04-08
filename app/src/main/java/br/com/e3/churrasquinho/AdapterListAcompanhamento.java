package br.com.e3.churrasquinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Eduardo on 08/04/2015.
 */
public class AdapterListAcompanhamento extends BaseAdapter {
    private LayoutInflater inflater;
    private List<acompanhamentoActivity> acompanhamento;

    public AdapterListAcompanhamento(Context context, List<acompanhamentoActivity> acompanhamento) {
        this.acompanhamento = acompanhamento;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return acompanhamento.size();
    }

    @Override
    public acompanhamentoActivity getItem(int position) {
        return acompanhamento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ItemSuporte {
        CheckBox nomeAcompanhamento;
        EditText valorAcompanhamento;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_acompanhamentos, null);

            item = new ItemSuporte();

            item.nomeAcompanhamento = (CheckBox) convertView.findViewById(R.id.nomeAcompanhamento);
            item.valorAcompanhamento = (EditText) convertView.findViewById(R.id.valorAcompanhamento);

            convertView.setTag(item);
        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Acompanhamento acompanhamento = getItem(position);
        item.nomeAcompanhamento.setText(acompanhamento.getNomeAcompanhamento());
        item.valorAcompanhamento.setText(String.valueOf(acompanhamento.getValorAcompanhamento()));

        return convertView;

    }
}
