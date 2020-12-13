package com.peakyu.application.controller.admin;

import com.peakyu.application.dao.*;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.service.admin.logService;
import com.peakyu.application.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("/admin/account")
public class AccountController {

    @Autowired
    private com.peakyu.application.mapper.admin.adminMapper adminMapper;

    @Autowired
    private userService userService;

    @Autowired
    private com.peakyu.application.mapper.employee.employeeMapper employeeMapper;

    @Autowired
    private logService logService;

    @Autowired
    private com.peakyu.application.service.messageService messageService;

    //根据类型和页码获取数据
    private PageUtils<user> accountPageGet(Integer id, Integer page){
        PageUtils<user> userPageUtils;
        if (id == 1){
            userPageUtils = userService.selectNotActiveUserAccount(page);
        }
        else if (id == 2){
            userPageUtils = userService.selectActiveUserAccount(page);
        }
        else if (id == 3){
            userPageUtils = userService.selectAllUserAccount(page);
        }
        else {
            userPageUtils = userService.selectAllUserAccount(page);
        }
        return userPageUtils;

    }


    //审核页面
    @PostMapping("/approval")
    public String accountApproval(@RequestParam("typeid") Integer id,
                               @RequestParam(value = "currentPage", defaultValue = "1") Integer page,Model model){
        //id =1未审批 2已审批  3全部用户  4 5

        PageUtils<user> userPageUtils = accountPageGet(id, page);

        model.addAttribute("type",id);
        model.addAttribute("message",userPageUtils);


        return "a/accountApproval";
    }


    //审核页面
    @PostMapping("/changepsk")
    @ResponseBody
    public String changepsk(@RequestParam("old") String old,
                            @RequestParam("new") String newpsk,Model model){

        admin admin = adminMapper.SeByName("admin");
        if (admin.getpsk().equals(old)){
            adminMapper.updateadminPsk(newpsk);
            return "1";
        }
       else
           return "2";


    }



    //通过审核
    @PostMapping("/pass")
    public String accountPass(@RequestParam("email") String email,
                            @RequestParam("type") Integer type,
                            @RequestParam("page") Integer page,
                            Model model) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        exam exam = new exam(email,1,"",timestamp);
        userService.updateUserAccountActive(email,exam);

        //操作日志
        log us_login_log = new log(1,"通过用户"+email+"的注册审核",timestamp,"admin");
        //日志 用户登录情况
        logService.insertLog(us_login_log);
        log log = new log(4,"用户注册",timestamp,email);

        //注册成功发送消息
        message message = new message(email,"您好，恭喜您通过审核，您可以<a href=\"javascript:accountEdit(1);\">点击此处</a>修改或完善您的个人信息",0,"欢迎使用本系统",timestamp,"admin");
        Integer i = messageService.insertMessage(message);
        log log2 = new log(4,"向"+email+"发送了一条消息",timestamp,"admin");

        //日志 用户登录情况
        logService.insertLog(log2);



        //返回注册审核页面默认未审核
        PageUtils<user> userPageUtils = accountPageGet(type, page);
        model.addAttribute("type",type);
        model.addAttribute("message",userPageUtils);

        return "a/accountApproval";

    }

    //不通过审核
    @PostMapping("/fail")
    public String accountFail(@RequestParam("select") Integer val,
                                 @RequestParam("myinput") String reason,
                                 @RequestParam("tag") String email,
                                 @RequestParam("pageType") Integer type,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 Model model){

        if (val == 0){
            reason = "未填写";
        }
        else if (val == 1){
            reason = "邮箱格式错误";
        }
        else if (val == 2){
            reason = "车牌号格式错误";
        }

        else if (val == 3){
            reason = "电话号码格式错误";
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //操作日志
        log log = new log(1,"暂时拒绝用户"+email+"的注册审核",timestamp,"admin");
        //日志 用户登录情况
        logService.insertLog(log);
        exam exam = new exam(email,0,reason,timestamp);
        userService.updateUserAccountNotActive(email,exam);



        //返回注册审核页面

        PageUtils<user> users = accountPageGet(type, page);
        model.addAttribute("type",type);
        model.addAttribute("message",users);

        return "a/accountApproval";
    }

    //检索账号
    @PostMapping("/search")
    public String accountSearch(@RequestParam("type") Integer type,
                      @RequestParam("keyword") String keyword,
                      @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,Model model
    ){

        user user = null;
        if(type == 0){
            user = userService.selectUserAccountByEmail(keyword);
            model.addAttribute("type","邮箱");
        }else if(type == 1){
            user = userService.selectUserAccountByLicense(keyword);
            model.addAttribute("type","车牌号");
        }
        else if(type == 2){
            user = userService.selectUserAccountByPhone(keyword);
            model.addAttribute("type","电话号码");
        }
        if(user != null) {
            if (user.getActive() == 0) {
                model.addAttribute("active", 1);
                model.addAttribute("state", "未审核");
            } else if (user.getActive() == 1)
                model.addAttribute("state", "已通过");
            else if (user.getActive() == 2){
                model.addAttribute("state", "未通过");
            }
        }


        //返回关键字及结果集
        model.addAttribute("keyword",keyword);
        model.addAttribute("result",user);
        return "a/searchAccountResult";
    }


    //编辑账号信息
    @PostMapping("/edit")
    public String accountEdit(@RequestParam("id") Integer id,Model model){
        user user = userService.selectAccountById(id);
        model.addAttribute("user",user);
        return "a/accountEdit";
    }

    //编辑账号信息
    @PostMapping("/edited")
    @ResponseBody
    public Integer accountEdited(@RequestParam("id") Integer id,@RequestParam("Email") String Email,
                                 @RequestParam("name") String name,@RequestParam("phone") String phone,
                                 @RequestParam("sex") Integer sex,@RequestParam("license") String license,
                                 @RequestParam("reason") String reason,@RequestParam("active") Integer active,Model model){
        user user =userService.selectAccountById(id);
        //审核不过则添加审核不过消息
        if (active == 2 && user.getActive() != active){

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            exam exam = new exam(user.getEmial(),0,reason,timestamp);
            userService.updateUserAccountNotActive(user.getEmial(),exam);

        }


        //修改信息
        user.setEmial(Email);user.setActive(active);user.setLicense(license);user.setPhone(phone);user.setName(name);user.setSex(sex);
        int i = userService.adminUpdateUser(user);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //操作日志
        log log = new log(2,"更新用户"+Email+"的账户信息",timestamp,"admin");
        //日志 用户登录情况
        logService.insertLog(log);

        return i;
    }



    //添加用户账号信息
    @PostMapping("/user")
    @ResponseBody
    public int addAccountUser(@RequestParam("Email") String Email,@RequestParam("pwd") String pwd,
                                 @RequestParam("name") String name,@RequestParam("phone") String phone,
                                 @RequestParam("sex") Integer sex,@RequestParam("license") String license,
                                Model model){




        //检查重复账号
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user user =userService.selectUserAccountByEmail(Email);

        if (user  != null){
            return 2;
        }

        user user1 = new user(Email,pwd,1,timestamp,phone,sex,name,license);



        int i = userService.insertUserAccount(user1);



        //操作日志
        log log = new log(2,"创建用户"+Email+"账户",timestamp,"admin");
        //日志 用户登录情况
        logService.insertLog(log);

        return i;
    }


    //编辑账号信息
    @PostMapping("/employee")
    @ResponseBody
    public Integer addAccountEmployee(@RequestParam("sign") String Email,@RequestParam("pwd2") String pwd,
                                 @RequestParam("name2") String name, @RequestParam("station") Integer station, Model model){

        employee employee = employeeMapper.selectEmployeeByName(Email);

        //审核不过则添加审核不过消息
        if (employee!= null){
            return 2;
        }

        employee employee2 = new employee(Email,pwd,name,station);


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //操作日志
        log log = new log(2,"创建员工"+Email+"账户",timestamp,"admin");
        //日志 用户登录情况
        logService.insertLog(log);

        int i = employeeMapper.insertEmployee(employee2);

        return i;
    }






    //删除账号信息
    @PostMapping("/delete")
    @ResponseBody
    public Integer accountDelete(@RequestParam("id") Integer id){

        user user = userService.selectAccountById(id);


        int i = userService.adminDeleteUser(id);



        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //操作日志
        log log = new log(3,"删除用户"+user.getEmial()+"的账户信息",timestamp,"admin");
        //日志 用户登录情况
        logService.insertLog(log);

        return i;

    }
}
