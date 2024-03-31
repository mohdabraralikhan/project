package org.nicecharity.crowdfunding.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.nicecharity.crowdfunding.user.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    MyUserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                        .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/login","/register").permitAll()                 
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                        .formLogin(login -> login.disable());
                        
        // http
        //                 .csrf(csrf -> 
        //                 csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        //                  http.headers(headers ->
        //         headers.xssProtection(
        //                 xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
        //         ).contentSecurityPolicy(
        //                 cps -> cps.policyDirectives("script-src 'self'")
        //         ));
        return http.build();
    }

   @Bean
public PasswordEncoder passwordEncoder() {
    String encodingId = "argon2"; // Prioritize Argon2
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put(encodingId, new Argon2PasswordEncoder(16, 32, 1, 8, 2));
     encoders.put("bcrypt", new BCryptPasswordEncoder());
    return new DelegatingPasswordEncoder(encodingId, encoders);
}

@Bean
CorsConfigurationSource corsConfigurationSource() {
	CorsConfiguration configuration = new CorsConfiguration();
	configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","UPDATE","DELETE"));
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", configuration);
	return source;
}

@Bean
public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);

    ProviderManager providerManager = new ProviderManager(authenticationProvider);
    providerManager.setEraseCredentialsAfterAuthentication(false);

    return providerManager;
}
}