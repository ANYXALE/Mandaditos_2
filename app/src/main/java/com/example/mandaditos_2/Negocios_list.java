package com.example.mandaditos_2;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Negocios_list extends AppCompatActivity {

    GridView gridView;
    ArrayList<Negocios> list;
    NegociosListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negocios_list);

        gridView = findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new NegociosListAdapter(this, R.layout.negocios_items, list);
        gridView.setAdapter(adapter);

        //get data
        Cursor cursor = RegistroNegocios.sqLiteHelper.getData("SELECT * FROM NEGOCIOS");

        list.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String negocio = cursor.getString(1);
            String categoria = cursor.getString(2);
            String descripcion = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            Log.d("Neg", negocio);
            Log.d("Cat", categoria);
            Log.d("Des", descripcion);

            list.add(new Negocios(id, negocio, categoria, descripcion, image));



        }



        adapter.notifyDataSetChanged();

    }
}
