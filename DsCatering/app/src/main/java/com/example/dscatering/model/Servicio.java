package com.example.dscatering.model;

public class Servicio {

    private String name;
    private String Descriotion;
    private int image;

    public Servicio(String name, String descriotion, int image) {
        this.name = name;
        Descriotion = descriotion;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriotion() {
        return Descriotion;
    }

    public void setDescriotion(String descriotion) {
        Descriotion = descriotion;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
