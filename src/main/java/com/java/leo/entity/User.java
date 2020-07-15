package com.java.leo.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("用户信息")
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    // 电话
    private String phone;
    // 密码
    private String password;
    // 性别
    private Boolean gender;
    // 登录状态
    private Boolean status;
    // 用户等级
    private Integer level;
    // 用户名
    private String username;
    // 省
    private String province;
    // 市
    private String city;
    // 注册时间
    private Date reg_time;
    // 上次登录时间
    private Date last_login;
    // 用户头像
    private String avatar_url;
    // 邮箱
    private String email;
    // 关注
    private Integer focus_count;
    // 粉丝数
    private Integer focused_count;
    // 收藏
    private Integer favor_count;
    // 未读消息
    private Integer msg_count_not_read;
    // 无广告时间
    private Date noAdv_time;
    // 游戏币
    private BigDecimal money;
    // 个性签名
    private String motto;

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "gender")
    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Column(name = "reg_time")
    public Date getReg_time() {
        return reg_time;
    }

    public void setReg_time(Date reg_time) {
        this.reg_time = reg_time;
    }
    @Column(name = "last_login")
    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    @Column(name = "avatar_url")
    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Column(name = "e_mail")
    public String getEmail() {
        return email;
    }

    public void setEmail(String e_mail) {
        this.email = e_mail;
    }
    @Column(name = "focus_count")
    public Integer getFocus_count() {
        return focus_count;
    }

    public void setFocus_count(Integer focus_count) {
        this.focus_count = focus_count;
    }
    @Column(name = "focused_count")
    public Integer getFocused_count() {
        return focused_count;
    }

    public void setFocused_count(Integer focused_count) {
        this.focused_count = focused_count;
    }
    @Column(name = "favor_count")
    public Integer getFavor_count() {
        return favor_count;
    }

    public void setFavor_count(Integer favor_count) {
        this.favor_count = favor_count;
    }
    @Column(name = "msg_count_not_read")
    public Integer getMsg_count_not_read() {
        return msg_count_not_read;
    }

    public void setMsg_count_not_read(Integer msg_count_not_read) {
        this.msg_count_not_read = msg_count_not_read;
    }
    @Column(name = "noAdv_time")
    public Date getNoAdv_time() {
        return noAdv_time;
    }

    public void setNoAdv_time(Date noAdv_time) {
        this.noAdv_time = noAdv_time;
    }
    @Column(name = "money")
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    @Column(name = "motto")
    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
}