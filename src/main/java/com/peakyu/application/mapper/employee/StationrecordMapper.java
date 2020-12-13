package com.peakyu.application.mapper.employee;

import com.peakyu.application.dao.Stationrecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StationrecordMapper {


    //根据车牌查最近的出入站记录
    @Select("select * from stationrecord where license = #{license}  order by id desc limit 0,1")
    Stationrecord selectStationrecordByLicense(String license);




    //入栈订单
    @Insert("INSERT INTO stationrecord (license, time, status, station_id) VALUES (#{license},#{time},#{status},#{station_id})")
    Integer insertStationrecord(Stationrecord Stationrecord);


}
