package com.peakyu.application.mapper;

import com.peakyu.application.dao.message;
import com.peakyu.application.dao.operatemessage;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface messageMapper {

    ////admin删除信件
    //admin删除message表中数据1
    @Delete("delete from message where id = #{id}")
    int adminDeleteMail(Integer id);

    //admin删除readmessage表中数据2
    @Delete("delete from readmessage where mid = #{id}")
    int adminDeleteReadMail(Integer id);

    //admin删除deletemessage表中数据3
    @Delete("delete from deletemessage where mid = #{id}")
    int adminDeleteDeleteReadMail(Integer id);
    ////删除信件结束！！！









    ////信件详情功能
    //查找已读消息
    @Select("select * from readmessage where Email = #{Email} and mid = #{mid}")
    operatemessage selectReadMessage(operatemessage message);

    //添加已读消息
    @Insert("INSERT INTO readmessage (Email,mid) VALUES (#{Email},#{mid})")
    int insertReadMessage(operatemessage message);

    //添加删除消息
    @Insert("INSERT INTO deletemessage (Email,mid) VALUES (#{Email},#{mid})")
    int insertDeleteMessage(operatemessage message);

    //撤回已删除消息
    @Insert("DELETE FROM deletemessage WHERE Email = #{Email} and mid = #{mid}")
    int deleteDeleteMessage(operatemessage message);
    ////信件详情功能结束








    ////admin 信件操作
    //模糊查找
    //admin根据title模糊查找信息
    @Select("Select * From message where title like CONCAT('%',#{title},'%') order by id desc limit #{start},#{pageSize}")
    List<message> adminSelectMailByTitle(String title, Integer start, Integer pageSize);

    //admin根据txt模糊查找信息
    @Select("Select * From message where txt like CONCAT('%',#{txt},'%') order by id desc limit #{start},#{pageSize}")
    List<message> adminSelectMailByTxt(String txt,Integer start,Integer pageSize);

    //admin根据email查找信息
    @Select("Select * From message where Email = #{role} order by id desc limit #{start},#{pageSize} ")
    List<message> adminSelectMailByEmail(String role,Integer start,Integer pageSize);

    //查询发送给admin的消息
    @Select("Select * From message where Email = 'admin' order by id desc limit #{start},#{pageSize}")
    List<message> selectSendToAdminMessage(Integer start,Integer pageSize);


    //查询发送给admin的未读消息
    @Select("Select * From message where Email = 'admin' and active = 0  order by id desc limit #{start},#{pageSize}")
    List<message> selectAdminNotReadMessage(Integer start,Integer pageSize);


    //查询发送给admin的已读消息
    @Select("Select * From message where Email = 'admin' and active = 1  order by id desc limit #{start},#{pageSize}")
    List<message> selectAdminReadMessage(Integer start,Integer pageSize);

    //查询全部消息
    @Select("Select * From message order by id desc limit #{start},#{pageSize}")
    List<message> selectAllMessage( Integer start,Integer pageSize );

    //添加消息
    @Insert("insert into message(Email,txt,active,title,time,role) values(#{Email},#{txt},#{active},#{title},#{time},#{role})")
    int insertMessage(message message);


    //修改消息
    @Update(" UPDATE message SET txt= #{txt}, title=#{title} WHERE id=#{id}")
    int updateMessage(String txt,String title,Integer id );

    ////admin 信件操作结束！！！！









    ////用户操作信件
    //查询email下所有消息
    @Select("Select * From message where (Email = #{email} or Email = 'all' or Email = 'us') and (active=0 or active=1) order by id desc limit #{start},#{pageSize}")
    List<message> selectMailByEmail(String email,Integer start,Integer pageSize);


    //查询email下所有发送消息  or admin根据email查找信息
    @Select("Select * From message where role = #{email} order by id desc limit #{start},#{pageSize} ")
    List<message> selectSendMessage(String email,Integer start,Integer pageSize);


    //查询当前用户已删除消息
    @Select("Select a.* From message a,deletemessage b where b.Email = #{email} and a.id=b.mid order by id desc limit #{start},#{pageSize} ")
    List<message> selectDeleteMessage(String email,Integer start,Integer pageSize);

    //查询当前用户未读消息
    @Select("SELECT * from message WHERE active=0 and  (Email = #{Email} or  Email = 'all' or Email = 'user' ) order by id desc limit #{start},#{pageSize}")
    List<message> selectNotReadMessage(String Email,Integer start,Integer pageSize);

    //查询当前用户已读消息
    @Select("SELECT * from message WHERE active=1 and  (Email = #{Email} or  Email = 'all' or Email = 'user' ) order by id desc limit #{start},#{pageSize}")
    List<message> selectReadMail(String Email,Integer start,Integer pageSize);
    ////结束！！！1


    //查询当前未处理紧急情况
    @Select("SELECT * from message WHERE active=0 and  (Email = '0' or  Email = '1' or Email = '2' ) order by id desc limit #{start},#{pageSize}")
    List<message> selectNotEmergent(Integer start,Integer pageSize);

    //查询全部紧急情况
    @Select("SELECT * from message WHERE (Email = '0' or  Email = '1' or Email = '2' ) order by id desc limit #{start},#{pageSize}")
    List<message> selectAllEmergent(Integer start,Integer pageSize);

    //查询当前已处理紧急情况
    @Select("SELECT * from message WHERE active=5 or active=6 and  (Email = '0' or  Email = '1' or Email = '2' ) order by id desc limit #{start},#{pageSize}")
    List<message> selectSettleEmergent(Integer start,Integer pageSize);

    ////用户模糊查找
    //用户根据内容关键字查询email下所有消息
    @Select("Select * From message where txt like CONCAT('%',#{txt},'%') and (Email = #{email} or Email = 'all' or Email = 'us') and (active=0 or active=1) order by id desc limit #{start},#{pageSize}")
    List<message> selectMailByTxt(String txt,String email,Integer start,Integer pageSize);

    //模糊查找紧急处理
    @Select("SELECT * from message WHERE txt like CONCAT('%',#{txt},'%') and  (Email = '0' or  Email = '1' or Email = '2' ) order by id desc limit #{start},#{pageSize}")
    List<message> selectEmergentByTxt(String txt,Integer start,Integer pageSize);

    //模糊查找紧急处理
    @Select("SELECT * from message WHERE role = #{role} and  (Email = '0' or  Email = '1' or Email = '2' ) order by id desc limit #{start},#{pageSize}")
    List<message> selectEmergentByRole(String role,Integer start,Integer pageSize);


    //用户根据发件人关键字查询email下所有消息
    @Select("Select * From message where role = #{role}  and (Email = #{email} or Email = 'all' or Email = 'us') and (active=0 or active=1) order by id desc limit #{start},#{pageSize}")
    List<message> selectMailByRole(String role,String email,Integer start,Integer pageSize);

    //用户根据title关键字查询email下所有消息
    @Select("Select * From message where title like CONCAT('%',#{title},'%') and (Email = #{email} or Email = 'all' or Email = 'us') and (active=0 or active=1) order by id desc limit #{start},#{pageSize}")
    List<message> selectMailByTitle(String title,String email,Integer start,Integer pageSize);
    //模糊查找结束




    //用户根据内容关键字查询Note
    @Select("Select * From message where txt like CONCAT('%',#{txt},'%') and Email ='note'  order by id desc limit #{start},#{pageSize}")
    List<message> selectNoteByTxt(String txt,Integer start,Integer pageSize);

    //用户根据发件人关键字查询Note
    @Select("Select * From message where role = #{role}  and Email ='note' order by id desc limit #{start},#{pageSize}")
    List<message> selectNoteByRole(String role,Integer start,Integer pageSize);

    //用户根据title关键字查询Note
    @Select("Select * From message where title like CONCAT('%',#{title},'%') and Email ='note' order by id desc limit #{start},#{pageSize}")
    List<message> selectNoteByTitle(String title,Integer start,Integer pageSize);
    //模糊查找结束
    ////用户操作信件结束！！！！




    ////进信箱操作！！！！
    //通过id查找消息
    @Select("Select * From message where id = #{id}")
    message selectMailById(int id);

    //设置已读消息
    @Update("update message set active=1 WHERE id in(SELECT mid from readmessage where Email=#{Email})")
    int updateMessageRead(String Email);




    //设置审核信息
    @Update("update message set active=#{res} WHERE id =#{id}")
    int updateMessageEmergant(Integer id, Integer res);



    //设置删除消息
    @Update("update message set active=2  WHERE id in(SELECT mid from deletemessage where Email=#{Email})")
    int updateMessageDelete(String Email);

    //设置未读消息
    @Update("update message set active=0 where active!=5")
    int updateMessageNotRead();

    //设置已发送消息
    @Update("update message set active=3 where role = #{email}")
    int updateMessageSend(String email);
    ////结束

}
