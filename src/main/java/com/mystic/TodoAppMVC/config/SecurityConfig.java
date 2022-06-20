package com.mystic.TodoAppMVC.config;

import com.mystic.TodoAppMVC.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeRequests((authz) -> authz
                        .antMatchers("/css/*","/js/*").permitAll()
                        .antMatchers("/tasks/**").hasRole("USER")
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


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth, PasswordEncoder encoder) throws Exception {
            auth
                .userDetailsService(userService)
                .passwordEncoder(encoder);

            return auth.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public UserDetailsManager userDetailsManager() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.withUsername("MyStic")
                                .password(encoder.encode("asdsasfd"))
                                .roles("USER")
                                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
