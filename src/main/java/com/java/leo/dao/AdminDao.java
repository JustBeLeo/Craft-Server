package com.java.leo.dao;

import com.java.leo.entity.Admin;

/**
 * @Package: Craft.com.java.leo.dao
 * @Author: Leo
 * @Date: 2020/7/7 22:21
 * @Description:
 */
public interface AdminDao extends BaseDao<Admin>{
    Admin findByName(String name);
}
