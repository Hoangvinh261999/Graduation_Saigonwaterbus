package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StationDao extends JpaRepository<Station, Integer> {
    Station findStationById(Long id);
    @Modifying
    @Transactional
    @Query(value = "update Station set status = 'INACTIVE', delete_at = CURRENT_TIMESTAMP where id = :id", nativeQuery = true)
    int softDeleteStation(@Param("id") Long id);
}
