package com.peakyu.application.controller.user;

import com.peakyu.application.dao.message;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/note")
public class noteController {

    @Autowired
    private com.peakyu.application.service.messageService messageService;

    @RequestMapping("/all")
    public String allNote(@RequestParam(value = "currentPage", defaultValue = "1") Integer page, Model model){


        PageUtils<message> note = messageService.adminSelectMailByEmail(page, "note");

        for(message me:note.getList()){
            if (me.getTxt().length() >= 10)
                me.setTxt(me.getTxt().substring(0, 9)+"......");
        }
        model.addAttribute("note",note);

        return "u/note";
    }

    //公告详情
    @PostMapping("/details")
    public String mailDetails(@RequestParam("id") String id, Model model,HttpSession session){

        //查询信件
        message message = messageService.selectMailById(Integer.parseInt(id));
        model.addAttribute("detial",message);
        return "u/noteDetails";
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
        model.addAttribute("allmessage",message);
        return "u/searchNoteResult";
    }





}
