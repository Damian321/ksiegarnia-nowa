/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksiegarnia.jsf;

import com.ksiegarnia.model.News;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Damian
 */
public class NewsBean implements Serializable {

    public String tresc;
    public Date data;

    NewsBo newsBo;   
    
    public NewsBo getNewsBo() {
        return newsBo;
    }

    public void setNewsBo(NewsBo newsBo) {
        this.newsBo = newsBo;        
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    //get all customer data from database
    public List<News> getNewsList() {    
        return newsBo.findAllNews();
    }

    //add a new customer data into database
    public String addNews() {

        News cust = new News();
        cust.setTresc(getTresc());
        cust.setData(new Date());        

        newsBo.addNews(cust);

        clearForm();

        return "";
    }

    //clear form values
    private void clearForm() {
        setTresc("");
        setData(new Date());
    }

}
