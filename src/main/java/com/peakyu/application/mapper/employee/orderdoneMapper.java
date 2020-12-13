package com.peakyu.application.mapper.employee;

import com.peakyu.application.dao.Orderdone;
import com.peakyu.application.dao.orderadvance;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface orderdoneMapper {


    //通过订单号查状态
    @Select("select * from orderdone where oid = #{oid} order by id desc limit 0,1")
    Orderdone selectOrderdoneByOid(int oid);

    //zhifu订单]
    @Update("Update orderdone set status = 1 where oid = #{oid} ")
    int payOrder(int oid);


    //入栈订单
    @Insert("INSERT INTO orderdone (oid, status) VALUES (#{oid}, #{status})")
    Integer insertOrderderdone(Orderdone order);

    ////admin查看订单
    //admin查看全部订单状态
    @Select("select * from orderadvance order by id desc  limit #{start},#{pageSize}")
    List<orderadvance> selectAllOrderAdvanceByadmin(Integer start, Integer pageSize);

    //admin查看未支付订单状态
    @Select("select b.* from orderdone a,orderadvance b where  a.oid = b.id and a.status = 0  order by b.id desc limit #{start},#{pageSize}")
    List<orderadvance> selectOrderAdvanceByadmin(Integer start,Integer pageSize);

    //admin查看已支付订单状态
    @Select("select b.* from orderdone a,orderadvance b where  a.oid = b.id and a.status = 1 order by b.id desc limit #{start},#{pageSize}")
    List<orderadvance> selectNotOrderAdvanceByadmin(Integer start,Integer pageSize);
    ////admin查看订单end








    ////员工查看订单
    //员工查看全部订单状态
    @Select("select b.* from orderdone a,orderadvance b where (b.operatorIn=#{Email} or b.operatorOut=#{Email}) and a.oid = b.id order by b.id desc limit #{start},#{pageSize}")
    List<orderadvance> selectAllOrderAdvanceByEmployee(String Email,Integer start,Integer pageSize);

    //员工查看未支付订单状态
    @Select("select b.* from orderdone a,orderadvance b where (b.operatorIn=#{Email} or b.operatorOut=#{Email}) and a.oid = b.id and a.status = 0 order by b.id limit #{start},#{pageSize}")
    List<orderadvance> selectOrderAdvanceByEmployee(String Email,Integer start,Integer pageSize);

    //员工查看已支付订单状态
    @Select("select b.* from orderdone a,orderadvance b where (b.operatorIn=#{Email} or b.operatorOut=#{Email}) and a.oid = b.id and a.status = 1 order by b.id limit #{start},#{pageSize}")
    List<orderadvance> selectNotOrderAdvanceByEmployee(String Email,Integer start,Integer pageSize);
    ////员工查看订单end


    ////用户查看订单
    //用户查看全部订单状态
    @Select("select b.* from orderdone a,orderadvance b where b.license = #{license} and a.oid = b.id order by b.id limit #{start},#{pageSize}")
    List<orderadvance> selectAllOrderAdvanceByuser(String license,Integer start,Integer pageSize);

    //用户查看未支付订单状态
    @Select("select b.* from orderdone a,orderadvance b where b.license = #{license} and a.oid = b.id and a.status = 1 order by b.id limit #{start},#{pageSize}")
    List<orderadvance> selectOrderAdvanceByuser(String license,Integer start,Integer pageSize);

    //用户查看yi支付订单状态
    @Select("select b.* from orderdone a,orderadvance b where b.license = #{license} and a.oid = b.id and a.status = 0 order by b.id limit #{start},#{pageSize}")
    List<orderadvance> selectNotOrderAdvanceByuser(String license,Integer start,Integer pageSize);
    ////

    //删除订单
    @Delete("delete from orderdone WHERE (oid=#{id}) ")
    Integer deleteOrder(int id);

}
