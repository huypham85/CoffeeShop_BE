/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model;

/**
 *
 * @author HUY PHAM
 */
public class IdBillDelete {
    private int id_bill;

    public IdBillDelete() {
    }

    public IdBillDelete(int id_bill) {
        this.id_bill = id_bill;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }
    
}
