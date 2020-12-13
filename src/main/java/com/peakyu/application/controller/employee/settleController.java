package com.peakyu.application.controller.employee;


import com.peakyu.application.dao.*;
import com.peakyu.application.mapper.employee.StationrecordMapper;
import com.peakyu.application.mapper.employee.employeeMapper;
import com.peakyu.application.mapper.employee.orderdoneMapper;
import com.peakyu.application.service.employee.orderService;
import com.peakyu.application.service.user.userService;
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
@RequestMapping("employee/settle")
public class settleController {


    @Autowired
    private userService userService;

    @Autowired
    private com.peakyu.application.service.stationService stationService;
    @Autowired
    private StationrecordMapper stationrecordMapper;
    @Autowired
    private com.peakyu.application.service.admin.logService logService;
    @Autowired
    private employeeMapper employeeMapper;
    @Autowired
    private orderService orderService;
    @Autowired
    private orderdoneMapper orderdoneMapper;

    @Autowired
    private StationrecordMapper  StationrecordMapper;

    @RequestMapping("/order/in")
    public String settlrOrderin(Model model){
        //默认是进站
        model.addAttribute("type",0);
        return "e/settleInStation";
    }
    @RequestMapping("/order/out")
    public String settlrOrderout(Model model){
        //默认是进站
        model.addAttribute("type",1);
        return "e/settleOutStation";
    }

    @PostMapping("/inOperate")
    public String settleOrderOperate(@RequestParam("license") String  license,
                                     @RequestParam("stationType") Integer  type, HttpSession session,
            Model model){

        //检查登录密码通过用户名查找
        employee employee = employeeMapper.selectEmployeeByName((String) session.getAttribute("operator"));

        //检查用户
        user user = userService.selectUserAccountByLicense(license);

        if(user == null){
            model.addAttribute("tips","请检查车牌号输入是否正确后再试！"); //返回信息
            model.addAttribute("type",0); //返回信息
            return "e/settleInStation";
        }

        Stationrecord stationrecord = stationrecordMapper.selectStationrecordByLicense(license);

        if(stationrecord == null){
            model.addAttribute("user",user); //返回信息
            model.addAttribute("type",2); //返回信息
            return "e/settleInStation";
        }


        if (type == 0){
            //查进出站记录

            if (stationrecord.getStatus()==0)
                //如果只有进站记录 -- 处罚
            model.addAttribute("punish",1); //返回信息
            model.addAttribute("reason","没有出站记录！"); //返回信息

        }
        else if (type == 1){
            //查进出站记录

            if (stationrecord.getStatus()==1)
                //如果只有进站记录 -- 处罚
                model.addAttribute("punish",1); //返回信息
            model.addAttribute("reason","没有入站记录！"); //返回信息

        }

        model.addAttribute("stationrecord",stationrecord); //返回信息

        station station = stationService.selectStationById((int) stationrecord.getStation_id());

        model.addAttribute("station",station.getName()); //返回信息
        model.addAttribute("stationId",station.getId()); //返回信息
        model.addAttribute("nowId",employee.getStationId()); //返回信息

        model.addAttribute("user",user); //返回信息
        model.addAttribute("type",2); //返回信息
        return "e/settleInStation";
    }

    @PostMapping("/outOperate")
    public String settleOrderOperateOut(@RequestParam("license") String  license,
                                     @RequestParam("stationType") Integer  type, HttpSession session,
                                        Model model){
        //检查登录密码通过用户名查找
        employee employee = employeeMapper.selectEmployeeByName((String) session.getAttribute("operator"));

        user user = userService.selectUserAccountByLicense(license);

        if(user == null){
            model.addAttribute("tips","请检查车牌号输入是否正确后再试！"); //返回信息
            model.addAttribute("type",0); //返回信息
            return "e/settleOutStation";
        }

        Stationrecord stationrecord = stationrecordMapper.selectStationrecordByLicense(license);

        //最近入栈记录为空
        if(stationrecord == null){
            model.addAttribute("user",user); //返回信息
            model.addAttribute("type",2); //返回信息
            model.addAttribute("punish",1); //返回信息
            model.addAttribute("reason","没有入站记录！"); //返回信息
            return "e/settleOutStation";
        }

        if (stationrecord.getStatus()==1){
                //如果没有进站记录 -- 处罚
                model.addAttribute("punish",1); //返回信息
                model.addAttribute("reason","没有入站记录！"); //返回信息
        }

        model.addAttribute("stationrecord",stationrecord); //返回信息
        station station = stationService.selectStationById((int) stationrecord.getStation_id());
        model.addAttribute("station",station.getName()); //返回信息
        model.addAttribute("stationId",station.getId()); //返回信息
        model.addAttribute("nowId",employee.getStationId()); //返回信息


        model.addAttribute("user",user); //返回信息
        model.addAttribute("type",2); //返回信息
        return "e/settleOutStation";
    }

    @PostMapping("/inStation")
    public String settleInStation(@RequestParam("license") String  license, HttpSession session
                                     ,Model model){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        user user = userService.selectUserAccountByLicense(license);

        //检查登录密码通过用户名查找
        employee employee = employeeMapper.selectEmployeeByName((String) session.getAttribute("operator"));


        //创建订单
        orderadvance order = new orderadvance();
        order.setFirId( (int)employee.getStationId());
        order.setLicense(license); order.setOperatorIn((String) session.getAttribute("operator"));order.setStartTime(timestamp);

        //查进出站记录
        Stationrecord stationrecord = stationrecordMapper.selectStationrecordByLicense(license);

        //查历史订单记录
        orderadvance orderadvance = orderService.selectOrderByLicense(license);

        //没有进出站记录
        if (orderadvance == null){
            orderService.insertOrderIn(order);
            model.addAttribute("tips","成功创建了一个订单！"); //返回信息
            model.addAttribute("type",0); //返回信息
            //chuangjian 进站记录
            Stationrecord Stationrecord = new Stationrecord(license,timestamp,0,employee.getStationId());
            StationrecordMapper.insertStationrecord(Stationrecord);

            //创建日志
            log log = new log(2,"创建了一个订单",timestamp,(String) session.getAttribute("operator"));
            log log2 = new log(2,"用户进站",timestamp,user.getEmial());
            //日志
            logService.insertLog(log);
            logService.insertLog(log2);
            return "e/settleInStation";
        }

        //有进出站记录，订单等
        Orderdone orderdone = orderdoneMapper.selectOrderdoneByOid(orderadvance.getId());

        //没有订单等
        if (orderdone == null) {
            model.addAttribute("tips","创建订单失败！请检查后再试！"); //返回信息
            model.addAttribute("type",0); //返回信息
            return "e/settleInStation";
        }

        //有订单等
        if (stationrecord.getStatus() == 1 && orderdone.getStatus() == 1){

        orderService.insertOrderIn(order);
        model.addAttribute("tips","成功创建了一个订单！"); //返回信息

         Stationrecord Stationrecord = new Stationrecord(license,timestamp,0,employee.getStationId());
         StationrecordMapper.insertStationrecord(Stationrecord);

        //创建日志
        log log = new log(2,"创建了一个订单",timestamp,(String) session.getAttribute("operator"));
        //日志
        logService.insertLog(log);
        }
        else{
            model.addAttribute("tips","创建订单失败！请检查后再试"); //返回信息
        }

        model.addAttribute("type",0); //返回信息
        if (orderdone.getStatus() != 1 )
            model.addAttribute("tips","创建订单失败！历史订单未支付！"); //返回信息
        return "e/settleInStation";
    }


    @PostMapping("/outStation")
    public String settleOutStation(@RequestParam("license") String  license, HttpSession session
            , @RequestParam("selectStyle") Integer  selectStyle,Model model){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user user = userService.selectUserAccountByLicense(license);

        //查出订单来
        orderadvance order = orderService.selectOrderByLicense(license);

        //检查登录密码通过用户名查找
        employee employee = employeeMapper.selectEmployeeByName((String) session.getAttribute("operator"));

        order.setEndTime(timestamp);order.setLasId((int)employee.getStationId());
        order.setOperatorOut((String) session.getAttribute("operator"));


        //查进出站记录
        Stationrecord stationrecord = stationrecordMapper.selectStationrecordByLicense(license);

        if (stationrecord.getStatus() == 0) {

            orderService.updateOrderOut(order);
            //创建日志
            log log = new log(2, "完成了一个订单", timestamp, (String) session.getAttribute("operator"));
            //日志
            logService.insertLog(log);
            Stationrecord Stationrecord = new Stationrecord(license,timestamp,1,employee.getStationId());
            StationrecordMapper.insertStationrecord(Stationrecord);
            model.addAttribute("tips","该订单已提交！可以在历史订单查看状态"); //返回信息

            if (selectStyle == 0) {
                Orderdone Orderdone = new Orderdone(order.getId(),1);
                orderdoneMapper.insertOrderderdone(Orderdone);
            }
            else  if (selectStyle == 1){
                Orderdone Orderdone = new Orderdone(order.getId(),0);
                orderdoneMapper.insertOrderderdone(Orderdone);
            }



        }
        else{
            model.addAttribute("tips","修改订单失败！请检查后再试"); //返回信息
        }

        model.addAttribute("type",1); //返回信息
        return "e/settleOutStation";
    }

    @PostMapping("/punish")
    @ResponseBody
    public String punish(@RequestParam("license") String  license, HttpSession session
            ,Model model){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user user = userService.selectUserAccountByLicense(license);

        //查出订单来
        orderadvance order = orderService.selectOrderByLicense(license);

        //检查登录密码通过用户名查找
        employee employee = employeeMapper.selectEmployeeByName((String) session.getAttribute("operator"));

        order.setEndTime(timestamp);order.setLasId(-1);
        order.setOperatorOut((String) session.getAttribute("operator"));


            orderService.updateOrderOut(order);
            //创建日志
            log log = new log(2, "完成了一个订单", timestamp, (String) session.getAttribute("operator"));
            //日志
            logService.insertLog(log);
            Stationrecord Stationrecord = new Stationrecord(license,timestamp,1,employee.getStationId());
            StationrecordMapper.insertStationrecord(Stationrecord);


        Orderdone Orderdone = new Orderdone(order.getId(),0);
        orderdoneMapper.insertOrderderdone(Orderdone);

        model.addAttribute("type",1); //返回信息
        return "1";
    }

    //没有入栈记录
    @PostMapping("/punish2")
    @ResponseBody
    public String punish2(@RequestParam("license") String  license, HttpSession session
            ,Model model){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user user = userService.selectUserAccountByLicense(license);




        //检查登录密码通过用户名查找
        employee employee = employeeMapper.selectEmployeeByName((String) session.getAttribute("operator"));

        //创建订单
        orderadvance order = new orderadvance();
        order.setFirId( (int)employee.getStationId());
        order.setLicense(license); order.setOperatorIn((String) session.getAttribute("operator"));order.setStartTime(timestamp);




        orderService.insertOrderIn(order);

        //创建日志
        log log = new log(2, "完成了一个订单", timestamp, (String) session.getAttribute("operator"));
        //日志
        logService.insertLog(log);


        Stationrecord Stationrecord2 = new Stationrecord(license,timestamp,0,employee.getStationId());
        Stationrecord Stationrecord = new Stationrecord(license,timestamp,1,employee.getStationId());
        StationrecordMapper.insertStationrecord(Stationrecord2);
        StationrecordMapper.insertStationrecord(Stationrecord);


        orderadvance order2 = orderService.selectOrderByLicense(license);
        Orderdone Orderdone = new Orderdone(order2.getId(),0);
        order2.setEndTime(timestamp);
        order2.setLasId(-1);
        order2.setOperatorOut((String) session.getAttribute("operator"));
        orderService.updateOrderOut(order2);
        orderdoneMapper.insertOrderderdone(Orderdone);



        return "1";
    }



}
