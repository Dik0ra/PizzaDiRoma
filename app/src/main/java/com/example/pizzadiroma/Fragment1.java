package com.example.pizzadiroma;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.pizzadiroma.ui.main.DBHalper;

import org.jetbrains.annotations.NotNull;

public class Fragment1 extends Fragment {

    private static final String FLOG1 =  "LOG_F1";
    MyRecycleViewAdapter adapter;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment1_layout, container, false );
        View view = inflater.inflate(R.layout.fragment1_layout, container, false );


        ContentValues contentValues = new ContentValues();
        DBHalper dbHelper = new DBHalper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.getInformations(contentValues, db);
        String data_from_db = "" + contentValues.get(dbHelper.KEY_ID) + "," + contentValues.get(dbHelper.KEY_NAME);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
        String[] data = {data_from_db,"2","4","6","5"};
        Log.d(FLOG1, "RVStart");
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.RecycleFrgmnt1);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), numberOfColumns));
        adapter = new MyRecycleViewAdapter(getActivity(), data, contentValues);
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        Log.d(FLOG1, "RVFinish");
        return view;

    }
}
