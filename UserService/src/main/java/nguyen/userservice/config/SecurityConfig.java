package nguyen.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("/api/login/**", "/api/register/**")
                );
        return httpSecurity.build();

    }
}
