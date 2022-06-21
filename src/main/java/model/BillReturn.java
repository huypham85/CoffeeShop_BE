/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.List;


/**
 *
 * @author Dang Dung
 */
public class BillReturn {
    private Bill bill;
    private Customer customer;
    private List<BillDetail> listBillDetail;
    private double totalMoney;

    public BillReturn() {
    }

    public BillReturn(Bill bill, Customer customer, List<BillDetail> listBillDetail, double totalMoney) {
        this.bill = bill;
        this.customer = customer;
        this.listBillDetail = listBillDetail;
        this.totalMoney = totalMoney;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BillDetail> getListBillDetail() {
        return listBillDetail;
    }

    public void setListBillDetail(List<BillDetail> listBillDetail) {
        this.listBillDetail = listBillDetail;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    
}
