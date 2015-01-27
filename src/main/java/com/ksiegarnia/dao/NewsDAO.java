/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.dao;

import com.ksiegarnia.model.News;
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
public class NewsDAO {
    private JdbcTemplate jdbcTemplate;
    private String query;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public void addNews(String tresc){       
        query = "INSERT INTO news(tresc,data) VALUES ('"+tresc+"',current_date)";
        
        this.jdbcTemplate.execute(query);
    }
    
    public List<News> getAll(){
        query = "SELECT * FROM news";
        
        return this.jdbcTemplate.query(query, new NewsMapper());
    }
    
    public List<News> getNewest(){
        query = "SELECT * FROM news WHERE data BETWEEN current_date AND current_date + interval 7 day";
        
        return this.jdbcTemplate.query(query, new NewsMapper());
    }
    
    
     private static final class NewsMapper implements RowMapper<News> {

        public News mapRow(ResultSet rs, int rowNum) throws SQLException {
            News news = new News();
            news.setId(rs.getInt("id"));
            news.setTresc(rs.getString("tresc"));
            news.setData(rs.getDate("data"));

            return news;
        }
     }
}
