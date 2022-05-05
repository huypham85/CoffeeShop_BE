/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;


/**
 *
 * @author HUY PHAM
 */
public class Bill {
    private int id_bill;
    private int id_customer;
    private String note;
    private String address;
    private Date order_day;

    public Bill() {
    }

    public Bill(int id_bill, int id_customer, String note, String address, Date order_day) {
        this.id_bill = id_bill;
        this.id_customer = id_customer;
        this.note = note;
        this.address = address;
        this.order_day = order_day;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrder_day() {
        return order_day;
    }

    public void setOrder_day(Date order_day) {
        this.order_day = order_day;
    }
    
    
         
}
