package com.example.pizzadiroma;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.pizzadiroma.ui.main.DBHalper;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.database.sqlite.SQLiteDatabase;

import com.example.pizzadiroma.ui.main.SectionsPagerAdapter;
import com.example.pizzadiroma.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private static final String MALOG = "LOG_MA";


    ActivityMainBinding binding;
    ArrayList<String> productsList;
    ArrayAdapter<String> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;
    DBHalper dbHelper;

    MyRecycleViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        Log.d(MALOG, "onCreate");

        initializeProductsList();

        new fetchData().start();

//        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    private void initializeProductsList() {
        productsList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productsList);
        binding.productsList.setAdapter(listAdapter);
    }

    class fetchData extends Thread {

        String data = "";

        @Override
        public void run() {
            Log.d(MALOG, "run fetcjDATA");
            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Featch data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });


            try {
                Log.d(MALOG, "URL connection");
                URL url = new URL("https://api.kronst.dev/pizza-di-roma/api/v1/products");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null) {

                    data = data + line;
                }
                Log.d(MALOG, data);


                dbHelper = new DBHalper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                Log.d(MALOG, "Parsing");
                if (!data.isEmpty()) {
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        String description = jsonObject.getString("description");
                        String weight = jsonObject.getString("weight");
                        String type = jsonObject.getString("type");
                        String imageUrl = jsonObject.getString("imageUrl");
                        String price = jsonObject.getString("price");
                        Log.d(MALOG, name);

                        dbHelper.addInformation(id, name, description, weight, type, imageUrl, price, db);
                    }
                }
                dbHelper.close();
//                        bdHelper.close();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    listAdapter.notifyDataSetChanged();
                }
            });

        }
    }

}