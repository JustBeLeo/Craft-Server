package com.java.leo.service;

import com.java.leo.dao.TypeDao;
import com.java.leo.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/8 0:03
 * @Description:
 */
@Transactional
@Component
public class TypeService extends CrudService<Type, TypeDao> {

    @Autowired
    TypeDao typeDao;

    @Override
    protected TypeDao getDao() {
        return typeDao;
    }
}
