package com.java.leo.service;

import com.java.leo.dao.ActivityMemberDao;
import com.java.leo.entity.ActivityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @Package: Craft.com.java.leo.service
 * @Author: Leo
 * @Date: 2020/7/8 16:19
 * @Description:
 */
@Component
@Transactional
public class ActivityMemberService extends CrudService<ActivityMember, ActivityMemberDao>{

    @Autowired
    ActivityMemberDao activityMemberDao;

    @Override
    protected ActivityMemberDao getDao() {
        return activityMemberDao;
    }


    public ActivityMember findByUserIdAndActivity_Id(Long userId, Long activity_id){
        return getDao().findByUserIdAndActivity_Id(userId,activity_id);
    }

}
