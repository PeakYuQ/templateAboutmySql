package com.peakyu.application.controller.admin;

import com.peakyu.application.dao.road;
import com.peakyu.application.dao.station;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.mapper.roadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/station")
public class StationManagerController {


    @Autowired
    private com.peakyu.application.service.stationService stationService;

    @Autowired
    private com.peakyu.application.service.roadService roadService;
    @Autowired
    private roadMapper roadMapper;


    @RequestMapping("/all")
    public String allStation(@RequestParam(value = "currentPage", defaultValue = "1") Integer page,
                             Model model){
        //查询所有station并分页
        PageUtils<station> station = stationService.selectAllStation(page);

        model.addAttribute("stations",station);
        return "a/station";
    }


    @RequestMapping("/add")
    @ResponseBody
    public String addStation(@RequestParam("name") String name,
                             @RequestParam("inter") String inter,
                             Model model){

        List<station> stations = stationService.selectStationByName(name);
        station station = new station(name,inter);
        if (!stations.isEmpty() ){

            return "2";
        }
        stationService.insertStation(station);
        return "1";
    }


    @RequestMapping("/addRoad")
    @ResponseBody
    public String addStation(@RequestParam("select") Integer id1,@RequestParam("select2") Integer id2,
                             @RequestParam("name") String name, @RequestParam("price") Integer price,
                             @RequestParam("time") Integer time,@RequestParam("length") Integer length,
                             Model model){
        road road = roadMapper.selectRoadByfirlasId(id1, id2);

        if (!(road == null) ){
            return "2";
        }
        road road2= new road(name, id1, id2, length, time, price);

        roadMapper.insertRoad(road2);
        return "1";
    }

    @RequestMapping("/editRoad")
    @ResponseBody
    public String editRoad(
                             @RequestParam("editRoadName") String name, @RequestParam("price") Integer price,
                             @RequestParam("time") Integer time,@RequestParam("length") Integer length,
                             Model model){
        road road = roadMapper.selectRoadByName(name);

        if (!price.equals(""))
            road.setPrice(price);
            if (!length.equals(""))
                road.setLength(length);
                if (!time.equals(""))
                    road.setTime(time);

        roadMapper.updateRoad(road);
        return "1";
    }

    @RequestMapping("/deleteRoad")
    @ResponseBody
    public String deleteRoad( @RequestParam("roadName") String name){
        roadMapper.deleteRoad(name);
        return "1";
    }



    @PostMapping("/details")
    public String stationDetails(@RequestParam("name") String keyword,
                                 Model model){
        List<station> station = stationService.selectAllStation(0, 20);

        //所有站点查询
        model.addAttribute("station",station);

        List<station> stations = stationService.selectStationByName(keyword);

        model.addAttribute("details",stations.get(0).getInterduce());
        model.addAttribute("isNull",1);

        if (  stations.isEmpty() ){

            model.addAttribute("isNull",1);

        }
        else{
            model.addAttribute("isNull",0);
            List<road> roads = roadService.selectRoadById(stations.get(0).getId());
            model.addAttribute("roads",roads);
        }

        return "a/stationDetails";
    }

}
