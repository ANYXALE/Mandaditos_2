package com.example.mandaditos_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdmin extends AppCompatActivity {

    Button negocios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        getSupportActionBar().hide();

        negocios = findViewById(R.id.btnNegocios);
        negocios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuAdmin.this,RegistroNegocios.class);
                startActivity(i);
            }
        });
    }
}
