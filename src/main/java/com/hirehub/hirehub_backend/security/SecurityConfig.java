package com.hirehub.hirehub_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(
            JwtAuthenticationFilter jwtFilter,
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

    // Public APIs
    .requestMatchers(
            "/api/users/register",
            "/api/users/login",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    ).permitAll()

    // Students, Recruiters and Admins can view jobs
    .requestMatchers(HttpMethod.GET, "/api/jobs/**")
    .hasAnyRole("STUDENT", "RECRUITER", "ADMIN")

    // Only Recruiters and Admins can create/update/delete jobs
    .requestMatchers(HttpMethod.POST, "/api/jobs/**")
    .hasAnyRole("RECRUITER", "ADMIN")

    .requestMatchers(HttpMethod.PUT, "/api/jobs/**")
    .hasAnyRole("RECRUITER", "ADMIN")

    .requestMatchers(HttpMethod.DELETE, "/api/jobs/**")
    .hasRole("ADMIN")

    // Applications
    .requestMatchers("/api/applications/**")
    .hasRole("STUDENT")

    // Recruiters
    .requestMatchers("/api/recruiters/**")
    .hasAnyRole("RECRUITER", "ADMIN")

    // Companies
    .requestMatchers("/api/companies/**")
    .hasRole("ADMIN")

    // Everything else
    .anyRequest().authenticated()
)

                .authenticationProvider(authenticationProvider())

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}