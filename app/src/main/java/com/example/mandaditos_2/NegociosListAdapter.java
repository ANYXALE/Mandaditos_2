package com.example.mandaditos_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NegociosListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Negocios> listaNegocios;

    public NegociosListAdapter(Context context, int layout, ArrayList<Negocios> listaNegocios) {
        this.context = context;
        this.layout = layout;
        this.listaNegocios = listaNegocios;
    }

    @Override
    public int getCount() {
        return listaNegocios.size();
    }

    @Override
    public Object getItem(int position) {
        return listaNegocios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
         ImageView imageView;
         TextView Negocio,Categoria,Descripcion;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.Negocio = row.findViewById(R.id.txtNegocio);
            holder.Categoria =  row.findViewById(R.id.txtCategoria);
            holder.Descripcion =  row.findViewById(R.id.txtDescripcion);
            holder.imageView =  row.findViewById(R.id.imgVnego);

            row.setTag(holder);

        }else{
            holder = (ViewHolder) row.getTag();
        }

        Negocios negocios = listaNegocios.get(position);

        holder.Negocio.setText(negocios.getNegocio());
        holder.Categoria.setText(negocios.getNegocio());
        holder.Descripcion.setText(negocios.getNegocio());

        byte[] negocioImage = negocios.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(negocioImage,0, negocioImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
