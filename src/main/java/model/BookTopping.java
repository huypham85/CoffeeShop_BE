/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dang Dung
 */
public class BookTopping {
    private int id_topping;
    private int id_bill_detail;

    public BookTopping() {
    }

    public BookTopping(int id_topping, int id_bill_detail) {
        this.id_topping = id_topping;
        this.id_bill_detail = id_bill_detail;
    }

    public int getId_topping() {
        return id_topping;
    }

    public void setId_topping(int id_topping) {
        this.id_topping = id_topping;
    }

    public int getId_bill_detail() {
        return id_bill_detail;
    }

    public void setId_bill_detail(int id_bill_detail) {
        this.id_bill_detail = id_bill_detail;
    }
    
}
