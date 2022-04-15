/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CommonResponse;
import model.Drink;

/**
 *
 * @author HUY PHAM
 */
@WebServlet(name = "GetDrinksServlet", urlPatterns = "/getDrinks")
public class GetDrinksServlet extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private CoffeeDao coffeeDao = new CoffeeDao();

    public GetDrinksServlet() {
        super();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Drink> list = coffeeDao.getDrinks();

        String listDrinksString = this.gson.toJson(list);

        PrintWriter out = response.getWriter();
        out.print(listDrinksString);
        out.close();
    }
    
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Drink> list = coffeeDao.getDrinks();

        String listDrinksString = this.gson.toJson(list);

        PrintWriter out = response.getWriter();
        out.print(listDrinksString);
        out.close();
    }
}
