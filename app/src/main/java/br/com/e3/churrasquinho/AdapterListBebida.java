package br.com.e3.churrasquinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.List;

public class AdapterListBebida extends BaseAdapter{
    private LayoutInflater inflater;
    private List<Bebida> bebida;

    public AdapterListBebida(Context context, List<Bebida> bebidas) {
        this.bebida = bebidas;
        inflater = LayoutInflater.from(context);
    }

    public List<Bebida> getBebidas(){
        return bebida;
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
        TextView txtTipoBebida;
        TextView valorBebida;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_bebida, null);

            item = new ItemSuporte();

            item.nomeBebida = (CheckBox) convertView.findViewById(R.id.nomeBebida);
            item.valorBebida = (TextView) convertView.findViewById(R.id.valorBebida);
            item.txtTipoBebida = (TextView) convertView.findViewById(R.id.txtTipoBebida);

            convertView.setTag(item);

            item.nomeBebida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;

                    Bebida bebidis = getItem(position);

                    bebidis.setMarcado(cb.isChecked());

                }
            });

        } else {
            item = (ItemSuporte) convertView.getTag();
        }
        final DecimalFormat dc = new DecimalFormat("0.00");
        Bebida bebida = getItem(position);
        item.nomeBebida.setText(bebida.getNomeBebida());
        item.valorBebida.setText(String.valueOf(dc.format(bebida.getValorBebida())));
        item.txtTipoBebida.setText(bebida.getTipoBebida());

        return convertView;

    }
}
