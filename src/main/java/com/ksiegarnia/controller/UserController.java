/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksiegarnia.controller;

import com.ksiegarnia.dao.KsiazkaDAO;
import com.ksiegarnia.dao.WypozyczenieDAO;
import com.ksiegarnia.model.Koszyk;
import com.ksiegarnia.model.Ksiazka;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Damian
 */
@Controller
@RequestMapping("/user/*")
public class UserController {

    private DriverManagerDataSource dataSource;
    private ModelAndView model;
    private KsiazkaDAO ksiazkaDAO;
    private WypozyczenieDAO wypozyczenieDAO;
    private Koszyk koszyk;

    @PostConstruct
    public void init() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        dataSource.setUrl("jdbc:derby://localhost:1527/projekt-ksiegarnia");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        ksiazkaDAO = new KsiazkaDAO();
        wypozyczenieDAO = new WypozyczenieDAO();

        ksiazkaDAO.setDataSource(dataSource);
        wypozyczenieDAO.setDataSource(dataSource);

        koszyk = new Koszyk();
    }

    @RequestMapping("/historia")
    public ModelAndView historia(@RequestParam(value = "username", required = true) String username) {
        model = new ModelAndView("user/historia");
        model.addObject("wypozyczenia", wypozyczenieDAO.findByUsername(username));
        return model;
    }

    @RequestMapping("/koszyk")
    public ModelAndView koszyk(@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "usun", required = false) String usun,
            @RequestParam(value = "wypozycz", required = false) String wypozycz) {
        model = new ModelAndView("user/koszyk");

        if (id != null && usun == null) {
            if (koszyk.getUsername() == null) {
                koszyk.setUsername(username);
            } else if (!koszyk.getUsername().equals(username)) {
                koszyk = new Koszyk();
            }
            koszyk.dodajKsiazke(ksiazkaDAO.findById(id).get(0));

        } else if (usun != null && id != null) {
            koszyk.usunKsiazke(ksiazkaDAO.findById(id).get(0));

        } else if (wypozycz != null) {
            List<Ksiazka> doWypozyczenia = new ArrayList<Ksiazka>();
            doWypozyczenia = koszyk.getLista_ksiazek();

            for (Ksiazka tmp : doWypozyczenia) {
                wypozyczenieDAO.createWypozyczenie(tmp.getId(), koszyk.getUsername(), "czeka na odbiór");

            }
        }

        model.addObject("suma", koszyk.getSuma());
        model.addObject("lista_ksiazek", koszyk.getLista_ksiazek());
        return model;
    }
}
