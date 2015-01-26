/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.model;

/**
 *
 * @author Damian
 */
public class Wypozyczenie {
    private int id;
    private String id_ksiazki;
    private String username;
    private String stan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_ksiazki() {
        return id_ksiazki;
    }

    public void setId_ksiazki(String id_ksiazki) {
        this.id_ksiazki = id_ksiazki;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    @Override
    public String toString() {
        return "Wypozyczenie{" + "id=" + id + ", id_ksiazki=" + id_ksiazki + ", username=" + username + ", stan=" + stan + '}';
    }   
}
