package com.example.cardviewandreciclerview.model;

public class BandejaSandiwch {

    private String nombre;
    private int nSanwiches;
    private String descripcion;
    private int imagen;

    public BandejaSandiwch() {
        super();
        this.nombre = "";
        this.nSanwiches = 0;
        this.descripcion = "";
        this.imagen = 0;
    }

    public BandejaSandiwch(String nombre, int nSanwiches, String descripcion, int imagen) {
        this();
        this.nombre = nombre;
        this.nSanwiches = nSanwiches;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setnSanwiches(int nSanwiches) {
        this.nSanwiches = nSanwiches;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getnSanwiches() {
        return nSanwiches;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
