package com.peakyu.application.controller.admin;


import com.peakyu.application.dao.log;
import com.peakyu.application.dao.message;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
@RequestMapping("/admin/emergent")
public class settleEmergentController {


    @Autowired
    private com.peakyu.application.service.admin.logService logService;

    @Autowired
    private com.peakyu.application.service.messageService messageService;



    @RequestMapping("/all")
    public String allEmergent(@RequestParam("type") Integer type,
            @RequestParam(value = "currentPage", defaultValue = "1") Integer page, Model model){


        PageUtils<message> messages;
        if (type == 1) {
            //admin未读邮件
            messages = messageService.selectNotEmergent(page);
        }
        else if (type == 2){
            //admin的收件箱
            messages = messageService.selectSettleEmergent(page);
        }
        else if (type == 3){
            //admin的收件箱
            messages = messageService.selectAllEmergent(page);
        }else{
            messages = messageService.selectNotEmergent(page);
        }



        model.addAttribute("message",messages);
        model.addAttribute("type",type);
        return "a/emergent";
    }


    @PostMapping("/exam")
    @ResponseBody
    public String examEmergent(@RequestParam("id") Integer id,
                          @RequestParam("result") Integer result, Model model){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message message = messageService.selectMailById(id);

        if(message.getEmail().equals("1")){

            log log = new log(4,"发布一条通告",timestamp,"admin");

            //日志 用户登录情况
            logService.insertLog(log);

            message message2 = new message("note",message.getTxt()+"--来自用户"+message.getrole(),0,"紧急路况信息",timestamp,"admin");
            messageService.insertMessage(message2);

        }
        String s;
        if (result == 0){
            s ="审核不通过";
            messageService.updateMessageEmergant(id,6);
        }else {
            s ="审核通过";
            messageService.updateMessageEmergant(id,5);
        }


        message message3 = new message(message.getrole(),"您的紧急情况已处理!结果是"+s,0,"紧急情况回执",timestamp,"admin");

        messageService.insertMessage(message3);
        return "1";
    }



    //紧急详情
    @PostMapping("/details")
    public String mailDetails(@RequestParam("id") String id, Model model){

        //查询信件
        message message = messageService.selectMailById(Integer.parseInt(id));
        model.addAttribute("detial",message);
        return "a/emergentDetails";
    }

    //检索紧急
    @PostMapping("/search")
    public String mailSearch(@RequestParam("type") Integer type,
                             @RequestParam("keyword") String keyword,
                             @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
                             Model model,
                             HttpSession session){
        PageUtils<message> message = null;
        if(type == 1){
            message = messageService.selectEmergentByTxt(keyword,page);
            model.addAttribute("type","内容");
        }
        else if(type == 2){
            message = messageService.selectEmergentByRole(keyword,page);
            model.addAttribute("type","用户名");
        }

        model.addAttribute("keyword",keyword);
        model.addAttribute("message",message);
        return "a/searchEmergentResult";
    }



}
