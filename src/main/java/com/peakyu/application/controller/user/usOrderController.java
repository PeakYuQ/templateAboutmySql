package com.peakyu.application.controller.user;


import com.peakyu.application.dao.*;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.mapper.roadMapper;
import com.peakyu.application.mapper.stationMapper;
import com.peakyu.application.service.employee.OrderDoneService;
import com.peakyu.application.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class usOrderController {

    @Autowired
    private OrderDoneService OrderDoneService;

    @Autowired
    private com.peakyu.application.mapper.employee.orderMapper orderMapper;

    @Autowired
    private roadMapper roadMapper;

    @Autowired
    private com.peakyu.application.mapper.employee.orderdoneMapper orderdoneMapper;

    @Autowired
    private userService userService;

    @Autowired
    private stationMapper stationMapper;



    @PostMapping("/all")
    public String orderAll(@RequestParam("header") Integer type,
                           @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                           HttpSession session, Model model){

        //检查用户
        user user = userService.selectUserAccountByEmail((String) session.getAttribute("operator"));

        PageUtils<orderadvance> NotOrderorderadvance = OrderDoneService.userSelectNotOrder(currentPage,user.getLicense());

        PageUtils<orderadvance>  settleorderadvance = OrderDoneService.userSelectOrder(currentPage,user.getLicense());

        PageUtils<orderadvance> allorderadvance = OrderDoneService.userSelectAllOrder(currentPage,user.getLicense());


        model.addAttribute("type",type);
        model.addAttribute("notorderadvance",NotOrderorderadvance);
        model.addAttribute("orderadvance",settleorderadvance);
        model.addAttribute("allorderadvance",allorderadvance);

        return "u/order";
    }


    @RequestMapping("/path")
    public String path(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,HttpSession session, Model model){
        user operator = userService.selectUserAccountByEmail(String.valueOf(session.getAttribute("operator")));

        PageUtils<orderadvance> allorderadvance = OrderDoneService.userSelectAllOrder(currentPage,operator.getLicense() );


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
        return "u/path";
    }








    @PostMapping("/details")
    public String details(@RequestParam("id") Integer id,
                           HttpSession session, Model model){

        Orderdone orderdone = orderdoneMapper.selectOrderdoneByOid(id);
        model.addAttribute("status",orderdone.getStatus());

        //根据id查记录
        orderadvance orderadvance = orderMapper.selectOrderById(id);
        //查站点
        station station1 = stationMapper.selectStationById(orderadvance.getFirId());
        if (orderadvance.getLasId() == -1){
            model.addAttribute("orderadvance",orderadvance);
            model.addAttribute("station1",station1.getName());
            model.addAttribute("station2","逃票");
            return "u/orderDetails";
        }
        station station2 = stationMapper.selectStationById(orderadvance.getLasId());
        road road = roadMapper.selectRoadByfirlasId(orderadvance.getFirId(), orderadvance.getLasId());



        model.addAttribute("station1",station1.getName());
        model.addAttribute("station2",station2.getName());
        model.addAttribute("road",road);
        model.addAttribute("orderadvance",orderadvance);


        return "u/orderDetails";
    }

    @PostMapping("/pay")
    public String pay(@RequestParam("id") Integer id,
                      HttpSession session, Model model){

        orderdoneMapper.payOrder(id);
        Orderdone orderdone = orderdoneMapper.selectOrderdoneByOid(id);
        model.addAttribute("status",orderdone.getStatus());
        //根据id查记录
        orderadvance orderadvance = orderMapper.selectOrderById(id);
        //查站点
        station station1 = stationMapper.selectStationById(orderadvance.getFirId());
        if (orderadvance.getLasId() == -1){
            model.addAttribute("orderadvance",orderadvance);
            model.addAttribute("station1",station1.getName());
            model.addAttribute("station2","逃票");
            return "u/orderDetails";
        }
        station station2 = stationMapper.selectStationById(orderadvance.getLasId());
        road road = roadMapper.selectRoadByfirlasId(orderadvance.getFirId(), orderadvance.getLasId());

        model.addAttribute("station1",station1.getName());
        model.addAttribute("station2",station2.getName());
        model.addAttribute("road",road);
        model.addAttribute("orderadvance",orderadvance);
        return "u/orderDetails";

    }




}
