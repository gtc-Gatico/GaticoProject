package cn.com.gatico.dao;

import cn.com.gatico.entity.PostgresTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresTestDao extends JpaRepository<PostgresTestEntity,Integer> {

}
