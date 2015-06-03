package br.com.e3.churrasquinho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Eduardo on 08/04/2015.
 */
public class AdapterListOutro extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Outro> outro;
    int qtd = 0;

    public AdapterListOutro(Context context, List<Outro> outro){
        this.outro = outro;
        inflater = LayoutInflater.from(context);
    }

    public List<Outro> getOutros(){
        return outro;
    }

    @Override
    public int getCount() {
        return outro.size();
    }

    @Override
    public Outro getItem(int position) {
        return outro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ItemSuporte{
        CheckBox nomeOutro;
        TextView valorOutro;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_outros, null);

            item = new ItemSuporte();

            item.nomeOutro = (CheckBox) convertView.findViewById(R.id.nomeOutro);
            item.valorOutro = (TextView) convertView.findViewById(R.id.valorOutro);

            convertView.setTag(item);

            if(item.nomeOutro !=null) {

                item.nomeOutro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;

                        Outro outro = getItem(position);

                        String s = "";

                        if (cb.isChecked() == true)
                            qtd++;
                        if (cb.isChecked() == false)
                            qtd--;

                        if (qtd >= 2)
                            s = "s selecionadas";
                        else
                            s = " selecionada";

                        if (qtd == 0)
                            Toast.makeText(v.getContext(), "Nenhuma despesa selecionada.", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(v.getContext(), qtd + " despesa" + s, Toast.LENGTH_SHORT).show();

                        outro.setMarcado(cb.isChecked());
                    }
                });
            }
        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Outro outro = getItem(position);
        if(item.nomeOutro !=null) {
            item.nomeOutro.setText(outro.getNomeOutro());
        }
        if(item.valorOutro != null)
            item.valorOutro.setText(String.valueOf(outro.getValorOutro()));

        return convertView;
    }
}
