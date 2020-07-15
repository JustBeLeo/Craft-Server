package com.java.leo.service;

import com.java.leo.dao.FocusDao;
import com.java.leo.entity.Focus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/8 13:39
 * @Description:
 */
@Component
@Transactional
public class FocusService extends CrudService<Focus, FocusDao> {

    @Autowired
    FocusDao focusDao;

    @Override
    protected FocusDao getDao() {
        return focusDao;
    }

    public List<Focus> findByUser_id(Long user_id){
        return getDao().findByUser_id(user_id);
    }

    // 查用户被关注的信息
    public List<Focus> findByFocus_id(Long user_id){
        return getDao().findByFocus_id(user_id);
    }

    public Focus findByUser_idAndFocus_id(Long user_id,Long focus_id){
        return getDao().findByUser_idAndFocus_id(user_id,focus_id);
    }
}
