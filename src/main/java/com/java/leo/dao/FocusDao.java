package com.java.leo.dao;

import com.java.leo.entity.Focus;
import com.java.leo.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Package: Craft.com.java.leo.dao
 * @Author: Leo
 * @Date: 2020/7/8 13:39
 * @Description:
 */
public interface FocusDao extends BaseDao<Focus>{

    @Query("from Focus f where f.user.id = ?1")
    List<Focus> findByUser_id(Long user_id);

    @Query("from Focus f where f.focus_user.id = ?1")
    List<Focus> findByFocus_id(Long focus_id);

    @Query("from Focus f where f.user.id = ?1 and f.focus_user.id = ?2")
    Focus findByUser_idAndFocus_id(Long user_id, Long focus_id);
}
