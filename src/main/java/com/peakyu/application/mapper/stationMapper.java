package com.peakyu.application.mapper;

import com.peakyu.application.dao.station;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface stationMapper {

    @Select("select * from station limit #{start},#{pageSize}")
    List<station> selectAllStation(Integer start, Integer pageSize);

    @Select("select * from station where name = #{name}")
    List<station> selectStationByName(String name);

    @Select("select * from station where id = #{id}")
    station selectStationById(Integer id);

    @Insert("Insert into station(name,interduce) values (#{name},#{interduce})")
    int insertStation(station station);
}
