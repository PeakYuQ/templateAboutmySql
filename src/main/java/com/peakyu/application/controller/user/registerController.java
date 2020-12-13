package com.peakyu.application.controller.user;

import com.peakyu.application.dao.exam;
import com.peakyu.application.dao.log;
import com.peakyu.application.dao.user;
import com.peakyu.application.service.admin.logService;
import com.peakyu.application.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class registerController {

    @Autowired
    private userService userService;

    @Autowired
    private com.peakyu.application.mapper.admin.examMapper examMapper;

    @Autowired
    private logService logService;

    @GetMapping("/register")
    public String register(){
        return "u/register";
    }

    @PostMapping("/us_regist")
    public String us_register(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam("name") String name,
                              @RequestParam("sex") Integer sex,
                              @RequestParam("license") String license,
                              @RequestParam("phone") String phone, HttpSession session,Model model){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user user1 = userService.selectUserAccountByEmail(email);




        if (user1 == null){
            user user = new user(email,password,0,timestamp,phone,sex,name,license);
            userService.insertUserAccount(user);
            model.addAttribute("tip","注册申请已发送，请耐心等待，<a>点击此处</a>查看注册进展！");
        }
        else
            model.addAttribute("tip","此邮箱已注册，请查证后再注册！");
        // System.out.println(user.toString());
        return "index";
    }


    //查询注册解果
    @PostMapping("regresult")
    public String regresult(@RequestParam("email") String email,HttpSession session,Model model ){
        user user = userService.selectUserAccountByEmail(email);
        exam exam = examMapper.selectByEmail(email);
        if( exam == null){
            model.addAttribute("noresult",1);
        }
            model.addAttribute("email",email);
            model.addAttribute("result",exam);
            model.addAttribute("user",user);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        log log = new log(5,"查询了"+"注册结果",timestamp,email);

        //日志 用户登录情况
        logService.insertLog(log);

        return "index";
    }


    //用户编辑信息
    @PostMapping("/account/edit")
    public String accountEdit(@RequestParam("id") Integer id ,Model model ){

        user user = userService.selectAccountById(id);
        model.addAttribute("user",user);
        return "u/accountEdit";
    }



    //编辑账号信息
    @PostMapping("/account/edited")
    @ResponseBody
    public Integer accountEdited(@RequestParam("id") Integer id,
                                 @RequestParam("name") String name, @RequestParam("phone") String phone,
                                 @RequestParam("sex") Integer sex, @RequestParam("license") String license,
                                 Model model, HttpSession session){
        user user =userService.selectAccountById(id);

        //修改信息
        user.setLicense(license);user.setPhone(phone);user.setName(name);user.setSex(sex);


        session.setAttribute("user",user);


        int i = userService.adminUpdateUser(user);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        log log = new log(2,"用户更新了账号信息",timestamp,(String) session.getAttribute("operator"));

        //日志 用户登录情况
        logService.insertLog(log);



        return i;
    }


}
