package com.clase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SeguridadConfig   {

//    @Value("${usr}")
//    private String usr;
//
//    @Value("${pass}")
//    private String pass;
//
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails oDetails = User.withDefaultPasswordEncoder()
                .username("UCEM_IRENE")
                .password("1234")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(oDetails);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/personas/**")
                .authenticated()
                .and()
                .httpBasic(Customizer.withDefaults())
                .build();

    }
}
