package com.api.desafio.back.config;

// 🛑 REMOVA OS IMPORTS DESNECESSÁRIOS:
// - UsuarioRepository
// - DaoAuthenticationProvider
// - AuthenticationConfiguration
// - UserDetailsService
// - UsernameNotFoundException
// - BCryptPasswordEncoder
// - PasswordEncoder

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider; // 👈 IMPORTE ESTE
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  // 🛑 REMOVA O AUTOWIRED DO UsuarioRepository

  @Autowired
  private JwtAuthFilter jwtAuthFilter;

  // ✅ INJETE O AUTHENTICATION PROVIDER (QUE VEM DA NOVA CLASSE)
  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // ✅ USE O PROVIDER INJETADO
            .authenticationProvider(authenticationProvider)

            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  // 🛑 REMOVA TODOS OS OUTROS @Beans DESTE ARQUIVO
  // - userDetailsService()
  // - passwordEncoder()
  // - authenticationProvider()
  // - authenticationManager()
  // ELES AGORA ESTÃO NA CLASSE ApplicationConfig
}