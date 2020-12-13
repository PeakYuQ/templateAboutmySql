package com.peakyu.application.controller.user;


import com.peakyu.application.dao.road;
import com.peakyu.application.dao.station;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.utils.dijkstra.Dijkstra;
import com.peakyu.application.utils.dijkstra.Graph;
import com.peakyu.application.utils.dijkstra.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
@RequestMapping("/station")
public class stationController {

    @Autowired
    private com.peakyu.application.service.stationService stationService;

    @Autowired
    private com.peakyu.application.service.roadService roadService;

    @RequestMapping("/all")
    public String allStation(@RequestParam(value = "currentPage", defaultValue = "1") Integer page,
                             Model model){
        //查询所有station并分页
        PageUtils<station> station = stationService.selectAllStation(page);


        model.addAttribute("station",station);
        return "u/station";
    }

    @PostMapping("/search")
    public String stationSearch(@RequestParam("keyword") String keyword,
                             Model model){
        //查询所有station并分页

        PageUtils<station> station = new PageUtils<>();

        List<station> stations = stationService.selectStationByName(keyword);
        station.setCurrentPage(1);
        station.setPageSize(1);
        station.setTotalCount(1);
        station.setTotalPage(1);
        station.setList(stations);

        model.addAttribute("station",station);
        return "u/station";
    }

    @PostMapping("/details")
    public String stationDetails(@RequestParam("name") String keyword,
                                Model model){
        //查询所有station并分页

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

        return "u/stationDetails";
    }


    @RequestMapping("/plan")
    public String stationPlan( Model model){
        List<station> stations = stationService.selectAllStation(0, 20);

        //所有站点查询
        model.addAttribute("station",stations);
        //显示规划一下框
        model.addAttribute("isNull",1);

        return "u/stationPlan";
    }



    @RequestMapping("/plan/result")
    public String stationPlanResult(@RequestParam("select") Integer id1,@RequestParam("select2") Integer id2, Model model){
        List<station> stations = stationService.selectAllStation(0, 20);
        //所有站点查询
        model.addAttribute("station",stations);
        //不显示规划一下框
        model.addAttribute("isNull",0);

        //用Dijkstra求解最短路径
        Dijkstra obj = new Dijkstra();
        // 创建图，共9个节点
        //最短路径
        Graph g = new Graph(stations.size());
        //最短消费
        Graph h = new Graph(stations.size());
        //最短时间
        Graph i = new Graph(stations.size());

        List<road> roads = roadService.selectAllRoad();
        //添加节点
        for(road s:roads)
            g.addEdge(s.getFirId(),s.getlasId(),s.getLength());
        for(road s:roads)
            g.addEdge(s.getlasId(),s.getFirId(),s.getLength());
        for(road s:roads)
            h.addEdge(s.getFirId(),s.getlasId(),s.getPrice());
        for(road s:roads)
            h.addEdge(s.getlasId(),s.getFirId(),s.getPrice());
        for(road s:roads)
            i.addEdge(s.getFirId(),s.getlasId(),s.getTime());
        for(road s:roads)
            i.addEdge(s.getlasId(),s.getFirId(),s.getTime());
        //计算节点
        obj.calculate(g.getVertex(id1));
        obj.calculate(h.getVertex(id1));
        obj.calculate(i.getVertex(id1));
        //取出重点
        Vertex vertex = g.getVertices().get(id2);
        Vertex vertex2 = h.getVertices().get(id2);
        Vertex vertex3 = i.getVertices().get(id2);

        double minDistance = vertex.minDistance;
        double minDistance2 = vertex2.minDistance;
        double minDistance3 = vertex3.minDistance;

        List<station> stationPath = new LinkedList<>();
        List<station> stationPath2 = new LinkedList<>();
        List<station> stationPath3 = new LinkedList<>();

        for(Vertex pathvert:vertex.path) {
            stationPath.add(stationService.selectStationById(Integer.parseInt(pathvert.name)));
        }
        for(Vertex pathvert:vertex2.path) {
            stationPath2.add(stationService.selectStationById(Integer.parseInt(pathvert.name)));
        }
        for(Vertex pathvert:vertex3.path) {
            stationPath3.add(stationService.selectStationById(Integer.parseInt(pathvert.name)));
        }


        station endStation = stationService.selectStationById(id2);
        station startStation = stationService.selectStationById(id1);


        stationPath.add(endStation);
        stationPath2.add(endStation);
        stationPath3.add(endStation);


        model.addAttribute("distance",minDistance);
        model.addAttribute("price",minDistance2);
        model.addAttribute("time",minDistance3);

        model.addAttribute("endStation",endStation);
        model.addAttribute("startStation",startStation);

        model.addAttribute("distancePath",stationPath);
        model.addAttribute("pricePath",stationPath2);
        model.addAttribute("timePath",stationPath3);



        return "u/stationPlan";
    }


}
