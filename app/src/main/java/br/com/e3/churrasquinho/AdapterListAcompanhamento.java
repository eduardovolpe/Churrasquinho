package br.com.e3.churrasquinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Eduardo on 08/04/2015.
 */
public class AdapterListAcompanhamento extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Acompanhamento> acompanhamento;

    public AdapterListAcompanhamento(Context context, List<Acompanhamento> acompanhamento) {
        this.acompanhamento = acompanhamento;
        inflater = LayoutInflater.from(context);
    }

    public List<Acompanhamento> getAcompanhamentos(){
        return acompanhamento;
    }

    @Override
    public int getCount() {
        return acompanhamento.size();
    }

    @Override
    public Acompanhamento getItem(int position) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_acompanhamentos, null);

            item = new ItemSuporte();

            item.nomeAcompanhamento = (CheckBox) convertView.findViewById(R.id.nomeAcompanhamento);
            item.valorAcompanhamento = (EditText) convertView.findViewById(R.id.valorAcompanhamento);

            convertView.setTag(item);

            item.nomeAcompanhamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;

                    Acompanhamento acomp = getItem(position);

                    String status = "";

                    if (cb.isChecked() == true)
                        status = "selecionado";

                    Toast.makeText(v.getContext(), cb.getText() + " foi " + status, Toast.LENGTH_SHORT).show();
                    acomp.setMarcado(cb.isChecked());

                }
            });

        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Acompanhamento acompanhamento = getItem(position);
        item.nomeAcompanhamento.setText(acompanhamento.getNomeAcompanhamento());
        item.valorAcompanhamento.setText(String.valueOf(acompanhamento.getValorAcompanhamento()));

        return convertView;

    }
}
