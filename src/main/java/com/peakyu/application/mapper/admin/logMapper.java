package com.peakyu.application.mapper.admin;

import com.peakyu.application.dao.log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface logMapper {


    ////admin 信件操作
    //模糊查找
    //admin根据operate模糊查找log
    @Select("Select * From log where operate like CONCAT('%',#{operate},'%') limit #{start},#{pageSize}")
    List<log> adminSelectLogByOperate(String operate, Integer start, Integer pageSize);

    //admin根据operator模糊查找log
    @Select("Select * From log where operator = #{operator} limit #{start},#{pageSize}")
    List<log> adminSelectLogByOperator(String operator,Integer start,Integer pageSize);

    //admin根据type查找log
    @Select("Select * From log where type = #{type} limit #{start},#{pageSize} ")
    List<log> adminSelectLogByType(Integer type,Integer start,Integer pageSize);

    ////admin log
    //模糊查找
    //admin根据operate模糊查找log and time
    @Select("Select * From log where  time>=#{Date1} and time<=#{Date2}  and operate like CONCAT('%',#{operate},'%') limit #{start},#{pageSize}")
    List<log> adminSelectLogByOperateAndTime(String Date1,String Date2,String operate, Integer start, Integer pageSize);

    //admin根据operator模糊查找log time
    @Select("Select * From log where  time>=#{Date1} and time<=#{Date2}  and operator = #{operator} limit #{start},#{pageSize}")
    List<log> adminSelectLogByOperatorAndTime(String Date1,String Date2,String operator,Integer start,Integer pageSize);

    //admin根据type查找log time
    @Select("Select * From log where  time>=#{Date1} and time<=#{Date2}  and type = #{type} limit #{start},#{pageSize} ")
    List<log> adminSelectLogByTypeAndTime(String Date1,String Date2,Integer type,Integer start,Integer pageSize);




    //根据日期查询日志
    @Select("Select * From log where time>=#{Date1} and time<=#{Date2} order by id desc limit #{start},#{pageSize} ")
    List<log> selectLogByDate(String Date1,String Date2,Integer start,Integer pageSize);




    //查询所有日志
    @Select("Select * From log order by id desc limit #{start},#{pageSize} ")
    List<log> selectAllLog(Integer start,Integer pageSize);





    //通过日志类型查询日志
    @Select("SELECT * FROM LOG WHERE TYPE=#{type} limit #{start},#{pageSize} order by id desc")
    List<log> selectLogByType(Integer type);

    //插入一条日志
    @Insert("INSERT INTO LOG(TYPE,OPERATE,TIME,OPERATOR) VALUES(#{type},#{operate},#{time},#{operator})")
    int insertLog(log log);


    @Delete("delete from log where id = #{id}")
    int deleteLogByid(Integer id);

}
