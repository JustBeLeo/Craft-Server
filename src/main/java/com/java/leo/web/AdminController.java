package com.java.leo.web;

import com.java.leo.entity.Admin;
import com.java.leo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Package: Craft.com.java.leo.web
 * @Author: Leo
 * @Date: 2020/7/7 22:17
 * @Description:
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @RequestMapping(value = "login")
    public String login(@RequestParam(name = "name") String name,
                        @RequestParam(name = "password") String password) {

        Admin admin = adminService.findByName(name);
        if (admin == null){
            return "用户不存在";
        }
        if (admin.getPassword().equals(password)){
            return "index";
        }
        return "index";
    }

}
