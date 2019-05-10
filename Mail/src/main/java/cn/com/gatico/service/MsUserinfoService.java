package cn.com.gatico.service;

import cn.com.gatico.entity.MsUserinfoEntity;

import java.util.List;

public interface MsUserinfoService {

    List<MsUserinfoEntity> listAll();

    List<MsUserinfoEntity> findMsUserinfoEntityById(Long Id);

}
