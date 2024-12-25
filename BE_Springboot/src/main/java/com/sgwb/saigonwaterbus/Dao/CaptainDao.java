package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CaptainDao extends JpaRepository<Captain, Long> {
    Captain findCaptainById(Long id);
    @Modifying
    @Transactional
    @Query(value = "update Captain set status = 'INACTIVE', delete_at = CURRENT_TIMESTAMP where id = :id", nativeQuery = true)
    int softDeleteCaptain(@Param("id") Long id);

    boolean existsByPhoneNumber(String phoneCaptain);
    boolean existsByShipLicense(String lisence);


}
