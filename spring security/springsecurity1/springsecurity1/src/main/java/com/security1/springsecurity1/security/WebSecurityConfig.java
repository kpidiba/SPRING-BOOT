package com.security1.springsecurity1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security1.springsecurity1.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public UserDetailsService userDetailsService(){
        return  new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authentificationProvider(){
        DaoAuthenticationProvider authentificationProvider = new DaoAuthenticationProvider();
        authentificationProvider.setUserDetailsService(userDetailsService());
        authentificationProvider.setPasswordEncoder(passwordEncoder());
        return authentificationProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll()
        .defaultSuccessUrl("/", true)
        .and()
        .logout().permitAll()
        .logoutSuccessUrl("/")
        ;
        return http.build();
    }

    // @Bean
    // public WebSecurityCustomizer    webSecurityCustomizer() throws Exception{
    //     return (web) -> web.ignoring().antMatchers("/images/**","/js/**","/webjars/**");
    // }
}
