package com.brookapps.spacelife.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brookapps.spacelife.R;
import com.brookapps.spacelife.SelecionarTerrenoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    Button btnComprarAgora;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();
        btnComprarAgora = view.findViewById(R.id.btnComprarAgora);
        btnComprarAgora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionPay = new Intent(context, SelecionarTerrenoActivity.class);
                startActivity(optionPay);
            }
        });


        return view;
    }

}
