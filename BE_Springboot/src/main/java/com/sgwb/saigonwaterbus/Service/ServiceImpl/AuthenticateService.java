package com.sgwb.saigonwaterbus.Service.ServiceImpl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sgwb.saigonwaterbus.Dao.AccountDao;
import com.sgwb.saigonwaterbus.Mapper.AccountMapper;
import com.sgwb.saigonwaterbus.Model.Account;
import com.sgwb.saigonwaterbus.Model.DTO.AccountDTO;
import com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO.*;
import com.sgwb.saigonwaterbus.Model.EmailCode.EmailCodeStore;
import com.sgwb.saigonwaterbus.Model.Enum.Role;
import com.sgwb.saigonwaterbus.Model.Enum.Status;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @NonFinal
    protected static final String signKey = "c9tN1u5MJkUuhqg8ml+CLqYtWsZfGxwjVZX+DVIAIipJvYuLomZGarmDsgt0ykD7";

    public RegisterResponse register(Account request, String codeGmailVerifyRegister) {
        if(accountDao.existsAccountByUsername(request.getUsername())){
            throw new DataIntegrityViolationException("Tên tài khoản đã tồn tại");
        }
        if(accountDao.existsAccountByEmail(request.getEmail())){
            throw new DataIntegrityViolationException("Gmail này đã được đăng ký");
        }
        if(accountDao.existsAccountByPhoneNumber(request.getPhoneNumber())){
            throw new DataIntegrityViolationException("Số điện thoại đã tồn tại");
        }
        if (EmailCodeStore.getCode(request.getEmail()) == null) {
            throw new DataIntegrityViolationException("Mã xác nhận không tồn tại, vui lòng kiểm tra lại");
        }
        if (!codeGmailVerifyRegister.equalsIgnoreCase(EmailCodeStore.getCode(request.getEmail()))) {
            throw new DataIntegrityViolationException("Mã xác nhận không đúng, vui lòng kiểm tra lại");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Account account = Account.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .password(encodedPassword)
                .status(Status.ACTIVE)
                .createAt(LocalDate.now())
                .role(Role.USER)
                .build();
        EmailCodeStore.removeCode(request.getEmail());
        return AccountMapper.INSTANCE.toRegisterResponse(accountDao.save(account));
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        var user = accountDao.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated)
            throw new RuntimeException();
        var token = generateToken(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        // Lưu Authentication vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .authenticate(true)
                .token(token)
                .build();
    }

    public AuthenticationResponse loginAmin(AuthenticationRequest request) throws InvalidBearerTokenException {
        var user = accountDao.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated || user.getRole() == Role.USER) {
            throw new InvalidBearerTokenException("Invalid credentials or role");
        }
        var token = generateToken(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        // Lưu Authentication vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .authenticate(true)
                .token(token)
                .build();
    }
    public Map<String, String> fetchUserInfoFromGoogle(String code) {
        // Gọi API của Google để trao đổi mã code lấy access token
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", clientRegistrationRepository.findByRegistrationId("google").getClientId());
        requestBody.add("client_secret", clientRegistrationRepository.findByRegistrationId("google").getClientSecret());
        requestBody.add("redirect_uri", clientRegistrationRepository.findByRegistrationId("google").getRedirectUri());
        requestBody.add("code", code);
        requestBody.add("grant_type", "authorization_code");
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(requestBody, tokenHeaders);
        ResponseEntity<Map> tokenResponse = restTemplate.postForEntity("https://www.googleapis.com/oauth2/v4/token", tokenRequest, Map.class);

        // Lấy access token từ response
        String accessToken = (String) tokenResponse.getBody().get("access_token");

        // Gọi API của Google để lấy thông tin người dùng
        HttpHeaders userInfoHeaders = new HttpHeaders();
        userInfoHeaders.setBearerAuth(accessToken);
        HttpEntity<Void> userInfoRequest = new HttpEntity<>(userInfoHeaders);
        ResponseEntity<Map> userInfoResponse = restTemplate.exchange("https://www.googleapis.com/oauth2/v1/userinfo", HttpMethod.GET, userInfoRequest, Map.class);

        // Lấy thông tin người dùng từ response
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("email", (String) userInfoResponse.getBody().get("email"));
        userInfo.put("name", (String) userInfoResponse.getBody().get("name"));
        return userInfo;
    }




    public String generateToken(Account account) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getUsername())
                .issuer("saigonwaterbus.com.vn")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()
                ))
                .claim("scope", account.getRole())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(signKey));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspectResponse(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();
        JWSVerifier verifier = new MACVerifier(signKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date experiedTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && experiedTime.after(new Date()))
                .build();
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest) throws ParseException, JOSEException {
        var token = changePasswordRequest.getToken();
        JWSVerifier verifier = new MACVerifier(signKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        if (!signedJWT.verify(verifier)) {
            throw new RuntimeException("Invalid token");
        }

        String username = (String) signedJWT.getJWTClaimsSet().getClaim("sub");
        var user = accountDao.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        boolean authenticated = passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword());
        if (!authenticated) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        accountDao.save(user);
    }

    public Account showProfile(IntrospectRequest tokenRequest) throws JOSEException, ParseException {
        var token = tokenRequest.getToken();
        JWSVerifier verifier = new MACVerifier(signKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        if (!signedJWT.verify(verifier)) {
            throw new RuntimeException("Invalid token");
        }

        String username = (String) signedJWT.getJWTClaimsSet().getClaim("sub");
        Account user = accountDao.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return user;
    }
    public Long claimUserID(IntrospectRequest tokenRequest) throws JOSEException, ParseException {
        String token = tokenRequest.getToken();

        // Verify the token
        JWSVerifier verifier = new MACVerifier(signKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        if (!signedJWT.verify(verifier)) {
            throw new RuntimeException("Invalid token");
        }

        // Extract username from token claims
        String username = (String) signedJWT.getJWTClaimsSet().getClaim("sub");

        // Find user by username
        Account user = accountDao.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return user.getId();
    }

    public Account updateProfile(IntrospectRequest tokenRequest, AccountDTO accountDTO) throws ParseException, JOSEException {
        var token = tokenRequest.getToken();
        JWSVerifier verifier = new MACVerifier(signKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        if (!signedJWT.verify(verifier)) {
            throw new RuntimeException("Invalid token");
        }

        String username = (String) signedJWT.getJWTClaimsSet().getClaim("sub");
        Account user = accountDao.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setFirstname(accountDTO.getFirstname());
        user.setLastname(accountDTO.getLastname());
        if (!accountDao.existsAccountByEmail(accountDTO.getEmail())) {
            user.setEmail(accountDTO.getEmail());
        }
        if (!accountDao.existsAccountByUsername(accountDTO.getUsername())) {
            user.setUsername(accountDTO.getUsername());
        }
        if (!accountDao.existsAccountByPhoneNumber(accountDTO.getPhoneNumber())){
            user.setPhoneNumber(accountDTO.getPhoneNumber());
        }
        user.setUpdateAt(LocalDate.now());
        accountDao.save(user);
        return user;
    }

}