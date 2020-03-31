package com.example.mandaditos_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegistroNegocios extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    ImageView imanegocio;
    EditText nomNegocio, descripcion;
    Spinner spinner;
    Button registrarNegocio,btnMostrar;
    ImageButton btnImagen;

    public static SQLiteHelper sqLiteHelper;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_negocios);
        getSupportActionBar().hide();

        //Inicializar los componentes
        init();

        //Mostrar lista spinner de categoria
        dataSpinner();

        sqLiteHelper = new SQLiteHelper(this, "Mandaditos_2.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS NEGOCIOS" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, negocio VARCHAR, " +
                "categoria VARCHAR, descripcion VARCHAR, image BLOB)");

        try {
            btnImagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(
                            RegistroNegocios.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            registrarNegocio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLiteHelper.insertData(
                            nomNegocio.getText().toString().trim(),
                            spinner.getSelectedItem().toString().trim(),
                            descripcion.getText().toString().trim(),
                            imageViewtoBytes(imanegocio)
                    );
                    Log.d("tag", nomNegocio.getText().toString());
                    Log.d("tag", spinner.getSelectedItem().toString());
                    Log.d("tag", descripcion.getText().toString());
                    Toast.makeText(getApplicationContext(),"Negocio agregado", Toast.LENGTH_SHORT);
                    nomNegocio.setText("");
                    descripcion.setText("");
                    imanegocio.setImageResource(R.mipmap.ic_launcher_round);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            btnMostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(RegistroNegocios.this, Negocios_list.class);
                    startActivity(i);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private byte[] imageViewtoBytes(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return  byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST_CODE_GALLERY);
            }else {
                Toast.makeText(getApplicationContext(),"PERMISO REQUERIDO PARA CONTINUAR", Toast.LENGTH_LONG);
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imanegocio.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void dataSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void init(){
        btnImagen = findViewById(R.id.btnimanego);
        btnMostrar = findViewById(R.id.btnMostrar);
        descripcion = findViewById(R.id.TexDescripNeg);
        imanegocio = findViewById(R.id.imageNegocio);
        nomNegocio = findViewById(R.id.TxNomNegocio);
        registrarNegocio = findViewById(R.id.BtnRegistNegocio);
        spinner = findViewById(R.id.spinCategoria);
    }

    /*public void onclick(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"seleccione la imagen"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path=data.getData();
            imanegocio.setImageURI(path);
        }
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
