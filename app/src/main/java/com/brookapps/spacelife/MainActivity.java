package com.brookapps.spacelife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brookapps.spacelife.fragment.HomeFragment;
import com.brookapps.spacelife.fragment.TerrenosListFragment;
import com.brookapps.spacelife.model.TerrenoModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stephentuso.welcome.WelcomeHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WelcomeHelper welcomeScreen;
    Button btnComprarAgora;
    private SharedPreferences dados;
    public boolean bonusReceived;
    Context context;
    TextView txtviewNome,txtviewMoney;
    int quant_add_user = 2000000;
    public static List<TerrenoModel> terrenoList = new ArrayList<>();
    private HomeFragment homeFragment;
    private TerrenosListFragment terrenosListFragment;
    TabItem tabHome,tabTerrenos;
    TabLayout tabfragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dados = getSharedPreferences(getString(R.string.sharedpref_key),MODE_PRIVATE);
        Gson gson = new Gson();
        String json = dados.getString(getString(R.string.objterrenolist_pref),"");
        Type type = new TypeToken<List<TerrenoModel>>(){}.getType();
        if(json != ""){
            terrenoList = gson.fromJson(json,type);
        }


        homeFragment = new HomeFragment();
        tabHome = findViewById(R.id.tabHome);
        tabTerrenos = findViewById(R.id.tabTerrenos);
        tabfragments = findViewById(R.id.tabfragments);
        tabs();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameConteudo,homeFragment);
        transaction.commit();

        context = this;
        welcomeScreen = new WelcomeHelper(this, MyWelcomeActivity.class);
        welcomeScreen.show(savedInstanceState);

        bonusReceived = dados.getBoolean(getString(R.string.bonusReceived_pref),false);
        if(!bonusReceived){
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.popup_bonus_receive);
            dialog.show();
            dialog.findViewById(R.id.btnResgatarBonus).setOnClickListener(onclickResgatarBonus(dialog));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
        }
        AtualizarDados();
    }

    private void tabs(){
        tabfragments.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        homeFragment = new HomeFragment();
                        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                        transaction3.replace(R.id.frameConteudo,homeFragment);
                        transaction3.commit();

                        break;
                    case 1:
                        terrenosListFragment = new TerrenosListFragment();
                        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                        transaction2.replace(R.id.frameConteudo,terrenosListFragment);
                        transaction2.commit();
                        break;
                } }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    public void AtualizarDados() {
        txtviewNome = findViewById(R.id.txtviewNome);
        txtviewMoney = findViewById(R.id.txtviewMoney);

        txtviewNome.setText(dados.getString(getString(R.string.personName_pref),""));
        int t = dados.getInt(getString(R.string.moneyQuantity_pref),0);

        txtviewMoney.setText("$ "+numberEncurter(t));

    }
    public static String numberEncurter(int t){
        if(t>999 && t <=999999){
            double z = t/1000.0;
            //return String.valueOf(t);
            return String.format("%.2f",z) + "M";
        } else if(t>999999){
            double z = t/1000000.0;
            return String.format("%.2f",z) + "B";
            //return String.valueOf(t);
        }
        return String.valueOf(t);
    }

    private View.OnClickListener onclickResgatarBonus(final Dialog dialog){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtNome = dialog.findViewById(R.id.txtNome);
                if(txtNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),getString(R.string.error_insira_um_nome_text),Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(getApplicationContext(),txtNome.getText().toString(),Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = dados.edit();
                    editor.putString(getString(R.string.personName_pref),txtNome.getText().toString());
                    editor.putInt(getString(R.string.moneyQuantity_pref),quant_add_user);
                    editor.putBoolean(getString(R.string.bonusReceived_pref),true);
                    editor.commit();
                    bonusReceived = true;
                    AtualizarDados();
                    dialog.dismiss();
                }
            }
        };
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_info:
                Dialog dialog =new Dialog(context);
                dialog.setContentView(R.layout.popup_info_app);
                dialog.show();
                dialog.findViewById(R.id.app_1).setOnClickListener(clickapp(dialog,getString(R.string.app1_playstore_pack)));
                dialog.findViewById(R.id.app_2).setOnClickListener(clickapp(dialog,getString(R.string.app2_playstore_pack)));
                break;
            case R.id.item_source:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.github_repo_link)));
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public View.OnClickListener clickapp(Dialog dialog, final String pack){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id="+pack));
                startActivity(intent);
            }
        };
    }
}
