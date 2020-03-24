package com.example.mandaditos_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class entradaAdmin extends AppCompatActivity {

    Button entrar;
    EditText datos;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_admin);

        datos = findViewById(R.id.edtPassword);


        entrar = findViewById(R.id.btnEntrar);
        ref = FirebaseDatabase.getInstance().getReference().child("users").child("id1").child("pass");
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String firebasePass = dataSnapshot.getValue(String.class);
                        final String editPass = datos.getText().toString();

                        if (firebasePass.equals(editPass)){
                            Log.d("","Succed");
                            Intent i = new Intent(entradaAdmin.this,MenuAdmin.class);
                            startActivity(i);
                        }else{
                            Intent i = new Intent(entradaAdmin.this,MainActivity.class);
                            startActivity(i);
                            Log.d("","ERROR");
                        }

                        Log.d("EditText",editPass);
                        Log.d("Firebase",firebasePass);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });







    }
}
