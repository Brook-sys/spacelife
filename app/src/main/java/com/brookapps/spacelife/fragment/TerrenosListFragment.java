package com.brookapps.spacelife.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brookapps.spacelife.MainActivity;
import com.brookapps.spacelife.R;
import com.brookapps.spacelife.adapter.TerrenoCompradoAdapter;
import com.brookapps.spacelife.model.TerrenoModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TerrenosListFragment extends Fragment {


    public TerrenosListFragment() {
        // Required empty public constructor
    }
    RecyclerView recyview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_terrenos_list, container, false);
        recyview = view.findViewById(R.id.recyview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        TerrenoCompradoAdapter adapter = new TerrenoCompradoAdapter(MainActivity.terrenoList);
        recyview.setLayoutManager(manager);
        recyview.setAdapter(adapter);


        return view;
    }

}
