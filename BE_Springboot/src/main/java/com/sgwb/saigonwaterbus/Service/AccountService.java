package com.sgwb.saigonwaterbus.Service;

import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.DTO.AccountDTO;
import com.sgwb.saigonwaterbus.Model.Enum.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    Account creatStaff(AccountDTO accountDTO);
    boolean deletStaff(Long id);
    AccountDTO getStaffByID(Long id);
    Page<AccountDTO> findAll(Pageable pageable);
    Page<AccountDTO> findAllByRole(Role role, Pageable pageable);
    Account updateStaffByadmin(Long id,Account account);
}
