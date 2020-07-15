package com.java.leo.service;

import com.java.leo.dao.ActivityDao;
import com.java.leo.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/8 16:12
 * @Description:
 */
@Component
@Transactional
public class ActivityService extends CrudService<Activity, ActivityDao>{

    @Autowired
    ActivityDao activityDao;

    @Override
    protected ActivityDao getDao() {
        return activityDao;
    }
}
