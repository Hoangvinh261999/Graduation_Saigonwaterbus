package com.sgwb.saigonwaterbus.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    protected static final String signKey = "c9tN1u5MJkUuhqg8ml+CLqYtWsZfGxwjVZX+DVIAIipJvYuLomZGarmDsgt0ykD7";

    private static final String[] PUBLIC_ENDPOINTS = new String[]{
            "/api/saigonwaterbus/check-ticket",
            "/api/saigonwaterbus/check-ticket/**",
            "/api/saigonwaterbus/checking",
            "/api/saigonwaterbus/checking/*",
            "/api/saigonwaterbus/checking/**",
            "/api/saigonwaterbus/hold-ticket",
            "/api/saigonwaterbus/hold-ticket",
            "/api/saigonwaterbus/save-booking-complete",
            "/api/saigonwaterbus/check-ticket/**",
            "/api/saigonwaterbus/login",
            "/api/saigonwaterbus/ticketAlls",
            "/api/saigonwaterbus/trips/**",
            "/api/saigonwaterbus/trips/*",
            "/api/saigonwaterbus/stations",
            "/api/saigonwaterbus/accesstoken",
            "/api/saigonwaterbus/accesstoken/*",
            "/api/saigonwaterbus/accesstoken",
            "api/saigonwaterbus/login/google",
            "/api/saigonwaterbus/login/oauth2/code",
            "/api/saigonwaterbus/booking-ticket/*",
            "/api/saigonwaterbus/booking-ticket/**",
            "/api/saigonwaterbus/booking-ticket",
            "/api/saigonwaterbus/register",
            "/api/saigonwaterbus/register/**",
            "/api/saigonwaterbus/introspect",
            "/api/saigonwaterbus/send-mail/*",
            "api/saigonwaterbus/send-mail-code/**",
            "/api/saigonwaterbus/payment",
            "/api/saigonwaterus/payment/**",
            "/api/saigonwaterbus/booking-history",
            "/api/saigonwaterbus/send-mail-code/*",
            "/api/saigonwaterbus/send-mail-code",
            "/api/saigonwaterbus/send-mailcode-forgetpass",
            "/api/saigonwaterbus/send-mailcode-forgetpass/*",
            "/api/saigonwaterbus/admin/login",
            "/api/saigonwaterbus/saveLocalStorageData",
            "/api/saigonwaterbus/booking-history/*",
            "/api/saigonwaterbus/forgot-password"
    };
    private static final String[] HASROLESADMIN_ENDPOINTS = {
            "/api/saigonwaterbus/admin/**","/api/saigonwaterbus/admin/*",
            "/api/saigonwaterbus/admin"

    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->request
                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HASROLESADMIN_ENDPOINTS).hasAuthority("SCOPE_ADMIN")
                .anyRequest().permitAll());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.oauth2ResourceServer(oauth -> oauth.jwt(jwtConfigurer -> {
            jwtConfigurer.decoder(jwtDecoder());
        }));
        return httpSecurity.build();
    }


    @Bean
    JwtDecoder jwtDecoder() throws InvalidBearerTokenException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signKey.getBytes(), "HS512");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .build();
        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }
    @Bean
    public ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .redirectUri("https://saigonwaterbus.click/login")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("profile","email")
                .build();
    }
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(googleClientRegistration());
    }
}