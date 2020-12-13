package com.peakyu.application.mapper;

import com.peakyu.application.dao.road;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface roadMapper {


    @Select("select * from road")
    List<road> selectAllRoad();

    @Select("select * from road where name = #{name}")
    road selectRoadByName(String name);

    @Select("select * from road where fir_id = #{id} or las_id = #{id}")
    List<road> selectRoadById(Integer id);

    @Select("select * from road where (fir_id = #{id} and las_id = #{id2}) or (las_id = #{id} and fir_id = #{id2})")
    road selectRoadByfirlasId(Integer id,Integer id2);

    @Insert("Insert into road(name, fir_id, las_id, length, time, price) values(#{name},#{firId},#{lasId},#{length},#{time},#{price})")
    int insertRoad(road road);

    @Update("update  road set length = #{length}, time=#{time}, price=#{price} where name = #{name}")
    int updateRoad(road road);

    @Delete("delete from road where name=#{road}")
    int deleteRoad(String road);
}
