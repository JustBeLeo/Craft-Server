package com.java.leo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Package: Craft.com.java.leo.entity
 * @Author: Leo
 * @Date: 2020/7/7 15:59
 * @Description:
 */
@Entity
@Table(name = "announce")
public class Announce extends BaseEntity {
    // 发送者id
    private Long sender_id;
    // 标题
    private String title;
    // 内容
    private String content;
    // 发布时间
    private Date post_time;
    // 是否被删除
    private boolean delete;
    // 点击次数
    private int click_count;

    @Column(name = "sender_id")
    public Long getSender_id() {
        return sender_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Column(name = "click_count")
    public int getClick_count() {
        return click_count;
    }

    public void setClick_count(int click_count) {
        this.click_count = click_count;
    }
}
