package com.example.dscatering.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Carrito {

    private List<Producto> productos;
    private List<Servicio> servicios;
    private Date fechaCarrito;


    public Carrito() {
        this.productos = new ArrayList<Producto>();
        this.servicios = null;
        this.fechaCarrito = new Date();
    }

    public Carrito(List<Producto> productos, List<Servicio> servicios, Date fechaCarrito) {
        this.productos = productos;
        this.servicios = servicios;
        this.fechaCarrito = fechaCarrito;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Date getFechaCarrito() {
        return fechaCarrito;
    }

    public void setFechaCarrito(Date fechaCarrito) {
        this.fechaCarrito = fechaCarrito;
    }
}
