package pl.project.cryptotradinguser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Włącz obsługę CORS
        .csrf(csrf -> csrf.disable()) // Wyłączenie CSRF dla stateless JWT
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/api/public/**", "/api/users/**").permitAll() // Endpointy dostępne bez uwierzytelnienia
            .anyRequest().authenticated() // Wszystkie inne endpointy wymagają uwierzytelnienia
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Stateless session

    return http.build();
  }

  @Bean
  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Adres frontendu
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Dozwolone metody
    configuration.setAllowedHeaders(List.of("*")); // Dozwolone nagłówki
    configuration.setAllowCredentials(true); // Włącz obsługę ciasteczek i uwierzytelniania

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // Zastosowanie konfiguracji CORS dla wszystkich endpointów
    return source;
  }
}
