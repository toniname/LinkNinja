package com.applink.app.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ComponentScan(value = "com.applink.app")
public class ApplicationConfiguration {
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers("/swagger-ui-custom.html",
                                        "/swagger-ui/**",
                                        "/api-docs/**",
                                        "/urls/*").permitAll()
                                .requestMatchers( HttpMethod.POST,"/api/v1/login").permitAll()
                                .requestMatchers( HttpMethod.POST,"/api/v1/registration").permitAll()
                                .requestMatchers( HttpMethod.POST, "/api/v1/create").authenticated()
                                .requestMatchers( HttpMethod.GET, "/api/v1/urls").authenticated()
                                .requestMatchers( HttpMethod.POST, "/api/v1/delete").authenticated()
                                .requestMatchers( HttpMethod.GET, "/404").permitAll()
                                .requestMatchers( HttpMethod.GET, "/urls/*").permitAll()
                                .anyRequest().authenticated())
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("LinkNinjas auth service"))
                .addSecurityItem(new SecurityRequirement().addList("LinkNinjasSecurityScheme"))
                .components(new Components().addSecuritySchemes("LinkNinjasSecurityScheme", new SecurityScheme()
                        .name("LinkNinjasSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }
}
