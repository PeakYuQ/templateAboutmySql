package com.peakyu.application.controller;

import com.peakyu.application.dao.*;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.mapper.messageMapper;
import com.peakyu.application.mapper.roadMapper;
import com.peakyu.application.mapper.stationMapper;
import com.peakyu.application.service.employee.OrderDoneService;
import com.peakyu.application.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class testController {

    @Autowired
    private com.peakyu.application.service.employee.orderService orderService;
    @Autowired
    private messageMapper messageMapper;
    @Autowired
    private com.peakyu.application.mapper.employee.orderdoneMapper orderdoneMapper;



    @Autowired
    private stationMapper stationMapper;

    @Autowired
    private roadMapper roadMapper;

    @Autowired
    private com.peakyu.application.service.messageService messageService;

    @Autowired
    private userService userService;

    @Autowired
    private OrderDoneService OrderDoneService;

    @Autowired
    com.peakyu.application.mapper.admin.logMapper logMapper;


    //操作产生log日志
    @GetMapping("/aaa")
    public String test(){

        return "a/login";
    }

    //快捷进入admin
    @GetMapping("za")
    public String test2(HttpSession session,Model model){
        //初始化-刷新admin邮箱
        messageService.adminInitializeBox("admin");

        //admin已读/未读邮件
        List<message> notreadMessage = messageService.selectAdminNotReadMessage(0,1000);

        //将bars信箱未读邮件存进session
        session.setAttribute("notreadmessage",notreadMessage);

        List<message> messages = messageService.selectNotEmergent(0, 5);
        for(message me:messages){
            if (me.getTxt().length() >= 10)
                me.setTxt(me.getTxt().substring(0, 9)+"......");
        }
        model.addAttribute("emergent",messages);

        List<log> logs = logMapper.selectAllLog(0, 4);

        PageUtils<orderadvance> orderadvancePageUtils = OrderDoneService.adminSelectAllOrder(1);
        model.addAttribute("orderadvance",orderadvancePageUtils);

        model.addAttribute("logs",logs);
        return "a/index";
    }

    //快捷进入user
    @GetMapping("zaa")
    public String test3(HttpSession session, Model model){
        session.setAttribute("operator","a@1.com");//用户登录成功 写进session
        messageService.InitializeBox("a@1.com"); //设置消息状态 整体未读-已读

        user user =userService.selectAccountById(1);

        session.setAttribute("user",user);

        PageUtils<message> notReadmessage = messageService.selectNotReadMessage(1,"a@1.com");//查询未读消息 写入session
        session.setAttribute("message",notReadmessage);

        List<message> note = messageService.adminSelectMailByEmail("note", 0, 5);
        for(message me:note){
            if (me.getTxt().length() >= 10)
            me.setTxt(me.getTxt().substring(0, 9)+"......");
        }
        model.addAttribute("note",note);




        //历史行程
        user operator = userService.selectUserAccountByEmail(String.valueOf(session.getAttribute("operator")));

        PageUtils<orderadvance> allorderadvance = OrderDoneService.userSelectAllOrder(1,operator.getLicense() );



        for(orderadvance order:allorderadvance.getList()){
            station station1 = stationMapper.selectStationById(order.getFirId());
            order.setOperatorIn(station1.getName());
            if (order.getLasId() == -1){
                order.setOperatorOut("逃票");
                order.setFirId(-1);
                order.setLasId(-1);
            }else{
                station station2 = stationMapper.selectStationById(order.getLasId());
                order.setOperatorOut(station2.getName());
                road road = roadMapper.selectRoadByfirlasId(order.getFirId(), order.getLasId());
                order.setFirId(road.getLength());
                order.setLasId(road.getPrice());
            }
        }
        model.addAttribute("order",allorderadvance);
        //与待处理订单
        PageUtils<orderadvance> NotOrderorderadvance = OrderDoneService.userSelectNotOrder(1,operator.getLicense());
        model.addAttribute("notorderadvance",NotOrderorderadvance);



        return "u/index";
    }

    //地图算法
    @GetMapping("zaaa")
    public String test4(HttpSession session, Model model){


        session.setAttribute("operator","aa@1.com");//用户登录成功 写进session
        messageService.InitializeBox("aa@1.com"); //设置消息状态 整体未读-已读

        //admin已读/未读邮件
        List<message> notreadMessage = messageService.selectNotReadMessage("aa@1.com",0,1000);

        //将bars信箱未读邮件存进session
        session.setAttribute("notreadmessage",notreadMessage);



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


        return "e/index";
    }


    @GetMapping("/jdbctest")
    public String newtest(Model model){

        List<message> messages=messageService.selectAllMessage(1,15);
        model.addAttribute("message",messages);
        return "new-table";
    }

}
