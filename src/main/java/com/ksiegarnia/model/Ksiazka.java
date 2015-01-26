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
public class Ksiazka {

    private String id;
    private int id_kat;
    private String tytul;
    private String autor;
    private String ISBN;
    private String cytat;
    private String opis;
    private String autor_cytatu;
    private int liczba_stron;
    private double cena;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getId_kat() {
        return id_kat;
    }

    public void setId_kat(int id_kat) {
        this.id_kat = id_kat;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCytat() {
        return cytat;
    }

    public void setCytat(String cytat) {
        this.cytat = cytat;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getAutor_cytatu() {
        return autor_cytatu;
    }

    public void setAutor_cytatu(String autor_cytatu) {
        this.autor_cytatu = autor_cytatu;
    }

    public int getLiczba_stron() {
        return liczba_stron;
    }

    public void setLiczba_stron(int liczba_stron) {
        this.liczba_stron = liczba_stron;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Ksiazka{" + "id=" + id + ", id_kat=" + id_kat + ", tytul=" + tytul + ", autor=" + autor + ", ISBN=" + ISBN + ", cytat=" + cytat + ", opis=" + opis + ", autor_cytatu=" + autor_cytatu + ", liczba_stron=" + liczba_stron + ", cena=" + cena + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
       Ksiazka ksiazka;

        if (obj == null || !(obj instanceof Ksiazka)) { // or you might be more restrictive
            return false;
        }
        ksiazka = (Ksiazka)obj;
        return ksiazka.getId() == this.getId();
    }

}
