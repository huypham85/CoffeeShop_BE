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
import model.News;


/**
 *
 * @author hdmin
 */
@WebServlet(name = "NewsServlet", urlPatterns = "/news")
public class NewsController extends HttpServlet {

    private Gson gson = new GsonBuilder().create();
    private NewsDao newsDao = new NewsDao();

    public NewsController() {
        super();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        News news = this.gson.fromJson(request.getReader(), News.class);
        newsDao.insertNews(news);
        
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

        List<News> list = new ArrayList();
        
        String idNews = request.getParameter("id");
        if(idNews == null || idNews == "")
        {
            list = newsDao.getNews();
           
        }
        else
        {
             list = newsDao.getNewsById(Integer.parseInt(idNews));
        }

        String listNewsString = this.gson.toJson(list);
                CommonResponse commonResponse = new CommonResponse("get successfully");

        PrintWriter out = response.getWriter();
        out.print(listNewsString);
        out.close();
    }
    
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)  throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        News news = this.gson.fromJson(request.getReader(), News.class);
        newsDao.updateNews(news);
        
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
        
        String idNews = request.getParameter("id");
        newsDao.deleteNews(Integer.parseInt(idNews));
        CommonResponse commonResponse = new CommonResponse("delete successfully");
        String commonResponseString = this.gson.toJson(commonResponse);

        PrintWriter out = response.getWriter();
        out.print(commonResponseString);
        out.close();
    }


}
