package com.java.leo.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.leo.entity.Announce;
import com.java.leo.service.AnnounceService;
import com.java.leo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Package: Craft.com.java.leo.web
 * @Author: Leo
 * @Date: 2020/7/7 16:07
 * @Description:
 */

@Controller
@RequestMapping(value = "/announce")
public class AnnounceController {

    @Autowired
    AnnounceService announceService;

    @ResponseBody
    @RequestMapping(value = "/deploy")
    public Msg deploy(@RequestParam(name = "title") String title,
                      @RequestParam(name = "content") String content,
                      @RequestParam(name = "sender_id") Long sender_id) {
        Announce announce = new Announce();
        announce.setClick_count(0);
        announce.setTitle(title);
        announce.setContent(content);
        announce.setSender_id(sender_id);
        announce.setPost_time(new Date());
        announce.setDelete(false);
        announceService.save(announce);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/getAll")
    public Msg getAll() {
        List<Announce> announces = announceService.findAll();
        return Msg.success().add("announces",announces);
    }

    @ResponseBody
    @GetMapping("/getAnnounce")
    public Msg getAnnounce(@RequestParam(value = "pn", defaultValue = "1") Integer page) {
        PageHelper.startPage(page, 8);
        List<Announce> list = announceService.findAll();
        PageInfo<Announce> pages = new PageInfo<Announce>(list, 8);
        return Msg.success().add("list", pages);
    }

    @ResponseBody
    @DeleteMapping("/deleteById")
    public Msg delete(Long id) {
        announceService.delete(id);
        return Msg.success();
    }

    @ResponseBody
    @PutMapping("/modify")
    public Msg modify(Announce announce) {
        announceService.save(announce);
        return Msg.success();
    }


}
