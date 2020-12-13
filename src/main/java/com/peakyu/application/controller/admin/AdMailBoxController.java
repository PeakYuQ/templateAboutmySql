package com.peakyu.application.controller.admin;

import com.peakyu.application.dao.log;
import com.peakyu.application.dao.message;
import com.peakyu.application.dao.operatemessage;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.service.admin.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/admin/mail")
public class AdMailBoxController {
    @Autowired
    private com.peakyu.application.service.messageService messageService;

    @Autowired
    private logService logService;


    private void refreshMailBox(HttpSession session){
        //初始化-刷新admin邮箱
        messageService.adminInitializeBox("admin");

        //admin已读/未读邮件
        List<message> notreadMessage = messageService.selectAdminNotReadMessage(0,1000);

        //将bars信箱未读邮件存进session
        session.setAttribute("notreadmessage",notreadMessage);
    }


    private PageUtils<message> adminMailBox(Integer type, Integer currentPage){


        //id =1 未读  2收件箱  3全部信息  4 已发送
        PageUtils<message> messages;
        //初始化-刷新admin邮箱
        messageService.adminInitializeBox("admin");



        if (type == 1) {
            //admin未读邮件
            messages = messageService.selectAdminNotReadMessage(currentPage);
        }
        else if (type == 2){
            //admin的收件箱
            messages = messageService.selectSendToAdminMessage(currentPage);
        }
        else if (type == 3){
            //邮箱全部邮件
            messages = messageService.selectAllMessage(currentPage);
        }
        else if (type == 4){
            //admin已发送
            messages = messageService.selectSendMessage(currentPage);
        }
        else{
            //admin已读/未读邮件
            messages = messageService.selectAdminNotReadMessage(currentPage);
        }
        return messages;
    }


    //收件箱
    @PostMapping("/box")
    public String mailBox (@RequestParam("type") Integer type,
                           @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,Model model,HttpSession session){
        PageUtils<message> messages=adminMailBox(type,currentPage);

        refreshMailBox(session);

        model.addAttribute("type",type);
        model.addAttribute("message",messages);
        return "a/mailBox";
    }


    //发信息
    @PostMapping("/send")
    @ResponseBody
    public Integer sendMail(@RequestParam("select") Integer val,
                              @RequestParam("myinput") String Email,
                              @RequestParam("title")String title,
                              @RequestParam("message")String txt,
                              Model model,HttpSession session){




        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (val == 1){
            Email="emp";
        }
        else if (val == 2){
            Email="user";
        }
        else if (val == 3){
            Email="all";
        }


        log log = new log(4,"向"+Email+"发送了一条消息",timestamp,"admin");

        //日志 用户登录情况
        logService.insertLog(log);


        message message = new message(Email,txt,0,title,timestamp,"admin");
        Integer i = messageService.insertMessage(message);

        refreshMailBox(session);
        return i;
    }



    //信件详情
    @PostMapping("/details")
    public String mailDetails (@RequestParam("id") Integer id, HttpSession session,
                              Model model){
        //设置为已读
        operatemessage operatemessage = new operatemessage("admin",id);
        messageService.insertReadMessage(operatemessage);

        //查询信件
        message message = messageService.selectMailById(id);
        model.addAttribute("detial",message);


        //刷新admin邮箱
        messageService.adminInitializeBox("admin");
        //admin未读邮件
        List<message> notreadMessage = messageService.selectAdminNotReadMessage(0,1000);
        //将bars信箱未读邮件存进session
        session.setAttribute("notreadmessage",notreadMessage);

        refreshMailBox(session);
        return "a/mailDetails";
    }

    //检索信件
    @PostMapping("/search")
    public String mailSearch(@RequestParam("type") Integer type,
                      @RequestParam("keyword") String keyword,
                      @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,Model model ){

        //查询结果
        PageUtils<message> message = null;
        if(type == 0){
            message = messageService.adminSelectMailByTitle(currentPage,keyword);
            model.addAttribute("type","标题");
        }else if(type == 1){
            message = messageService.adminSelectMailByTxt(currentPage,keyword);
            model.addAttribute("type","内容");
        }
        else if(type == 2){
            message = messageService.selectSendMessage(currentPage,keyword);
            model.addAttribute("type","发件人");
        }else if(type == 3){
            message = messageService.adminSelectMailByEmail(currentPage,keyword);
            model.addAttribute("type","收件人");
        }
        //将关键字和查询结果返回
        model.addAttribute("keyword",keyword);
        model.addAttribute("message",message);

        return "a/searchMailResult";
    }



    //删除信件
    @PostMapping("/delete")
    @ResponseBody
    public String mailDelete(@RequestParam("id") Integer id, HttpServletResponse response,HttpSession session){


        response.setDateHeader("Expires", -1);
        messageService.adminDeleteMessage(id);

        //日志
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        log log = new log(3,"删除了一条消息",timestamp,"admin");
        //日志
        logService.insertLog(log);

        refreshMailBox(session);
        return "1";
    }


    //编辑用户信息
    @PostMapping("/edit")
    @ResponseBody
    public Integer mailDelete(@RequestParam("mailTitle") String title,
                             @RequestParam("mailTxt") String txt,
                              @RequestParam("mailId") Integer id){

        int i = messageService.updateMessage(txt, title, id);


        //日志
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        log log = new log(2,"修改了一条消息的内容",timestamp,"admin");
        //日志
        logService.insertLog(log);
        return i;
    }


    //回复信件
    @PostMapping("/reply")
    @ResponseBody
    public Integer mailQuickReply( @RequestParam("mailReplyId") Integer id,
                                @RequestParam("mailReplyTxt") String txt,@RequestParam("mailReplyTitle") String title,
                              Model model,HttpSession session,HttpServletResponse response) throws IOException {


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        message oldMail = messageService.selectMailById(id);

        String history = oldMail.getTxt();

        String role = oldMail.getrole();

        String newtxt ="<font color=\"#006cfa\" size=\"1\"> "+ history+"</font><br>"+txt+"<br>";
        message message = new message(role,newtxt,0,title,timestamp,"admin");

        int i = messageService.insertMessage(message);


        //日志
        log log = new log(4,"向"+role +"回复/发送一条消息的内容",timestamp,"admin");
        //日志
        logService.insertLog(log);
        refreshMailBox(session);
        return i;
    }


}
