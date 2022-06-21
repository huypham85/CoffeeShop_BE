/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.NewsDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import api.model.CommonResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dao.BillDao;
import dao.BillDetailDao;
import dao.CustomerDao;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bill;
import model.BillDetail;
import model.BillReturn;
import model.BookTopping;
import model.Customer;
import model.News;


/**
 *
 * @author dang dung
 */
@WebServlet(name = "Checkout", urlPatterns = "/checkout")
public class BillDetailServlet extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private CustomerDao customerDao = new CustomerDao();
    private BillDetailDao billDetailDao = new BillDetailDao();
    private BillDao billDao = new BillDao();

    public BillDetailServlet() {
        super();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        JsonObject obj = this.gson.fromJson(request.getReader(), JsonObject.class);
        JsonObject customerObject = obj.getAsJsonObject("customer");
        JsonArray listBillDetail = obj.getAsJsonArray("list_bill_detail");
        
        
        String sDate1= obj.get("order_day").getAsString();  
        
        Date date = Date.valueOf(sDate1);
        
        Customer customer = new Customer(
                -1,
                customerObject.get("name_customer").getAsString(),
                customerObject.get("phone_number").getAsString(),
                customerObject.get("address").getAsString()
        );
        
        Customer cusFDB = billDetailDao.getCustomerByPhoneNumber(customer);
        if (cusFDB == null) {
            customerDao.insertNewCustomer(customer);
            cusFDB = billDetailDao.getCustomerByPhoneNumber(customer);
        }
        
        Bill bill = new Bill(
                -1,
                cusFDB.getId_customer(),
                obj.get("note_of_bill").getAsString(),
                customer.getAddress(),
                date
        );
        
        billDao.insertNewBill(bill);
        int idBill = billDetailDao.getLatedBill();
        
        for(JsonElement i: listBillDetail) {
            System.out.println(i.getAsJsonObject().get("id_product").getAsInt());
            BillDetail billDetail = new BillDetail(
                    -1,
                    idBill,
                    billDetailDao.getDrinkById(i.getAsJsonObject().get("id_product").getAsInt()),
                    billDetailDao.getDrinkById(i.getAsJsonObject().get("id_product").getAsInt()).getPrice(),
                    billDetailDao.getSizeById(i.getAsJsonObject().get("id_size").getAsInt()),
                    i.getAsJsonObject().get("note").getAsString(),
                    i.getAsJsonObject().get("amount").getAsInt()
            );
            billDetailDao.insertNewBillDetail(billDetail);
            int idBillDetail = billDetailDao.getLatedBillDetail();
            JsonArray listBookTopping = i.getAsJsonObject().get("list_topping").getAsJsonArray();
            
            for (JsonElement j: listBookTopping) {
                BookTopping bookTopping = new BookTopping(
                        j.getAsJsonObject().get("id_topping").getAsInt(),
                        idBillDetail
                );
                
                billDetailDao.insertBookTopping(bookTopping);
            }
            
        }
        
//        System.err.println(customer.getName_customer() 
//                + " "+ customer.getPhone_number() 
//                + " "+ customer.getPhone_number());

//        News news = this.gson.fromJson(request.getReader(), News.class);
//        
//        newsDao.insertNews(news);
        
        CommonResponse commonResponse = new CommonResponse("insert successfully");
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
        List<BillDetail> list = new ArrayList();
        
        String idBill = request.getParameter("id");
        
        BillReturn billReturn = new BillReturn();
        if(idBill == null || idBill == "")
        {
//            list = billDetailDao.getListBillDetail();
        }
        else
        {
//            list = billDetailDao.getListBillDetail(Integer.parseInt(idBill));
            billReturn = billDetailDao.getBillDisplay(Integer.parseInt(idBill));
        }

        String listNewsString = this.gson.toJson(billReturn);
                CommonResponse commonResponse = new CommonResponse("get successfully");

        PrintWriter out = response.getWriter();
        out.print(listNewsString);
        out.close();
    }

}
