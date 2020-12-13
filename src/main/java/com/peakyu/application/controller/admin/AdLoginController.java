package com.peakyu.application.controller.admin;

import com.peakyu.application.dao.admin;
import com.peakyu.application.dao.log;
import com.peakyu.application.dao.message;
import com.peakyu.application.dao.orderadvance;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.service.admin.adminService;
import com.peakyu.application.service.admin.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdLoginController {

    @Autowired
    com.peakyu.application.mapper.admin.logMapper logMapper;

    @Autowired
    private adminService adminService;

    @Autowired
    private com.peakyu.application.service.messageService messageService;
    @Autowired
    private logService logService;
    @Autowired
    private com.peakyu.application.service.employee.OrderDoneService OrderDoneService;

    //主页
    @GetMapping("/index")
    public String adindex(Model model){


        //主页显示最近新增日志
        List<log> logs = logMapper.selectAllLog(0, 4);

        model.addAttribute("logs",logs);


        List<message> messages = messageService.selectNotEmergent(0, 5);
        for(message me:messages){
            if (me.getTxt().length() >= 10)
                me.setTxt(me.getTxt().substring(0, 9)+"......");
        }
        model.addAttribute("emergent",messages);
        PageUtils<orderadvance> orderadvancePageUtils = OrderDoneService.adminSelectAllOrder(1);
        model.addAttribute("orderadvance",orderadvancePageUtils);

        return "a/index";
    }


    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }


    //入口
    @GetMapping("/login")
    public String ad_login(){
        return "a/login";
    }

    //登录
    @PostMapping("/index")
    public  String ad_confim(@RequestParam("username") String username,
                             @RequestParam("password") String password, Model model,
                             HttpSession session){

        //检查登录密码通过用户名查找
        admin admin = adminService.SeByName(username);

        //查无此账号
        if (admin == null){
            model.addAttribute("tip1","账号输入错误，请检查后再试！");
            return "a/login";
        }

        //查密码
        if(admin.getpsk().equals(password))
        {
            //初始化-刷新admin邮箱
            messageService.adminInitializeBox("admin");

            //日志 用户登录情况
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            log us_login_log = new log(0,"用户登录",timestamp,"admin");

            //日志 用户登录情况
            logService.insertLog(us_login_log);

            //主页显示最近新增日志
            List<log> logs = logMapper.selectAllLog(0, 4);

            model.addAttribute("logs",logs);


            PageUtils<orderadvance> orderadvancePageUtils = OrderDoneService.adminSelectAllOrder(1);
            model.addAttribute("orderadvance",orderadvancePageUtils);

            //admin已读/未读邮件
            List<message> notreadMessage = messageService.selectAdminNotReadMessage(0,1000);
            //操作者存进session，设置屏蔽
            session.setAttribute("operator","admin");
            //将bars信箱未读邮件存进session
            session.setAttribute("notreadmessage",notreadMessage);
            //紧急情况出
            List<message> messages = messageService.selectNotEmergent(0, 5);
            for(message me:messages){
                if (me.getTxt().length() >= 10)
                    me.setTxt(me.getTxt().substring(0, 9)+"......");
            }
            model.addAttribute("emergent",messages);
            return "a/index";
        }
        else{
            //密码错误返回登录
            model.addAttribute("tip2","密码输入错误，请检查后再试！");
            return "a/login";
        }

    }
}
