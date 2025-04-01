package com.trollMarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Timer;
import java.util.TimerTask;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfiguration {
    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(request -> request
                        .requestMatchers("/account/**", "/resources/**", "dashboard/**", "/swagger").permitAll()
                        .requestMatchers("/profile/**").hasAnyAuthority("Buyer", "Seller")
                        .requestMatchers("/shop/**").hasAuthority("Buyer")
                        .requestMatchers("/merchandise/**").hasAuthority("Seller")
                        .requestMatchers("/shipper/**").hasAuthority("Administrator")
                        .requestMatchers("/cart/**").hasAuthority("Buyer")
                        .requestMatchers("/admin/**").hasAuthority("Administrator")
                        .requestMatchers("/history/**").hasAuthority("Administrator")
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/account/login")
                        .loginProcessingUrl("/authenticating")
                        .failureUrl("/account/failLogin"))
                .logout(logout -> logout.logoutUrl("/logout"))
                .exceptionHandling(exception -> exception.accessDeniedPage("/account/accessDenied"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
