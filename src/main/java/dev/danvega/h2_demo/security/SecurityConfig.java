package dev.danvega.h2_demo.security;


import dev.danvega.h2_demo.model.Role;
import dev.danvega.h2_demo.model.User;
import dev.danvega.h2_demo.repository.RoleRepository;
import dev.danvega.h2_demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private CustomAuthenticationSuccessHandler successHandler;
    private CustomAuthenticationFailureHandler failureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http

                .csrf(csrf -> csrf
                        .csrfTokenRequestHandler(requestHandler).disable()
                )
                //.cors(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/products/**").permitAll()
                        .requestMatchers("/api/sales/**").permitAll()
                        .requestMatchers("/api/current-user").authenticated()
                        .requestMatchers("/api/users/create-user").permitAll()
                )
                .formLogin(form -> form
                       .loginProcessingUrl("/api/products")
                         .successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .defaultSuccessUrl("http://localhost:8081", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8081"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

   /* @PostConstruct
    public void init() {
        Role adminRole = new Role(1L, "ADMIN");
        Role userRole = new Role(2L, "KULLANICI");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User admin = new User(1L, "admin", new BCryptPasswordEncoder().encode("123"), adminRole);
        User user = new User(2L, "user", new BCryptPasswordEncoder().encode("1234"), userRole);

        userRepository.save(admin);
        userRepository.save(user);
    }*/
}

