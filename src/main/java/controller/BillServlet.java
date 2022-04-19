/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DrinkDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import api.model.CommonResponse;
import api.model.IdBillDelete;
import api.model.IdDrinkDelete;
import dao.BillDao;
import java.util.List;
import model.Bill;
import model.Drink;

/**
 *
 * @author HUY PHAM
 */
@WebServlet(name = "BillServlet", urlPatterns = "/bill")
public class BillServlet extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private BillDao billDao = new BillDao();

    public BillServlet() {
        super();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Bill bill = this.gson.fromJson(request.getReader(), Bill.class);
        billDao.insertNewBill(bill);
        CommonResponse commonResponse = new CommonResponse("post successfully");
        String commonResponseString = this.gson.toJson(commonResponse);

        PrintWriter out = response.getWriter();
        out.print(commonResponseString);
        out.close();
    }
    
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Bill> list = billDao.getBills();

        String listBillsString = this.gson.toJson(list);

        PrintWriter out = response.getWriter();
        out.print(listBillsString);
        out.close();
    }
    
    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        IdBillDelete idBillDelete = this.gson.fromJson(request.getReader(), IdBillDelete.class);
        System.out.println(idBillDelete.getId_bill());
        billDao.deleteBill(idBillDelete.getId_bill());
        CommonResponse commonResponse = new CommonResponse("delete successfully");
        String commonResponseString = this.gson.toJson(commonResponse);

        PrintWriter out = response.getWriter();
        out.print(commonResponseString);
        out.close();
    }
}
