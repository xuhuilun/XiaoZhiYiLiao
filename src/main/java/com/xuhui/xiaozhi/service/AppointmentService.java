package com.xuhui.xiaozhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuhui.xiaozhi.entity.Appointment;
import org.springframework.stereotype.Service;


@Service
public interface AppointmentService extends IService<Appointment> {
    Appointment selectOne(Appointment appointment);
}
