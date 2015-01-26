/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.controller;

import com.ksiegarnia.dao.KsiazkaDAO;
import com.ksiegarnia.dao.NewsDAO;
import com.ksiegarnia.dao.UserDAO;
import com.ksiegarnia.model.News;
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

@RequestMapping("/admin/*")
@Controller
public class AdminController {
    
    private DriverManagerDataSource dataSource;
    private ModelAndView model;
    private KsiazkaDAO ksiazkaDAO;
    private UserDAO userDAO;
    private NewsDAO newsDAO;
    private News news;

    @PostConstruct
    public void init() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        dataSource.setUrl("jdbc:derby://localhost:1527/projekt-ksiegarnia");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        ksiazkaDAO = new KsiazkaDAO();        
        userDAO = new UserDAO();
        newsDAO = new NewsDAO();
                
        ksiazkaDAO.setDataSource(dataSource);
        userDAO.setDataSource(dataSource);
        newsDAO.setDataSource(dataSource);
    }
    
    @RequestMapping("panel.htm")
    public ModelAndView panel(@RequestParam(value="edycja", required=false) String edycja, 
                                @RequestParam(value="dodaj", required=false) String dodaj,
                                @RequestParam(value="usun", required=false) String usun,
                                @RequestParam(value="username", required=false) String username,
                                @RequestParam(value="id_ksiazki", required=false) String id_ksiazki,
                                @RequestParam(value="id_newsa", required=false) String id_newsa,
                                @RequestParam(value="password", required=false) String password,
                                @RequestParam(value="active", required=false) String enabled,
                                @RequestParam(value="isbn", required=false) String isbn,
                                @RequestParam(value="tytul", required=false) String tytul,
                                @RequestParam(value="autor", required=false) String autor,
                                @RequestParam(value="cytat", required=false) String cytat,
                                @RequestParam(value="autor_cytatu", required=false) String autor_cytatu,
                                @RequestParam(value="opis", required=false) String opis,
                                @RequestParam(value="liczba_stron", required=false) String liczba_stron,
                                @RequestParam(value="cena", required=false) String cena,
                                @RequestParam(value="dodaj_usera", required=false) String dodaj_usera,
                                @RequestParam(value="tresc_newsa", required=false) String tresc_newsa){
        
        model = new ModelAndView("admin/panel");            
          
        if(edycja != null){
            if(username != null){
                model.addObject("edit_user",userDAO.findByUsername(username).get(0));
            }else if(id_ksiazki != null){
                model.addObject("edit_book",ksiazkaDAO.findById(id_ksiazki).get(0));
            }else if(id_newsa != null){
                
            }
            
            if(password!=null){                                
                Boolean ed;
                if(enabled == null)             ed = Boolean.FALSE;
                else if(enabled.equals("true"))    ed = Boolean.TRUE;
                else                            ed = Boolean.FALSE;
                
                userDAO.editUser(username, password, ed);
                model.clear();
            }else if(isbn!=null){  
                ksiazkaDAO.editById(id_ksiazki, tytul, autor, autor_cytatu, cytat, opis, liczba_stron, isbn, cena);
           
                model.clear();
            }
            
        }else if(usun != null){
            if(username != null){
                userDAO.deleteUser(username);
            }else if(id_ksiazki != null){
                ksiazkaDAO.deleteBook(id_ksiazki);
            }else if(id_newsa != null){
                
            }
        }else if(dodaj != null){
            switch (dodaj) {
                case "user":
                    model.addObject("dodaj_usera","user");
                    break;
                case "pracownik":
                    model.addObject("dodaj_usera","pracownik");
                    break;           
                case "ksiazka":
                    model.addObject("dodaj_ksiazke","1");
                    break;
            }
            
            if(dodaj_usera != null){
                Boolean ed;
                if(enabled == null)                 ed = Boolean.FALSE;
                else if(enabled.equals("true"))     ed = Boolean.TRUE;
                else                                ed = Boolean.FALSE;
                
                switch (dodaj_usera){
                    case "user":
                        userDAO.addUser(username, password, ed, "ROLE_USER");
                        break;
                    case "pracownik":
                        userDAO.addUser(username, password, ed, "ROLE_PRACOWNIK");
                        break;
                }
                
                model.clear();
            }
        }else if(tresc_newsa != null){         
            newsDAO.addNews(tresc_newsa);
        }
        
        model.addObject("lista_ksiazek", ksiazkaDAO.findAll());
        model.addObject("lista_pracownikow", userDAO.findAllEmployees());
        model.addObject("lista_uzytkownikow", userDAO.findAllUsers());
        return model;
    }

}
