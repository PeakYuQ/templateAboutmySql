package com.peakyu.application.service.user;

import com.peakyu.application.dao.exam;
import com.peakyu.application.dao.user;
import com.peakyu.application.mapper.user.userMapper;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService implements com.peakyu.application.mapper.user.userMapper {
    @Autowired
    private userMapper userMapper;
    @Autowired
    private com.peakyu.application.mapper.admin.examMapper examMapper;


    ////用户审核查询！！
    //分页查询全部用户记录
    public PageUtils<user> selectAllUserAccount(Integer currentPage) {
        PageUtils<user> user = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = userMapper.selectAllUserAccount(0,1000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<user> lists = userMapper.selectAllUserAccount(start, pageSize);

        //开始封装
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);
        user.setTotalCount(totalCount);
        user.setTotalPage(totalPage);
        user.setList(lists);
        return user;
    }

    //分页查询已审核用户信息
    public PageUtils<user> selectActiveUserAccount(Integer currentPage) {
        PageUtils<user> user = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = userMapper.selectActiveUserAccount(0,1000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<user> lists = userMapper.selectActiveUserAccount(start, pageSize);

        //开始封装
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);
        user.setTotalCount(totalCount);
        user.setTotalPage(totalPage);
        user.setList(lists);
        return user;
    }

    //分页查询未审核用户信息
    public PageUtils<user> selectNotActiveUserAccount(Integer currentPage) {
        PageUtils<user> user = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = userMapper.selectNotActiveUserAccount(0,1000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<user> lists = userMapper.selectNotActiveUserAccount(start, pageSize);

        //开始封装
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);
        user.setTotalCount(totalCount);
        user.setTotalPage(totalPage);
        user.setList(lists);
        return user;
    }
    ////结束！！




    ////进信箱分页查询
    //查询已审核用户信息 start起始记录 pagesize页面大小  0,10000为查询全部
    @Override
    public List<user> selectActiveUserAccount(Integer start,Integer pagesize) {
        return userMapper.selectActiveUserAccount(start,pagesize);
    }

    //查询未审核用户信息
    @Override
    public List<user> selectNotActiveUserAccount(Integer start,Integer pagesize) {
        return userMapper.selectNotActiveUserAccount(start,pagesize);
    }

    //查询全部用户信息
    @Override
    public List<user> selectAllUserAccount(Integer start, Integer pagesize) {
        return userMapper.selectAllUserAccount(start,pagesize);
    }
    ////结束！！



    ////admin模糊查找用户信息
    //通过电话查询用户信息
    @Override
    public user selectUserAccountByPhone(String phone) {
        return userMapper.selectUserAccountByPhone(phone);
    }

    //通过车牌号查询用户信息
    @Override
    public user selectUserAccountByLicense(String license) {
        return userMapper.selectUserAccountByLicense(license);
    }

    //通过邮箱查询用户信息
    @Override
    public user selectUserAccountByEmail(String email) {
        return userMapper.selectUserAccountByEmail(email);
    }
    //////结束




    //admin跟新用户信息
    @Override
    public int adminUpdateUser(user user) {
        return userMapper.adminUpdateUser(user);
    }

    @Override
    public int UpdateUserPsk(user user) {
        return userMapper.UpdateUserPsk(user);
    }

    //admin删除用户信息
    @Override
    public int adminDeleteUser(Integer id) {
       String email = userMapper.selectAccountById(id).getEmial();

        if(examMapper.selectByEmail(email) != null)
            examMapper.deleteByEmail(email);

        return userMapper.adminDeleteUser(id);
    }

    //通过邮箱设置用户信息不通过
    @Override
    public int updateUserAccountNotActive(String email, exam exam) {

        if(examMapper.selectByEmail(email) != null)
        examMapper.deleteByEmail(email);
        examMapper.insertIntoExam(exam);
        return userMapper.updateUserAccountNotActive(email,exam);
    }

    //通过邮箱设置用户信息通过
    @Override
    public int updateUserAccountActive(String email, exam exam) {
        if(examMapper.selectByEmail(email) != null)
        examMapper.deleteByEmail(email);
        examMapper.insertIntoExam(exam);
        return userMapper.updateUserAccountActive(email,exam);
    }

    //插入一条用户信息
    @Override
    public int insertUserAccount(user user) {
        return userMapper.insertUserAccount(user);
    }

    @Override
    public user selectAccountById(Integer id) {
        return userMapper.selectAccountById(id);
    }
}
