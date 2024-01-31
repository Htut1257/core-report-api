/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.config;

import cv.report.api.helper.AuthenticationFaillHandler;
import cv.report.api.helper.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *
 * @author myoht
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationSuccessHandler authenSuccessHandler;

    @Autowired
    private AuthenticationFaillHandler authenFaillHandler;

    private static final String[] excludedAuthPages = {
        "/user/excludedAuthPages",
        "/user/save-user",
        "/auth/excludedAuthPages",
        "/webjars/swagger-ui/**",
        //"/swagger-ui/**",
        "/v3/api-docs/**", //"/swagger-resources/**"
    };

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(csrf -> csrf.disable())
                .authorizeExchange()
                .pathMatchers(excludedAuthPages).permitAll()
                .and()
                .authorizeExchange()
                .pathMatchers("/user/admin").hasRole("admin")
                .pathMatchers("/**").authenticated()
                //        .and()
                //        .httpBasic()
                .and()
                .formLogin()
                .authenticationSuccessHandler(authenSuccessHandler)
                .authenticationFailureHandler(authenFaillHandler)
                .and()
                .logout().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
