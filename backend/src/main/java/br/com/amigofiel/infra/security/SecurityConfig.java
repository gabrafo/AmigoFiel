package br.com.amigofiel.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated() // Exige autenticação para qualquer requisição
                )
                .httpBasic(Customizer.withDefaults()) // Habilita autenticação básica
                .csrf(csrf -> csrf.disable()); // Desativa CSRF para simplificação no desenvolvimento

        return http.build();
    }
}
