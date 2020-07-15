package com.java.leo.entity;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

/**
 * @Package: Craft.com.java.leo.entity
 * @Author: Leo
 * @Date: 2020/7/8 16:01
 * @Description:
 */
@Entity
@Table(name = "activity")
public class Activity extends BaseEntity {
    // 用户
    User user;
    // 用户名
    String name;
    // 地点
    String site;
    // 封面图片
    String cover_url;
    // 文字内容
    String content;
    // 开始时间
    Date start_time;
    // 结束时间
    Date end_time;
    // 参与人数
    int member_count;

    @Column(name = "member_count")
    public int getMember_count() {
        return member_count;
    }

    public void setMember_count(int member_count) {
        this.member_count = member_count;
    }

    @ManyToOne(targetEntity = User.class)
    @Cascade(value = {org.hibernate.annotations.CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "site")
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Column(name = "cover_url")
    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "start_time")
    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    @Column(name = "end_time")
    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
}
