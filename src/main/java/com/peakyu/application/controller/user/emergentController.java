package com.peakyu.application.controller.user;


import com.peakyu.application.dao.log;
import com.peakyu.application.dao.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
@RequestMapping("/emergent")
public class emergentController {
    @Autowired
    private com.peakyu.application.service.admin.logService logService;
    @Autowired
    private com.peakyu.application.service.messageService messageService;


    @GetMapping("/all")
    public String emergent(){
        return "u/emergent";
    }


    @PostMapping("/submit")
    @ResponseBody
    public int emergentSbumit(@RequestParam("select") String type,
                              @RequestParam("txt") String txt, HttpSession session){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        message message = new message(type,txt,0,"用户"+(String)session.getAttribute("operator")+"上报紧急情况",timestamp,(String)session.getAttribute("operator"));
        int i = messageService.insertMessage(message);


        //日志
        log log = new log(4,"用户"+(String)session.getAttribute("operator")+"提交紧急通知",timestamp,(String) session.getAttribute("operator"));
        //日志
        logService.insertLog(log);



        return i;
    }



}
