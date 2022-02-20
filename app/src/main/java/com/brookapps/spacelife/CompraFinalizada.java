package com.brookapps.spacelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompraFinalizada extends AppCompatActivity {
    Button btnHome;
    TextView txtviewCodigoCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_finalizada);
        txtviewCodigoCompra = findViewById(R.id.txtviewCodigoCompra);
        Bundle bb = getIntent().getExtras();
        String t = bb.getString(getString(R.string.numerorand_pref),"000000000");
        txtviewCodigoCompra.setText(getString(R.string.codigo_de_compra_text)+t);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirHome();
            }
        });
    }
    private void abrirHome(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("EXIT", true);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        abrirHome();
    }
}
