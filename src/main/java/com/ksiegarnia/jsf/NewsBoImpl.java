/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.jsf;

import com.ksiegarnia.model.News;
import java.util.List;


public class NewsBoImpl implements NewsBo {

    NewsDao newsDao;

    public NewsBoImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }
    
    public NewsBoImpl(){}
    
    
    public NewsDao getNewsDao() {
        return newsDao;
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }
  
	public void addNews(News news){
 
		newsDao.addNews(news);
 
	}
 
	public List<News> findAllNews(){
		return newsDao.findAllNews();
	}
    
}
