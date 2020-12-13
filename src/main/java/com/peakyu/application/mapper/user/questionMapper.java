package com.peakyu.application.mapper.user;

import com.peakyu.application.dao.question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface questionMapper {

    //用户通过邮箱查找密保问题
    @Select("SELECT * FROM question where Email = #{email}")
    question seByEmail(String email);
}
