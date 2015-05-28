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
public class AdapterListOutro extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Outro> outro;

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
        EditText valorOutro;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ItemSuporte item;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_carne, null);

            item = new ItemSuporte();

            item.nomeOutro = (CheckBox) convertView.findViewById(R.id.nomeOutro);
            item.valorOutro = (EditText) convertView.findViewById(R.id.valorOutro);

            convertView.setTag(item);
            item.nomeOutro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;

                    Outro outros = getItem(position);

                    String status = "";

                    if (cb.isChecked() == true)
                        status = "selecionado";

                    Toast.makeText(v.getContext(), cb.getText() + " foi " + status, Toast.LENGTH_SHORT).show();
                    outros.setMarcado(cb.isChecked());

                }
            });
        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Outro outro = getItem(position);
        item.nomeOutro.setText(outro.getNomeOutro());
        item.valorOutro.setText(String.valueOf(outro.getValorOutro()));

        return convertView;
    }
}
