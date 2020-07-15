package com.java.leo.web;

import com.java.leo.entity.Type;
import com.java.leo.service.TypeService;
import com.java.leo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Package: Craft.com.java.leo.web
 * @Author: Leo
 * @Date: 2020/7/8 0:15
 * @Description:
 */
@Controller
@RequestMapping(value = "/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @ResponseBody
    @RequestMapping("/getAll")
    public Msg getAll() {
        List<Type> list = typeService.findAll();
        return Msg.success().add("types", list);
    }
}
