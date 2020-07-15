package com.java.leo.service;

import com.java.leo.dao.VideoDao;
import com.java.leo.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/8 0:46
 * @Description:
 */

@Component
@Transactional
public class VideoService extends CrudService<Video, VideoDao> {

    @Autowired
    VideoDao videoDao;

    @Override
    protected VideoDao getDao() {
        return videoDao;
    }
}
