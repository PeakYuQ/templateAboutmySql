package com.peakyu.application.controller.employee;

import com.peakyu.application.dao.employee;
import com.peakyu.application.dao.log;
import com.peakyu.application.dao.message;
import com.peakyu.application.dao.orderadvance;
import com.peakyu.application.mapper.employee.employeeMapper;
import com.peakyu.application.service.admin.logService;
import com.peakyu.application.service.employee.OrderDoneService;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class EmLoginController {

    @Autowired
    private employeeMapper employeeMapper;
    @Autowired
    private logService logService;
    @Autowired
    private com.peakyu.application.service.messageService messageService;
    @Autowired
    private OrderDoneService OrderDoneService;


    @PostMapping("/employee/changepsk")
    @ResponseBody
    public String changepsk(@RequestParam("old") String old,HttpSession session,
                            @RequestParam("new") String newpsk,Model model){

        employee operator = employeeMapper.selectEmployeeByName(String.valueOf(session.getAttribute("operator")));
        if (operator.getPwd().equals(old)){
            operator.setPwd(newpsk);
            employeeMapper.updateEmployee(operator);
            return "1";
        }
        else
            return "2";


    }

    @GetMapping("/em_login")
    public String em_login(){
        return "e/login";
    }

    @GetMapping("/employee/index")
    public String em_index(HttpSession session, Model model){
        //查看未处理紧急情况
        List<message> messages = messageService.selectNotEmergent(0, 5);
        for(message me:messages){
            if (me.getTxt().length() >= 10)
                me.setTxt(me.getTxt().substring(0, 9)+"......");
        }
        model.addAttribute("emergent",messages);
        //zuijin订单
        PageUtils<orderadvance> orderadvancePageUtils = OrderDoneService.EmployeeSelectAllOrder(1,(String) session.getAttribute("operator"));
        model.addAttribute("orderadvance",orderadvancePageUtils);

        PageUtils<message> note = messageService.adminSelectMailByEmail(1, "note");

        for(message me:note.getList()){
            if (me.getTxt().length() >= 10)
                me.setTxt(me.getTxt().substring(0, 9)+"......");
        }
        model.addAttribute("note",note);


        return   "e/index";
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @PostMapping("/employee/login")
    public  String em_login_confim(@RequestParam("username") String username,
                             @RequestParam("password") String password, Model model,
                             HttpSession session){

        //检查登录密码通过用户名查找
        employee employee = employeeMapper.selectEmployeeByName(username);

        //查无此账号
        if (employee == null){
            model.addAttribute("tip1","账号输入错误，请检查后再试！");
            return "e/login";
        }

        //查密码
        if(employee.getPwd().equals(password))
        {

            //日志 用户登录情况
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            log us_login_log = new log(0,"用户登录",timestamp,employee.getSign());

            //日志 用户登录情况
            logService.insertLog(us_login_log);

            //操作者存进session，设置屏蔽
            session.setAttribute("operator",employee.getSign());
            //存通知
            PageUtils<message> note = messageService.adminSelectMailByEmail(1, "note");

            for(message me:note.getList()){
                if (me.getTxt().length() >= 10)
                    me.setTxt(me.getTxt().substring(0, 9)+"......");
            }
            model.addAttribute("note",note);

            //查看未处理紧急情况
            List<message> messages = messageService.selectNotEmergent(0, 5);
            for(message me:messages){
                if (me.getTxt().length() >= 10)
                    me.setTxt(me.getTxt().substring(0, 9)+"......");
            }
            model.addAttribute("emergent",messages);
            //zuijin订单
            PageUtils<orderadvance> orderadvancePageUtils = OrderDoneService.EmployeeSelectAllOrder(1,(String) session.getAttribute("operator"));
            model.addAttribute("orderadvance",orderadvancePageUtils);

            //将bars信箱未读邮件存进session
            List<message> notreadMessage = messageService.selectNotReadMessage(username, 0, 1000);//查询未读消息 写入session
            session.setAttribute("notreadmessage",notreadMessage);
            return "e/index";
        }
        else{
            //密码错误返回登录
            model.addAttribute("tip2","密码输入错误，请检查后再试！");
            return "e/login";
        }

    }
}
