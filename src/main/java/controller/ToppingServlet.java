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
import dao.ToppingDao;
import java.util.ArrayList;
import java.util.List;
import model.Topping;

/**
 *
 * @author HUY PHAM
 */
@WebServlet(name = "ToppingServlet", urlPatterns = "/topping")
public class ToppingServlet extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private ToppingDao toppingDao = new ToppingDao();

    public ToppingServlet() {
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

        Topping topping = this.gson.fromJson(request.getReader(), Topping.class);
        toppingDao.insertNewTopping(topping);
        CommonResponse commonResponse = new CommonResponse("post successfully");
        String commonResponseString = this.gson.toJson(commonResponse);

        PrintWriter out = response.getWriter();
        out.print(commonResponseString);
        out.close();
    }
    
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Topping topping = this.gson.fromJson(request.getReader(), Topping.class);
        toppingDao.updateTopping(topping);
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
        String id_topping = request.getParameter("id_topping");
        System.out.println(id_topping);
        toppingDao.deleteTopping(Integer.parseInt(id_topping));
        CommonResponse commonResponse = new CommonResponse("delete successfully");
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

        List<Topping> list = toppingDao.getTopping();
        String listToppingString = this.gson.toJson(list);

        PrintWriter out = response.getWriter();
        out.print(listToppingString);
        out.close();
    }
}
