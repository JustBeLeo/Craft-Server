package com.java.leo.web;

import com.java.leo.entity.*;
import com.java.leo.entity.Video;
import com.java.leo.service.VideoService;
import com.java.leo.service.TypeService;
import com.java.leo.service.UserService;
import com.java.leo.service.VideoService;
import com.java.leo.utils.JsonReaderResponse;
import com.java.leo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: Craft.com.java.leo.web
 * @Author: Leo
 * @Date: 2020/7/8 0:49
 * @Description:
 */

@Controller
@RequestMapping(value = "/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    UserService userService;

    @Autowired
    TypeService typeService;

    UploadController uploadController = new UploadController();

    @ResponseBody
    @RequestMapping(value = "/deploy")
    public Msg deploy(@RequestParam(name = "name") String name,
                      @RequestParam(name = "user_id") Long user_id,
                      @RequestParam(name = "type_id") Long type_id,
                      @RequestParam(name = "craft_id") Long craft_id,
                      @RequestParam(name = "cover") MultipartFile cover,
                      @RequestParam(name = "video") MultipartFile video,
                      @RequestParam(name = "des",required = false) String desc,
                      HttpServletRequest request) {

        Video v = new Video();
        v.setCheck(false);
        v.setClick_count(0);
        v.setFavor_count(0);
        v.setComments_count(0);
        v.setLike_count(0);
        v.setRelease_time(new Date());

        v.setName(name);
        v.setCraft_id(craft_id);
        v.setDes(desc);
        User user = userService.findOne(user_id);
        if (user == null) {
            return Msg.error(500, "未找到该用户");
        }
        v.setUser(user);

        Type type = typeService.findOne(type_id);
        if (type == null) {
            return Msg.error(500, "未找到该工艺品类型");
        }
        v.setType(type);

        // 存封面
        Msg cover_msg = uploadController.uploadPicture(cover, request);
        if (cover_msg.getCode() == 200) {
            String str = String.valueOf(cover_msg.getData().get("url"));
            v.setCover_url(str);
        } else {
            return Msg.error(500, "封面存储失败");
        }

        // 存视频
        Msg video_msg = uploadController.uploadVideo(video, request);
        if (video_msg.getCode() == 200) {
            String str = String.valueOf(video_msg.getData().get("url"));
            v.setVideo_url(str);
        } else {
            return Msg.error(500, "视频存储失败");
        }

        videoService.save(v);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public JsonReaderResponse<Video> getList(@RequestParam(value = "sort", defaultValue = "") String sort,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) throws ParseException {

        Map<String, Object> searchParams = new HashMap<>();
        Page<Video> list = videoService.getList(searchParams, pageNum, pageSize, sort, Video.class);
        System.out.println(pageNum);
        System.out.println(pageSize);
        return new JsonReaderResponse<>(list, pageSize, pageNum);
    }

    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Msg VideoDetail(@RequestParam(value = "Video_id") Long video_id,
                             @RequestParam(value = "user_id") Long user_id) {
        Video video = videoService.findOne(video_id);
        if (video == null) {
            return Msg.error(404, "未找到该视频");
        }
        // 浏览量+1
        int click_count = video.getClick_count();
        video.setClick_count(++click_count);
        videoService.save(video);
        return Msg.success().add("video", video);
    }

    @ResponseBody
    @RequestMapping(value = "getUserVideo", method = RequestMethod.GET)
    public JsonReaderResponse<Video> getUserVideo(@RequestParam(value = "user_id") Long user_id,
                                                      @RequestParam(value = "sort", defaultValue = "") String sort,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) throws ParseException {
        Map<String, Object> searchParams = new HashMap<>();
        User user = userService.findOne(user_id);
        searchParams.put("LIKE_user.email", user.getEmail());
        Page<Video> list = videoService.getList(searchParams, pageNum, pageSize, sort, Video.class);
        return new JsonReaderResponse<>(list, pageSize, pageNum);
    }
}
