package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ShipDao extends JpaRepository<Ship, Long> {
    Ship findShipById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update Ship set status = 'INACTIVE', delete_at = CURRENT_TIMESTAMP where id = :id", nativeQuery = true)
    int softDeleteShip(@Param("id") Long id);
    boolean existsByNumberPlate(String numberPlate);

}
