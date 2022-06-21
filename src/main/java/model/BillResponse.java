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
public class BillResponse {
    private int id_bill;
    private Customer customer;
    private String note;
    private String address;
    private Date order_day;

    public BillResponse() {
    }

    public BillResponse(int id_bill, Customer customer, String note, String address, Date order_day) {
        this.id_bill = id_bill;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
