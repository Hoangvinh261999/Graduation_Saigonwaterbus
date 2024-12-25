package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RouteDao extends JpaRepository<Route, Long> {
    Route findRouteById(Long id);
    @Modifying
    @Transactional
    @Query(value = "update Route set status = 'INACTIVE', delete_at = CURRENT_TIMESTAMP where id = :id", nativeQuery = true)
    int softDeleteRoute(@Param("id") Long id);
}
