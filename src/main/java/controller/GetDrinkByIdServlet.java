/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.GetDrinkDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Drink;
import model.DrinkDetail;

/**
 *
 * @author HUY PHAM
 */
@WebServlet(name = "GetDrinkIdServlet", urlPatterns = "/get-drink-by-id")
public class GetDrinkByIdServlet extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private GetDrinkDao getDrinkDao = new GetDrinkDao();

    public GetDrinkByIdServlet() {
        super();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        DrinkDetail drinkDetail = getDrinkDao.getDrinkDetailById(Integer.parseInt(id));

        String listDrinksString = this.gson.toJson(drinkDetail);

        PrintWriter out = response.getWriter();
        out.print(listDrinksString);
        out.close();
    }
    
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
    }
}
