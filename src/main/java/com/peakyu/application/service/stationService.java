package com.peakyu.application.service;

import com.peakyu.application.dao.station;
import com.peakyu.application.mapper.stationMapper;
import com.peakyu.application.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class stationService implements stationMapper {

    @Autowired
    private stationMapper stationMapper;

    ////分页查询station -- service
    //所有station
    public PageUtils<station> selectAllStation(int currentPage) {
        PageUtils<station> message = new PageUtils<>();
        //定义每页显示的记录数
        int pageSize = 5;
        //查询总记录数
        int totalCount = stationMapper.selectAllStation(0,10000).size();  //查询当前总记录数
        double tc = totalCount;
        //查询总页数
        int totalPage = (int) Math.ceil(tc / pageSize);
        //查询每页显示的结果集
        int start = (currentPage - 1) * pageSize;
        List<station> lists = stationMapper.selectAllStation(start, pageSize);

        //开始封装
        message.setCurrentPage(currentPage);
        message.setPageSize(pageSize);
        message.setTotalCount(totalCount);
        message.setTotalPage(totalPage);
        message.setList(lists);
        return message;
    }

    @Override
    public station selectStationById(Integer id) {
        return stationMapper.selectStationById(id);
    }

    @Override
    public int insertStation(station station) {
        return stationMapper.insertStation(station);
    }

    @Override
    public List<station> selectStationByName(String name) {
        return stationMapper.selectStationByName(name);
    }

    @Override
    public List<station> selectAllStation(Integer start,Integer pageSize) {
        return stationMapper.selectAllStation(0,1000);
    }
}
