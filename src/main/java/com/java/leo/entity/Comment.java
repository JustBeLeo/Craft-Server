package com.java.leo.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;

/**
 * @Package: Craft.com.java.leo.entity
 * @Author: Leo
 * @Date: 2020/7/7 15:58
 * @Description:
 */
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    // 发布用户
    User user;
    // 是否回复
    boolean reply;
    // 父评论id
    Long parent_id;
    // 喜爱数
    int like_count;
    // 发表时间
    Date post_time;
    // 是否被删除
    boolean delete;
    // 评论内容
    String content;
    // 评论类型 0-评论 1-跟评
    int type;

    @ManyToOne(targetEntity = User.class)
    @Cascade(value = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "is_reply")
    public boolean isReply() {
        return reply;
    }

    public void setReply(boolean reply) {
        this.reply = reply;
    }

    @Column(name = "parent_id")
    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    @Column(name = "like_count")
    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    @Column(name = "post_time")
    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    @Column(name = "isdelete")
    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
