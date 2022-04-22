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
public class Size {
    private int id;
    private double percentPlus;
    private String nameSize;

    public Size() {
    }

    public Size(int id, double percentPlus, String nameSize) {
        this.id = id;
        this.percentPlus = percentPlus;
        this.nameSize = nameSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPercentPlus() {
        return percentPlus;
    }

    public void setPercentPlus(double percentPlus) {
        this.percentPlus = percentPlus;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }

    
}
