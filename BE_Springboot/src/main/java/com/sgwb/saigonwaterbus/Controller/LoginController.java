package com.sgwb.saigonwaterbus.Controller;

import com.nimbusds.jose.JOSEException;
import com.sgwb.saigonwaterbus.Dao.AccountDao;
import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.DTO.*;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.*;
import com.sgwb.saigonwaterbus.Model.Enum.Role;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import com.sgwb.saigonwaterbus.Service.AccountService;
import com.sgwb.saigonwaterbus.Service.ServiceImpl.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/saigonwaterbus")
public class LoginController {
    @Autowired
    AuthenticateService authenticateService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountDao accountDao;
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody AuthenticationRequest request){
        var result= authenticateService.login(request);
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>();
        response.setResult(result);
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Login success");
        return ResponseEntity.ok(response);
    }

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @CrossOrigin(origins = "*")
    @GetMapping("/login/google")
    public ResponseEntity<String> loginWithGoogle() {
        ClientRegistration googleClientRegistration = clientRegistrationRepository.findByRegistrationId("google");
        OAuth2AuthorizationRequest authorizationRequest = OAuth2AuthorizationRequest
                .authorizationCode()
                .clientId(googleClientRegistration.getClientId())
                .redirectUri(googleClientRegistration.getRedirectUri())
                .authorizationUri(googleClientRegistration.getProviderDetails().getAuthorizationUri())
                .additionalParameters(params -> {
                    params.put(OAuth2ParameterNames.RESPONSE_TYPE, "code");
                    params.put(OAuth2ParameterNames.SCOPE, String.join(" ", googleClientRegistration.getScopes()));
                })
                .build();
        return ResponseEntity.ok(authorizationRequest.getAuthorizationRequestUri());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/accesstoken")
    public ResponseEntity<Map<String, String>> getAccessToken(@RequestParam("code") String code) {
        Map<String, String> userInfo = authenticateService.fetchUserInfoFromGoogle(code);
        String email = userInfo.get("email");
        String name = userInfo.get("name");
        String jwt = "";
        String username = "";
        if (!accountDao.existsAccountByEmail(email)) {
            Account newAccount = Account.builder()
                    .email(email)
                    .role(Role.USER)
                    .username(email)
                    .status(Status.ACTIVE)
                    .phoneNumber("Not update" + LocalDateTime.now().toString())
                    .createAt(LocalDate.now())
                    .firstname(name)
                    .build();
            jwt = authenticateService.generateToken(accountDao.save(newAccount));
            username = newAccount.getUsername();
        } else {
            Account existingAccount = accountDao.findByEmail(email);
            jwt = authenticateService.generateToken(existingAccount);
            username = existingAccount.getUsername();
        }
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", jwt);
        responseBody.put("username", username);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(
            @RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result= authenticateService.introspectResponse(request);
        ApiResponse<IntrospectResponse> authen = new ApiResponse<>();
        authen.setResult(result);
        return authen;
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> changePass(@RequestBody ChangePasswordRequest changePasswordRequest) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            authenticateService.changePassword(changePasswordRequest);
            response.setCode(200);
            response.setMessage("Password changed successfully");
            response.setResult("Success");
            return ResponseEntity.ok(response);
        } catch (RuntimeException | ParseException | JOSEException e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
            response.setResult("Failed");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
