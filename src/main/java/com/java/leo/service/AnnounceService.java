package com.java.leo.service;

import com.java.leo.dao.AnnounceDao;
import com.java.leo.entity.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/7 16:05
 * @Description:
 */

@Component
@Transactional
public class AnnounceService extends CrudService<Announce, AnnounceDao>{

    @Autowired
    AnnounceDao announceDao;

    @Override
    protected AnnounceDao getDao() {
        return announceDao;
    }


}
