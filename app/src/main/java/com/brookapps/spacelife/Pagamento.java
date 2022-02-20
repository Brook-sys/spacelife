package com.brookapps.spacelife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brookapps.spacelife.model.TerrenoModel;
import com.google.gson.Gson;

import java.util.Random;

public class Pagamento extends AppCompatActivity {
    Button btnConcluirPagamento;
    TextView txtviewPrecoTotal,txtviewSaldo,txtviewSaldoStatus;
    int precoTotal,saldo;
    LinearLayout back_btn_layout;
    int tamanho,pacote,tipo;
    TerrenoModel terreno;
    Bundle dados;
    SharedPreferences dados2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dados = getIntent().getExtras();
        dados2 = getSharedPreferences(getString(R.string.sharedpref_key),MODE_PRIVATE);

        back_btn_layout = findViewById(R.id.back_btn_layout);
        txtviewSaldoStatus = findViewById(R.id.txtviewSaldoStatus);
        txtviewSaldo = findViewById(R.id.txtviewSaldo);
        txtviewPrecoTotal = findViewById(R.id.txtviewPrecoTotal);
        btnConcluirPagamento = findViewById(R.id.btnConcluirPagamento);

        terreno = (TerrenoModel) dados.getSerializable(getString(R.string.objterreno_bundle));
        precoTotal = dados.getInt(getString(R.string.valorTotal_bundle),0);
        saldo = dados2.getInt(getString(R.string.moneyQuantity_pref),0);

        txtviewSaldo.setText("$"+MainActivity.numberEncurter(saldo));
        txtviewPrecoTotal.setText("$ "+ MainActivity.numberEncurter(precoTotal));

        if(precoTotal> saldo){
            back_btn_layout.setBackgroundResource(R.drawable.back_option_pay_stroke_red);
            txtviewSaldoStatus.setText(getString(R.string.sem_saldo_text));
            Toast.makeText(getApplicationContext(),getString(R.string.sem_saldo_text),Toast.LENGTH_SHORT).show();
        }

        btnConcluirPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(precoTotal>saldo){
                    Toast.makeText(getApplicationContext(),getString(R.string.sem_saldo_text),Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor prefsEditor = dados2.edit();
                    prefsEditor.putInt(getString(R.string.moneyQuantity_pref),saldo - precoTotal);
                    terreno.setNumTerreno(numeroTerrenoRand());
                    MainActivity.terrenoList.add(terreno);
                    Gson gson = new Gson();
                    String json = gson.toJson(MainActivity.terrenoList);
                    prefsEditor.putString(getString(R.string.objterrenolist_pref),json);
                    prefsEditor.commit();
                    Intent compraFinalizada = new Intent(getApplicationContext(),CompraFinalizada.class);
                    compraFinalizada.putExtra(getString(R.string.numerorand_pref),terreno.getNumTerreno());
                    startActivity(compraFinalizada);
                }

            }
        });
    }

    private String numeroTerrenoRand(){
        return String.valueOf(new Random().nextInt(999999999));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id== android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
