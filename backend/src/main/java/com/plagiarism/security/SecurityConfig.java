package com.plagiarism.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Value;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${app.cors.allowed-origins:http://localhost:3000}")
    private String allowedOrigins;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Password encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Disable CSRF because we are using REST APIs + JWT
                .csrf(csrf -> csrf.disable())

                // JWT authentication is stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // Authorization rules
                .authorizeHttpRequests(auth -> auth

                        // =====================================
                        // PUBLIC APIs
                        // =====================================

                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login",
                                "/api/test/**"
                        ).permitAll()


                        // =====================================
                        // STUDENT APIs
                        // =====================================

                        .requestMatchers(
                                "/api/student/**"
                        ).hasRole("STUDENT")


                        // =====================================
                        // TEACHER APIs
                        // =====================================

                        .requestMatchers(
                                "/api/teacher/**"
                        ).hasRole("TEACHER")


                        // =====================================
                        // ASSIGNMENT APIs
                        // =====================================

                        // Students and teachers can view
                        // available assignments
                        .requestMatchers(
                                "/api/assignments/available"
                        ).hasAnyRole("STUDENT", "TEACHER")

                        // Teacher can view his/her assignments
                        .requestMatchers(
                                "/api/assignments/my"
                        ).hasRole("TEACHER")

                        // Students and teachers can list assignments
                        .requestMatchers(org.springframework.http.HttpMethod.GET,
                                "/api/assignments")
                        .hasAnyRole("STUDENT", "TEACHER")

                        // Only teachers can create assignments
                        .requestMatchers(org.springframework.http.HttpMethod.POST,
                                "/api/assignments")
                        .hasRole("TEACHER")


                        // =====================================
                        // SUBMISSION APIs
                        // =====================================

                        .requestMatchers(org.springframework.http.HttpMethod.GET,
                                "/api/submissions")
                        .hasRole("TEACHER")

                        // Students can submit code
                        .requestMatchers(
                                "/api/submissions"
                        ).hasRole("STUDENT")

                        // Teachers can view submissions
                        // belonging to an assignment
                        .requestMatchers(
                                "/api/submissions/assignment/**"
                        ).hasRole("TEACHER")

                        // Students can view their own submissions
                        .requestMatchers(
                                "/api/submissions/student/**"
                        ).hasRole("STUDENT")


                        // =====================================
                        // SIMILARITY APIs
                        // =====================================

                        // Only teachers can perform plagiarism checks
                        .requestMatchers(
                                "/api/similarity/**"
                        ).hasRole("TEACHER")


                        // =====================================
                        // REPORT APIs
                        // =====================================

                        // Only teachers can generate/view reports
                        .requestMatchers(
                                "/api/reports/**"
                        ).hasRole("TEACHER")


                        // =====================================
                        // DASHBOARD APIs
                        // =====================================

                        // Only teachers can access dashboard statistics
                        .requestMatchers(
                                "/api/dashboard/**"
                        ).hasRole("TEACHER")


                        // =====================================
                        // AUDIT LOG APIs
                        // =====================================

                        // Only teachers can view audit logs
                        .requestMatchers(
                                "/api/audit/**"
                        ).hasRole("TEACHER")


                        // =====================================
                        // EVERYTHING ELSE
                        // =====================================

                        // Any other API requires login
                        .anyRequest().authenticated()
                )


                // ==========================================
                // ERROR HANDLING
                // ==========================================

                .exceptionHandling(exception -> exception

                        // User is NOT logged in
                        .authenticationEntryPoint(
                                new HttpStatusEntryPoint(
                                        HttpStatus.UNAUTHORIZED
                                )
                        )

                        // User is logged in but does NOT
                        // have required role
                        .accessDeniedHandler(
                                (request,
                                 response,
                                 accessDeniedException) -> {

                                    response.setStatus(
                                            HttpStatus.FORBIDDEN.value()
                                    );
                                }
                        )
                )


                // ==========================================
                // JWT FILTER
                // ==========================================

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.stream(allowedOrigins.split(","))
                .map(String::trim).filter(origin -> !origin.isEmpty()).toList());
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
        configuration.setExposedHeaders(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
