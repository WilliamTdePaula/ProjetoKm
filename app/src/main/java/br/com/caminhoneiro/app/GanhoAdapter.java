package br.com.caminhoneiro.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by 16254868 on 30/10/2017.
 */

public class GanhoAdapter extends ArrayAdapter<RodadoGanho> {
    public GanhoAdapter(Context context, List<RodadoGanho> list){

        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_lista, null);

            RodadoGanho item = getItem(position);

            TextView txtMes = (TextView)v.findViewById(R.id.txtMes);
            TextView txtGanho = (TextView)v.findViewById(R.id.txtItem);

            //Converte para real
            Locale l = new Locale("pt", "BR");
            NumberFormat nf = NumberFormat.getCurrencyInstance(l);

            String ganho = nf.format(item.getBonus());

            txtMes.setText(item.getMes());
            txtGanho.setText(ganho);
        }
        return v;
    }
}
