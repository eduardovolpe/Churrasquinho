package br.com.e3.churrasquinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Eduardo on 06/04/2015.
 */


public class AdapterListCarne extends BaseAdapter {
    private LayoutInflater inflater;
    private List<carneActivity> carne;

    public AdapterListCarne(Context context, List<carneActivity> carnes){
        this.carne = carne;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return carne.size();
    }

    @Override
    public carneActivity getItem(int position) {
        return carne.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ItemSuporte{
        CheckBox nomeCarne;
        EditText valorCarne;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_carne, null);

            item = new ItemSuporte();

            item.nomeCarne = (CheckBox) convertView.findViewById(R.id.nomeCarne);
            item.valorCarne = (EditText) convertView.findViewById(R.id.valorCarne);

            convertView.setTag(item);
        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Carne carne = getItem(position);
        item.nomeCarne.setText(carne.getNomeCarne());
        item.valorCarne.setText(carne.getValorCarne());

        return convertView;
    }
}
