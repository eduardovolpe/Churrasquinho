package br.com.e3.churrasquinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Eduardo on 06/04/2015.
 */


public class AdapterListCarne extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Carne> carne;
    int qtd = 0;


    public AdapterListCarne(Context context, List<Carne> carnes){
        this.carne = carnes;
        inflater = LayoutInflater.from(context);
    }

    public List<Carne> getCarnes(){
        return carne;
    }

    @Override
    public int getCount() {
        return carne.size();
    }

    @Override
    public Carne getItem(int position) {
        return carne.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ItemSuporte{
        CheckBox nomeCarne;
        TextView valorCarne;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_carne, null);

            item = new ItemSuporte();

            item.nomeCarne = (CheckBox) convertView.findViewById(R.id.nomeCarne);
            item.valorCarne = (TextView) convertView.findViewById(R.id.valorCarne);

            convertView.setTag(item);

            item.nomeCarne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Carne carn = getItem(position);

                    carn.setMarcado(cb.isChecked());
                }
            });

        } else {
            item = (ItemSuporte) convertView.getTag();
        }
        final DecimalFormat dc = new DecimalFormat("0.00");
        Carne carne = getItem(position);
        item.nomeCarne.setText(carne.getNomeCarne());
        item.valorCarne.setText(String.valueOf(dc.format(carne.getValorCarne())));

        return convertView;
    }
}
