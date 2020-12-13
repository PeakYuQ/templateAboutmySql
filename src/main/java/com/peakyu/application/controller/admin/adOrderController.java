package com.peakyu.application.controller.admin;

import com.peakyu.application.dao.Orderdone;
import com.peakyu.application.dao.orderadvance;
import com.peakyu.application.dao.road;
import com.peakyu.application.dao.station;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/order")
public class adOrderController {

    @Autowired
    private com.peakyu.application.service.employee.OrderDoneService OrderDoneService;

    @Autowired
    private com.peakyu.application.mapper.employee.orderMapper orderMapper;

    @Autowired
    private com.peakyu.application.service.employee.orderService orderService;

    @Autowired
    private com.peakyu.application.mapper.roadMapper roadMapper;

    @Autowired
    private com.peakyu.application.mapper.employee.orderdoneMapper orderdoneMapper;

    @Autowired
    private com.peakyu.application.mapper.stationMapper stationMapper;
    @Autowired
    private com.peakyu.application.service.user.userService userService;


    @PostMapping("/all")
    public String orderAll(@RequestParam("type") Integer type,
                           @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                           HttpSession session, Model model){
        PageUtils<orderadvance> orderadvance;
        if (type == 1) {
            //全部
            orderadvance = OrderDoneService.adminSelectAllOrder(currentPage );
        }
        else if (type == 2){
            //已支付
            orderadvance = OrderDoneService.adminSelectOrder(currentPage );
        }
        else if (type == 3){
            //未支付
            orderadvance = OrderDoneService.adminSelectNotOrder(currentPage );
        }else
            orderadvance = OrderDoneService.adminSelectAllOrder(currentPage );

        model.addAttribute("type",type);
        model.addAttribute("orderadvance",orderadvance);

        return "a/order";
    }



    @PostMapping("/delete")
    @ResponseBody
    public String deleteorder(@RequestParam("id") Integer type,
                           HttpSession session){
        orderService.addeleteOrder(type);
        return "1";
    }




    @PostMapping("/details")
    public String details(@RequestParam("id") Integer id,
                          HttpSession session, Model model){

        Orderdone orderdone = orderdoneMapper.selectOrderdoneByOid(id);

        if (orderdone == null){
            return "a/orderDetails";
        }


        model.addAttribute("status",orderdone.getStatus());

        //根据id查记录
        orderadvance orderadvance = orderMapper.selectOrderById(id);
        //查站点
        station station1 = stationMapper.selectStationById(orderadvance.getFirId());
        if (orderadvance.getLasId() == -1){
            model.addAttribute("orderadvance",orderadvance);
            model.addAttribute("station1",station1.getName());
            model.addAttribute("station2","逃票");
            return "a/orderDetails";
        }
        station station2 = stationMapper.selectStationById(orderadvance.getLasId());
        road road = roadMapper.selectRoadByfirlasId(orderadvance.getFirId(), orderadvance.getLasId());



        model.addAttribute("station1",station1.getName());
        model.addAttribute("station2",station2.getName());
        model.addAttribute("road",road);
        model.addAttribute("orderadvance",orderadvance);


        return "a/orderDetails";
    }



}
