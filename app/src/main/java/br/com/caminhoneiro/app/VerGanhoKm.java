package br.com.caminhoneiro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class VerGanhoKm extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<RodadoGanho> adapter;
    List<RodadoGanho> lstMesKm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_km);

        listView = (ListView)findViewById(R.id.lstKms);

        Intent intent = getIntent();

        boolean visualizar = intent.getBooleanExtra("visualizar", false);

        if (visualizar == true){
            lstMesKm = RodadoGanho.listAll(RodadoGanho.class);
            adapter = new QuilometragemMesAdapter(this, lstMesKm);
        }else{
            calculaGanho();
            lstMesKm = RodadoGanho.listAll(RodadoGanho.class);
            adapter = new GanhoAdapter(this, lstMesKm);
        }

        listView.setAdapter(adapter);
    }

    private void calculaGanho(){
        //int i = 1;
        int kmSoma = 0;
        int kmMes;
        double bonus = 0;

        for (int i = 1; i <= RodadoGanho.listAll(RodadoGanho.class).size(); i++){
            RodadoGanho kmR = RodadoGanho.findById(RodadoGanho.class, i);

            kmSoma = kmR.getQuilometro() + kmSoma;
            kmMes = kmR.getQuilometro();

            if (kmSoma < 4000){
                bonus = (kmMes * 1.5) + bonus;
            }else{
                bonus = (kmMes*1.25) + bonus;
            }

            kmR.setBonus(bonus);
            kmR.save();
        }
    }
}
