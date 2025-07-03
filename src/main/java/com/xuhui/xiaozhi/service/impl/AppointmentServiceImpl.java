package com.xuhui.xiaozhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuhui.xiaozhi.entity.Appointment;
import com.xuhui.xiaozhi.mapper.AppointmentMapper;
import com.xuhui.xiaozhi.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {
    /**
     * 查询订单是否存在
     * @param appointment
     * @return
     */
    @Override
    public Appointment selectOne(Appointment appointment) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
        queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard());
        queryWrapper.eq(Appointment::getDepartment, appointment.getDepartment());
        queryWrapper.eq(Appointment::getDate, appointment.getDate());
        queryWrapper.eq(Appointment::getTime, appointment.getTime());
        return baseMapper.selectOne(queryWrapper);
    }
}
