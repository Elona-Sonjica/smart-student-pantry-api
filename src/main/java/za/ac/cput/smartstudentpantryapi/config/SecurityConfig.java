package za.ac.cput.smartstudentpantryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Enables CORS mapping
                .csrf(csrf -> csrf.disable())   // Disable CSRF for stateless REST APIs if needed
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // Adjust your public endpoints as needed
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}