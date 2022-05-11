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
public class Customer {
    private int id_customer;
    private String name_customer;
    private String phone_number;
    private String address;

    public Customer(int id_customer, String name_customer, String phone_number, String address) {
        this.id_customer = id_customer;
        this.name_customer = name_customer;
        this.phone_number = phone_number;
        this.address = address;
    }
    
    public Customer(String name_customer, String phone_number, String address) {
        this.name_customer = name_customer;
        this.phone_number = phone_number;
        this.address = address;
    }

    public Customer() {
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    
}
