package com.peakyu.application.service.admin;

import com.peakyu.application.dao.admin;
import com.peakyu.application.mapper.admin.adminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminService implements com.peakyu.application.mapper.admin.adminMapper {

    @Autowired
    private adminMapper adminMapper;

    //通过admin账号查找admin信息
    @Override
    public admin SeByName(String name) {
        return adminMapper.SeByName(name);
    }

    @Override
    public int updateadminPsk(String psk) {
        return 0;
    }
}
