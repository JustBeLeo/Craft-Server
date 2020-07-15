package com.java.leo.service;

import com.java.leo.dao.ArticleDao;
import com.java.leo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/3 13:45
 * @Description:
 */

@Component
@Transactional
public class ArticleService extends CrudService<Article, ArticleDao>{

    @Autowired
    ArticleDao articleDao;

    @Override
    protected ArticleDao getDao() {
        return articleDao;
    }

}
