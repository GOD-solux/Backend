//package com.godseven.muntour.users.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors(Customizer.withDefaults()) // CORS 설정
//                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless 세션 설정
//                .authorizeHttpRequests(auth -> auth.requestMatchers(
//                                "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources",
//                                "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui/**",
//                                 "/webjars/**", "/swagger-ui.html")
//                                 .permitAll()
//                                 .anyRequest()
//                                 .authenticated());
//
//        return http.build();
//                                 }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
