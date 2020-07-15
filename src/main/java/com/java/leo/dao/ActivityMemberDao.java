package com.java.leo.dao;

import com.java.leo.entity.ActivityMember;
import org.springframework.data.jpa.repository.Query;

/**
 * @Package: Craft.com.java.leo.dao
 * @Author: Leo
 * @Date: 2020/7/8 16:19
 * @Description:
 */

public interface ActivityMemberDao extends BaseDao<ActivityMember> {

    @Query("from ActivityMember f where f.user.id = ?1 and f.activity.id = ?2")
    ActivityMember findByUserIdAndActivity_Id(Long userId, Long activity_id);
}
