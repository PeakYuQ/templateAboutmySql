package com.peakyu.application.controller.admin;


import com.peakyu.application.dao.log;
import com.peakyu.application.dao.message;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.service.admin.logService;
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
@RequestMapping("/admin/note")
public class adminNoteController {

    @Autowired
    private logService logService;


    @Autowired
    private com.peakyu.application.service.messageService messageService;

    @RequestMapping("/all")
    public String allNote(@RequestParam(value = "currentPage", defaultValue = "1") Integer page, Model model){


        PageUtils<message> note = messageService.adminSelectMailByEmail(page, "note");

        for(message me:note.getList()){
            if (me.getTxt().length() >= 10)
                me.setTxt(me.getTxt().substring(0, 9)+"......");
        }
        model.addAttribute("message",note);

        return "a/note";
    }

    //公告详情
    @PostMapping("/details")
    public String mailDetails(@RequestParam("id") String id, Model model, HttpSession session){

        //查询信件
        message message = messageService.selectMailById(Integer.parseInt(id));
        model.addAttribute("detial",message);
        return "a/noteDetails";
    }


    //发信息
    @PostMapping("/send")
    @ResponseBody
    public Integer sendMail(
                            @RequestParam("title")String title,
                            @RequestParam("message")String txt,
                            Model model){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());



        log log = new log(4,"发布一条通告",timestamp,"admin");

        //日志 用户登录情况
        logService.insertLog(log);


        message message = new message("note",txt,0,title,timestamp,"admin");
        Integer i = messageService.insertMessage(message);
        return i;
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
            message = messageService.selectNoteByTitle(page,keyword);
            model.addAttribute("type","标题");
        }else if(type == 1){
            message = messageService.selectNoteByTxt(page,keyword);
            model.addAttribute("type","内容");
        }
        else if(type == 2){
            message = messageService.selectNoteByRole(page,keyword);
            model.addAttribute("type","发件人");
        }
        model.addAttribute("keyword",keyword);
        model.addAttribute("message",message);
        return "a/searchNoteResult";
    }

}
