package com.java.leo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Package: Craft.com.java.leo.web
 * @Author: Leo
 * @Date: 2020/7/7 16:23
 * @Description:
 */

@Controller
public class UrlController {

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "adminManager")
    public String adminManager() {
        return "adminManager";
    }


    @RequestMapping(value = "announceManager")
    public String announceManager() {
        return "announceManager";
    }


    @RequestMapping(value = "CourseAudit")
    public String CourseAudit() {
        return "CourseAudit";
    }


    @RequestMapping(value = "VideoAudit")
    public String VideoAudit() {
        return "VideoAudit";
    }

    @RequestMapping(value = "ArticleAudit")
    public String ArticleAudit() {
        return "ArticleAudit";
    }

    @RequestMapping(value = "CommentsAudit")
    public String CommentsAudit() {
        return "CommentsAudit";
    }

    @RequestMapping(value = "ArtistAudit")
    public String ArtistAudit() {
        return "ArtistAudit";
    }

    @RequestMapping(value = "entriesManage")
    public String entriesManage() {
        return "entriesManage";
    }

    @RequestMapping(value = "activityManage")
    public String activityManage() {
        return "activityManage";
    }

    @RequestMapping(value = "welcome")
    public String welcome() {
        return "welcome";
    }
}
