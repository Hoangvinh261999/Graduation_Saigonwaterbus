package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Route;
import com.sgwb.saigonwaterbus.Model.RouteWaypoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RouteWaypointsDao extends JpaRepository<RouteWaypoints, Long> {
    @Transactional
    @Modifying
    @Query("delete from RouteWaypoints r where r.route = ?1")
    int deleteByRoute(Route route);
}
