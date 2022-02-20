package com.brookapps.spacelife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.brookapps.spacelife.model.TerrenoModel;

public class SelecionarTerrenoActivity extends AppCompatActivity {
    Button btnPagar;
    TerrenoModel terreno;
    RadioButton radiotamanho1,radiotamanho2,radiotamanho3,radiopacote1,radiopacote2,radiopacote3,radiotipo1,radiotipo2;
    // Para fins de Evitar Bugs, sempre que colocar um preço imagine que ele é mil vezes mais, por exemplo:
    // se vc colocar 1000 que obviamente é 1 mil.
    // Porem no app vai ser mostrado como $ 1M que é 1 milhão.
    // para ficar mais prático, coloque o numero que deseja e só diminua três zeros.
    int precoterreno1 = 80000;
    int precoterreno2 = 140000;
    int precoterreno3 = 200000;
    int precoViagem = 100000;
    int precoSeguro = 100000;
    int precoComercial = 100000;
    int precoTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_terreno);
        terreno = new TerrenoModel(1,1,1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radiotamanho1 = findViewById(R.id.radiotamanho1);
        radiotamanho1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terreno.setTamanhoTerreno(1);
                atualizarPreco();
            }
        });

        radiotamanho2 = findViewById(R.id.radiotamanho2);
        radiotamanho2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terreno.setTamanhoTerreno(2);
                atualizarPreco();
            }
        });

        radiotamanho3 = findViewById(R.id.radiotamanho3);
        radiotamanho3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terreno.setTamanhoTerreno(3);
                atualizarPreco();
            }
        });

        radiopacote1 = findViewById(R.id.radiopacote1);
        radiopacote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terreno.setPacoteDesejado(1);
                atualizarPreco();
            }
        });

        radiopacote2 = findViewById(R.id.radiopacote2);
        radiopacote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terreno.setPacoteDesejado(2);
                atualizarPreco();
            }
        });

        radiopacote3 = findViewById(R.id.radiopacote3);
        radiopacote3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terreno.setPacoteDesejado(3);
                atualizarPreco();
            }
        });

        radiotipo1 = findViewById(R.id.radiotipo1);
        radiotipo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terreno.setTipoTerreno(1);
                atualizarPreco();
            }
        });

        radiotipo2 = findViewById(R.id.radiotipo2);
        radiotipo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terreno.setTipoTerreno(2);
                atualizarPreco();
            }
        });

        btnPagar = findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagamento = new Intent(getApplicationContext(),Pagamento.class);
                pagamento.putExtra(getString(R.string.valorTotal_bundle),precoTotal);
                pagamento.putExtra(getString(R.string.objterreno_bundle),terreno);
                startActivity(pagamento);
            }
        });
        atualizarPreco();

    }
    private void atualizarPreco(){
        int preco=0;
        if (terreno.getTamanhoTerreno() == 1){
            preco+=precoterreno1;
        } else if(terreno.getTamanhoTerreno() == 2){
            preco +=precoterreno2;
        } else if(terreno.getTamanhoTerreno()==3){
            preco+=precoterreno3;
        }

        if(terreno.getPacoteDesejado()==1){

        }else if(terreno.getPacoteDesejado()==2){
            preco+=precoViagem;
        } else if(terreno.getPacoteDesejado()==3){
            preco+=precoViagem+precoSeguro;
        }

        if(terreno.getTipoTerreno()==1){

        }else if(terreno.getTipoTerreno()==2){
            preco+=precoComercial;
        }

        precoTotal = preco;
        btnPagar.setText(getString(R.string.pay_button_text)+MainActivity.numberEncurter(preco));
        terreno.setPrecoTerreno(MainActivity.numberEncurter(preco));
    }

    @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
