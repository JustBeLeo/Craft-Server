package com.java.leo.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * @Package: Craft.com.java.leo.entity
 * @Author: Leo
 * @Date: 2020/7/8 16:14
 * @Description:
 */
@Entity
@Table(name = "activity_members")
public class ActivityMember extends BaseEntity {

    User user;
    Activity activity;

    @ManyToOne(targetEntity = User.class)
    @Cascade(value = {CascadeType.REFRESH})
    @JoinColumn(name = "member_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = Activity.class)
    @Cascade(value = {CascadeType.REFRESH})
    @JoinColumn(name = "activity_id")
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
