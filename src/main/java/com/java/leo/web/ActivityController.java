package com.java.leo.web;

import com.java.leo.entity.Activity;
import com.java.leo.entity.ActivityMember;
import com.java.leo.entity.Article;
import com.java.leo.entity.User;
import com.java.leo.service.ActivityMemberService;
import com.java.leo.service.ActivityService;
import com.java.leo.service.UserService;
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
 * @Date: 2020/7/8 16:13
 * @Description:
 */

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityMemberService activityMemberService;

    @Autowired
    UserService userService;

    UploadController uploadController = new UploadController();

    @ResponseBody
    @RequestMapping("/deploy")
    public Msg deploy(@RequestParam(value = "user_id") Long user_id,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "site") String site,
                      @RequestParam(value = "cover") MultipartFile cover,
                      @RequestParam(value = "content") String content,
                      @RequestParam(value = "start_time") Long start_time,
                      @RequestParam(value = "end_time") Long end_time,
                      HttpServletRequest request) {
        Activity activity = new Activity();
        activity.setContent(content);
        activity.setName(name);
        activity.setSite(site);
        Date date = new Date();
        date.setTime(start_time);
        activity.setStart_time(date);
        date.setTime(end_time);
        activity.setEnd_time(date);
        activity.setMember_count(0);

        Msg cover_msg = uploadController.uploadPicture(cover, request);
        if (cover_msg.getCode() == 200) {
            String str = String.valueOf(cover_msg.getData().get("url"));
            activity.setCover_url(str);
        } else {
            return Msg.error(500, "封面存储失败");
        }

        User user = userService.findOne(user_id);
        if (user == null) {
            return Msg.error(500, "用户未找到");
        }

        activityService.save(activity);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public JsonReaderResponse<Activity> getList(@RequestParam(value = "sort", defaultValue = "") String sort,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) throws ParseException {

        Map<String, Object> searchParams = new HashMap<>();
        Page<Activity> list = activityService.getList(searchParams, pageNum, pageSize, sort, Activity.class);
        System.out.println(pageNum);
        System.out.println(pageSize);
        return new JsonReaderResponse<>(list, pageSize, pageNum);
    }

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Msg activityDetail(@RequestParam(value = "activity_id") Long activity_id,
                              @RequestParam(value = "user_id") Long user_id) {
        Activity activity = activityService.findOne(activity_id);
        if (activity == null) {
            return Msg.error(404, "未找到该文章");
        }
        return Msg.success().add("activity", activity);
    }

    @ResponseBody
    @RequestMapping(value = "/join")
    public Msg joinActivity(@RequestParam(value = "activity_id") Long activity_id,
                            @RequestParam(value = "user_id") Long user_id) {
        Activity activity = activityService.findOne(activity_id);
        User user = userService.findOne(user_id);
        if (activity == null || user == null) {
            return Msg.error(404, "未找到活动或用户");
        }
        ActivityMember activityMember = new ActivityMember();
        activityMember.setActivity(activity);
        activityMember.setUser(user);
        activity.setMember_count(activity.getMember_count() + 1);
        activityService.save(activity);
        activityMemberService.save(activityMember);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/exit")
    public Msg exitActivity(@RequestParam(value = "activity_id") Long activity_id,
                            @RequestParam(value = "user_id") Long user_id) {
        ActivityMember am = activityMemberService.findByUserIdAndActivity_Id(user_id, activity_id);
        if (am == null) {
            return Msg.error(404, "未找到活动或用户");
        }
        Activity activity = activityService.findOne(activity_id);
        activity.setMember_count(activity.getMember_count() - 1);
        activityService.save(activity);
        activityMemberService.delete(am.getId());
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/isJoin")
    public Msg isJoin(@RequestParam(value = "activity_id") Long activity_id,
                      @RequestParam(value = "user_id") Long user_id) {
        ActivityMember am;
        try {
            am = activityMemberService.findByUserIdAndActivity_Id(user_id, activity_id);
        } catch (Exception e) {
            return Msg.error(404, "未找到").add("isJoin", false);
        }
        if (am == null) {
            return Msg.error(404, "未找到").add("isJoin", false);
        }
        return Msg.success().add("isJoin", true);
    }

}
