package com.peakyu.application.mapper.admin;

import com.peakyu.application.dao.exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface examMapper {


    //通过邮箱查找审核结果
    @Select("SELECT * from exam where Email = #{email}")
    exam selectByEmail(String email);


    //通过邮箱删除操作记录
    @Select("delete from exam where Email = #{email}")
    exam deleteByEmail(String email);

    //管理员添加审核结果
    @Insert("INSERT INTO exam (Email, result, reason,ConTime) VALUES (#{Email},#{result} ,#{reason},#{ConTime} )")
    int insertIntoExam(exam exam);
}
