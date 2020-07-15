package com.java.leo.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Package: Craft.com.java.leo.entity
 * @Author: Leo
 * @Date: 2020/7/4 18:14
 * @Description:
 */
@Entity
@Table(name = "article")
public class Article extends BaseEntity {
    User user;
    // 封面url
    String cover_url;
    // 文章内容
    String text;
    // 内容图片url
    String content_url;
    // 文章标题
    String title;
    // 工艺类型
    Type type;
    // 收费状态
    int status;
    // 发布时间
    Date post_time;
    // 喜爱数量
    int like_count;
    // 收藏数量
    int favor_count;
    // 评论
    List<Comment> comments;
    // 点击数量
    int click_count;
    // 分享数量
    int share_count;
    // 是否删除
    boolean delete;
    // 工艺品id
    Long craft_id;
    // 是否通过审核
    boolean pass;
    // 是否检查
    boolean check;

    public Article() {
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

    @Column(name = "content_url")
    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String text) {
        this.content_url = text;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "cover_url")
    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    @ManyToOne(targetEntity = Type.class)
    @Cascade(value = {CascadeType.REFRESH})
    @JoinColumn(name = "type_id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "post_time")
    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    @Column(name = "like_count")
    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    @Column(name = "favor_count")
    public int getFavor_count() {
        return favor_count;
    }

    public void setFavor_count(int favor_count) {
        this.favor_count = favor_count;
    }

    @OneToMany(fetch=FetchType.EAGER)
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "article_id")
    @OrderBy("post_time asc")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Column(name = "click_count")
    public int getClick_count() {
        return click_count;
    }

    public void setClick_count(int click_count) {
        this.click_count = click_count;
    }

    @Column(name = "share_count")
    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    @Column(name = "isdelete")
    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Column(name = "craft_id")
    public Long getCraft_id() {
        return craft_id;
    }

    public void setCraft_id(Long craft_id) {
        this.craft_id = craft_id;
    }

    @Column(name = "ispass")
    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    @Column(name = "ischeck")
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", cover_url='" + cover_url + '\'' +
                ", content_url='" + content_url + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", post_time=" + post_time +
                ", like_count=" + like_count +
                ", favor_count=" + favor_count +
                ", click_count=" + click_count +
                ", share_count=" + share_count +
                ", delete=" + delete +
                ", craft_id=" + craft_id +
                ", pass=" + pass +
                ", check=" + check +
                '}';
    }
}
