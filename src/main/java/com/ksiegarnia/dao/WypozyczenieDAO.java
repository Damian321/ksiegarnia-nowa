/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksiegarnia.dao;

import com.ksiegarnia.model.Wypozyczenie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Damian
 */
public class WypozyczenieDAO {

    private JdbcTemplate jdbcTemplate;
    private String query;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public List<Wypozyczenie> findAll(){
        query = "SELECT * FROM wypozyczenia";
        return jdbcTemplate.query(query, new WypozyczenieMapper());
    }
    
    public List<Wypozyczenie> findById(String id){
        query = "SELECT * FROM wypozyczenia WHERE id="+id;
        return jdbcTemplate.query(query, new WypozyczenieMapper());
    }
    
    public List<Wypozyczenie> findByUsername(String id){
        query = "SELECT * FROM wypozyczenia WHERE username='"+id+"'";
        return jdbcTemplate.query(query, new WypozyczenieMapper());
    }
    
    public void setById(String id, String stan){
        query = "UPDATE wypozyczenia SET stan='"+stan+"' WHERE id="+id;
        this.jdbcTemplate.execute(query);
    } 
        
    
    public void createWypozyczenie(String id_ksiazki, String username, String stan){
        query = "INSERT INTO wypozyczenia(id_ksiazki, username, stan) VALUES("+id_ksiazki+",'"+username+"','"+stan+"')";
   
        this.jdbcTemplate.execute(query);               
    }
    
     private static final class WypozyczenieMapper implements RowMapper<Wypozyczenie> {

        public Wypozyczenie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Wypozyczenie wypozyczenie = new Wypozyczenie();
            wypozyczenie.setId(rs.getInt("id"));
            wypozyczenie.setId_ksiazki(rs.getString("id_ksiazki"));
            wypozyczenie.setStan(rs.getString("stan"));
            wypozyczenie.setUsername(rs.getString("username"));
            
            return wypozyczenie;
        }
    }
}
