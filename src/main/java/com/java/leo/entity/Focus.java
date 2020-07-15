package com.java.leo.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Package: Craft.com.java.leo.entity
 * @Author: Leo
 * @Date: 2020/7/8 13:33
 * @Description:
 */
@Entity
@Table(name = "focus")
public class Focus extends BaseEntity{

    User focus_user;

    User user;

    @ManyToOne(targetEntity = User.class)
    @Cascade(value = {CascadeType.REFRESH})
    @JoinColumn(name = "focus_id")
    public User getFocus_user() {
        return focus_user;
    }

    public void setFocus_user(User focus_user) {
        this.focus_user = focus_user;
    }

    @ManyToOne(targetEntity = User.class)
    @Cascade(value = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
