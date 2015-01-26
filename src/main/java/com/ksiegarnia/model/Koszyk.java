/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Damian
 */
public class Koszyk {
    private List<Ksiazka> lista_ksiazek;
    private String username;
    private double suma;

    public Koszyk(){
        lista_ksiazek = new ArrayList<Ksiazka>();
        suma = 0;

    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }
    
    public List<Ksiazka> getLista_ksiazek() {
        return lista_ksiazek;
    }

    public void setLista_ksiazek(List<Ksiazka> lista_ksiazek) {
        this.lista_ksiazek = lista_ksiazek;
    }
    
    public void dodajKsiazke(Ksiazka ksiazka){
        lista_ksiazek.add(ksiazka);
        suma += ksiazka.getCena();
    }
    
    public void usunKsiazke(Ksiazka ksiazka){
        lista_ksiazek.remove(ksiazka);
        suma -= ksiazka.getCena();
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Koszyk{" + "lista_ksiazek=" + lista_ksiazek + ", username=" + username + '}';
    }


}
