package com.ednaldo.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("Ednaldo")
                .password(passwordEncoder().encode("321"))
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpAuthorization) throws Exception {
        httpAuthorization.csrf().disable()
                .authorizeRequests()
                .antMatchers("api/v1/clientes/**")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("api/v1/produtos/**")
                .hasAnyRole("ADMIN")
                .antMatchers("api/v1/pedidos/**")
                .hasAnyRole("USER","ADMIN")
                .and().httpBasic();
        ;
    }
}
