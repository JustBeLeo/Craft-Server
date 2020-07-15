package com.java.leo.web;

import com.java.leo.utils.Msg;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @Package: Craft.com.java.leo.web
 * @Author: Leo
 * @Date: 2020/7/3 14:51
 * @Description:
 */

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    @ResponseBody
    @RequestMapping(value = "/picture")
    public Msg uploadPicture(@RequestParam(name = "file") MultipartFile file, HttpServletRequest request) {
        String type = Objects.requireNonNull(
                file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf("."));
        String filename = System.currentTimeMillis() + type;
        String path = request.getSession().getServletContext().getRealPath("/static/upload/picture/" + filename);
        File destFile = new File(path);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
        } catch (IOException e) {
            e.printStackTrace();
            return Msg.error(101, "文件存储失败");
        }
        path = "/static/upload/picture/" + filename;
        System.out.println(path);
        return Msg.success().add("url", path);
    }

    @ResponseBody
    @RequestMapping(value = "/video")
    public Msg uploadVideo(@RequestParam(name = "file") MultipartFile file, HttpServletRequest request) {
        String type = Objects.requireNonNull(
                file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf("."));
        String filename = System.currentTimeMillis() + type;
        String path = request.getSession().getServletContext().getRealPath("/static/upload/video/" + filename);
        File destFile = new File(path);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
        } catch (IOException e) {
            e.printStackTrace();
            return Msg.error(101, "文件存储失败");
        }
        path = "/static/upload/video/" + filename;
        System.out.println(path);
        return Msg.success().add("url", path);
    }
}
