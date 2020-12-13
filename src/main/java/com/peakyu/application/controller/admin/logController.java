package com.peakyu.application.controller.admin;

import com.peakyu.application.dao.log;
import com.peakyu.application.utils.PageUtils;
import com.peakyu.application.service.admin.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/admin/log")
public class logController {

    @Autowired
    private  logService logService;

    @PostMapping("/all")
    public String log(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {

        PageUtils<log> log = logService.selectAllLog(currentPage);

        model.addAttribute("log", log);
        model.addAttribute("search", 0);
        return "a/log";
    }


    @PostMapping("/search")
    public String logSearch(@RequestParam("type") Integer type,
                     @RequestParam("keyword") String keyword,
                     @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {


        //查询结果
        PageUtils<log> log = null;

        if (type == 0) {
            log = logService.adminSelectLogByOperator(currentPage, keyword);
            model.addAttribute("searchType", "用户");
        } else if (type == 1) {
            log = logService.adminSelectLogByOperate(currentPage, keyword);
            model.addAttribute("searchType", "操作");
        } else if (type == 2) {
            log = logService.adminSelectLogByType(currentPage, Integer.parseInt(keyword));
            model.addAttribute("searchType", "类型");
        }
        //将关键字和查询结果返回

        model.addAttribute("type", type);

        model.addAttribute("keyword", keyword);

        model.addAttribute("log", log);

        model.addAttribute("search", 2);
        return "a/searchLogResult";
    }


    public String dateAddOneDay(String time) throws ParseException {
        long oneDayTime = 1000 * 3600 * 24;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(time);
        String format = sdf.format(new Date(date1.getTime() + oneDayTime));
        return format;
    }


    @PostMapping("/search/byTime")
    public String logSearchByTime(@RequestParam("time") String time,
                           @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) throws ParseException {

        //pageType是页面  0是全部页面筛选  1 是筛选页面再筛选
        //查询结果
        PageUtils<log> log = null;

        String time2 = dateAddOneDay(time);
        log = logService.selectLogByDate(time, time2, currentPage);


        model.addAttribute("log", log);
        model.addAttribute("time", time);
        model.addAttribute("search", 1);
        return "a/searchLogByTimeResult";
    }

    @PostMapping("/search/byTimeAndKey")
    public String logSearchByTimeAndKey(@RequestParam("time") String time,
                                 @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                 @RequestParam("type") Integer type, @RequestParam("keyword") String keyword, Model model) throws ParseException {

        //pageType是页面  0是全部页面筛选  1 是筛选页面再筛选
        //查询结果
        PageUtils<log> log = null;

        String time2 = dateAddOneDay(time);

        if (type == 0) {
            log = logService.adminSelectLogByOperatorAndTime(currentPage, keyword, time, time2);
            model.addAttribute("searchType", "用户");
        } else if (type == 1) {
            log = logService.adminSelectLogByOperateAndTime(currentPage, keyword, time, time2);
            model.addAttribute("searchType", "操作");
        } else if (type == 2) {
            log = logService.adminSelectLogByTypeAndTime(currentPage, Integer.parseInt(keyword), time, time2);
            model.addAttribute("searchType", "类型");
        }
        //将关键字和查询结果返回

        model.addAttribute("type", type);

        model.addAttribute("keyword", keyword);
        model.addAttribute("log", log);
        model.addAttribute("time", time);
        model.addAttribute("search", 2);
        return "a/searchLogByTimeResult";
    }


    @PostMapping("/search/byTimeRange")
    public String logSearchByTimeRange(@RequestParam("time1") String time1,
                                @RequestParam("time2") String time2,
                                @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {


        //查询结果
        PageUtils<log> log = null;

        log = logService.selectLogByDate(time1, time2, currentPage);
        //将关键字和查询结果返回

        model.addAttribute("log", log);

        model.addAttribute("time1", time1);
        model.addAttribute("time2", time2);
        model.addAttribute("search", 1);//0time    1timeRange   2timeandkey 3timerangeandkey
        return "a/searchLogByTimeResult";
    }


    @PostMapping("/search/byTimeRangeAndKey")
    public String logSearchByTimeRangeAndKey(@RequestParam("time1") String time1,
                                      @RequestParam("time2") String time2,
                                      @RequestParam("type") Integer type,
                                      @RequestParam("keyword") String keyword,
                                      @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {


        //查询结果
        PageUtils<log> log = null;

        if (type == 0) {
            log = logService.adminSelectLogByOperatorAndTime(currentPage, keyword, time1, time2);
            model.addAttribute("searchType", "用户");
        } else if (type == 1) {
            log = logService.adminSelectLogByOperateAndTime(currentPage, keyword, time1, time2);
            model.addAttribute("searchType", "操作");
        } else if (type == 2) {
            log = logService.adminSelectLogByTypeAndTime(currentPage, Integer.parseInt(keyword), time1, time2);
            model.addAttribute("searchType", "类型");}
            //将关键字和查询结果返回

            model.addAttribute("type", type);

            model.addAttribute("keyword", keyword);

            model.addAttribute("log", log);

            model.addAttribute("time1", time1);
            model.addAttribute("time2", time2);
            model.addAttribute("search", 3);
            return "a/searchLogByTimeResult";
        }
    @PostMapping("/delete")
    @ResponseBody
    public  Integer logDelete(@RequestParam("id") Integer id){


        int i = logService.deleteLogByid(id);
        return i;
    }
}