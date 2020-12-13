package com.peakyu.application.controller.user;

import com.peakyu.application.dao.log;
import com.peakyu.application.dao.message;
import com.peakyu.application.dao.operatemessage;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.service.admin.logService;
import com.peakyu.application.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
@RequestMapping("/mail")
public class BarsMessageController {

    @Autowired
    private userService userService;

    @Autowired
    private logService logService;
    @Autowired
    private com.peakyu.application.service.messageService messageService;




    private void refreshMailBox(HttpSession session){
        //初始化心想
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除
        PageUtils<message> notReadmessage = messageService.selectNotReadMessage(1,(String) session.getAttribute("operator"));//查询未读消息 写入session
        session.setAttribute("message",notReadmessage);

    }

    //我的信箱
    @GetMapping("/box")
    public String mailBox(HttpSession session, Model model){
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除

        PageUtils<message> sendeadmessage = messageService.selectSendMessage(1,(String)session.getAttribute("operator"));
        model.addAttribute("sendmessage",sendeadmessage);
        PageUtils<message> notReadmessage = messageService.selectNotReadMessage(1,(String) session.getAttribute("operator"));//查询未读消息
        model.addAttribute("notReadmessage",notReadmessage);
        PageUtils<message> readmessage = messageService.selectReadMail(1,(String) session.getAttribute("operator"));//查询已读消息
        model.addAttribute("readmessage",readmessage);
        PageUtils<message> allmessage = messageService.selectMailByEmail(1,(String) session.getAttribute("operator"));//查询全部消息
        model.addAttribute("allmessage",allmessage);
        PageUtils<message> deletemessage = messageService.selectDeleteMessage(1,(String) session.getAttribute("operator"));//查询已删除消息
        model.addAttribute("deletemessage",deletemessage);

        //0 未读 1已读  2发送 3已删除 4全部消息 Box的header
        model.addAttribute("header",0);

        refreshMailBox(session);
        return "u/mailBox";
    }

    //我的信箱分页入口
    @PostMapping("/box")
    public String mailBoxPost(HttpSession session,
                              @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
                              @RequestParam("header") Integer header,
                              Model model){
        Integer page0=1,page1=1,page2=1,page3=1,page4=1;

        if(header == 0)
            page0 = page;
        else if(header == 1)
            page1 = page;
        else if(header == 2)
            page2 = page;
        else if(header == 3)
            page3 = page;
        else if(header == 4)
            page4 = page;

        PageUtils<message> notReadmessage = messageService.selectNotReadMessage(page0,(String) session.getAttribute("operator"));//查询未读消息
        model.addAttribute("notReadmessage",notReadmessage);
        PageUtils<message> readmessage = messageService.selectReadMail(page1,(String) session.getAttribute("operator"));//查询已读消息
        model.addAttribute("readmessage",readmessage);
        PageUtils<message> sendeadmessage = messageService.selectSendMessage(page2,(String)session.getAttribute("operator"));
        model.addAttribute("sendmessage",sendeadmessage);
        PageUtils<message> deletemessage = messageService.selectDeleteMessage(page3,(String) session.getAttribute("operator"));//查询已删除消息
        model.addAttribute("deletemessage",deletemessage);
        PageUtils<message> allmessage = messageService.selectMailByEmail(page4,(String) session.getAttribute("operator"));//查询全部消息
        model.addAttribute("allmessage",allmessage);

        //0 未读 1已读  2发送 3已删除 4全部消息 Box的header
        model.addAttribute("header",header);
        refreshMailBox(session);
        return "u/mailBox";
    }

    //信件详情
    @PostMapping("/details")
    public String mailDetails(@RequestParam("id") String id, Model model,HttpSession session){
        //设置为已读
        operatemessage operatemessage = new operatemessage((String) session.getAttribute("operator"),Integer.parseInt(id));
        messageService.insertReadMessage(operatemessage);

        //刷新状态栏的信息
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除
        PageUtils<message> notReadmessage = messageService.selectNotReadMessage(1,(String) session.getAttribute("operator"));//查询未读消息 写入session
        session.setAttribute("message",notReadmessage);

        //查询信件
        message message = messageService.selectMailById(Integer.parseInt(id));
        model.addAttribute("detial",message);
        refreshMailBox(session);
        return "u/mailDetails";
    }

    //检索信件
    @PostMapping("/search")
    public String mailSearch(@RequestParam("type") Integer type,
                      @RequestParam("keyword") String keyword,
                      @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
                      Model model,
                      HttpSession session){
        PageUtils<message> message = null;
        if(type == 0){
            message = messageService.selectMailByTitle(page,keyword,(String) session.getAttribute("operator"));
            model.addAttribute("type","标题");
        }else if(type == 1){
            message = messageService.selectMailByTitle(page,keyword,(String) session.getAttribute("operator"));
            model.addAttribute("type","内容");
        }
        else if(type == 2){
            message = messageService.selectMailByRole(page,keyword,(String) session.getAttribute("operator"));
            model.addAttribute("type","发件人");
        }
        model.addAttribute("keyword",keyword);
        model.addAttribute("allmessage",message);
        return "u/searchMailResult";
    }

    //设置为已读
    @PostMapping("/setRead")
    public void mailSetRead(@RequestParam("id") Integer id, Model model,HttpSession session,HttpServletResponse response) throws IOException {
        //设置为已读
        operatemessage operatemessage = new operatemessage((String) session.getAttribute("operator"),id);
        messageService.insertReadMessage(operatemessage);

        //刷新状态栏的信息
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除
        PageUtils<message> notReadmessage = messageService.selectNotReadMessage(1,(String) session.getAttribute("operator"));//查询未读消息 写入session
        session.setAttribute("message",notReadmessage);
        refreshMailBox(session);
        //返回我的信箱
        response.sendRedirect("/mail/box");
    }

    //设置为删除
    @PostMapping("/delete")
    public void mailDelete(@RequestParam("id") Integer id, Model model,HttpSession session,HttpServletResponse response) throws IOException {
        //设置为删除
        operatemessage operatemessage = new operatemessage((String) session.getAttribute("operator"),id);
        messageService.insertDeleteMessage(operatemessage);

        //日志
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        log log = new log(3,"删除一条消息",timestamp,(String) session.getAttribute("operator"));
        //日志
        logService.insertLog(log);

        //刷新状态栏的信息
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除
        PageUtils<message> notReadmessage = messageService.selectNotReadMessage(1,(String) session.getAttribute("operator"));//查询未读消息 写入session
        session.setAttribute("message",notReadmessage);
        refreshMailBox(session);
        //返回我的信箱
        response.sendRedirect("/mail/box");
    }

    //撤回删除
    @PostMapping("/backdelete")
    public void mailBackDelete(@RequestParam("id") Integer id, Model model,HttpSession session,HttpServletResponse response) throws IOException {
        //撤回删除
        operatemessage operatemessage = new operatemessage((String) session.getAttribute("operator"),id);
        messageService.deleteDeleteMessage(operatemessage);


        //刷新状态栏的信息
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除
        PageUtils<message> notReadmessage = messageService.selectNotReadMessage(1,(String) session.getAttribute("operator"));//查询未读消息 写入session
        session.setAttribute("message",notReadmessage);
        //返回我的信箱
        refreshMailBox(session);
        response.sendRedirect("/mail/box");
    }

    //回复信件
    @PostMapping("/quickReply")
    @ResponseBody
    public Integer mailQuickReply(@RequestParam("txt") String txt,@RequestParam("history") String history,@RequestParam("title") String title,
                           @RequestParam("role") String role,Model model,HttpSession session,HttpServletResponse response) throws IOException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String newtxt ="<font color=\"#006cfa\" size=\"1\"> "+ history+"</font><br>"+txt+"<br>";
        message message = new message(role,newtxt,0,title,timestamp,(String)session.getAttribute("operator"));
        int i = messageService.insertMessage(message);


        //日志
        log log = new log(4,"向"+role +"回复/发送一条消息的内容",timestamp,(String) session.getAttribute("operator"));
        //日志
        logService.insertLog(log);
        refreshMailBox(session);
        //返回我的信箱
       return i;
    }


}
