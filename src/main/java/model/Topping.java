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
public class Topping {
    private int id;
    private String nameTopping;
    private double pricePlus;
    

    public Topping() {
    }

    public Topping(int id, String nameTopping, double pricePlus) {
        this.id = id;
        this.nameTopping = nameTopping;
        this.pricePlus = pricePlus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTopping() {
        return nameTopping;
    }

    public void setNameTopping(String nameTopping) {
        this.nameTopping = nameTopping;
    }

    public double getPricePlus() {
        return pricePlus;
    }

    public void setPricePlus(double pricePlus) {
        this.pricePlus = pricePlus;
    }

    
}
