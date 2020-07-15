package com.java.leo.dao;

import com.java.leo.entity.User;

/**
 * @Package: Craft.com.java.leo.dao
 * @Author: Leo
 * @Date: 2020/7/3 13:43
 * @Description:
 */
public interface UserDao extends BaseDao<User> {
    public User findByPhone(String phone);
    public User findByEmail(String email);
}
