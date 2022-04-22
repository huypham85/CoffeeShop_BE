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
    private int id_topping;
    private String name_topping;
    private double price_plus;

    public Topping() {
    }

    public Topping(int id_topping, String name_topping, double price_plus) {
        this.id_topping = id_topping;
        this.name_topping = name_topping;
        this.price_plus = price_plus;
    }

    public Topping(String name_topping, double price_plus) {
        this.name_topping = name_topping;
        this.price_plus = price_plus;
    }

    public int getId_topping() {
        return id_topping;
    }

    public void setId_topping(int id_topping) {
        this.id_topping = id_topping;
    }

    public String getName_topping() {
        return name_topping;
    }

    public void setName_topping(String name_topping) {
        this.name_topping = name_topping;
    }

    public double getPrice_plus() {
        return price_plus;
    }

    public void setPrice_plus(double price_plus) {
        this.price_plus = price_plus;
    }
    
    
}
