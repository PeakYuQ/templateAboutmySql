package com.peakyu.application.controller.employee;

import com.peakyu.application.dao.message;
import com.peakyu.application.dao.operatemessage;
import com.peakyu.application.dao.log;
import com.peakyu.application.service.admin.logService;
import com.peakyu.application.service.user.userService;
import com.peakyu.application.utils.PageUtils;
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
@RequestMapping("/employee/mail")
public class messageController {

    @Autowired
    private com.peakyu.application.service.messageService messageService;

    @Autowired
    private userService userService;

    @Autowired
    private logService logService;




    private void refreshMailBox(HttpSession session){
        //初始化心想
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除
        //admin已读/未读邮件
        List<message> notreadMessage = messageService.selectNotReadMessage("aa@1.com",0,1000);

        //将bars信箱未读邮件存进session
        session.setAttribute("notreadmessage",notreadMessage);

    }

    //我的信箱
    @PostMapping("/box")
    public String employeeMailBox(@RequestParam("type") Integer type,
                                  @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                  HttpSession session, Model model){

        //初始化心想
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除


        PageUtils<message> messages;

        if (type == 1) {
            //admin未读邮件
            messages = messageService.selectNotReadMessage(currentPage,(String) session.getAttribute("operator"));
        }
        else if (type == 2){
            //admin的收件箱
            messages = messageService.selectMailByEmail(currentPage,(String) session.getAttribute("operator"));
        }
        else if (type == 3){
            //邮箱全部邮件
            messages = messageService.selectMailByEmail(currentPage,(String) session.getAttribute("operator"));//查询全部消息
        }
        else if (type == 4){
            //admin已发送
            messages = messageService.selectSendMessage(currentPage,(String)session.getAttribute("operator"));
        }
        else{
            //admin已读/未读邮件
            messages = messageService.selectReadMail(currentPage,(String) session.getAttribute("operator"));//查询已读消息
        }

        model.addAttribute("type",type);
        model.addAttribute("message",messages);
        return "e/mailBox";
    }




    @PostMapping("/send")
    @ResponseBody
    public Integer EmployeeSendMail(@RequestParam("select") Integer val,
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

        log log = new log(4,"向"+Email+"发送了一条消息",timestamp,(String) session.getAttribute("operator"));

        //日志 用户登录情况
        logService.insertLog(log);

        message message = new message(Email,txt,0,title,timestamp,(String) session.getAttribute("operator"));
        Integer i = messageService.insertMessage(message);
        refreshMailBox(session);
        return i;
    }



    //信件详情
    @PostMapping("/details")
    public String employeeMailDetails(@RequestParam("id") Integer id, Model model,HttpSession session){
        //设置为已读
        operatemessage operatemessage = new operatemessage((String) session.getAttribute("operator"),id);
        messageService.insertReadMessage(operatemessage);

        //查询信件
        message message = messageService.selectMailById(id);
        model.addAttribute("detial",message);


        //初始化心想
        messageService.InitializeBox((String) session.getAttribute("operator")); //刷新消息状态 整体未读-已读-已删除

        //admin已读/未读邮件
        List<message> notreadMessage = messageService.selectNotReadMessage("aa@1.com",0,1000);

        //将bars信箱未读邮件存进session
        session.setAttribute("notreadmessage",notreadMessage);

        refreshMailBox(session);
        return "e/mailDetails";
    }


    //检索信件
    @PostMapping("/search")
    public String employeeMailSearch(@RequestParam("type") Integer type,
                             @RequestParam("keyword") String keyword,
                             @RequestParam(value = "currentPage", defaultValue = "1") Integer page,Model model,HttpSession session ){

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
        model.addAttribute("message",message);

        return "e/searchMailResult";
    }


    //删除信件
    @PostMapping("/delete")
    @ResponseBody
    public String employeeMailDelete(@RequestParam("id") Integer id, HttpServletResponse response,HttpSession session){


        response.setDateHeader("Expires", -1);
        messageService.adminDeleteMessage(id);

        //日志
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        log log = new log(3,"删除了一条消息",timestamp,(String) session.getAttribute("operator"));
        //日志
        logService.insertLog(log);

        refreshMailBox(session);
        return "1";
    }


    //回复信件
    @PostMapping("/reply")
    @ResponseBody
    public Integer employeeMailQuickReply( @RequestParam("mailReplyId") Integer id,
                                   @RequestParam("mailReplyTxt") String txt,@RequestParam("mailReplyTitle") String title,
                                   Model model,HttpSession session,HttpServletResponse response) throws IOException {


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        message oldMail = messageService.selectMailById(id);

        String history = oldMail.getTxt();

        String role = oldMail.getrole();

        String newtxt ="<font color=\"#006cfa\" size=\"1\"> "+ history+"</font><br>"+txt+"<br>";
        message message = new message(role,newtxt,0,title,timestamp,(String) session.getAttribute("operator"));

        int i = messageService.insertMessage(message);


        //日志
        log log = new log(4,"向"+role +"回复/发送一条消息的内容",timestamp,(String) session.getAttribute("operator"));
        //日志
        logService.insertLog(log);
        refreshMailBox(session);
        return i;
    }


}
