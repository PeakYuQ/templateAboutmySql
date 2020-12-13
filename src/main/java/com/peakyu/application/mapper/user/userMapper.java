package com.peakyu.application.mapper.user;

import com.peakyu.application.dao.exam;
import com.peakyu.application.dao.user;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface userMapper {

    //1.分页查询用户信息
    //分页查询全部用户信息
    @Select("SELECT * from user limit #{start},#{pagesize}")
    List <user> selectAllUserAccount(Integer start, Integer pagesize);

    //查询未审核用户信息
    @Select("SELECT * from user where active = 0 limit #{start},#{pagesize}")
    List <user> selectNotActiveUserAccount(Integer start,Integer pagesize);

    //查询已审核用户信息
    @Select("SELECT * from user where active = 1 or active = 2 limit #{start},#{pagesize}")
    List <user> selectActiveUserAccount(Integer start,Integer pagesize);
    //1.结束


    //admin模糊查找用户信息
    //通过邮箱查询用户信息
    @Select("select * from user where Email = #{email}")
    user selectUserAccountByEmail(String email);

    //通过电话查询用户信息
    @Select("select * from user where phone = #{phone} ")
    user selectUserAccountByPhone(String phone);

    //通过车牌号查询用户信息
    @Select("select * from user where license = #{license} ")
    user selectUserAccountByLicense(String license);
    //结束



    //插入一条用户信息
    @Insert("INSERT INTO user (Email,Password,active,ReTime,phone,license,name) VALUES (#{Email},#{Password},#{active},#{ReTime},#{phone},#{license},#{name})")
    int insertUserAccount(user user);




    //通过邮箱设置用户信息不通过
    @Update("UPDATE user set active = 2 where Email = #{email}")
    int updateUserAccountNotActive(String email, exam exam);

    //通过邮箱设置用户信息通过
    @Update("UPDATE user set active = 1 where Email = #{email}")
    int updateUserAccountActive(String email, exam exam);

    //根据id查找用户信息
    @Select("select * from user where id = #{id}")
    user selectAccountById(Integer id);

    //更新用户信息！
    @Update("UPDATE user SET Email =#{Email} ,   active = #{active} ,  phone = #{phone} ,  license = #{license} ,  name = #{name} ,  sex = #{sex}  WHERE ( id = #{id}  ) ")
    int adminUpdateUser(user user);

    //更新用户mima！
    @Update("UPDATE user SET Password = #{Password} WHERE  Email = #{Email}   ")
    int UpdateUserPsk(user user);

    @Delete("delete from user where id = #{id}")
    int adminDeleteUser(Integer id);

}
