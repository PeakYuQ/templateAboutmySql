package com.peakyu.application.controller;


import com.peakyu.application.dao.sensor;
import com.peakyu.application.mapper.sensorMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class sensorController {


    @Autowired
    private sensorMapper sensorMapper;


    //产生足够的数据库数据
    @GetMapping("/generate")
    public String generate(){

        //当前时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());


        for(int i=1;i<=10;i++){

            for (int j=0;j<10;j++){

                long time=timestamp.getTime()+(long)j*500*3600;
                Timestamp timestamp2 =new Timestamp(time);
                Integer temperature =  (int) Math.floor(Math.random() * 50);;      //温度
                Integer humidity = (int) Math.floor(Math.random() * 100);         //湿度
                Integer starch = (int) Math.floor(Math.random() * 100);           //淀粉含量
                Integer alcohol = (int) Math.floor(Math.random() * 100);          //酒精含量
                Integer acidity = (int) Math.floor(Math.random() * 100);
                sensor sensor = new sensor(i,timestamp2,temperature,humidity,starch,alcohol,acidity);
                sensorMapper.updateSensor(sensor);
            }
        }





        return "index";
    }

    //首页启动图
    @GetMapping("/")
    public String start(){

        return "start";
    }


    //首页启动图
    @PostMapping("/index")
    public String index(@RequestParam("id") Integer id, Model model){

        List<sensor> sensors = sensorMapper.retrieveSensor(id);


        model.addAttribute("sensors",sensors);

        return "index";
    }




}
