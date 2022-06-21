package com.mystic.TodoAppMVC.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeRequests((authz) -> authz
                        .antMatchers("/css/*","/js/*").permitAll()
                        .antMatchers("/registration").permitAll()
                        .antMatchers("/tasks/**").hasAuthority("USER")
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin()
                    .defaultSuccessUrl("/tasks")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
      return (web) -> web
                        .ignoring()
                        .antMatchers("/ignore1", "/ignore2");
    }

}
