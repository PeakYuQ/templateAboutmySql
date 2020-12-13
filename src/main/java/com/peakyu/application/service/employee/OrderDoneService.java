package com.peakyu.application.service.employee;

import com.peakyu.application.dao.Orderdone;
import com.peakyu.application.dao.orderadvance;
import com.peakyu.application.mapper.employee.orderdoneMapper;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDoneService implements com.peakyu.application.mapper.employee.orderdoneMapper {


    @Autowired
    private orderdoneMapper orderdoneMapper;

    ////admin 查询订单
    //所有订单
    public PageUtils<orderadvance> adminSelectAllOrder(int currentPage){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectAllOrderAdvanceByadmin(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectAllOrderAdvanceByadmin(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }



    //未支付订单
    public PageUtils<orderadvance> adminSelectOrder(int currentPage ){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectOrderAdvanceByadmin( 0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectOrderAdvanceByadmin( start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }


    //已支付订单
    public PageUtils<orderadvance> adminSelectNotOrder(int currentPage ){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectNotOrderAdvanceByadmin( 0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectNotOrderAdvanceByadmin( start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    ////admin 查询订单end







    ////employee 查询订单
    //所有订单
    public PageUtils<orderadvance> EmployeeSelectAllOrder(int currentPage,String email){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectAllOrderAdvanceByEmployee(email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectAllOrderAdvanceByEmployee(email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    @Override
    public Integer deleteOrder(int id) {
        return null;
    }

    //未支付订单
    public PageUtils<orderadvance> EmployeeSelectOrder(int currentPage,String email){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectOrderAdvanceByEmployee(email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectOrderAdvanceByEmployee(email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }


    //已支付订单
    public PageUtils<orderadvance> EmployeeSelectNotOrder(int currentPage,String email){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectNotOrderAdvanceByEmployee(email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectNotOrderAdvanceByEmployee(email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    ////employee 查询订单end




    ////用户查看订单

    //wei支付订单
    public PageUtils<orderadvance> userSelectNotOrder(int currentPage,String email){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectNotOrderAdvanceByuser(email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectNotOrderAdvanceByuser(email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //已支付订单
    public PageUtils<orderadvance> userSelectOrder(int currentPage,String email){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectOrderAdvanceByuser(email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectOrderAdvanceByuser(email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //wei支付订单
    public PageUtils<orderadvance> userSelectAllOrder(int currentPage,String email){
        PageUtils<orderadvance> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = orderdoneMapper.selectAllOrderAdvanceByuser(email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<orderadvance> lists = orderdoneMapper.selectAllOrderAdvanceByuser(email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }














    @Override
    public Orderdone selectOrderdoneByOid(int oid) {
        return null;
    }

    @Override
    public int payOrder(int oid) {
        return 0;
    }

    @Override
    public Integer insertOrderderdone(Orderdone order) {
        return null;
    }

    @Override
    public List<orderadvance> selectAllOrderAdvanceByEmployee(String Email, Integer start, Integer pageSize) {
        return null;
    }

    @Override
    public List<orderadvance> selectOrderAdvanceByEmployee(String Email, Integer start, Integer pageSize) {
        return null;
    }

    @Override
    public List<orderadvance> selectNotOrderAdvanceByEmployee(String Email, Integer start, Integer pageSize) {
        return null;
    }

    @Override
    public List<orderadvance> selectAllOrderAdvanceByuser(String license, Integer start, Integer pageSize) {
        return null;
    }

    @Override
    public List<orderadvance> selectOrderAdvanceByuser(String license, Integer start, Integer pageSize) {
        return null;
    }

    @Override
    public List<orderadvance> selectNotOrderAdvanceByuser(String license, Integer start, Integer pageSize) {
        return null;
    }
    @Override
    public List<orderadvance> selectAllOrderAdvanceByadmin(Integer start, Integer pageSize) {
        return null;
    }

    @Override
    public List<orderadvance> selectOrderAdvanceByadmin(Integer start, Integer pageSize) {
        return null;
    }

    @Override
    public List<orderadvance> selectNotOrderAdvanceByadmin(Integer start, Integer pageSize) {
        return null;
    }
}
