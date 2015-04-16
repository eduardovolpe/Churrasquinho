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
public class AdapterListBebida extends BaseAdapter{
    private LayoutInflater inflater;
    private List<Bebida> bebida;

    public AdapterListBebida(Context context, List<Bebida> bebidas) {
        this.bebida = bebida;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bebida.size();
    }

    @Override
    public Bebida getItem(int position) {
        return bebida.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ItemSuporte {
        CheckBox nomeBebida;
        EditText valorBebida;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_bebida, null);

            item = new ItemSuporte();

            item.nomeBebida = (CheckBox) convertView.findViewById(R.id.nomeBebida);
            item.valorBebida = (EditText) convertView.findViewById(R.id.valorBebida);

            convertView.setTag(item);
        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Bebida bebida = getItem(position);
        item.nomeBebida.setText(bebida.getNomeBebida());
        item.valorBebida.setText(String.valueOf(bebida.getValorBebida()));

        return convertView;

    }
}
