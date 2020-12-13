package com.peakyu.application.service;

import com.peakyu.application.dao.operatemessage;
import com.peakyu.application.dao.message;
import com.peakyu.application.mapper.messageMapper;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messageService implements messageMapper {
    @Autowired
    messageMapper messageMapper;

    ////初始化功能
    //用户初始化邮箱 or 刷新邮箱
    public void InitializeBox(String Email){
        messageMapper.updateMessageNotRead();          //设置未读
        messageMapper.updateMessageRead(Email);        //设置已读
        messageMapper.updateMessageDelete(Email);      //设置删除
        messageMapper.updateMessageSend(Email);          //设置已发送
    }

    //admin初始化邮箱 or 刷新邮箱
    public void adminInitializeBox(String Email){
        messageMapper.updateMessageNotRead();          //设置未读
        messageMapper.updateMessageRead(Email);        //设置已读
    }


    //admin删除message表中数据1
    public void adminDeleteMessage(Integer id){
        messageMapper.adminDeleteMail(id);
        messageMapper.adminDeleteReadMail(id);
        messageMapper.adminDeleteDeleteReadMail(id);
    }
    ////功能结束！！！








    ////分页查询message -- service
    //admin未读邮件
    public PageUtils<message> selectAdminNotReadMessage(int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectAdminNotReadMessage(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectAdminNotReadMessage(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //admin的收件箱
    public PageUtils<message> selectSendToAdminMessage(int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectSendToAdminMessage(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectSendToAdminMessage(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //邮箱全部邮件
    public PageUtils<message> selectAllMessage(int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectAllMessage(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectAllMessage(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //admin已发送
    public PageUtils<message> selectSendMessage(int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectSendMessage("admin",0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectSendMessage("admin",start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    ////分页查询service




    ////紧急情况
    //查询suoyou紧急情况
    public PageUtils<message> selectAllEmergent(int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectAllEmergent(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectAllEmergent(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //查询当前未处理紧急情况
    public PageUtils<message> selectNotEmergent(int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectNotEmergent(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectNotEmergent(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //查询当前已经处理紧急情况
    public PageUtils<message> selectSettleEmergent(int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectSettleEmergent(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectSettleEmergent(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //查询suoyou紧急情况
    public PageUtils<message> selectEmergentByRole(String keyword,int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectEmergentByRole(keyword,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectEmergentByRole(keyword,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //查询suoyou紧急情况
    public PageUtils<message> selectEmergentByTxt(String keyword,int currentPage) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectEmergentByTxt(keyword,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectEmergentByTxt(keyword,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    ////





    ////admin分页模糊查询
    //admin模糊搜索根据标题
    public PageUtils<message> adminSelectMailByTitle(int currentPage,String title) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.adminSelectMailByTitle(title,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.adminSelectMailByTitle(title,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //admin模糊搜索根据内容
    public PageUtils<message> adminSelectMailByTxt(int currentPage,String txt) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.adminSelectMailByTxt(txt,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.adminSelectMailByTxt(txt,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }



    //admin模糊搜索根据收件人
    public PageUtils<message> adminSelectMailByEmail(int currentPage,String role) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.adminSelectMailByEmail(role,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.adminSelectMailByEmail(role,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //admin模糊搜索根据发件人
    public PageUtils<message> selectSendMessage(int currentPage,String Email) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectSendMessage(Email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectSendMessage(Email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    ////结束







    ////用户进入信件

    //查询已删除信件
    public PageUtils<message> selectDeleteMessage(int currentPage,String Email) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectDeleteMessage(Email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectDeleteMessage(Email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //查询email下所有消息
    public PageUtils<message> selectMailByEmail(int currentPage,String Email) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectMailByEmail(Email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectMailByEmail(Email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }


    //查询未读消息
    public PageUtils<message> selectNotReadMessage(int currentPage,String Email) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectNotReadMessage(Email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectNotReadMessage(Email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }


    //查询已读消息
    public PageUtils<message> selectReadMail(int currentPage,String Email) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectReadMail(Email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectReadMail(Email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    ////结束！！！！




    ////用户模糊查询信件

    //用户模糊查询根据标题
    public PageUtils<message> selectMailByTitle(int currentPage,String title, String email) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectMailByTitle(title,email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectMailByTitle(title,email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    //用户模糊查询根据内容
    public PageUtils<message> selectMailByTxt (int currentPage,String txt,String email) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectMailByTxt(txt,email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectMailByTxt(txt,email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }


    //用户模糊查询根据NOTE Txt
    public PageUtils<message> selectNoteByTxt (int currentPage,String txt) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectNoteByTxt(txt,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectNoteByTxt(txt,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    //用户模糊查询根据NOTE role
    public PageUtils<message> selectNoteByRole (int currentPage,String txt) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectNoteByRole(txt,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectNoteByRole(txt,start, pageSize);
        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    //用户模糊查询根据NOTE Title
    public PageUtils<message> selectNoteByTitle (int currentPage,String txt) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectNoteByTitle(txt,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectNoteByTitle(txt,start, pageSize);
        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }




    //用户模糊查询根据发件人
    public PageUtils<message> selectMailByRole(int currentPage,String role, String email) {
        PageUtils<message> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = messageMapper.selectMailByRole(role,email,0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<message> lists = messageMapper.selectMailByRole(role,email,start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }
    ////结束




    ////admin 信件操作

    //admin模糊搜索根据标题
    @Override
    public List<message> adminSelectMailByTitle(String title,Integer start,Integer pageSize) {
        return messageMapper.adminSelectMailByTitle(title,start,pageSize);
    }

    //admin模糊搜索根据内容
    @Override
    public List<message> adminSelectMailByTxt(String txt,Integer start,Integer pageSize) {
        return messageMapper.adminSelectMailByTxt(txt,start,pageSize);
    }

    //admin模糊搜索根据邮箱
    @Override
    public List<message> adminSelectMailByEmail(String role,Integer start,Integer pageSize) {
        return messageMapper.adminSelectMailByEmail(role,start,pageSize);
    }

    //查询发送给admin的消息
    @Override
    public List<message> selectSendToAdminMessage(Integer start,Integer pageSize) {
        return messageMapper.selectSendToAdminMessage(start,pageSize);
    }

    //查询admin的未读消息
    @Override
    public List<message> selectAdminNotReadMessage(Integer start,Integer pageSize) {
        return messageMapper.selectAdminNotReadMessage(start,pageSize);
    }

    //查询admin的已读消息
    @Override
    public List<message> selectAdminReadMessage(Integer start,Integer pageSize) {
        return messageMapper.selectAdminReadMessage(start,pageSize);
    }

    //查询所有的信息
    @Override
    public List<message> selectAllMessage(Integer start,Integer pageSize) {
        return messageMapper.selectAllMessage(start,pageSize);
    }


    //添加消息
    @Override
    public int insertMessage(message message) {
        return messageMapper.insertMessage(message);
    }


    ////1admin删除信件
    @Override
    public int adminDeleteMail(Integer id) {
        return messageMapper.adminDeleteMail(id);
    }
    //1admin删除已读信件
    @Override
    public int adminDeleteReadMail(Integer id) {
        return messageMapper.adminDeleteReadMail(id);
    }
    //1admin删除已删除信件
    @Override
    public int adminDeleteDeleteReadMail(Integer id) {
        return messageMapper.adminDeleteDeleteReadMail(id);
    }
    ////1admin删除信件结束
    ////admin操作信件结束！！！！






    ////用户操作信件
    //设置信件为已删除
    @Override
    public int updateMessageDelete(String Email) {
        return messageMapper.updateMessageDelete(Email);
    }

    //查询已删除信件
    @Override
    public List<message> selectDeleteMessage(String email,Integer start,Integer pageSize) {
        return messageMapper.selectDeleteMessage(email,start,pageSize);
    }

    //查询email下所有消息
    @Override
    public List<message> selectMailByEmail(String eamil,Integer start,Integer pageSize) {
        return messageMapper.selectMailByEmail(eamil,start,pageSize);
    }

    //查询未读消息
    @Override
    public List<message> selectNotReadMessage(String Email,Integer start,Integer pageSize) {
        return messageMapper.selectNotReadMessage(Email,start,pageSize);
    }

    //查询已读消息
    @Override
    public List<message> selectReadMail(String Email,Integer start,Integer pageSize) {
        return messageMapper.selectReadMail(Email,start,pageSize);
    }



    //用户模糊查询根据标题
    @Override
    public List<message> selectMailByTitle(String title, String email,Integer start,Integer pageSize) {
        return messageMapper.selectMailByTitle(title,email,start,pageSize);
    }

    //用户模糊查询根据内容
    @Override
    public List<message> selectMailByTxt (String txt, String email,Integer start,Integer pageSize) {
        return messageMapper.selectMailByTxt(txt,email,start,pageSize);
    }

    //用户模糊查询根据发件人
    @Override
    public List<message> selectMailByRole(String role, String email,Integer start,Integer pageSize) {
        return  messageMapper.selectMailByRole(role,email,start,pageSize);
    }

    //查询已读信件
    @Override
    public operatemessage selectReadMessage(operatemessage message) {
        return messageMapper.selectReadMessage(message);
    }


    @Override
    public int updateMessage(String txt, String title, Integer id) {

        if (selectMailById(id) == null)
            return 0;
        else
        return messageMapper.updateMessage(txt,title,id);
    }

    //添加已读信件
    @Override
    public int insertReadMessage(operatemessage message) {
        if (messageMapper.selectReadMessage(message) == null){
            messageMapper.insertReadMessage(message);
            return 1;
        }
        else
            return 0;
    }

    //添加删除信件
    @Override
    public int insertDeleteMessage(operatemessage message) {
        return messageMapper.insertDeleteMessage(message);
    }

    //查询已发送信件
    @Override
    public List<message> selectSendMessage(String eamil,Integer start,Integer pageSize) {
        return messageMapper.selectSendMessage(eamil,start,pageSize);
    }

    //设置信件为已发送
    @Override
    public int updateMessageSend(String email) {
        return messageMapper.updateMessageSend(email);
    }



    //撤回已删除信件
    @Override
    public int deleteDeleteMessage(operatemessage message) {
        return messageMapper.deleteDeleteMessage(message);
    }


    ////用户操作信件结束！！！！


    //用户模糊查询根据内容
    @Override
    public List<message> selectNoteByTxt(String txt, Integer start, Integer pageSize) {
        return null;
    }
    //用户模糊查询根据内容
    @Override
    public List<message> selectNoteByRole(String role, Integer start, Integer pageSize) {
        return null;
    }
    //用户模糊查询根据内容
    @Override
    public List<message> selectNoteByTitle(String title, Integer start, Integer pageSize) {
        return null;
    }



    ////进信箱操作！！！1
    //通过id查找消息
    @Override
    public message selectMailById(int id) {
        return messageMapper.selectMailById(id);
    }


    //设置已读消息
    @Override
    public int updateMessageRead(String Email) {
        return messageMapper.updateMessageRead(Email);
    }

    //设置未读消息
    @Override
    public int updateMessageNotRead() {
        return messageMapper.updateMessageNotRead();
    }

    ////结束

    //查询当前未处理紧急情况
    @Override
    public List<message> selectNotEmergent(Integer start, Integer pageSize) {
        return messageMapper.selectNotEmergent(start,pageSize);
    }
    //查询当前未处理紧急情况
    @Override
    public List<message> selectAllEmergent(Integer start, Integer pageSize) {
        return messageMapper.selectAllEmergent(start,pageSize);
    }
    //查询当前未处理紧急情况
    @Override
    public List<message> selectSettleEmergent(Integer start, Integer pageSize) {
        return messageMapper.selectSettleEmergent(start,pageSize);
    }

    //查询模糊查找紧急情况
    @Override
    public List<message> selectEmergentByTxt(String txt, Integer start, Integer pageSize) {
        return messageMapper.selectEmergentByTxt(txt,start,pageSize);
    }
    //查询模糊查找紧急情况

    @Override
    public List<message> selectEmergentByRole(String role, Integer start, Integer pageSize) {
        return messageMapper.selectEmergentByRole(role,start,pageSize);
    }


    @Override
    public int updateMessageEmergant(Integer id, Integer res) {
        return messageMapper.updateMessageEmergant(id,res);
    }
}
