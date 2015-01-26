/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksiegarnia.dao;

import com.ksiegarnia.model.Kategoria;
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
public class KategoriaDAO {

    private JdbcTemplate jdbcTemplate;
    private String query;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Kategoria> findById(String id){
        query = "select id,nazwa from kategoria where id ="+id;
        return jdbcTemplate.query(query, new CategoryMapper());
    }
    
    public List<Kategoria> findFirstNodes() {
        query = "select id,nazwa from kategoria where id <=7";
        return jdbcTemplate.query(query, new CategoryMapper());
    }

    public List<Kategoria> findChildren(String parent_id) {
        query = "select k.ID, k.nazwa from KAT_PATHS kp, KATEGORIA k where k.ID = kp.NODE_ID and kp.parent_id=" + parent_id + " and kp.depth_path=1";
        return jdbcTemplate.query(query, new CategoryMapper());
    }

    public List<String> findParents(String id) {
        query = "SELECT parent_id FROM kat_paths kp, kategoria k WHERE k.ID=kp.NODE_ID and node_id = "+id+" order by depth_path DESC";
        List<String> lista = jdbcTemplate.query(query, new RowMapper() {
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("parent_id");
            }
        });
        return lista;
    }
    

    

    private static final class CategoryMapper implements RowMapper<Kategoria> {

        public Kategoria mapRow(ResultSet rs, int rowNum) throws SQLException {
            Kategoria kategoria = new Kategoria();
            kategoria.setId(rs.getInt("id"));
            kategoria.setNazwa(rs.getString("nazwa"));

            return kategoria;
        }
    }
// Implement the DAO methods using jdbcTemplate
}
