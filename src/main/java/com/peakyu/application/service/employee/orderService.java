package com.peakyu.application.service.employee;

import com.peakyu.application.dao.orderadvance;
import com.peakyu.application.mapper.employee.orderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class orderService implements com.peakyu.application.mapper.employee.orderMapper {

    @Autowired
    private orderMapper orderMapper;
    @Autowired
    private com.peakyu.application.mapper.employee.orderdoneMapper orderdoneMapper;



    public Integer addeleteOrder(int id) {
        orderMapper.deleteOrder(id);
        orderdoneMapper.deleteOrder(id);

        return 1;
    }




    @Override
    public Integer deleteOrder(int id) {
        return null;
    }

    @Override
    public orderadvance selectOrderById(Integer id) {
        return orderMapper.selectOrderById(id);
    }

    @Override
    public Integer insertOrderIn(orderadvance order) {
        return orderMapper.insertOrderIn(order);
    }

    @Override
    public Integer updateOrderOut(orderadvance order) {
        return orderMapper.updateOrderOut(order);
    }

    @Override
    public orderadvance selectOrderByLicense(String license) {
        return orderMapper.selectOrderByLicense(license);
    }
}
