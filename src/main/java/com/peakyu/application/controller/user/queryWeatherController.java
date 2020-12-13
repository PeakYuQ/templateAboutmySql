package com.peakyu.application.controller.user;


import com.peakyu.application.dao.log;
import com.peakyu.application.dao.weather.Forecast;
import com.peakyu.application.dao.weather.Yesterday;
import com.peakyu.application.dao.weather.weatherData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class queryWeatherController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private com.peakyu.application.service.admin.logService logService;
    /**
     * 过滤非数字
     * @param str
     * @return
     */
    public static String getNumeric(String str) {
        str=str.trim();
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2 += str.charAt(i);
                }
            }
        }
        return str2;
    }


    @PostMapping("/weather")
    public String charts7(@RequestParam("city") String city, Model model,HttpSession session){
        String apiURL = "http://wthrcdn.etouch.cn/weather_mini?city="+city;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
        //获得所有回传的消息
        String jsonsting=responseEntity.getBody();


        weatherData wed=new weatherData();
        JSONObject str =JSONObject.fromObject(jsonsting);
        //	第一步解析
        String status="";
        if(str.has("status")){
            status=	str.get("status").toString();
            //如果输入的城市不对回传的状态码不同
        }
        if(status.equals("1000")){

            if(str.has("data")){
                //获取正确的数据
                JSONObject str1 =JSONObject.fromObject(str.get("data"));
                if(str1.has("yesterday")){
                    //获取昨天数据
                    JSONObject yy =JSONObject.fromObject(str1.get("yesterday"));
                    //set到昨天实体类

                    Yesterday y=new Yesterday();
                    y.setDate(getNumeric(yy.getString("date")));

                    String fl = yy.getString("fl").substring(9, 12);

                    if(fl.charAt(2) == '级')
                        fl = yy.getString("fl").substring(9, 11);

                    if(fl.charAt(2) == ']')
                        fl = yy.getString("fl").substring(9, 10);

                    y.setFl(fl);
                    y.setFx(yy.getString("fx"));
                    y.setHigh(getNumeric(yy.getString("high")));
                    y.setLow(getNumeric(yy.getString("low")));
                    y.setType(yy.getString("type"));

                    wed.setYesterday(y);
                }
                //预报
                List<Forecast> fc=new ArrayList<Forecast>();
                if(str1.has("forecast")){
                    //System.err.println(str1.get("forecast"));
                    JSONArray tr=str1.getJSONArray("forecast");
                    for (int i = 0; i < tr.size(); i++) {
                        JSONObject yy1=JSONObject.fromObject(tr.get(i));
                        Forecast f=new Forecast();
                        f.setDate(getNumeric(yy1.getString("date")));
                        String fengli = yy1.getString("fengli").substring(9, 12);

                        if(fengli.charAt(2) == '级')
                            fengli = yy1.getString("fengli").substring(9, 11);
                        if(fengli.charAt(2) == ']')
                            fengli = yy1.getString("fengli").substring(9, 10);


                        f.setFengli(fengli);
                        f.setFengxiang(yy1.getString("fengxiang"));
                        f.setHigh(getNumeric(yy1.getString("high")));
                        f.setLow(getNumeric(yy1.getString("low")));
                        f.setType(yy1.getString("type"));
                        //	System.err.println(f+"f");
                        fc.add(f);
                    }
                    wed.setForecast(fc);
                }
                //感冒指数
                if(str1.has("ganmao")){
                    //System.err.println(str1.get("ganmao"));
                    wed.setGanmao(str1.get("ganmao").toString());
                }
                //城市
                if(str1.has("city")){
                    //System.err.println(str1.get("city"));
                    wed.setCity(str1.get("city").toString());
                }
                //温度
                if(str1.has("wendu")){
                    //	System.err.println(str1.get("wendu"));
                    wed.setWendu(str1.get("wendu").toString());
                }
                //System.err.println(wed+"wed");
                model.addAttribute("WeatherData", wed);
                model.addAttribute("city", city);
            }


        }else{

        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        log log = new log(5,"查询了"+city+"的天气情况",timestamp,(String) session.getAttribute("operator"));

        //日志 用户登录情况
        logService.insertLog(log);



        return "u/weather";
    }




}
