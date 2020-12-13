package com.peakyu.application.service.admin;

import com.peakyu.application.dao.log;
import com.peakyu.application.mapper.admin.logMapper;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class logService implements com.peakyu.application.mapper.admin.logMapper {

    @Autowired
    logMapper logMapper;


    ////分页查询log -- service
    //所有log
    public PageUtils<log> selectAllLog(int currentPage) {
        PageUtils<log> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = logMapper.selectAllLog(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<log> lists = logMapper.selectAllLog(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }




    ////admin分页模糊查询
    //admin模糊搜索根据operate
    public PageUtils<log> adminSelectLogByOperate(int currentPage,String operate) {
        PageUtils<log> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = logMapper.adminSelectLogByOperate(operate,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<log> lists = logMapper.adminSelectLogByOperate(operate,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //admin模糊搜索根据operator
    public PageUtils<log> adminSelectLogByOperator(int currentPage,String operator) {
        PageUtils<log> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = logMapper.adminSelectLogByOperator(operator,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<log> lists = logMapper.adminSelectLogByOperator(operator,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //admin模糊搜索根据type
    public PageUtils<log> adminSelectLogByType(int currentPage,Integer type) {
        PageUtils<log> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = logMapper.adminSelectLogByType(type,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<log> lists = logMapper.adminSelectLogByType(type,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //根据日期查询日志
    public PageUtils<log> selectLogByDate(String Date1,String Date2,int currentPage) {
        PageUtils<log> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = logMapper.selectLogByDate(Date1,Date2,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<log> lists = logMapper.selectLogByDate(Date1,Date2,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //admin模糊搜索根据operator and key
    public PageUtils<log> adminSelectLogByOperateAndTime(int currentPage,String operator,String Date1,String Date2) {
        PageUtils<log> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = logMapper.adminSelectLogByOperateAndTime(Date1,Date2,operator,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<log> lists = logMapper.adminSelectLogByOperateAndTime(Date1,Date2,operator,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }


    //admin模糊搜索根据operator and key
    public PageUtils<log> adminSelectLogByOperatorAndTime(int currentPage,String operator,String Date1,String Date2) {
        PageUtils<log> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = logMapper.adminSelectLogByOperatorAndTime(Date1,Date2,operator,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<log> lists = logMapper.adminSelectLogByOperatorAndTime(Date1,Date2,operator,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }


    //admin模糊搜索根据operator and key
    public PageUtils<log> adminSelectLogByTypeAndTime(int currentPage,Integer operator,String Date1,String Date2) {
        PageUtils<log> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = logMapper.adminSelectLogByTypeAndTime(Date1,Date2,operator,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<log> lists = logMapper.adminSelectLogByTypeAndTime(Date1,Date2,operator,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }


    ////admin log操作
    //模糊查找
    //admin根据operate模糊查找log
    @Override
    public List<log> adminSelectLogByOperate(String operate, Integer start, Integer pageSize) {
        return logMapper.adminSelectLogByOperate(operate,start,pageSize);
    }
    //admin根据operator模糊查找log
    @Override
    public List<log> adminSelectLogByOperator(String operator, Integer start, Integer pageSize) {
        return logMapper.adminSelectLogByOperator(operator,start,pageSize);
    }
    //admin根据type查找log
    @Override
    public List<log> adminSelectLogByType(Integer type, Integer start, Integer pageSize) {
        return logMapper.adminSelectLogByType(type,start,pageSize);
    }

    @Override
    public List<log> selectLogByDate(String Date1, String Date2, Integer start, Integer pageSize) {
        return logMapper.selectLogByDate(Date1,Date2,start,pageSize);
    }

    //查询所有的log
    @Override
    public List<log> selectAllLog(Integer start, Integer pageSize) {
        return logMapper.selectAllLog(start,pageSize);
    }

    //通过日志类型查询日志
    @Override
    public List<log> selectLogByType(Integer type) {
        return logMapper.selectLogByType(type);
    }

    //插入一条日志
    @Override
    public int insertLog(log log) {
        return logMapper.insertLog(log);
    }

    ////admin log操作
    //模糊查找
    //admin根据operate模糊查找log
    @Override
    public List<log> adminSelectLogByOperateAndTime(String Date1, String Date2, String operate, Integer start, Integer pageSize) {
        return null;
    }
    //admin根据operate模糊查找log
    @Override
    public List<log> adminSelectLogByOperatorAndTime(String Date1, String Date2, String operator, Integer start, Integer pageSize) {
        return null;
    }
    //admin根据operate模糊查找log
    @Override
    public List<log> adminSelectLogByTypeAndTime(String Date1, String Date2, Integer type, Integer start, Integer pageSize) {
        return null;
    }

    //删除一条log
    @Override
    public int deleteLogByid(Integer id) {
        return logMapper.deleteLogByid(id);
    }

}
