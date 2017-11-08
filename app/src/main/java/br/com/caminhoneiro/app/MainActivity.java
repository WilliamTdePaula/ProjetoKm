package br.com.caminhoneiro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerMeses;
    EditText txtKm;

    String xyz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(this);

        spinnerMeses = (Spinner)findViewById(R.id.selecionaMes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meses,
                android.R.layout.simple_spinner_dropdown_item);

        spinnerMeses.setAdapter(adapter);

        txtKm = (EditText)findViewById(R.id.txtDistancia);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.visualizar) {
            Intent intent = new Intent(this, VerGanhoKm.class);

            intent.putExtra("visualizar", true);//adapter do visualizar

            startActivity(intent);
        }

        if (id == R.id.calculo) {
            Intent intent = new Intent(this, VerGanhoKm.class);

            intent.putExtra("visualizar", false);//adapter do calculo

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void inserir(View view) {
        String mes = spinnerMeses.getSelectedItem().toString();
        Integer km;

        if (txtKm.getText().toString().isEmpty() || txtKm.getText().toString().equals("0")){
            txtKm.setError("Preencha esse campo");
            return;
        }else{
            km = Integer.parseInt(txtKm.getText().toString());
        }

        boolean add = false;

        List<RodadoGanho> lstMesKm = RodadoGanho.listAll(RodadoGanho.class);
        if (lstMesKm.size() == 0){
            add = true;
        }

        for(int i = 1; i <= lstMesKm.size(); i++){
            RodadoGanho rodadoGanho = RodadoGanho.findById(RodadoGanho.class, i);

            if (rodadoGanho.getMes().equals(mes)){
                //Update
                rodadoGanho.setQuilometro(km);
                rodadoGanho.save(); //

                Toast.makeText(this, "Atualizado!", Toast.LENGTH_SHORT).show();
                add = false;
                break;
            }else{
                add=true;
            }
        }

        if (add == true){
            RodadoGanho rodadoGanho = new RodadoGanho(mes, km);

            rodadoGanho.save();

            txtKm.setText(null);

            Toast.makeText(this, "Adicionado!", Toast.LENGTH_SHORT).show();
        }
    }
}
