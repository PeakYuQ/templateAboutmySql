package com.peakyu.application.mapper.admin;

import com.peakyu.application.dao.admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface adminMapper {


    //通过admin账号查找admin信息
    @Select("SELECT * FROM admin WHERE name=#{name}")
    admin SeByName(String name);


    @Update("update admin set  psk=#{psk} WHERE id=1")
    int updateadminPsk(String psk);
}
