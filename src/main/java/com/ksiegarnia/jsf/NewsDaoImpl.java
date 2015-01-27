/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.jsf;

import com.ksiegarnia.model.News;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


public class NewsDaoImpl extends  HibernateDaoSupport implements NewsDao {
    
    @Override
    public void addNews(News news) {
        news.setData(new Date());
        getHibernateTemplate().setCheckWriteOperations(false);   
        try{
            getHibernateTemplate().save(news);
        }catch(InvalidDataAccessResourceUsageException e){
            System.out.println("");
        }
    }

    @Override
    public List<News> findAllNews() {
        return (List<News>) getHibernateTemplate().find("from News");
    }
    
}
