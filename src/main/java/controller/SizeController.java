/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.SizeDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import api.model.CommonResponse;

import model.Size;

/**
 *
 * @author hdmin
 */
@WebServlet(name = "SizeServlet", urlPatterns = "/size")
public class SizeController extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private SizeDao sizeDao = new SizeDao();

    public SizeController() {
        super();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Size size = this.gson.fromJson(request.getReader(), Size.class);
        sizeDao.insertNewSize(size);
        
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

        
        List<Size> list = sizeDao.getSizes();
        String listSizeString = this.gson.toJson(list);
                CommonResponse commonResponse = new CommonResponse("get successfully");


        PrintWriter out = response.getWriter();
        out.print(listSizeString);
        out.close();
    }
    
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)  throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Size size = this.gson.fromJson(request.getReader(), Size.class);
        sizeDao.updateSize(size);
        
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
        sizeDao.deleteSize(Integer.parseInt(idCustomer));
        CommonResponse commonResponse = new CommonResponse("delete successfully");
        String commonResponseString = this.gson.toJson(commonResponse);

        PrintWriter out = response.getWriter();
        out.print(commonResponseString);
        out.close();
    }


}