/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.dao;

import com.ksiegarnia.model.User;
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
public class UserDAO {
    private JdbcTemplate jdbcTemplate;
    private String query;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public void addUser(String username, String password, Boolean enabled, String role){
        query = "INSERT INTO users VALUES ('"+username+"','"+password+"',"+enabled+")";
        jdbcTemplate.execute(query);
        
        query = "INSERT INTO authorities VALUES ('"+username+"','"+role+"')";
        jdbcTemplate.execute(query);
    }
    
    public void deleteUser(String username){                
        query = "DELETE FROM authorities WHERE username='"+username+"'";
        jdbcTemplate.execute(query);
        
        query = "DELETE FROM users WHERE username='"+username+"'";
        jdbcTemplate.execute(query);
    }
    
    public void editUser(String username, String password, Boolean enabled){
        query = "UPDATE users SET password='"+password+"', enabled="+enabled+" WHERE username='"+username+"'";
        
        jdbcTemplate.execute(query);
    }
    
    public List<User> findAllUsers(){
        query = "select u.username, u.password, u.enabled from users u, authorities a where u.USERNAME = a.USERNAME and a.AUTHORITY='ROLE_USER'";
        
        return jdbcTemplate.query(query, new UserMapper());
    }
    
    public List<User> findAllEmployees(){
        query = "select u.username, u.password, u.enabled from users u, authorities a where u.USERNAME = a.USERNAME and a.AUTHORITY='ROLE_PRACOWNIK'";
        
        return jdbcTemplate.query(query, new UserMapper());
    }
    
    public List<User> findByUsername(String username){
        query = "select * FROM users where username = '"+username+"'";
        
        return jdbcTemplate.query(query, new UserMapper());
    }
    
     private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));

            return user;
        }
    }
}
