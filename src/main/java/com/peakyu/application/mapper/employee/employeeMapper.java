package com.peakyu.application.mapper.employee;

import com.peakyu.application.dao.employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface employeeMapper {

    //通过employee账号查找employee信息
    @Select("SELECT * FROM employee WHERE sign=#{sign}")
    employee selectEmployeeByName(String sign);


    //添加账户信息
    @Insert("INSERT INTO employee (sign,pwd,name,stationId) VALUES (#{sign},#{pwd},#{name},#{stationId})")
    int insertEmployee(employee employee);

    @Update("update employee set  pwd=#{pwd} WHERE sign=#{sign}")
    int updateEmployee(employee employee);

}
