package com.java.leo.web;

import com.java.leo.entity.Article;
import com.java.leo.entity.Comment;
import com.java.leo.entity.Type;
import com.java.leo.entity.User;
import com.java.leo.service.ArticleService;
import com.java.leo.service.TypeService;
import com.java.leo.service.UserService;
import com.java.leo.utils.JsonReaderResponse;
import com.java.leo.utils.Msg;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.cs.US_ASCII;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * @Package: Craft.com.java.leo.web
 * @Author: Leo
 * @Date: 2020/7/5 16:59
 * @Description: 文章操作
 */

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    TypeService typeService;

    UploadController controller = new UploadController();

    @ResponseBody
    @RequestMapping(value = "/deploy", method = RequestMethod.POST)
    public Msg deploy(@RequestParam("user_id") Long user_id,
                      @RequestParam("type_id") Long type_id,
                      @RequestParam("craft_id") Long craft_id,
                      @RequestParam("title") String title,
                      @RequestParam("cover") MultipartFile cover,
                      @RequestParam("img") MultipartFile images,
                      @RequestParam("text") String text,
                      HttpServletRequest request) {

        Article article = new Article();
        article.setCheck(false);
        article.setClick_count(0);
        article.setFavor_count(0);
        article.setLike_count(0);
        article.setPass(false);
        article.setPost_time(new Date());
        article.setShare_count(0);
        Type type = typeService.findOne(type_id);
        if (type == null) {
            return Msg.error(500, "类型错误");
        }
        article.setType(type);
        article.setCraft_id(craft_id);
        article.setUser(userService.findOne(user_id));

        // 存封面
        Msg cover_msg = controller.uploadPicture(cover, request);
        if (cover_msg.getCode() == 200) {
            String str = String.valueOf(cover_msg.getData().get("url"));
            article.setCover_url(str);
        } else {
            return Msg.error(500, "封面存储失败");
        }

        // 存图片
        Msg msg = controller.uploadPicture(images, request);
        if (cover_msg.getCode() == 200) {
            String str = String.valueOf(cover_msg.getData().get("url"));
            article.setContent_url(str);
        } else {
            return Msg.error(500, "图片存储失败");
        }

        article.setText(text);
        article.setTitle(title);

        System.out.println(article);
        articleService.save(article);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public JsonReaderResponse<Article> getList(@RequestParam(value = "title", required = false) String title,
                                               @RequestParam(value = "sort", defaultValue = "") String sort,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) throws ParseException {

        Map<String, Object> searchParams = new HashMap<>();
        if (StringUtils.isNotBlank(title)) {
            searchParams.put("LIKE_title", title);
        }
        Page<Article> list = articleService.getList(searchParams, pageNum, pageSize, sort, Article.class);
        System.out.println(pageNum);
        System.out.println(pageSize);
        return new JsonReaderResponse<>(list, pageSize, pageNum);
    }

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Msg articleDetail(@RequestParam(value = "article_id") Long article_id,
                             @RequestParam(value = "user_id") Long user_id) {
        Article article = articleService.findOne(article_id);
        if (article == null) {
            return Msg.error(404, "未找到该文章");
        }
        // 浏览量+1
        int click_count = article.getClick_count();
        article.setClick_count(++click_count);
        articleService.save(article);
        return Msg.success().add("article", article);
    }

    @ResponseBody
    @RequestMapping(value = "/sendComment")
    public Msg sendComment(@RequestParam(value = "user_id") Long user_id,
                           @RequestParam(value = "article_id") Long article_id,
                           @RequestParam(value = "content") String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setDelete(false);
        comment.setLike_count(0);
        comment.setType(0);
        comment.setReply(false);
        comment.setPost_time(new Date());
        User user = userService.findOne(user_id);
        if (user == null) {
            return Msg.error(404, "未找到该用户");
        }
        comment.setUser(user);
        Article article = articleService.findOne(article_id);
        if (article == null) {
            return Msg.error(404, "未找到该文章");
        }

        List<Comment> list = article.getComments();
        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(comment);
        article.setComments(list);
        articleService.save(article);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value = "/getComments", method = RequestMethod.GET)
    public Msg getComments(@RequestParam(value = "article_id", defaultValue = "") Long article_id) {

        Article article = articleService.findOne(article_id);
        List<Comment> list = article.getComments();
        return Msg.success().add("list", list);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserArticle", method = RequestMethod.GET)
    public JsonReaderResponse<Article> getUserArticle(@RequestParam(value = "user_id") Long user_id,
                                                      @RequestParam(value = "sort", defaultValue = "") String sort,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) throws ParseException {
        Map<String, Object> searchParams = new HashMap<>();
        User user = userService.findOne(user_id);
        searchParams.put("LIKE_user.email", user.getEmail());
        Page<Article> list = articleService.getList(searchParams, pageNum, pageSize, sort, Article.class);
        System.out.println(pageNum);
        System.out.println(pageSize);
        return new JsonReaderResponse<>(list, pageSize, pageNum);
    }


}
