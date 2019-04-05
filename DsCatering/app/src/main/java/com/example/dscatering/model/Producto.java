package com.example.dscatering.model;



public class Producto {

    private String name;
    private String description;
    private String precio;
    private int nItems;
    private int image;
    //private Enum ptype;

    public Producto(String name, int nItems,String description, int image, String precio) {
        this.name = name;
        this.description = description;
        this.nItems = nItems;
        this.image = image;
        this.precio = precio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getnItems() {
        return nItems;
    }

    public void setnItems(int nItems) {
        this.nItems = nItems;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

//    public Enum getPtype() {
//        return ptype;
//    }
//
//    public void setPtype(Enum ptype) {
//        this.ptype = ptype;
//    }


    @Override
    public String toString() {
        return "Producto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", nItems=" + nItems +
                ", image=" + image +
                '}';
    }
}
