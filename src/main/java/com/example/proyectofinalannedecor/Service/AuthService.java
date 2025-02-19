package com.example.proyectofinalannedecor.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class AuthService {
/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource()) // Habilita CORS
                .and()
                .csrf().disable() // Desactiva CSRF si usas JWT
                .authorizeHttpRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated() // Ajusta según necesidad
                .and()
                .formLogin().disable()  // Desactivar login por formulario
                .httpBasic().disable();
        return http.build();
    }
/*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
      /*  CorsConfiguration config = new CorsConfiguration();
        // Orígenes específicos en lugar de "*"
        config.setAllowedOrigins(List.of("http://localhost:5174", "http://otro-origen.com"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true); // Permitir credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    */
}
