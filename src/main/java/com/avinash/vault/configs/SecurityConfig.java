package com.avinash.vault.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           http.authorizeHttpRequests(auth ->
                   auth.requestMatchers(
                       "/h2-console/**",
                       "/swagger-ui/**",
                       "/swagger-ui.html",
                       "/v3/api-docs/**"
                   ).permitAll()
                   .anyRequest().authenticated())
               .csrf(csrf -> csrf.ignoringRequestMatchers(
                       "/h2-console/**",
                       "/swagger-ui/**",
                       "/swagger-ui.html",
                       "/v3/api-docs/**"
                   ))
               .headers(headers -> headers.frameOptions(frame -> frame.disable()))
               .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
           return http.build();
       }

}
