package com.example.mandaditos_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class entradaAdmin extends AppCompatActivity {

    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_admin);

        Button entrar = findViewById(R.id.btnAdmin);

        EditText datos = findViewById(R.id.edtPassword);
        final String editPass = datos.getText().toString();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = FirebaseDatabase.getInstance().getReference().child("Users").child("id1");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String firebasePass = dataSnapshot.child("pass").getValue().toString();

                        if (firebasePass.equals(editPass)){
                            Intent i = new Intent(entradaAdmin.this,MenuAdmin.class);
                            startActivity(i);
                        }else {
                            Intent i = new Intent(entradaAdmin.this,MainActivity.class);
                            startActivity(i);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }


}
