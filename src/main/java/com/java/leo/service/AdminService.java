package com.java.leo.service;

import com.java.leo.dao.AdminDao;
import com.java.leo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/7 22:22
 * @Description:
 */

@Component
@Transactional
public class AdminService extends CrudService<Admin, AdminDao>{

    @Autowired
    AdminDao adminDao;

    @Override
    protected AdminDao getDao() {
        return adminDao;
    }

    public Admin findByName(String name){
        return adminDao.findByName(name);
    }
}
