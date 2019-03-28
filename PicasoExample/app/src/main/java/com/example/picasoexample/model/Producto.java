package com.example.picasoexample.model;

public class Producto {

    private String name;
    private String description;
    private int nItems;
    private int image;
    //private Enum ptype;

    public Producto(String name, int nItems,String description, int image) {
        this.name = name;
        this.description = description;
        this.nItems = nItems;
        this.image = image;
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
}
