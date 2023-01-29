package com.github.serhx4.security;

import com.github.serhx4.data.UserRepository;
import com.github.serhx4.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findById(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), new HashSet<SimpleGrantedAuthority>() {{
                        add(new SimpleGrantedAuthority("ROLE_USER"));
            }});
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeRequests()
                    .antMatchers("/burger/**", "/order/**").access("hasRole('USER')")
                    .antMatchers("/", "/**").permitAll()

                .and()
                    .formLogin()
                        .loginPage("/login")
                            .defaultSuccessUrl("/")

                .and()
                    .logout()
                        .logoutSuccessUrl("/login")

                .and()
                    .headers()
                        .frameOptions()
                            .sameOrigin() // for h2 console

                .and()
                    .csrf()
                        .ignoringAntMatchers("/h2-console/**","/api/v1/**")

                .and()
                .build()
        ;
    }
}
