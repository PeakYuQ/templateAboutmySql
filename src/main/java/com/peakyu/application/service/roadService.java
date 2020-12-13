package com.peakyu.application.service;


import com.peakyu.application.dao.road;
import com.peakyu.application.mapper.roadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roadService implements roadMapper {
    @Override
    public road selectRoadByfirlasId(Integer id, Integer id2) {
        return null;
    }

    @Override
    public road selectRoadByName(String name) {
        return null;
    }

    @Override
    public int deleteRoad(String road) {
        return 0;
    }

    @Override
    public int updateRoad(road road) {
        return 0;
    }

    @Override
    public int insertRoad(road road) {
        return 0;
    }

    @Autowired
   private  roadMapper roadMapper;

    //查询所有的线路
    @Override
    public List<road> selectAllRoad() {
        return roadMapper.selectAllRoad();
    }

    //根据id查询线路
    @Override
    public List<road> selectRoadById(Integer id) {
        return roadMapper.selectRoadById(id);
    }
}
