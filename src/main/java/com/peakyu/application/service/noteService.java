package com.peakyu.application.service;


import com.peakyu.application.dao.note;
import com.peakyu.application.mapper.noteMapper;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class noteService implements noteMapper {

    @Autowired
    private noteMapper noteMapper;



    //分页查所有公告
    public PageUtils<note> selectAllNote(int currentPage) {
        PageUtils<note> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = noteMapper.selectAllNote(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<note> lists = noteMapper.selectAllNote(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }




    @Override
    public List<note> selectAllNote(Integer start, Integer pageSize) {
        return noteMapper.selectAllNote(start,pageSize);
    }


    @Override
    public note selectNoteById(Integer id) {
        return noteMapper.selectNoteById(id);
    }
}
