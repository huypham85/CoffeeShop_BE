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
@WebServlet(name = "DrinkServlet", urlPatterns = "/addNewDrink")
public class DrinkServlet extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private CoffeeDao coffeeDao = new CoffeeDao();

    public DrinkServlet() {
        super();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Drink drink = this.gson.fromJson(request.getReader(), Drink.class);
        System.out.println(drink.getName_drink());
        coffeeDao.insertNewDrink(drink);
        CommonResponse commonResponse = new CommonResponse("post successfully");
        String commonResponseString = this.gson.toJson(commonResponse);

        PrintWriter out = response.getWriter();
        out.print(commonResponseString);
        out.close();
    }
}
