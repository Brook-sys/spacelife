package com.brookapps.spacelife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brookapps.spacelife.R;
import com.brookapps.spacelife.fragment.TerrenosListFragment;
import com.brookapps.spacelife.model.TerrenoModel;

import java.util.ArrayList;
import java.util.List;

public class TerrenoCompradoAdapter extends RecyclerView.Adapter<TerrenoCompradoAdapter.MyViewHolder> {
    List<TerrenoModel> terrenoList = new ArrayList<>();

    public TerrenoCompradoAdapter(List<TerrenoModel> terrenoList){
        this.terrenoList = terrenoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_terreno_comprado,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        TerrenoModel terreno = terrenoList.get(position);
        holder.txtviewNumero.setText(terreno.getNumTerreno());
        switch (terreno.getTamanhoTerreno()){
            case 1:
                holder.txtviewTamanho.setText(context.getString(R.string.option_tamanho_1_text));
                break;
            case 2:
                holder.txtviewTamanho.setText(context.getString(R.string.option_tamanho_2_text));
                break;
            case 3:
                holder.txtviewTamanho.setText(context.getString(R.string.option_tamanho_3_text));
                break;
        }
        switch (terreno.getPacoteDesejado()){
            case 1:
                holder.txtviewPacote.setText(context.getString(R.string.option_pacote_1_text));
                break;
            case 2:
                holder.txtviewPacote.setText(context.getString(R.string.option_pacote_2_text));
                break;
            case 3:
                holder.txtviewPacote.setText(context.getString(R.string.option_pacote_3_text));
                break;
        }
        switch (terreno.getTipoTerreno()){
            case 1:
                holder.txtviewTipo.setText(context.getString(R.string.option_tipo_1_text));
                break;
            case 2:
                holder.txtviewTipo.setText(context.getString(R.string.option_tipo_2_text));
                break;
        }
        holder.txtviewPreco.setText("$"+terreno.getPrecoTerreno());
    }

    @Override
    public int getItemCount() {
        return this.terrenoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtviewNumero,txtviewTamanho,txtviewPacote,txtviewTipo,txtviewPreco;
        public MyViewHolder(View view){
            super(view);
            txtviewNumero = view.findViewById(R.id.txtviewNumero);
            txtviewTamanho = view.findViewById(R.id.txtviewTamanho);
            txtviewPacote = view.findViewById(R.id.txtviewPacote);
            txtviewTipo = view.findViewById(R.id.txtviewTipo);
            txtviewPreco = view.findViewById(R.id.txtviewPreco3);
        }
    }
}
