package com.java.leo.service;

import com.java.leo.dao.UserDao;
import com.java.leo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/3 13:45
 * @Description:
 */
//Spring Bean的标识.
@Component
//默认将类中的所有public函数纳入事务管理.
@Transactional
public class UserService extends CrudService<User, UserDao>{

    @Autowired
    UserDao userDao;

    @Override
    protected UserDao getDao() {
        return userDao;
    }

    public User findByPhone(String phone){
        return getDao().findByPhone(phone);
    }

    public User findByEmail(String e_mail){
        return getDao().findByEmail(e_mail);
    }


}
