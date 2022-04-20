/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hdmin
 */
public class Size {
    private int id_size;
    private String name_size;
    private Float percent_plus;
    
    public Size()
    {
    }

    public Size(int id_size, String name_size, Float percent_plus) {
        this.id_size = id_size;
        this.name_size = name_size;
        this.percent_plus = percent_plus;
    }

    public Size(String name_size, Float percent_plus) {
        this.name_size = name_size;
        this.percent_plus = percent_plus;
    }

    public int getId_size() {
        return id_size;
    }

    public void setId_size(int id_size) {
        this.id_size = id_size;
    }

    public String getName_size() {
        return name_size;
    }

    public void setName_size(String name_size) {
        this.name_size = name_size;
    }

    public Float getPercent_plus() {
        return percent_plus;
    }

    public void setPercent_plus(Float percent_plus) {
        this.percent_plus = percent_plus;
    }

   

    
    
    
    
    
}
