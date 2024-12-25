package com.sgwb.saigonwaterbus.Controller;

import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.AuthenticationRequest;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.AuthenticationResponse;
import com.sgwb.saigonwaterbus.Service.ServiceImpl.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginAdminController {
    @Autowired
    private AuthenticateService authenticateService;
    @CrossOrigin(origins = "*")
    @PostMapping("/api/saigonwaterbus/admin/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody AuthenticationRequest request) throws BadCredentialsException {
        var result= authenticateService.loginAmin(request);
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>();
        response.setResult(result);
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Login success");

        return ResponseEntity.ok(response);
    }
}
