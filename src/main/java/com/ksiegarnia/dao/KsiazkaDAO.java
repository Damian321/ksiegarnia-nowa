/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksiegarnia.dao;

import com.ksiegarnia.model.Ksiazka;
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

public class KsiazkaDAO {

    private JdbcTemplate jdbcTemplate;
    private String query;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public void deleteBook(String id){
        query = "DELETE FROM ksiazka WHERE id="+id;        
        jdbcTemplate.execute(query);
    }
    
     public List<Ksiazka> findById(String id) {
        query = "select * from SA.KSIAZKA where id = "+id;
        return jdbcTemplate.query(query, new KsiazkaMapper());
    }
    
    public List<Ksiazka> findByKatId(String id) {
        query = "select * from SA.KSIAZKA where id_kat = "+id;
        return jdbcTemplate.query(query, new KsiazkaMapper());
    }
    
    public List<Ksiazka> findByTag(String nazwa) {
        query = "select k.ID,k.ID_KAT,k.TYTUL,k.AUTOR,k.CYTAT,k.AUTOR_CYTATU,k.OPIS,k.OPIS,k.LICZBA_STRON,k.ISBN,k.CENA " +
        "from KSIAZKA k, TAGI_KSIAZKA tk, TAGI t where k.ID = tk.ID_KSIAZKI AND t.ID = tk.ID_TAGU AND t.nazwa='"+nazwa+"'";
        
        return jdbcTemplate.query(query, new KsiazkaMapper());
    }
    
    public List<Ksiazka> findAll(){
        query = "select * from SA.KSIAZKA";
        return jdbcTemplate.query(query, new KsiazkaMapper());
    }
    
    public String getId_katById(String id){
        query = "select id_kat from SA.KSIAZKA where id="+id;
        return jdbcTemplate.queryForObject(query, String.class);
    }
    
    public List<Ksiazka> FindByTitle(String title){
        query = "select * from SA.KSIAZKA where tytul LIKE '%"+title+"%'";
        return jdbcTemplate.query(query, new KsiazkaMapper());
    }
    
    public List<Ksiazka> findByAuthor(String author){
        query = "select * from SA.KSIAZKA where autor LIKE '%"+author+"%'";
        return jdbcTemplate.query(query, new KsiazkaMapper());
    }
    
    public List<Ksiazka> findByISBN(String isbn){
        query = "select * from SA.KSIAZKA where isbn LIKE '%"+isbn+"%'";
        return jdbcTemplate.query(query, new KsiazkaMapper());
    }
    
    public void editById(String id,String tytul,String autor,String autor_cytatu,
            String cytat, String opis,String liczba_stron,String isbn,String cena){
        query = "UPDATE ksiazka SET tytul='"+tytul+"',autor='"+autor+"',cytat='"+cytat+"',"
                + "autor_cytatu='"+autor_cytatu+"',opis='"+opis+"',liczba_stron="+liczba_stron+",isbn='"+isbn+"',"
                + "cena="+cena+" WHERE id="+id;
        
        jdbcTemplate.execute(query);
    }
    
    private static final class KsiazkaMapper implements RowMapper<Ksiazka> {

        public Ksiazka mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ksiazka ksiazka = new Ksiazka();
            ksiazka.setId(rs.getString("id"));
            ksiazka.setId_kat(rs.getInt("id_kat"));
            ksiazka.setISBN(rs.getString("ISBN"));
            ksiazka.setTytul(rs.getString("tytul"));
            ksiazka.setAutor(rs.getString("autor"));
            ksiazka.setCytat(rs.getString("cytat"));
            ksiazka.setAutor_cytatu(rs.getString("autor_cytatu"));
            ksiazka.setOpis(rs.getString("opis"));
            ksiazka.setLiczba_stron(rs.getInt("liczba_stron"));
            ksiazka.setCena(rs.getDouble("cena"));

            return ksiazka;
        }
    }
}
