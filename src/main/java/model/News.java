/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hdmin
 */
public class News {
    private int id_news;
    private String name_news;
    private String img ;
    private String descript;

    public News(int id_news, String name_news, String img, String descript) {
        this.id_news = id_news;
        this.name_news = name_news;
        this.img = img;
        this.descript = descript;
    }

    public News(String name_news, String img, String descript) {
        this.name_news = name_news;
        this.img = img;
        this.descript = descript;
    }

    public News() {
        
    }

    public int getId_news() {
        return id_news;
    }

    public void setId_news(int id_news) {
        this.id_news = id_news;
    }

    public String getName_news() {
        return name_news;
    }

    public void setName_news(String name_news) {
        this.name_news = name_news;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
    
    
    
    
    
}
