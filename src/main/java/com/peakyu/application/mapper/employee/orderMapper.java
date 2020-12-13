package com.peakyu.application.mapper.employee;

import com.peakyu.application.dao.orderadvance;
import org.apache.ibatis.annotations.*;

@Mapper
public interface orderMapper {



    //根据id查入栈订单
    @Select("select * from orderadvance where id = #{id}")
    orderadvance selectOrderById(Integer id);


    //入栈订单
    @Insert("INSERT INTO orderAdvance (license, startTime, firId, operatorIn) VALUES (#{license},#{startTime},#{firId},#{operatorIn})")
    Integer insertOrderIn(orderadvance order);


    //出站订单
    @Update("UPDATE orderAdvance SET endTime=#{endTime}, lasId=#{lasId}, status=#{status}, operatorOut=#{operatorOut} WHERE (id=#{id}) ")
    Integer updateOrderOut(orderadvance order);

    //根据license查最近订单
    @Select(" select * from orderAdvance where license = #{license} order by id desc limit 0,1")
    orderadvance selectOrderByLicense(String license);

    //删除订单
    @Delete("delete from orderAdvance WHERE (id=#{id}) ")
    Integer deleteOrder(int id);

}
