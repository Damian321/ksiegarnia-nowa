/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.model;

import java.util.Date;

/**
 *
 * @author Damian
 */
public class News {
    private int id;
    private String tresc;
    private Date data; 

    public News(){
        data = new Date();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", tresc=" + tresc + ", data=" + data + '}';
    }   
}
