package com.sgwb.saigonwaterbus.Dao;

import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.Enum.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AccountDao extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Account findAccountById(Long id);
    Page<Account> findAllByRole(Role role, Pageable pageable);
    Account findByEmail(String email);
    Boolean existsAccountByUsername(String username);
    Boolean existsAccountByEmail(String email);
    Boolean existsAccountByPhoneNumber(String phoneNumber);
    @Modifying
    @Transactional
    @Query(value = "update account set delete_at = CURRENT_TIMESTAMP, status = 'INACTIVE' where id = :id", nativeQuery = true)
    int deleteStaff(@Param("id") Long id);
}
