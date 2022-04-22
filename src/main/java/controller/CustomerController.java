/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CustomerDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import api.model.CommonResponse;
import model.Customer;


/**
 *
 * @author hdmin
 */


@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerController extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private CustomerDao customerDao= new CustomerDao();

    public CustomerController() {
        super();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Customer customer = this.gson.fromJson(request.getReader(), Customer.class);
        customerDao.insertNewCustomer(customer);
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

        List<Customer> list;
        String phoneNumber = request.getParameter("phone-number");
        String idCustomer = request.getParameter("id");
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            list = customerDao.getCustomerByPhoneNumber(phoneNumber);
        }
        else if (idCustomer != null && !idCustomer.isEmpty()){
            list = customerDao.getCustomersById(Integer.parseInt(idCustomer));
        }
        else {
            list = customerDao.getCustomers();
        }
        

        String listCustomerString = this.gson.toJson(list);

        PrintWriter out = response.getWriter();
        out.print(listCustomerString);
        out.close();
    }
    
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)  throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Customer customer = this.gson.fromJson(request.getReader(), Customer.class);
        customerDao.updateCustomer(customer);
        CommonResponse commonResponse = new CommonResponse("update successfully");
        String commonResponseString = this.gson.toJson(commonResponse);

        PrintWriter out = response.getWriter();
        out.print(commonResponseString);
        out.close();
        
    }
    
    
    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
             request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String idCustomer = request.getParameter("id");
        
        Customer customer = this.gson.fromJson(request.getReader(), Customer.class);
        customerDao.deleteCustomer(Integer.parseInt(idCustomer));
        CommonResponse commonResponse = new CommonResponse("delete successfully");
        String commonResponseString = this.gson.toJson(commonResponse);

        PrintWriter out = response.getWriter();
        out.print(commonResponseString);
        out.close();
    }
}


