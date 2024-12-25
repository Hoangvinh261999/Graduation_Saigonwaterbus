package com.sgwb.saigonwaterbus.Controller;

import com.nimbusds.jose.JOSEException;
import com.sgwb.saigonwaterbus.Dao.AccountDao;
import com.sgwb.saigonwaterbus.Mapper.AccountMapper;
import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.DTO.*;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.IntrospectRequest;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.RegisterResponse;
import com.sgwb.saigonwaterbus.Model.Enum.Role;
import com.sgwb.saigonwaterbus.Service.AccountService;
import com.sgwb.saigonwaterbus.Service.ServiceImpl.AccountServiceImpl;
import com.sgwb.saigonwaterbus.Service.ServiceImpl.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/saigonwaterbus")
public class AccountController {
    @Autowired
    AuthenticateService authenticateService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountServiceImpl accountServiceImpl;
    @Autowired
    AccountDao accountDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody Account account, @RequestParam String codeGmailVerifyRegister) {
        var result = authenticateService.register(account,codeGmailVerifyRegister);
        ApiResponse<RegisterResponse> response = new ApiResponse<>();
        response.setResult(result);
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Register success");
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<AccountDTO>> showProfile(@RequestHeader HttpHeaders headers) throws ParseException, JOSEException {
        ApiResponse<AccountDTO> response = new ApiResponse<>();
        try {
            String tokenString = headers.getFirst("Authorization");
            String token = tokenString.substring(7); // Bỏ "Bearer " ra khỏi token
            IntrospectRequest tokenRequest = new IntrospectRequest(token);
            AccountDTO userProfile = AccountMapper.INSTANCE.toAccountDTO(authenticateService.showProfile(tokenRequest));
            response.setCode(200);
            response.setMessage("Show profile successfully");
            response.setResult(userProfile);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/profile/update")
    public ResponseEntity<ApiResponse<AccountDTO>> updateProfile(@RequestHeader HttpHeaders headers, @RequestBody AccountDTO accountDTO) {
        ApiResponse<AccountDTO> response = new ApiResponse<>();
        try {
            String tokenString = headers.getFirst("Authorization");
            String token = tokenString.substring(7); // Bỏ "Bearer " ra khỏi token
            IntrospectRequest tokenRequest = new IntrospectRequest(token);
            AccountDTO userProfile = AccountMapper.INSTANCE.toAccountDTO(authenticateService.updateProfile(tokenRequest, accountDTO));
            response.setCode(200);
            response.setMessage("Update profile successfully");
            response.setResult(userProfile);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin("*")
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> changePass(@RequestParam String codeGmail,String gmailForget,String newPass) {
        ApiResponse<String> response = new ApiResponse<>();
        accountServiceImpl.forgotPass(codeGmail,gmailForget,newPass);
        response.setCode(200);
        response.setMessage("Đã gửi mã xác nhận về gmail!");
        response.setResult("Success");
        return ResponseEntity.ok(response);
    }



    @GetMapping("/admin/staffs")
    public ResponseEntity<ApiResponse<Page<AccountDTO>>> getsStaffs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        ApiResponse<Page<AccountDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<AccountDTO> accountDTOPage = accountService.findAll(pageable);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get all staff success");
            response.setResult(accountDTOPage);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("/admin/staff1")
    public ResponseEntity<ApiResponse<Page<AccountDTO>>> getsStaffs1(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        ApiResponse<Page<AccountDTO>> response = new ApiResponse<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<AccountDTO> accountDTOPage = accountService.findAllByRole(Role.STAFF,pageable);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get all staff success");
            response.setResult(accountDTOPage);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/admin/staff/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> getStaffById(@PathVariable("id") Long id) {
        ApiResponse<AccountDTO> response = new ApiResponse<>();
        try {
            AccountDTO accountDTO = accountService.getStaffByID(id);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get staff success");
            response.setResult(accountDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        }
    }
    @CrossOrigin("*")
    @PostMapping("/admin/staff/save")
    public ResponseEntity<ApiResponse<Account>> saveStaff(@RequestBody AccountDTO accountDTO) {
        ApiResponse<Account> response = new ApiResponse<>();
                Account saveStaff = accountService.creatStaff(accountDTO);
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Save staff success");
                response.setResult(saveStaff);
                return ResponseEntity.ok(response);
        }


    @CrossOrigin("*")
    @PostMapping("/admin/staff/update/{id}")
    public ResponseEntity<ApiResponse<Account>> updateStaff(@PathVariable Long id, @RequestBody Account account) {
        ApiResponse<Account> response = new ApiResponse<>();
            accountService.updateStaffByadmin(id,account);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Update staff success");
            response.setResult(null);
            return ResponseEntity.ok(response);

    }
    @DeleteMapping("/admin/staff/delete/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> softDeleteStaff(@PathVariable("id") Long id) {
        ApiResponse<AccountDTO> response = new ApiResponse<>();
        try {
            if (accountService.deletStaff(id)) {
                response.setCode(HttpStatus.OK.value());
                response.setMessage("Soft delete staff success");
                response.setResult(null);
            } else {
                response.setCode(HttpStatus.NO_CONTENT.value());
                response.setMessage("Soft delete staff failed");
                response.setResult(null);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult(null);
            return ResponseEntity.badRequest().body(null);
        }
    }
}