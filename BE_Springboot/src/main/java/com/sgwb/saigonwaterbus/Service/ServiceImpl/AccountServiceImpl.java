package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.sgwb.saigonwaterbus.Dao.AccountDao;
import com.sgwb.saigonwaterbus.Mapper.AccountMapper;
import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.DTO.AccountDTO;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.IntrospectRequest;
import com.sgwb.saigonwaterbus.Model.EmailCode.EmailCodeStore;
import com.sgwb.saigonwaterbus.Model.Enum.Role;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void forgotPass(String codeGmail ,String emailForgotPass, String newPass)  {
        Account account = accountDao.findByEmail(emailForgotPass);
        if (account == null) {
            throw new UsernameNotFoundException("Không tìm thấy thông tin tài khoản!");
        }
        if ( !codeGmail.equalsIgnoreCase(EmailCodeStore.getCode(emailForgotPass))) {
            throw new UsernameNotFoundException("Mã xác thực sai,vui lòng kiểm tra lại!");
        }

        if (EmailCodeStore.getCode(emailForgotPass)==null) {
            throw new UsernameNotFoundException("Mã xác thực không tồn tại, vui lòng lấy mã !");
        }
        EmailCodeStore.removeCode(emailForgotPass);
        account.setPassword(passwordEncoder.encode(newPass));
        accountDao.save(account);
    }

    @Override
    public Account creatStaff(AccountDTO accountDTO) {
        if (accountDao.existsAccountByUsername(accountDTO.getUsername())) {
            throw new DataIntegrityViolationException("Tên đăng nhập đã tồn tại.");
        }else if (accountDao.existsAccountByEmail(accountDTO.getEmail())) {
            throw new DataIntegrityViolationException("Email đã tồn tại.");
        } else if (accountDao.existsAccountByPhoneNumber(accountDTO.getPhoneNumber())) {
            throw new DataIntegrityViolationException("số điên thoai đã tồn tại.");}

        Account saveStaff = Account.builder()
                .firstname(accountDTO.getFirstname())
                .lastname(accountDTO.getLastname())
                .email(accountDTO.getEmail())
                .phoneNumber(accountDTO.getPhoneNumber())
                .username(accountDTO.getEmail())
                .password(passwordEncoder.encode("12345"))
                .role(Role.STAFF)
                .status(Status.ACTIVE)
                .createAt(LocalDate.now())
                .build();
        return accountDao.save(saveStaff);
    }
    @Override
    public Account updateStaffByadmin(Long id,Account account) {
        Account account1=accountDao.findAccountById(id);
       if (accountDao.existsAccountByEmail(account.getEmail())&&!account1.getEmail().equalsIgnoreCase(account.getEmail())) {
                throw new DataIntegrityViolationException("Email đã tồn tại.");
         }
        if (accountDao.existsAccountByPhoneNumber(account.getPhoneNumber())&&!account1.getPhoneNumber().equalsIgnoreCase(account.getPhoneNumber())) {
            throw new DataIntegrityViolationException("Số điên thoai đã tồn tại.");}
        else if (account1==null) {
            throw new DataIntegrityViolationException("Không tồn tại nhân viên này!");}
        Account saveStaff = accountDao.findAccountById(id);
        saveStaff.setFirstname(account.getFirstname());
        saveStaff.setLastname(account.getLastname());
        saveStaff.setEmail(account.getEmail());
        saveStaff.setPhoneNumber(account.getPhoneNumber());
        saveStaff.setUsername(account.getEmail());
        saveStaff.setRole(account.getRole());
        saveStaff.setStatus(account.getStatus());
        saveStaff.setUpdateAt(LocalDate.now());
        return accountDao.save(saveStaff);
    }

    @Override
    public boolean deletStaff(Long id) {
        int rowResult = accountDao.deleteStaff(id);
        return rowResult > 0;
    }

    @Override
    public AccountDTO getStaffByID(Long id) {
        return AccountMapper.INSTANCE.toAccountDTO(accountDao.findAccountById(id));
    }

    @Override
    public Page<AccountDTO> findAll(Pageable pageable) {
        Page<Account> accountPage = accountDao.findAll(pageable);
        return accountPage.map(AccountMapper.INSTANCE::toAccountDTO);
    }
    @Override
    public Page<AccountDTO> findAllByRole(Role role, Pageable pageable) {
        Page<Account> accountPage = accountDao.findAllByRole(role, pageable);
        return accountPage.map(AccountMapper.INSTANCE::toAccountDTO);
    }



}
