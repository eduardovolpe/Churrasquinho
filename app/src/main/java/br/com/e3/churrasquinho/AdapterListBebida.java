package br.com.e3.churrasquinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Eduardo on 08/04/2015.
 */
public class AdapterListBebida extends BaseAdapter{
    private LayoutInflater inflater;
    private List<Bebida> bebida;
    int qtd = 0;


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

            convertView.setTag(item);

            item.nomeBebida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;

                    Bebida bebidis = getItem(position);

                    String s = "";

                    if (cb.isChecked() == true)
                        qtd ++;
                    if (cb.isChecked() == false)
                        qtd --;

                    if (qtd >= 2)
                        s = "s selecionadas";
                    else
                        s = " selecionada";

                    if (qtd == 0)
                        Toast.makeText(v.getContext(), "Nenhuma bebida selecionada.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(v.getContext(), qtd + " bebida" + s, Toast.LENGTH_SHORT).show();
                    bebidis.setMarcado(cb.isChecked());

                }
            });

        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Bebida bebida = getItem(position);
        item.nomeBebida.setText(bebida.getNomeBebida());
        item.valorBebida.setText(String.valueOf(bebida.getValorBebida()));

        return convertView;

    }
}
