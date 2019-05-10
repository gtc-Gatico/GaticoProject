package cn.com.gatico.dao;

import cn.com.gatico.entity.MsUserinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsUserinfoDao extends JpaRepository<MsUserinfoEntity,Long> {
    List<MsUserinfoEntity> findMsUserinfoEntityById(Long Id);
}
