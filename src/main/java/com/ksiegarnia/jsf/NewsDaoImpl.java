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
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


public class NewsDaoImpl extends  HibernateDaoSupport implements NewsDao {
    
    @Override
    public void addNews(News news) {
        news.setData(new Date());
        getHibernateTemplate().save(news);
    }

    @Override
    public List<News> findAllNews() {
        List<News> lista = new ArrayList<News>();
        News news = new News();
        news.setId(54);
        news.setData(new Date());
        news.setTresc("chuje muje");
        lista.add(news);
            System.out.println("KURWA MAC: " + lista);
  //      return lista;
        
        return (List<News>) getHibernateTemplate().find("from News");
    }
    
}
