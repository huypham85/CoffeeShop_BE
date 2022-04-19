/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HUY PHAM
 */
public class Drink {

    private int id_drink;
    private String name_drink;
    private double price;
    private String img;
    private int id_type;
    private String descript;

    public Drink() {
    }

    public Drink(int id_drink, String name_drink, double price, String img, int id_type, String descript) {
        this.id_drink = id_drink;
        this.name_drink = name_drink;
        this.price = price;
        this.img = img;
        this.id_type = id_type;
        this.descript = descript;
    }

    public Drink(String name_drink, double price, String img, int id_type, String descript) {
        this.name_drink = name_drink;
        this.price = price;
        this.img = img;
        this.id_type = id_type;
        this.descript = descript;
    }

    public int getId_drink() {
        return id_drink;
    }

    public void setId_drink(int id_drink) {
        this.id_drink = id_drink;
    }

    public String getName_drink() {
        return name_drink;
    }

    public void setName_drink(String name_drink) {
        this.name_drink = name_drink;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

}
