package com.peakyu.application.mapper;

import com.peakyu.application.dao.sensor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface sensorMapper {

    @Select("select * from sensor where sensorId = #{sensorId} order by time desc limit 6")
    List<sensor> retrieveSensor(Integer sensorId);


    @Insert("Insert into sensor(sensorId,sensorName,sensorDescribe,time, temperature, humidity,starch,alcohol, acidity) values (#{sensorId},#{sensorName},#{sensorDescribe},#{time},#{temperature}, #{humidity},#{starch},#{alcohol}, #{acidity})")
    int updateSensor(sensor sensor);


}
