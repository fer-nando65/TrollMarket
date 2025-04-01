package com.trollMarket.configuration;

import com.trollMarket.component.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class RestSecurityConfiguration {

    @Value("${frontend.url}")
    private String frontendUrl;

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public RestSecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

        @Bean
        @Order(1)
        public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception{
            http.securityMatcher("/api/**")
                    .csrf(cf -> cf.disable()).authorizeHttpRequests(request -> request
                            .requestMatchers("/api/authenticate", "/api/account/authenticate", "/api/account/admin").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/profile").hasAuthority("Buyer")
                            .requestMatchers(HttpMethod.POST, "/api/cart").hasAuthority("Buyer")
                            .requestMatchers(HttpMethod.GET, "/api/merchandise", "/api/merchandise/info").hasAuthority("Seller")
                            .requestMatchers(HttpMethod.GET, "/api/shipper", "/api/shipper/detail").hasAuthority("Administrator")
                            .requestMatchers(HttpMethod.POST, "/api/shipper").hasAuthority("Administrator")
                            .requestMatchers(HttpMethod.GET, "/api/shop", "/api/shop/info").hasAuthority("Buyer")
                            .anyRequest().authenticated()
                    ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(
                            jwtRequestFilter, UsernamePasswordAuthenticationFilter.class
                    ).httpBasic(basic -> basic
                            .authenticationEntryPoint(authenticationEntryPoint())
                    ).exceptionHandling(execption -> execption
                            .accessDeniedHandler(accessDeniedHandler())
                    ).cors(cors -> cors
                            .configurationSource(corsConfigurationSource())
                    );
            return http.build();
        }

        private AuthenticationEntryPoint authenticationEntryPoint(){
            return (request, response, authException) -> {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized Request Header");
            };
        }

        private AccessDeniedHandler accessDeniedHandler(){
            return (request, response, accessDeniedException) -> {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("Access Denied");
            };
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource(){
            var configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of(frontendUrl));
            configuration.setAllowedHeaders(List.of("*"));
            configuration.setAllowedMethods(List.of("GET","POST", "PUT", "PATCH", "DELETE"));

            var source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);

            return source;
        }
}
