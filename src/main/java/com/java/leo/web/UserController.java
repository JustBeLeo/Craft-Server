package com.java.leo.web;

import com.java.leo.entity.Focus;
import com.java.leo.entity.User;
import com.java.leo.service.FocusService;
import com.java.leo.service.UserService;
import com.java.leo.utils.Msg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @Package: Craft.com.java.leo.web
 * @Author: Leo
 * @Date: 2020/7/3 13:48
 * @Description:
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FocusService focusService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg login(@RequestParam(name = "type") int type,
                     @RequestParam(name = "phone", required = false) String phone,
                     @RequestParam(name = "e_mail", required = false) String email,
                     @RequestParam(name = "password") String password) {
        User user;
        if (type == 1) {
            user = userService.findByPhone(phone);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    Date date = user.getLast_login();
                    user.setLast_login(new Date());
                    userService.save(user);
                    user.setLast_login(date);
                    return Msg.success().add("data", user);
                }
            }
        }
        if (type == 2) {
            user = userService.findByEmail(email);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    Date date = user.getLast_login();
                    user.setLast_login(new Date());
                    userService.save(user);
                    user.setLast_login(date);
                    return Msg.success().add("data", user);
                }
            }
        }
        return Msg.error(404, "用户名或密码错误或用户不存在");
    }

    @ResponseBody
    @RequestMapping(value = "/getInfo")
    public Msg getInfo(@RequestParam("id") Long id) {
        User user = userService.findOne(id);
        return Msg.success().add("user", user);
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public Msg register(@RequestParam(name = "email") String email,
                        @RequestParam(name = "username") String username,
                        @RequestParam(name = "phone") String phone,
                        @RequestParam(name = "password") String password) {
        // 异常情况
        if (userService.findByPhone(phone) != null) {
            return Msg.error(1, "该手机号已被注册");
        }
        if (userService.findByEmail(email) != null) {
            return Msg.error(2, "该邮箱已被注册");
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setReg_time(new Date());
        user.setLevel(1);
        user.setFavor_count(0);
        user.setFocus_count(0);
        user.setFocused_count(0);
        user.setStatus(false);
        userService.save(user);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/modify")
    public Msg modifyUser(@RequestParam("id") Long id,
                          @RequestParam(name = "email", required = false) String email,
                          @RequestParam(name = "username", required = false) String username,
                          @RequestParam(name = "province", required = false) String province,
                          @RequestParam(name = "city", required = false) String city,
                          @RequestParam(name = "motto", required = false) String motto,
                          @RequestParam(name = "gender", required = false) Boolean gender,
                          @RequestParam(name = "avatar", required = false) MultipartFile file,
                          HttpServletRequest request) {
        User user = userService.findOne(id);

        if (StringUtils.isNotEmpty(email)) user.setEmail(email);
        if (StringUtils.isNotEmpty(username)) user.setUsername(username);
        if (StringUtils.isNotEmpty(province)) user.setProvince(province);
        if (StringUtils.isNotEmpty(city)) user.setCity(city);
        if (StringUtils.isNotEmpty(motto)) user.setMotto(motto);
        if (gender != null) user.setGender(gender);

        if (file != null) {
            UploadController controller = new UploadController();
            Msg msg = controller.uploadPicture(file, request);
            String path = "";
            if (msg.getCode() == 200) {
                path = String.valueOf(msg.getData().get("url"));
                System.out.println("LOG:avatar_url" + path);
                user.setAvatar_url(path);
            }
        }
        userService.save(user);
        return Msg.success().add("data", user);
    }

    @ResponseBody
    @RequestMapping(value = "addFocus")
    public Msg addFocus(@RequestParam(value = "user_id") Long user_id,
                        @RequestParam(value = "focus_id") Long focus_id) {
        if (user_id.equals(focus_id)) {
            return Msg.error(404, "你不能关注自己");
        }
        User user = userService.findOne(user_id);
        User focus_user = userService.findOne(focus_id);
        if (user == null || focus_user == null) {
            return Msg.error(404, "未找到用户");
        }

        Focus focus;
        focus = focusService.findByUser_idAndFocus_id(user_id, focus_id);
        if (focus != null) {
            return Msg.error(404, "你不能重复关注");
        }
        focus = new Focus();
        focus.setFocus_user(focus_user);
        focus.setUser(user);

        // 设置关注人数+1
        int focus_count = user.getFocus_count();
        user.setFocused_count(++focus_count);
        // 设置被关注人数+1
        int focused_count = focus_user.getFocused_count();
        focus_user.setFocused_count(++focused_count);
        userService.save(user);
        userService.save(focus_user);
        
        focusService.save(focus);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "cancelFocus")
    public Msg cancelFocus(@RequestParam(value = "user_id") Long user_id,
                           @RequestParam(value = "focus_id") Long focus_id) {
        Focus focus = focusService.findByUser_idAndFocus_id(user_id, focus_id);
        if (focus == null) {
            return Msg.error(404, "取消关注失败");
        }
        User user = userService.findOne(user_id);
        User focus_user = userService.findOne(focus_id);
        if (user == null || focus_user == null) {
            return Msg.error(404, "未找到用户");
        }

        // 设置关注人数-1
        int focus_count = user.getFocus_count();
        user.setFocused_count(--focus_count);
        // 设置被关注人数-1
        int focused_count = focus_user.getFocused_count();
        focus_user.setFocused_count(--focused_count);

        userService.save(user);
        userService.save(focus_user);
        focusService.delete(focus.getId());
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "isFocus")
    public Msg isFocus(@RequestParam(value = "user_id") Long user_id,
                       @RequestParam(value = "focus_id") Long focus_id) {

        Focus focus = focusService.findByUser_idAndFocus_id(user_id, focus_id);
        if (focus == null) {
            return Msg.success().add("isFocus", false);
        }
        return Msg.success().add("isFocus", true);
    }

    @ResponseBody
    @RequestMapping(value = "getFocus")
    public Msg getFocus(@RequestParam(value = "user_id") Long user_id) {
        List<Focus> focus;
        try {
            focus = focusService.findByUser_id(user_id);
        }catch (Exception e){
            return Msg.error(404, "未找到关注");
        }
        return Msg.success().add("list", focus);
    }
    // 获取粉丝列表
    @ResponseBody
    @RequestMapping(value = "getFocused")
    public Msg getFocused(@RequestParam(value = "user_id") Long user_id) {
        List<Focus> focus;
        try {
            // 查找被关注人是用户id的用户
            focus = focusService.findByFocus_id(user_id);
        }catch (Exception e){
            return Msg.error(404, "未找到关注");
        }
        return Msg.success().add("list", focus);
    }
}
