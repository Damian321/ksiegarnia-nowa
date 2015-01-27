/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ksiegarnia.jsf;

import com.ksiegarnia.model.News;
import java.util.List;

/**
 *
 * @author Damian
 */
public interface NewsBo {
    void addNews(News news);
 
    List<News> findAllNews();
}
