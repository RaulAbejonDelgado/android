package com.example.dscatering.dao;

import com.example.dscatering.model.Carrito;
import com.example.dscatering.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class CarritoDao {

    private static CarritoDao INSTANCE = null;
    private static List<Producto> productos = new ArrayList<Producto>();
    private static Carrito carrito = new Carrito();

    public static synchronized CarritoDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarritoDao();
        }
        return INSTANCE;
    }

    public void setIntemOnCarrito(Producto producto, int position){


        productos.add(producto);
        carrito.setProductos(productos);

    }

    public void updateItemOnCarrito(Producto producto, int position){

        productos.get(position).setnItems(producto.getnItems());
        carrito.setProductos(productos);

    }

    public Carrito getCarritos(){
        return carrito;
    }


    public void delete(Producto producto, int position) {
        productos.remove(position);
        carrito.setProductos(productos);
    }
}
