package com.example.mandaditos_2;

public class Negocios {
    private int id;
    private String negocio;
    private String categoria;
    private String descripcion;
    private byte[] image;

    public Negocios(int id, String negocio, String categoria, String descripcion, byte[] image) {
        this.id = id;
        this.negocio = negocio;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
