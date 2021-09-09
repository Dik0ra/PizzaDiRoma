package com.example.pizzadiroma;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzadiroma.ui.main.MyRecycleViewAdapter;

import org.jetbrains.annotations.NotNull;

public class Fragment1 extends Fragment {

    private static final String FLOG1 =  "LOG_F1";
    MyRecycleViewAdapter adapter;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false );


        String[] data = {"1","2","4","6","5"}; // заглушка
        Log.d(FLOG1, "RVStart");
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.RecycleFrgmnt1);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), numberOfColumns));
        adapter = new MyRecycleViewAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        Log.d(FLOG1, "RVFinish");
        return view;

    }
}
