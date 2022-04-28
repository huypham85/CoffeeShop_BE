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
import model.IdTypeDrink;

/**
 *
 * @author Dang Dung
 */
@WebServlet(name = "GetDrinksByCategoryServlet", urlPatterns = "/get-drinks-by-category-servlet")
public class GetDrinksByCategoryServlet extends HttpServlet{
    private Gson gson = new GsonBuilder().create();
    private GetDrinkDao drinkDao = new GetDrinkDao();

    public GetDrinksByCategoryServlet() {
        super();
    }
    
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
         System.out.println("");
        String idTypeDr = request.getParameter("id_type"); 
        List<Drink> list = drinkDao.getDrinksByCategory(Integer.parseInt(idTypeDr));
        String listDrinksString = this.gson.toJson(list);
        PrintWriter out = response.getWriter();
        out.print(listDrinksString);
        out.close();
    }
    
}
